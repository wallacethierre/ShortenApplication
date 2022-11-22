package com.project.shortenapplication.domain.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.shortenapplication.data.repository.AliasRepository
import com.project.shortenapplication.domain.entity.AliasDomain
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
import org.mockito.ArgumentMatchers.any

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
            aliasRepository.shortenURL(any())
        } returns true

        val listDomain = ShortenURLUseCase(aliasRepository)
        listDomain.shortenURL("www.google.com")
        listDomain.getAllLinks()

        coVerify(exactly = 1) { aliasRepository.shortenURL(any()) }
        coVerify(exactly = 1) { aliasRepository.getAllAlias() }
    }
}