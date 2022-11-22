package com.project.shortenapplication.domain.interactor

import androidx.lifecycle.LiveData
import com.project.shortenapplication.domain.entity.AliasDomain
import com.project.shortenapplication.domain.repository.AliasRepositoryContract
import javax.inject.Inject

class ShortenURLUseCase @Inject constructor(private val aliasRepository: AliasRepositoryContract) {

    suspend fun shortenURL(url: String):Boolean {
        return aliasRepository.shortenURL(url)
    }

    fun getAllLinks(): LiveData<List<AliasDomain>> {
        return aliasRepository.getAllAlias()
    }
}