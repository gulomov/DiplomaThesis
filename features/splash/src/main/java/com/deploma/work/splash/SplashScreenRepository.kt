package com.deploma.work.splash

import com.deploma.work.sharedpreference.SharedPreferencesManager
import javax.inject.Inject

class SplashScreenRepository @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager,
) {
    fun wasIntroductionShown() = sharedPreferencesManager.wasIntroductionShown()
}