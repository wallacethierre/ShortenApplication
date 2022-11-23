package com.project.shortenapplication.data.api

import com.project.shortenapplication.data.api.dto.AliasURLResponse
import com.project.shortenapplication.data.api.dto.UrlRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AliasAPIContract {
    @POST("alias")
    suspend fun createAliasFromURL(@Body url: UrlRequest): AliasURLResponse
}