package com.example.coacharea.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.OnConflictStrategy
import com.example.domain.entity.Client
import com.example.domain.repo.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val clientRepository: ClientRepository) : ViewModel() {
    val clients : Flow<List<Client>> = clientRepository.getAllClients()

    fun insertClient(client: Client) = viewModelScope.launch(Dispatchers.IO) {
        clientRepository.insertClient(client)
    }

    fun deleteClient(client: Client) = viewModelScope.launch(Dispatchers.IO) {
        clientRepository.deleteClient(client)
    }
}