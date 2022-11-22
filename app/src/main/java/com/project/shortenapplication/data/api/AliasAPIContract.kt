package com.project.shortenapplication.data.api

import com.project.shortenapplication.data.api.dto.AliasURLResponse
import com.project.shortenapplication.data.api.dto.UrlRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AliasAPIContract {
    @POST("alias")
    suspend fun reduceURL(@Body url: UrlRequest): AliasURLResponse

    @GET("alias/id")
    suspend fun getURL(@Query("id") alias: String): String


}