package com.project.shortenapplication.data.source

import com.project.shortenapplication.data.api.AliasAPIContract
import com.project.shortenapplication.data.api.dto.AliasURLResponse
import com.project.shortenapplication.data.api.dto.UrlRequest
import com.project.shortenapplication.domain.repository.AliasRemoteDataSource
import javax.inject.Inject

class AliasRemoteDataSourceImpl @Inject constructor(private val networkSource: AliasAPIContract):
    AliasRemoteDataSource {
    override suspend fun shortenURL(url: String): AliasURLResponse {
        return networkSource.reduceURL(UrlRequest(url))
    }

    override suspend fun getOriginalURL(alias: String): String {
        return networkSource.getURL(alias)
    }
}