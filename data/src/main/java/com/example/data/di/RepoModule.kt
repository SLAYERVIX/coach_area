package com.example.data.di

import com.example.data.local.ClientDao
import com.example.data.repo.ClientRepositoryImpl
import com.example.domain.repo.ClientRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideClientRepo(clientDao: ClientDao) : ClientRepository {
        return ClientRepositoryImpl(clientDao)
    }
}