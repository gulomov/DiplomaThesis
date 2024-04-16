package com.diploma.work.home.domain

import com.diploma.work.repository.repository.HomeRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchNewsFromFirebaseAndSaveUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke() = homeRepository.fetchAndSaveNewsInfoFromFirebase()
}