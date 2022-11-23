package com.project.shortenapplication.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.shortenapplication.data.local.entity.AliasEntity
import com.project.shortenapplication.data.util.containAlias

object LocalInMemoryAlias {

    private var mutableLiveDataAliasList: MutableLiveData<List<AliasEntity>> = MutableLiveData()
    private var storageMemory: List<AliasEntity>

    init {
        storageMemory = ArrayList()
    }

    fun insertURL(link: AliasEntity): Boolean {
        if (mutableLiveDataAliasList.value?.containAlias(link.aliasURL) != true) {
            storageMemory = storageMemory.plusElement(link)
            mutableLiveDataAliasList.value = storageMemory
            return true
        }
        return false
    }

    fun getAllAlias(): LiveData<List<AliasEntity>> {
        return mutableLiveDataAliasList
    }

    fun invoke(): LocalInMemoryAlias {
        return this
    }
}