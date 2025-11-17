package com.example.rekha.portfolioapp.data.model

import com.google.gson.annotations.SerializedName

data class HoldingResponse(
    @SerializedName("data") val data: HoldingData?
)
