package com.example.data.local

import androidx.room.*
import com.example.domain.entity.Client
import kotlinx.coroutines.flow.Flow


@Dao
interface ClientDao {
    @Query("SELECT * FROM client_table")
    fun getAllClients() : Flow<List<Client>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClient(client: Client)

    @Delete
    suspend fun deleteClient(client: Client)

    @Update
    suspend fun updateClient(client: Client)
}