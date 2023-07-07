package com.nacarseven.cats.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedResponse(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("origin") val origin: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("temperament") val temperament: String? = null,
    @SerialName("life_span") val lifeTime: String? = null,
    @SerialName("weight") val weight: WeightResponse? = null
)

@Serializable
data class WeightResponse(
    @SerialName("metric") val metric: String? = null
)
