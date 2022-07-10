package com.plcoding.cryptocurrencyappyt.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("explorer")
    val explorer: List<String>,
    @SerializedName("facebook")
    val facebook: List<String>,
    @SerializedName("reddit")
    val reddit: List<String>,
    @SerializedName("source_code")
    val sourceCode: List<String>,
    @SerializedName("website")
    val website: List<String>,
    @SerializedName("youtube")
    val youtube: List<String>
)