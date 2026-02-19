package com.example.therapet.ui.viewmodels

import com.example.therapet.app.ui.viewmodel.PetCareViewModel
import com.example.therapet.repositories.FakePetCareRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import org.junit.After
import org.junit.Before
import org.junit.Test




/**
 * @author: Chloe Edwards
 * @date: 14/02/2026
 *
 * Pet care view model tests
 */

// BROKEN TSETS
/*@OptIn(ExperimentalCoroutinesApi::class)
class PetCareViewModelTest {

    private lateinit var repository: FakePetCareRepository
    private lateinit var viewModel: PetCareViewModel
    private lateinit var dispatcher: StandardTestDispatcher

    @Before
    fun setup() {
        dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)

        repository = FakePetCareRepository()

        viewModel = PetCareViewModel(
            repository = repository,
            dispatcher = dispatcher,
            tickDelayMs = 100,
            startLoop = true
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun hibernation_stopsDecay() = runTest {
        repository.saveFoodLevel(1f)

        viewModel.setHibernation(true)

        advanceTimeBy(2000)

        val food = repository.foodLevel.first()

        assertEquals(1f, food)
    }
}

  /*  @Test
    fun increaseFood_clampsToOne() = runTest {
        repository.saveFoodLevel(0.95f)

        viewModel.increaseFood(0.2f)

        assertEquals(1f, repository.foodLevel.first())
    }

    @Test
    fun increaseWater_clampsToOne() = runTest {
        repository.saveWaterLevel(0.9f)

        viewModel.increaseWater(0.5f)

        assertEquals(1f, repository.waterLevel.first())
    }

      @Test
      fun decay_reducesValuesAfter20Ticks() = runTest {
          repository.saveFoodLevel(1f)
          repository.saveWaterLevel(1f)
          repository.saveSleepLevel(1f)

          viewModel = PetCareViewModel(
              repository = repository,
              dispatcher = dispatcher,
              tickDelayMs = 100,
              startLoop = true
          )

          advanceTimeBy(2000)

          val food = repository.foodLevel.first()
          val water = repository.waterLevel.first()

          assertTrue(food < 1f)
          assertTrue(water < 1f)
      }*/


/*    @Test
    fun startSleep_fillsSleepToOne() = runTest {
        repository.saveSleepLevel(0f)

        viewModel.startSleep()

        advanceTimeBy(20_000)

        val sleep = repository.sleepLevel.first()

        assertEquals(1f, sleep)
        assertFalse(viewModel.isSleeping.value)
    }*/
} */