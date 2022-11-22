package com.project.shortenapplication.di

import com.project.shortenapplication.data.repository.AliasRepository
import com.project.shortenapplication.data.source.AliasLocalDataSourceImpl
import com.project.shortenapplication.data.source.AliasRemoteDataSourceImpl
import com.project.shortenapplication.domain.repository.AliasLocalDataSource
import com.project.shortenapplication.domain.repository.AliasRemoteDataSource
import com.project.shortenapplication.domain.repository.AliasRepositoryContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AliasRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideAliasRemoteSource(impl: AliasRemoteDataSourceImpl): AliasRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideAliasLocalSource(impl: AliasLocalDataSourceImpl): AliasLocalDataSource

    @Binds
    @Singleton
    abstract fun provideAliasRepository(impl: AliasRepository): AliasRepositoryContract
}