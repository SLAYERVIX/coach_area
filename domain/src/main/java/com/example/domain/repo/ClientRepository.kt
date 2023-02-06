package com.example.domain.repo

import com.example.domain.entity.Client
import kotlinx.coroutines.flow.Flow


interface ClientRepository {
    fun getAllClients(): Flow<List<Client>>
    suspend fun insertClient(client: Client)
    suspend fun deleteClient(client: Client)
    suspend fun updateClient(client: Client)
}