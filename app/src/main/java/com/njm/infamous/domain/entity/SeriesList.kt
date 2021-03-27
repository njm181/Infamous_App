package com.njm.infamous.domain.entity

data class SeriesList(
        val page: Int,
        var results: List<Result>,
        val total_pages: Int,
        val total_results: Int
)