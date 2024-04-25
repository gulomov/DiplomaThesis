package com.diploma.work.diplomathesis.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRealTimeDatabase(): FirebaseDatabase =
        Firebase.database("https://dimplomathesis-default-rtdb.europe-west1.firebasedatabase.app")

    @Provides
    @Singleton
    fun provideDatabaseReference(): DatabaseReference = Firebase.database.reference

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}
