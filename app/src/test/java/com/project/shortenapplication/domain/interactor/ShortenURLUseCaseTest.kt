package com.project.shortenapplication.domain.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.shortenapplication.data.repository.AliasRepository
import com.project.shortenapplication.domain.repository.AliasRepositoryContract
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.coEvery
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
            aliasRepository.shortenURL(any())
        } returns
    }
}