package com.example.poke_demo

class Pokemon (
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val stats: List<Stat>,
    val types: List<TypeSlot>,
    val sprites: Sprites
)

class Stat(
    val base_stat: Int,
    val stat: StatInfo
)

class StatInfo(
    val name: String
)

class TypeSlot(
    val type: TypeInfo
)

class TypeInfo(
    val name: String
)

class Sprites(
    val front_default: String
)