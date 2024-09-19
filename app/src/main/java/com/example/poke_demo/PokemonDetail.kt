package com.example.poke_demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import java.util.Locale

// © 2024 [Beatriz Guerra, Pedro Ferreira, Pedro Pires, Tayara Cruz]

@Composable
fun PokemonDetailScreen(id: Int, navController: NavController, viewModel: PokemonViewModel) {
    val pokemon = viewModel.selectedPokemon

    LaunchedEffect(id) {
        viewModel.loadPokemonDetail(id)
    }

    pokemon?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Image(
                painter = rememberAsyncImagePainter(it.sprites.front_default), //vai buscar a sprite da api
                contentDescription = it.name,
                modifier = Modifier.size(200.dp).align(Alignment.CenterHorizontally)
            )

            Text(
                text = "#${it.id}",
                fontSize = 20.sp, modifier =
                Modifier.padding(bottom = 8.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )

            )


            Row(horizontalArrangement = Arrangement.Center) {

                // Define a cor do tipo
                val typeColor = when (it.types.firstOrNull()?.type?.name) {
                    "fire" -> Color.Red
                    "water" -> Color.Blue
                    "grass" -> Color.Green
                    "electric" -> Color.Yellow
                    "psychic" -> Color.Magenta
                    "ice" -> Color.Cyan
                    "dragon" -> Color(0xFF7038F8)
                    "dark" -> Color.DarkGray
                    "fairy" -> Color(0xFFEE99AC)
                    "fighting" -> Color(0xFFC03028)
                    "flying" -> Color(0xFFA890F0)
                    "poison" -> Color(0xFFA040A0)
                    "ground" -> Color(0xFFE0C068)
                    "rock" -> Color(0xFFB8A038)
                    "bug" -> Color(0xFFA8B820)
                    "ghost" -> Color(0xFF705898)
                    "steel" -> Color.Gray
                    "normal" -> Color(0xFFA8A878)
                    else -> Color.Black
                }
                Text(
                    text = it.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(2.dp),
                    style = TextStyle(
                        shadow = Shadow(
                            color = typeColor, // Cor da sombra
                            offset = Offset(4f, 4f), // Deslocamento da sombra
                            blurRadius = 8f // Raio de desfoque da sombra
                        )
                    )

                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center)
        {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Height: ${it.height}",
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Type: ${it.types.joinToString { type -> type.type.name }}",
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Weight: ${it.weight}",
                    modifier = Modifier.padding(8.dp)
                )
            }

            it.stats.forEach { stat ->
                val statValue = stat.base_stat

                //define o texto de cada stat
                val statName = when (stat.stat.name) {
                    "hp" -> "HP"
                    "attack" -> "Attack"
                    "defense" -> "Defense"
                    "special-attack" -> "Special Attack"
                    "special-defense" -> "Special Defense"
                    "speed" -> "Speed"
                    else -> stat.stat.name.replaceFirstChar { it.uppercase() }
                }

                // Definindo cores para cada stat específico

                val statColor = when (statName.lowercase()) {
                    "hp" -> Color(0xFFEF5350) // Vermelho para HP
                    "attack" -> Color(0xFFFFA726) // Laranja para Ataque
                    "defense" -> Color(0xFF42A5F5) // Azul para Defesa
                    "special attack" -> Color(0xFFAB47BC) // Roxo para Special Attack
                    "special defense" -> Color(0xFF66BB6A) // Verde para Special Defense
                    "speed" -> Color(0xFFFFEB3B) // Amarelo para Speed
                    else -> Color.Gray // Cinza para outros stats
                }

                val normalizedValue = statValue / 255f //define o "progresso" do stat
                // 255 é o max que um pokemon pode ter

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp)
                        .padding(vertical = 8.dp, horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "$statName - $statValue",
                        modifier = Modifier
                            .weight(1.5f)
                            .padding(end = 1.dp)
                    )
                    //linha de progresso para cada stat
                    LinearProgressIndicator(
                        progress = { normalizedValue },
                        modifier = Modifier
                            .weight(2f)
                            .height(18.dp),
                        color = statColor, // Aplica a cor específica do stat
                        trackColor = Color.LightGray,
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                onClick = { navController.popBackStack() }, //navega para trás
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFCB05),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(2.dp),
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 80.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(16.dp),
                        ambientColor = Color(0xFF3B4CCA),
                        spotColor = Color(0xFF3B4CCA)
                    )
            ) {
                Text(
                    text = "Voltar",
                    color = Color(0xFF3B4CCA),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        fontSize = 15.sp
                    )
                )
            }

            // Rodapé com informações de copyright
            Text(
                text = "© 2024 Beatriz Guerra, Pedro Ferreira, Pedro Pires, Tayara Cruz",
                modifier = Modifier.padding(top = 16.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            )
        }
    }
}
