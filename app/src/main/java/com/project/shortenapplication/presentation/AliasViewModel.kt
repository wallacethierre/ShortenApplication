package com.project.shortenapplication.presentation

import android.util.Log
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.shortenapplication.domain.interactor.GetAliasListUseCase
import com.project.shortenapplication.domain.interactor.GetOriginalURL
import com.project.shortenapplication.domain.interactor.ShortenURLUseCase
import com.project.shortenapplication.presentation.mapper.toListAliasView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

private const val TAG = "INTERVIEW"

@HiltViewModel
class AliasViewModel @Inject constructor(
    private val shortenURLUseCase: ShortenURLUseCase,
    private val aliasListUseCase: GetAliasListUseCase,
    private val getOriginalURL: GetOriginalURL
) : ViewModel() {

    val allAlias = Transformations.map(
        aliasListUseCase.getAllLinks()
    ) {
        it.toListAliasView()
    }

    fun doShortenURL(url: String): Boolean {
        var isSuccessInserted = false
        viewModelScope.launch {
            try {
                isSuccessInserted = shortenURLUseCase.createAliasFromURL(url)
            } catch (ex: Exception) {
                Log.e(TAG, "Error to reduceURL ", ex)
            }
        }
        return isSuccessInserted
    }

    fun getOriginalUrlByAlias(alias: String): String {
        var originalString = ""
        viewModelScope.launch {
            try {
                originalString = getOriginalURL.getOriginalUrlByAlias(alias)
            } catch (ex: Exception) {
                Log.e(TAG, "This alias is not available in memory", ex)
            }
        }
        return originalString
    }
}