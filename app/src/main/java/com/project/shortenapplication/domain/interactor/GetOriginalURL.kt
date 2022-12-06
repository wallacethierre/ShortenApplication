package com.project.shortenapplication.domain.interactor

import com.project.shortenapplication.domain.repository.AliasRepositoryContract
import javax.inject.Inject

class GetOriginalURL @Inject constructor(private val aliasRepository: AliasRepositoryContract) {

    suspend fun getOriginalUrlByAlias(alias: String): String {
        return aliasRepository.getOriginalUrlById(alias)
    }
}