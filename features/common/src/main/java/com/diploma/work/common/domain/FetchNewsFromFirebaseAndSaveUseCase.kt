package com.diploma.work.common.domain

import com.diploma.work.repository.repository.HomeRepository
import javax.inject.Inject

class FetchNewsFromFirebaseAndSaveUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
) {
    suspend operator fun invoke() = homeRepository.fetchAndSaveNewsInfoFromFirebase()
}
