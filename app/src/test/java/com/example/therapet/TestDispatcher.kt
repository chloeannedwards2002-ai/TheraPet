package com.example.therapet

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * @author: Chloe Edwards
 * @date: 08/01/2026
 *
 * The dispatcher controls what thread is used, when the coroutines run
 * It makes viewmodel coroutines testable
 */

class TestDispatcher : TestWatcher() {
    private val dispatcher = StandardTestDispatcher()

    override fun starting(description: Description){
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description){
        Dispatchers.resetMain()
    }

}