package com.project.shortenapplication.domain.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.shortenapplication.domain.repository.AliasRepositoryContract
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShortenURLUseCaseTest {

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var aliasRepository: AliasRepositoryContract

    init {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Before
    fun setup() {
        clearMocks(aliasRepository)
    }

    @Test
    fun `call to shorten url`() = runTest {
        coEvery {
            aliasRepository.createAliasFromURL(any())
        } returns true

        val shortenURLDomain = ShortenURLUseCase(aliasRepository)
        val listDomain = GetAliasListUseCase(aliasRepository)

        shortenURLDomain.createAliasFromURL("www.google.com")
        listDomain.getAllLinks()

        coVerify(exactly = 1) { aliasRepository.createAliasFromURL(any()) }
        coVerify(exactly = 1) { aliasRepository.getAllAlias() }
    }
}