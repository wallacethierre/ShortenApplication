package com.project.shortenapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.project.shortenapplication.data.local.entity.AliasEntity
import com.project.shortenapplication.domain.entity.AliasDomain
import com.project.shortenapplication.domain.mapper.toListAliasDomain
import com.project.shortenapplication.domain.repository.AliasLocalDataSource
import com.project.shortenapplication.domain.repository.AliasRemoteDataSource
import com.project.shortenapplication.domain.repository.AliasRepositoryContract
import javax.inject.Inject

class AliasRepository @Inject constructor(
    private val aliasRemoteDataSource: AliasRemoteDataSource,
    private val aliasLocalDataSource: AliasLocalDataSource
) : AliasRepositoryContract {
    override suspend fun createAliasFromURL(url: String): Boolean {
        val networkData = aliasRemoteDataSource.createAliasFromURL(url)
        if (networkData != null) {
            val aliasEntity = AliasEntity(
                networkData.aliasURL,
                networkData.links.selfURL,
                networkData.links.shortURL
            )
            aliasLocalDataSource.insertAlias(aliasEntity)
            return true
        }
        return false
    }

    override suspend fun getOriginalURL(alias: String): String =
        aliasRemoteDataSource.getOriginalURL(alias)

    override fun getAllAlias(): LiveData<List<AliasDomain>> = Transformations.map(
        aliasLocalDataSource.getAllAlias()
    ) {
        it.toListAliasDomain()
    }
}