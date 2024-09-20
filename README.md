# Aplicação Pokédex

Este é um projeto de uma aplicação **Pokédex** construída com **Kotlin** e **Jetpack Compose**, que consome a API [PokeAPI](https://pokeapi.co/) para listar e detalhar informação sobre os Pokémon.

## Funcionalidades

- Ecrã com lista de Pokémon com carregamento de Pokémon à medida que se faz scroll na aplicação.
- Ecrã de detalhe de cada Pokémon, incluindo imagem, número, nome, altura, peso, tipos e stats.

## Screenshots

<img alt ="Ecrã Lista de Pokémon" src="https://github.com/user-attachments/assets/68ce4ad2-fd33-42db-97bf-a360656a03da" width="400px">
<img alt ="Ecrã Detalhe de Pokémon" src="https://github.com/user-attachments/assets/e401eb97-9d21-4c8b-aba3-459ae7b3df4b" width="400px">


## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação para o desenvolvimento da app.
- **Jetpack Compose**: Framework UI para Android.
- **ViewModel**: Utilizado para gestão de estado da aplicação.
- **Retrofit**: Biblioteca para chamadas à API.
- **PokeAPI**: API pública para obtenção dos dados.

## Estrutura do Projeto

### `MainActivity.kt`
A atividade principal onde é inicializado o `NavHost`, que define as navegações entre a os escrãs lista de Pokémon e detalhes de cada Pokémon.

### `Pokemon.kt`
Define as classes de dados para os Pokémon, incluindo detalhes como `Stat`, `Type`, e `Sprites`. Estas classes são utilizadas para mapear as respostas da API.

### `PokemonRepository.kt`
Classe que faz as chamadas à API e devolve os dados necessários ao ViewModel

### `PokemonViewModel.kt`
O `ViewModel` responsável por manter o estado da aplicação, que contém a lógica de carregamento da lista e detalhes de Pokémon.

### `PokemonViewModelFactory.kt`
Cria instâncias do `PokemonViewModel` através do `PokemonRepository` para fornecer dados.

### `PokemonList.kt`
Ecrã que exibe a lista de Pokémon, com funcionalidade de scroll.

### `PokemonDetail.kt`
Ecrã de detalhes que mostra as informações de um Pokémon selecionado, incluindo imagem, número, nome, altura, peso, tipos e stats (HP, Ataque, Defesa, Ataque Especial, Defesa Especial, Velocidade).

### `PokemonApi.kt`
Interface que define as chamadas GET para a PokeAPI.

## Requisitos

- Android Studio 2024.1.2 ou superior.
- Ligação à Internet para consumir a API.

## Como Usar

1. Ao iniciar, a aplicação carrega automaticamente uma lista de Pokémon da PokeAPI.
2. Faça scroll para ver mais Pokémon à medida que são carregados.
3. Clique em qualquer Pokémon para ver detalhes do mesmo.

## Personalizações

- **Limite de Pokémon**: O número de Pokémon carregados por página pode ser ajustado na classe `PokemonViewModel`, alterando a variável `limit`.
- **Design da UI**: A aparência dos cards e dos detalhes de Pokémon pode ser personalizada nos ficheiros `PokemonList.kt` e `PokemonDetail.kt`.

## Bugs Conhecidos

- **Carregamento de Pokémon em scroll infinito**: Pode ocorrer um atraso ao carregar mais Pokémon à medida que se faz scroll, clicando num Pokémon da lista e voltando atrás os seguintes já deverão ter carregado.

## Autores

- [Beatriz Guerra](https://github.com/b-guerra97)
- [Tayara Cruz](https://github.com/Tayara32)
- [Pedro Pires](https://github.com/rookpmp)
- [Pedro Ferreira](https://github.com/PedrocasPitucho)
