package com.example.josmeyly_duarte_p1_p2.di

import com.example.josmeyly_duarte_p1_p2.data.repository.HuacalesRepositoryImpl
import com.example.josmeyly_duarte_p1_p2.domain.repository.HuacalesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

class RepositoryModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class RepositoryModule {

        @Binds
        @Singleton
        abstract fun bindHuacalesRepository(
            huacalesRepositoryImpl: HuacalesRepositoryImpl
        ): HuacalesRepository
    }
}