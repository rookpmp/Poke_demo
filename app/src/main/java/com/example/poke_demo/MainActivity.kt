package com.example.poke_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.poke_demo.ui.theme.Poke_demoTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// © 2024 [Beatriz Guerra, Pedro Ferreira, Pedro Pires, Tayara Cruz]

class MainActivity : ComponentActivity() {

    private val viewModel: PokemonViewModel by viewModels {
        PokemonViewModelFactory(PokemonRepository(NetworkModule.api))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "pokemon_list",
                builder = {
                    composable("pokemon_list") {
                        PokemonListScreen(navController, viewModel)
                    }
                }
            )
        }
    }
}

object NetworkModule {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val api: PokemonApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }
}
