package com.example.therapet.ui.viewmodels

import com.example.therapet.app.ui.viewmodel.PetViewModel
import com.example.therapet.helpers.TestDispatcher
import com.example.therapet.repositories.FakePetPreferencesRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before

import org.junit.Rule
import org.junit.Test


/**
 * @author: Chloe Edwards
 * @date: 11/01/2026
 *
 * Pet view model tests
 *
 * 1. Default pet colour is 0
 * 2. Selecting a new colour updates the selected colour index
 * 3. Selecting a new colour changes the index
 */


@OptIn(ExperimentalCoroutinesApi::class)
class PetViewModelTest {
    @get:Rule
    val dispatcherRule = TestDispatcher()

    private lateinit var viewModel: PetViewModel
    private lateinit var repository: FakePetPreferencesRepository


    @Before
    fun setup() {
        repository = FakePetPreferencesRepository()
        viewModel = PetViewModel(repository)
    }

    //1. Default pet colour is 0
    @Test
    fun default_pet_colour_index_is_zero() = runTest {
        assertEquals(0, viewModel.selectedColourIndex.value)
    }

    //2. Selecting a new colour updates the selected colour index
    @Test
    fun selecting_new_colour_updates_colour_index() = runTest {
        viewModel.selectColour(4)
        advanceUntilIdle()

        // force collection
        val value = viewModel.selectedColourIndex.first()

        assertEquals(4, value)
    }

    //3. Selecting a new colour changes the index
    @Test
    fun selecting_new_colour_changes_index() = runTest {
        viewModel.selectColour(1)
        advanceUntilIdle()

        viewModel.selectColour(3)
        advanceUntilIdle()

        assertEquals(3, viewModel.selectedColourIndex.value)
    }


    // 4.
    @Test
    fun pet_colour_persists_to_home_screen() = runTest {
        val createPetViewModel = PetViewModel(repository)

        createPetViewModel.selectColour(3)

        val homeViewModel = PetViewModel(repository)

        val value = homeViewModel.selectedColourIndex.first { it == 3 }

        assertEquals(3, value)
    }



}
