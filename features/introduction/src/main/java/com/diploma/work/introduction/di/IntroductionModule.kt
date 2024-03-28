package com.diploma.work.introduction.di

import com.diploma.work.introduction.IntroductionRepository
import com.diploma.work.sharedpreference.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IntroductionModule {

    @Provides
    @Singleton
    fun provideIntroductionRepository(
        sharedPreferencesManager: SharedPreferencesManager,
    ): IntroductionRepository {
        return IntroductionRepository(sharedPreferencesManager)
    }
}
