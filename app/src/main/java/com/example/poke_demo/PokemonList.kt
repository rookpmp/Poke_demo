package com.example.poke_demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.Locale

// © 2024 [Beatriz Guerra, Pedro Ferreira, Pedro Pires, Tayara Cruz]

@Composable
fun PokemonListScreen(navController: NavController, viewModel: PokemonViewModel) {
    val pokemonList = viewModel.pokemonList
    val isLoading by remember { mutableStateOf(viewModel.isLoading) }

    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                if (lastVisibleItemIndex == pokemonList.size - 1 && !isLoading) {
                    viewModel.loadPokemonList()
                }
            }
    }

    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.pokedex_logo),
            contentDescription = "Logo do Pokedex",
            modifier = Modifier.size(250.dp))

        LazyColumn(state = listState) {
            items(pokemonList) { pokemon ->
                PokemonListItem(pokemon = pokemon, onClick = {
                    val id = pokemon.url.split("/").filter { it.isNotEmpty() }.last().toInt()
                    navController.navigate("pokemon_detail/$id")
                })
            }
            if (isLoading) {
                item {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
            }
        }

    }
}

@Composable
fun PokemonListItem(pokemon: PokemonEntry, onClick: () -> Unit) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF4052D9)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
            modifier = Modifier.padding(16.dp).align(alignment = Alignment.CenterHorizontally),
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = TextUnit(1.5F, TextUnitType.Sp),
            color = Color(0xFFFFE031),
        )
    }
}