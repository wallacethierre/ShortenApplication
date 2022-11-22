package com.project.shortenapplication.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import com.project.shortenapplication.domain.entity.AliasDomain
import com.project.shortenapplication.domain.entity.URLLinksDomain
import com.project.shortenapplication.domain.interactor.ShortenURLUseCase
import com.project.shortenapplication.ui.model.AliasView
import com.project.shortenapplication.util.TestCoroutineRule
import com.project.shortenapplication.util.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AliasViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = TestCoroutineRule()

    @RelaxedMockK
    lateinit var shortenURLUseCase: ShortenURLUseCase

    init {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Before
    fun setup() {
        clearMocks(shortenURLUseCase)
    }

    @Test
    fun `should return a valid list of aliasView`() {
        coEvery {
            shortenURLUseCase.getAllLinks()
        } returns liveData {
            val aliasList = listOf(AliasDomain("5425", URLLinksDomain("wwww.google.com", "www.shorten.com/5425")))
            emit(aliasList)
        }


        val aliasViewModel = AliasViewModel(shortenURLUseCase)
        val result = aliasViewModel.allAlias.getOrAwaitValue()

        Assert.assertEquals(result, listOf(AliasView("5425","wwww.google.com", "www.shorten.com/5425" )))

    }
}