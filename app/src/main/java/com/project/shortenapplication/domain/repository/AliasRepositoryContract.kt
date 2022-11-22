package com.project.shortenapplication.domain.repository

import androidx.lifecycle.LiveData
import com.project.shortenapplication.data.api.dto.AliasURLResponse
import com.project.shortenapplication.data.local.entity.AliasEntity
import com.project.shortenapplication.domain.entity.AliasDomain

interface AliasRepositoryContract {
    suspend fun shortenURL(url: String): Boolean
    suspend fun getOriginalURL(alias: String): String
    fun getAllAlias(): LiveData<List<AliasDomain>>
}

interface AliasRemoteDataSource {
    suspend fun shortenURL(url: String): AliasURLResponse
    suspend fun getOriginalURL(alias: String): String
}

interface AliasLocalDataSource {
    fun insertAlias(links: AliasEntity): Boolean
    fun getAllAlias(): LiveData<List<AliasEntity>>
    fun getOriginalURLByAlias(alias: String): String?
}