package com.nacarseven.cats.data.mapper

import com.nacarseven.cats.data.remote.model.BreedResponse
import com.nacarseven.cats.domain.entities.Breed

fun List<BreedResponse>.mapToDomainList(): List<Breed> {
    return this.map { response ->
        Breed(
            id = response.id.orEmpty(),
            name = response.name.orEmpty(),
            origin = response.origin.orEmpty(),
            description = response.description.orEmpty(),
            temperament = response.temperament.orEmpty(),
            lifeTime = response.lifeTime.orEmpty(),
            weight = response.weight?.metric.orEmpty()
        )
    }
}