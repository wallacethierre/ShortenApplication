package com.project.shortenapplication.data.api

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
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

}