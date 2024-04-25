package com.diploma.work.introduction

import androidx.lifecycle.ViewModel
import com.diploma.work.repository.repository.IntroductionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel @Inject constructor(
    private val introductionRepository: IntroductionRepository,
) : ViewModel() {
    fun setIntroductionShown() = introductionRepository.setIntroductionShown()
}
