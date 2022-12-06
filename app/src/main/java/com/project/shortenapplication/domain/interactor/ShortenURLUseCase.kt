package com.project.shortenapplication.domain.interactor

import com.project.shortenapplication.domain.repository.AliasRepositoryContract
import javax.inject.Inject

class ShortenURLUseCase @Inject constructor(private val aliasRepository: AliasRepositoryContract) {

    suspend fun createAliasFromURL(url: String): Boolean {
        return aliasRepository.createAliasFromURL(url)
    }
}