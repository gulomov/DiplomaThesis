package com.diploma.work.comingsoon

import com.diploma.work.repository.data.ComingSoonProduct

data class ComingSoonUiState(
    val listOfComingProducts: List<ComingSoonProduct> = emptyList()
)
