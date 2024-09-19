package com.example.poke_demo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {
    var pokemonList: List<PokemonEntry> by mutableStateOf(emptyList())
        private set

    var selectedPokemon: Pokemon? by mutableStateOf(null)
        private set

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    private var currentOffset by mutableIntStateOf(0)
    private val limit = 20 // numero de items por "pagina", vao carregando mais a medida que faz scroll

    init {
        loadPokemonList()
    }

    fun loadPokemonList() {
        if (isLoading) return // previne loads simultaneos

        viewModelScope.launch {
            try {
                isLoading = true
                val newPokemons = repository.getPokemonList(limit, currentOffset)
                if(newPokemons.isNotEmpty()) {
                    pokemonList = pokemonList + newPokemons
                    currentOffset += limit
                }
            } catch (e: Exception) {
                errorMessage = "Falhou a obtenção de novos dados"
            } finally {
                isLoading = false
            }
        }
    }

    fun loadPokemonDetail(id: Int) {
        viewModelScope.launch {
            selectedPokemon = repository.getPokemonDetail(id)
        }
    }
}