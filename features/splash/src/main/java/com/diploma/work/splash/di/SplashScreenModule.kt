package com.diploma.work.splash.di

import com.diploma.work.sharedpreference.SharedPreferencesManager
import com.diploma.work.splash.SplashScreenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SplashScreenModule {
    @Provides
    @Singleton
    fun splashScreenRepository(sharedPreferencesManager: SharedPreferencesManager): SplashScreenRepository {
        return SplashScreenRepository(sharedPreferencesManager)
    }
}
