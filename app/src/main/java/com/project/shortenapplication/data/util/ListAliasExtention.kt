package com.project.shortenapplication.data.util

import com.project.shortenapplication.data.local.entity.AliasEntity

fun List<AliasEntity>.containAlias(alias: String): Boolean {
    this.forEach {
        val existAlias = it.alias == alias
        if (existAlias)
            return true
    }
    return false
}

fun List<AliasEntity>.getIndexOfItem(alias: String): Int {
    this.forEachIndexed { index, aliasEntity ->
        if (aliasEntity.alias == alias) {
            return index
        }
    }
    return -1
}