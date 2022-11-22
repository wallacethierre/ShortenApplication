package com.project.shortenapplication.presentation

import android.util.Log
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.shortenapplication.domain.interactor.GetURLFromAliasUseCase
import com.project.shortenapplication.domain.interactor.ShortenURLUseCase
import com.project.shortenapplication.presentation.mapper.toListAliasView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AliasViewModel @Inject constructor(
    private val shortenURLUseCase: ShortenURLUseCase
) : ViewModel() {

    val allAlias = Transformations.map(
        shortenURLUseCase.getAllLinks()
    ) {
        it.toListAliasView()
    }

    fun doShortenURL(url: String): Boolean {
        var isSuccessInserted = false
        viewModelScope.launch {
            try {
                isSuccessInserted = shortenURLUseCase.shortenURL(url)
            } catch (ex: Exception) {
                Log.e("INTERVIEW", "Error to reduceURL ", ex)
            }
        }
        return isSuccessInserted
    }
}