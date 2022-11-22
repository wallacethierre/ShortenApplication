package com.project.shortenapplication.presentation.mapper

import com.project.shortenapplication.domain.entity.AliasDomain
import com.project.shortenapplication.domain.entity.URLLinksDomain
import com.project.shortenapplication.ui.model.AliasView
import org.junit.Assert
import org.junit.Test

class AliasViewMapperTest {

    @Test
    fun `check mapper aliasDomain to aliasView`() {
        val aliasDomainList = listOf(
            AliasDomain("9796", URLLinksDomain("www.globo.com", "www.shorten.com/7232")),
            AliasDomain("3241", URLLinksDomain("www.google.com", "www.shorten.com/4667")),
        )

        val aliasViewList = listOf(
            AliasView("9796", "www.globo.com", "www.shorten.com/7232"),
            AliasView("3241", "www.google.com", "www.shorten.com/4667")

        )

        Assert.assertEquals(aliasDomainList.toListAliasView(), aliasViewList)
    }
}