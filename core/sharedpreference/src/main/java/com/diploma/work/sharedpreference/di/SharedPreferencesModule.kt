package com.diploma.work.sharedpreference.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.diploma.work.sharedpreference.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application
            .getSharedPreferences("DiplomaThesisPreferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesManager(
        sharedPreferences: SharedPreferences,
    ): SharedPreferencesManager {
        return SharedPreferencesManager(sharedPreferences)
    }
}
