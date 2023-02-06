package com.example.data.repo

import com.example.data.local.ClientDao
import com.example.domain.entity.Client
import com.example.domain.repo.ClientRepository
import kotlinx.coroutines.flow.Flow

class ClientRepositoryImpl(private val clientDao: ClientDao) : ClientRepository {
    override fun getAllClients(): Flow<List<Client>> = clientDao.getAllClients()

    override suspend fun insertClient(client: Client) = clientDao.insertClient(client)

    override suspend fun deleteClient(client: Client) = clientDao.deleteClient(client)

    override suspend fun updateClient(client: Client) = clientDao.updateClient(client)
}