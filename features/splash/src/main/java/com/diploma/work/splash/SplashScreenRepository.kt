package com.diploma.work.splash

import com.diploma.work.sharedpreference.SharedPreferencesManager
import javax.inject.Inject

class SplashScreenRepository @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager,
) {
    fun wasIntroductionShown() = sharedPreferencesManager.wasIntroductionShown()
}
