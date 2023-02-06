package com.example.coacharea.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Client
import com.example.domain.repo.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(private val clientRepository: ClientRepository) : ViewModel() {
    fun updateClient(client: Client) = viewModelScope.launch(Dispatchers.IO) {
        clientRepository.updateClient(client)
    }
}