package com.diploma.work.sharedpreference

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun wasIntroductionShown(): Boolean {
        return sharedPreferences.getBoolean(INTRODUCTION_SHOWN_KEY, false)
    }

    fun setIntroductionShown() {
        sharedPreferences.edit().putBoolean(INTRODUCTION_SHOWN_KEY, true).apply()
    }

    companion object {
        const val INTRODUCTION_SHOWN_KEY = "INTRODUCTION_SHOWN_KEY"
    }
}
