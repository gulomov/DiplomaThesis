package com.diploma.work.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel
    @Inject
    constructor(
        private val splashScreenRepository: SplashScreenRepository,
    ) : ViewModel() {
        fun wasIntroductionShown() = splashScreenRepository.wasIntroductionShown()
    }
