package com.project.shortenapplication.data.source

import androidx.lifecycle.LiveData
import com.project.shortenapplication.data.local.LocalInMemoryAlias
import com.project.shortenapplication.data.local.entity.AliasEntity
import com.project.shortenapplication.domain.repository.AliasLocalDataSource
import javax.inject.Inject

class AliasLocalDataSourceImpl @Inject constructor(private val aliasLocalDataSource: LocalInMemoryAlias) : AliasLocalDataSource {

    override fun insertAlias(link: AliasEntity): Boolean {
        return aliasLocalDataSource.insertURL(link)
    }

    override fun getAllAlias(): LiveData<List<AliasEntity>> {
        return aliasLocalDataSource.getAllAlias()
    }
}