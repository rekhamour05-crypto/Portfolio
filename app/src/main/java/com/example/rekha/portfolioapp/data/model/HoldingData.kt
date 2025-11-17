package com.example.rekha.portfolioapp.data.model

import com.google.gson.annotations.SerializedName

data class HoldingData(
    @SerializedName("userHolding") val userHolding: List<HoldingDto>?
)