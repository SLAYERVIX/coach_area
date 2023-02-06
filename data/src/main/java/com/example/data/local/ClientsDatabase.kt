package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.entity.Client

@Database(entities = [Client::class], version = 1, exportSchema = false)
abstract class ClientsDatabase : RoomDatabase() {
    abstract fun getClientDao() : ClientDao
}