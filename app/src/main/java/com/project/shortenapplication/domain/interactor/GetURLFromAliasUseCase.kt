package com.project.shortenapplication.domain.interactor

import com.project.shortenapplication.domain.repository.AliasRepositoryContract
import javax.inject.Inject

class GetURLFromAliasUseCase @Inject constructor(private val aliasRepository: AliasRepositoryContract) {
    suspend fun getOriginalURL(alias: String): String {
        return aliasRepository.getOriginalURL(alias)
    }
}