package com.diploma.work.introduction

import com.diploma.work.sharedpreference.SharedPreferencesManager
import javax.inject.Inject

class IntroductionRepository @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager,
) {
    fun setIntroductionShown() = sharedPreferencesManager.setIntroductionShown()
}
