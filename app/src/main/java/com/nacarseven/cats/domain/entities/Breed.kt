package com.nacarseven.cats.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Breed(
    val id: String,
    val name: String,
    val origin: String,
    val description: String,
    val temperament: String,
    val lifeTime: String,
    val weight: String
) : Parcelable
