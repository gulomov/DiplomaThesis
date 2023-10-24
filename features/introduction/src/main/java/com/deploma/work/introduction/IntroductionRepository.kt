package com.deploma.work.introduction

import com.deploma.work.sharedpreference.SharedPreferencesManager
import javax.inject.Inject

class IntroductionRepository @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager,
) {
    fun setIntroductionShown() = sharedPreferencesManager.setIntroductionShown()
}
