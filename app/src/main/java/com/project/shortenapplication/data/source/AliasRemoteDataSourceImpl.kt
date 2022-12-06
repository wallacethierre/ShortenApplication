package com.project.shortenapplication.data.source

import com.project.shortenapplication.data.api.AliasAPIContract
import com.project.shortenapplication.data.api.dto.AliasURLResponse
import com.project.shortenapplication.data.api.dto.UrlRequest
import com.project.shortenapplication.domain.repository.AliasRemoteDataSource
import javax.inject.Inject

class AliasRemoteDataSourceImpl @Inject constructor(private val networkSource: AliasAPIContract):
    AliasRemoteDataSource {
    override suspend fun createAliasFromURL(url: String): AliasURLResponse {
        return networkSource.createAliasFromURL(UrlRequest(url))
    }

    override suspend fun getOrinalUrlByAlias(alias: String): String {
        return networkSource.getOriginalURLByAlias(alias)
    }
}