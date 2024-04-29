package com.diploma.work.diplomathesis.di

import android.content.Context
import androidx.room.Room
import com.diploma.work.database.dao.HomeScreenDao
import com.diploma.work.database.dao.ProductsDao
import com.diploma.work.database.database.DiplomaThesisDatabase
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
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): DiplomaThesisDatabase {
        return Room.databaseBuilder(
            context,
            DiplomaThesisDatabase::class.java,
            "diploma_thesis_database",
        ).build()
    }

    @Provides
    fun provideProductListDao(database: DiplomaThesisDatabase): ProductsDao {
        return database.ProductDao()
    }

    @Provides
    fun provideHomeScreenDao(database: DiplomaThesisDatabase): HomeScreenDao {
        return database.HomeScreenDao()
    }
}
