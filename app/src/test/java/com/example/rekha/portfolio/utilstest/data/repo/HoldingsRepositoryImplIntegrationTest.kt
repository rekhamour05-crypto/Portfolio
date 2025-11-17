package com.example.rekha.portfolio.data.repo

import com.example.rekha.portfolioapp.data.remote.HoldingsApi
import com.example.rekha.portfolioapp.data.repository.HoldingsRepositoryImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HoldingsRepositoryImplIntegrationTest {

    private lateinit var server: MockWebServer
    private lateinit var api: HoldingsApi
    private lateinit var repo: HoldingsRepositoryImpl

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()

        api = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HoldingsApi::class.java)

        repo = HoldingsRepositoryImpl(api)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `repository returns domain list when api returns valid json`() = runBlocking {
        val json = """
            {
              "data": {
                "userHolding": [
                  {"symbol":"TST","quantity":2,"ltp":10.0,"avgPrice":5.0,"close":12.0}
                ]
              }
            }
        """.trimIndent()

        server.enqueue(MockResponse().setResponseCode(200).setBody(json))

        val result = repo.getHoldings()

        assertThat(result).hasSize(1)
        val first = result[0]
        assertThat(first.symbol).isEqualTo("TST")
        assertThat(first.qty).isEqualTo(2)
        assertThat(first.ltp).isEqualTo(10.0)
    }

    @Test
    fun `repository throws on server error`() = runBlocking {
        server.enqueue(MockResponse().setResponseCode(500).setBody("server error"))

        try {
            repo.getHoldings()
            assert(false) { "Expected exception but none thrown" }
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(Exception::class.java)
        }
    }
}
