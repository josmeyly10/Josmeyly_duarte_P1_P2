package com.example.josmeyly_duarte_p1_p2.di

import android.content.Context
import androidx.room.Room
import com.example.josmeyly_duarte_p1_p2.data.local.database.HuacalesDb


import com.example.josmeyly_duarte_p1_p2.domain.model.Huacales
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Provides
    @Singleton
    fun provideTicketDb(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            HuacalesDb::class.java,
            "HuacalesDb"
        ).fallbackToDestructiveMigration()
            .build()
    @Provides
    fun provideHuacalesDao(huacalesDb: HuacalesDb) = huacalesDb.huacalesDao()
}