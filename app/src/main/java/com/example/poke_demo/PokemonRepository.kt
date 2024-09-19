package com.example.poke_demo

class PokemonRepository(private val api: PokemonApi) {
    suspend fun getPokemonList(limit: Int = 20, offset: Int = 0): List<PokemonEntry> {
        return api.getPokemonList(limit, offset).results
    }

    suspend fun getPokemonDetail(id: Int): Pokemon {
        return api.getPokemonDetail(id)
    }
}