package com.project.shortenapplication.data.api

import com.google.gson.GsonBuilder
import com.project.shortenapplication.data.api.dto.AliasURLResponse
import com.project.shortenapplication.data.api.dto.URLLinks
import com.project.shortenapplication.data.api.dto.UrlRequest
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AliasAPIContractTest {

    private lateinit var server: MockWebServer
    private val aliasAPI: AliasAPIContract by lazy {
        Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AliasAPIContract::class.java)
    }

    @Before
    fun beforeEachTest() {
        server = MockWebServer()
        server.start()
    }

    @After
    fun afterEachTest() {
        server.shutdown()
    }

    @Test
    fun `should check api behavior`() = runBlocking {
        val aliasURLResponse = AliasURLResponse("5425", URLLinks("wwww.google.com", "www.shorten.com/5425"))

        val gson = GsonBuilder().create()
        val aliasAPIResponseJson = gson.toJson(aliasURLResponse)
        server.enqueue(MockResponse().setBody(aliasAPIResponseJson))

        val result = aliasAPI.createAliasFromURL(UrlRequest("wwww.google.com"))

        Assert.assertEquals(result, aliasURLResponse)

        val executeURL = server.takeRequest()
        Assert.assertEquals(executeURL.path, "/alias")
    }

}