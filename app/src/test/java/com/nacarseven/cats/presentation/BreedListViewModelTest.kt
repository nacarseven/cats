package com.nacarseven.cats.presentation

import com.nacarseven.cats.domain.entities.Breed
import com.nacarseven.cats.domain.usecase.GetBreedListUseCase
import com.nacarseven.cats.presentation.breedlist.BreedListAction
import com.nacarseven.cats.presentation.breedlist.BreedListViewModel
import com.nacarseven.cats.presentation.breedlist.BreedListViewState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class BreedListViewModelTest {

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val useCase: GetBreedListUseCase = mockk()
    private val viewModel =
        BreedListViewModel(getBreedListUseCase = useCase, dispatcher = dispatcher)

    private val initialState = BreedListViewState()
    private val loadingState = initialState.copy(isLoading = true)

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getBreedList should set breed list when use case returns success`() = runTest {
        // Given
        val breedList = listOf(
            Breed("1", "Abyssinian", "England", "Description", "Temperament", "10 years", "3 -5"),
            Breed("2", "Aegean", "Canada", "Description", "Temperament", "10 years", "4-7")
        )

        val successState =
            loadingState.copy(isLoading = false, breedList = breedList, isErrorState = false)

        val testResults = arrayListOf<BreedListViewState>()
        val job =
            launch(dispatcher) { viewModel.breedListViewState.toList(destination = testResults) }

        coEvery { useCase() } returns flow { emit(breedList) }

        // When
        viewModel.getBreedList()

        // Then
        assertEquals(listOf(initialState, loadingState, successState), testResults)
        job.cancel()

    }

    @Test
    fun `getBreedList should set error state when use case returns failure`() = runTest {
        // Given
        coEvery { useCase() } returns flow { throw Exception() }
        val errorState =
            loadingState.copy(isLoading = false, isErrorState = true)
        val testResults = arrayListOf<BreedListViewState>()
        val job =
            launch(dispatcher) { viewModel.breedListViewState.toList(destination = testResults) }

        // When
        viewModel.getBreedList()

        // Then
        assertEquals(listOf(initialState, loadingState, errorState), testResults)
        job.cancel()
    }

    @Test
    fun `clickOnBreedItem should navigate to DetailScreen`() = runTest {
        // Given
        val breedFirstPosition =
            Breed("1", "Abyssinian", "England", "Description", "Temperament", "10 years", "3 -5")
        val breedSecondPosition =
            Breed("2", "Aegean", "Canada", "Description", "Temperament", "10 years", "4-7")

        val breedList = listOf(breedFirstPosition, breedSecondPosition)

        val testResults = arrayListOf<BreedListAction>()
        val job = launch(dispatcher) { viewModel.breedListAction.toList(destination = testResults) }

        coEvery { useCase() } returns flow { emit(breedList) }

        // When
        viewModel.clickOnBreedItem(breedFirstPosition)

        // Then
        assertTrue(testResults[0] is BreedListAction.GoToBreedDetail)
        job.cancel()

    }

}
