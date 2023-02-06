package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.ClientDao
import com.example.data.local.ClientsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideClientDao(clientsDatabase: ClientsDatabase): ClientDao {
        return clientsDatabase.getClientDao()
    }

    @Provides
    @Singleton
    fun provideClientDatabase(@ApplicationContext context: Context): ClientsDatabase {
        return Room.databaseBuilder(
            context,
            ClientsDatabase::class.java,
            "clients_database"
        ).build()
    }
}