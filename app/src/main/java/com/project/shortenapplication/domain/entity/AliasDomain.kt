package com.project.shortenapplication.domain.entity

data class AliasDomain(
    val aliasDomain: String,
    val linksDomain: URLLinksDomain
)

data class URLLinksDomain(
    val selfURLDomain: String,
    val shortDomain: String
)