package com.task

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Created by VI20218870 on 06,February,2024
 */
@ExperimentalCoroutinesApi
class DispatcherRule(val dispatcher :TestDispatcher= StandardTestDispatcher()):TestWatcher() {
    override fun starting(description: Description) {
        super.starting(description)
    }

    override fun finished(description: Description) {
        super.finished(description)
    }
}