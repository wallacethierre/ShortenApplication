package com.project.shortenapplication.domain.mapper

import com.project.shortenapplication.data.api.dto.AliasURLResponse
import com.project.shortenapplication.data.local.entity.AliasEntity
import com.project.shortenapplication.domain.entity.AliasDomain
import com.project.shortenapplication.domain.entity.URLLinksDomain

fun AliasURLResponse.toAliasDomain() =
    AliasDomain(this.alias, linksDomain = URLLinksDomain(this.links.selfURL, this.links.shortURL))

fun List<AliasEntity>.toListAliasDomain(): List<AliasDomain> {
    return map { AliasDomain(it.alias, linksDomain = URLLinksDomain(it.selfURL, it.shortURL))}
}