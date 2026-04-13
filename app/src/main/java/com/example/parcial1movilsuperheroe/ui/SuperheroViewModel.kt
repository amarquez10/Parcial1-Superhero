package com.example.parcial1movilsuperheroe.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parcial1movilsuperheroe.data.remote.RetrofitInstance
import com.example.parcial1movilsuperheroe.data.repository.SuperheroRepository
import com.example.parcial1movilsuperheroe.model.Superhero
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Represents UI States
sealed class SuperheroUiState {
    object Loading : SuperheroUiState()
    data class Success(val heroes: List<Superhero>) : SuperheroUiState()
    data class Error(val message: String) : SuperheroUiState()
}

sealed class SuperheroDetailUiState {
    object Loading : SuperheroDetailUiState()
    data class Success(val hero: Superhero) : SuperheroDetailUiState()
    data class Error(val message: String) : SuperheroDetailUiState()
}

class SuperheroViewModel(
    private val repository: SuperheroRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SuperheroUiState>(SuperheroUiState.Loading)
    val uiState: StateFlow<SuperheroUiState> = _uiState.asStateFlow()

    private val _detailUiState = MutableStateFlow<SuperheroDetailUiState>(SuperheroDetailUiState.Loading)
    val detailUiState: StateFlow<SuperheroDetailUiState> = _detailUiState.asStateFlow()

    init {
        fetchSuperheroes()
    }

    fun fetchSuperheroes() {
        viewModelScope.launch {
            _uiState.value = SuperheroUiState.Loading
            try {
                val heroes = repository.getSuperheroes()
                _uiState.value = SuperheroUiState.Success(heroes)
            } catch (e: Exception) {
                _uiState.value = SuperheroUiState.Error(e.message ?: "Ocurrió un error inesperado")
            }
        }
    }

    fun fetchSuperheroById(id: Int) {
        viewModelScope.launch {
            _detailUiState.value = SuperheroDetailUiState.Loading
            try {
                val hero = repository.getSuperheroById(id)
                _detailUiState.value = SuperheroDetailUiState.Success(hero)
            } catch (e: Exception) {
                _detailUiState.value = SuperheroDetailUiState.Error(e.message ?: "No se encontró el héroe")
            }
        }
    }
}

class SuperheroViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuperheroViewModel::class.java)) {
            val repository = SuperheroRepository(RetrofitInstance.api)
            return SuperheroViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
    }
}
