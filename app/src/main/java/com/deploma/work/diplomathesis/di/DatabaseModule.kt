package com.deploma.work.diplomathesis.di

import android.content.Context
import androidx.room.Room
import com.deploma.work.data.dao.ProductListDao
import com.deploma.work.data.database.DiplomaThesisDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): DiplomaThesisDatabase {
        return Room.databaseBuilder(
            context,
            DiplomaThesisDatabase::class.java,
            "diploma_thesis_database",
        ).build()
    }

    @Provides
    fun provideProductListDao(database: DiplomaThesisDatabase): ProductListDao {
        return database.ProductListDao()
    }
}
