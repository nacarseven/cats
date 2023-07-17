package com.nacarseven.cats.data.repository

import app.cash.turbine.test
import com.nacarseven.cats.data.remote.datasource.BreedRemoteDataSource
import com.nacarseven.cats.data.remote.model.BreedResponse
import com.nacarseven.cats.data.remote.model.WeightResponse
import com.nacarseven.cats.domain.entities.Breed
import com.nacarseven.cats.domain.repository.BreedRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response


@ExperimentalCoroutinesApi
class BreedRepositoryImplTest {

    private val breedRemoteDataSource: BreedRemoteDataSource = mockk()

    private val breedRepository: BreedRepository = BreedRepositoryImpl(breedRemoteDataSource)

    @Test
    fun `getBreedList should return Breed entity list`() = runBlocking {
        val expected = listOf(
            Breed(
                id = "1",
                name = "Abyssinian",
                origin = "England",
                description = "Description",
                temperament = "Temperament",
                lifeTime = "10 years",
                weight = "3-5"
            ),
            Breed(
                id = "2",
                name = "Aegean",
                origin = "Canada",
                description = "Description",
                temperament = "Temperament",
                lifeTime = "10 years",
                weight = "4-7"
            )
        )

        val breedResponseList = listOf(
            BreedResponse(
                id = "1",
                name = "Abyssinian",
                origin = "England",
                description = "Description",
                temperament = "Temperament",
                lifeTime = "10 years",
                weight = WeightResponse(metric = "3-5")
            ),
            BreedResponse(
                id = "2",
                name = "Aegean",
                origin = "Canada",
                description = "Description",
                temperament = "Temperament",
                lifeTime = "10 years",
                weight = WeightResponse(metric = "4-7")
            )
        )

        coEvery { breedRemoteDataSource.getBreedList() } returns Response.success(breedResponseList)

        // When
        val result = breedRepository.getBreedList()

        // Then
        result.test {
            assertEquals(expected, awaitItem())
            awaitComplete()
        }
    }
}


