package com.example.starterCode.data.remote.dto

import com.example.starterCode.domain.model.RickyAndMorty

data class RickyAndMortyDto(
    val info: Info,
    val results: List<Result>
)

fun RickyAndMortyDto.toRickyAndMorty(): List<RickyAndMorty>{
    val rickyMortyList = mutableListOf<RickyAndMorty>()

    results.forEach {
        rickyMortyList.add(RickyAndMorty(name = it.name, gender = it.gender, image = it.image, id = it.id))
    }
    return rickyMortyList

}
