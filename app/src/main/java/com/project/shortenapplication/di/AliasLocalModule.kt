package com.project.shortenapplication.di

import com.project.shortenapplication.data.local.LocalInMemoryAlias
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AliasLocalModule {

    @Provides
    @Singleton
    fun provideLocalMemoryInstance(): LocalInMemoryAlias {
        return LocalInMemoryAlias.invoke()
    }
}