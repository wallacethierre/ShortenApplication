package com.project.shortenapplication.data.api.dto

import com.google.gson.annotations.SerializedName

data class AliasURLResponse(
    val alias: String,
    @SerializedName("_links") val links: URLLinks
)

data class URLLinks(
    @SerializedName("self") val selfURL: String,
    @SerializedName("short") val shortURL: String
)