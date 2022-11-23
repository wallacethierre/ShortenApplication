package com.project.shortenapplication.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.shortenapplication.data.api.AliasAPIContract
import com.project.shortenapplication.data.api.dto.AliasURLResponse
import com.project.shortenapplication.data.api.dto.URLLinks
import com.project.shortenapplication.data.api.dto.UrlRequest
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AliasRemoteDataSourceImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var aliasAPIContract: AliasAPIContract

    init {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Before
    fun setup() {
        clearMocks(aliasAPIContract)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should reduce remote link`() = runTest {
        coEvery {
            aliasAPIContract.createAliasFromURL(UrlRequest("www.google.com"))
        } returns AliasURLResponse("5425", URLLinks("wwww.google.com", "www.shorten.com/5425"))

        val aliasAPIRemote = AliasRemoteDataSourceImpl(aliasAPIContract)
        aliasAPIRemote.createAliasFromURL("www.google.com")

        coVerify(exactly = 1) {
            aliasAPIRemote.createAliasFromURL("www.google.com")
        }
    }
}