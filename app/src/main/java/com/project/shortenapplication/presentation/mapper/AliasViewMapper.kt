package com.project.shortenapplication.presentation.mapper

import com.project.shortenapplication.domain.entity.AliasDomain
import com.project.shortenapplication.ui.model.AliasView

fun AliasDomain.toAliasView(): AliasView =
    AliasView(this.aliasDomain, this.linksDomain.selfURLDomain, this.linksDomain.shortDomain)

fun List<AliasDomain>.toListAliasView(): List<AliasView> {
    return map {
        AliasView(it.aliasDomain, it.linksDomain.selfURLDomain, it.linksDomain.shortDomain)
    }
}