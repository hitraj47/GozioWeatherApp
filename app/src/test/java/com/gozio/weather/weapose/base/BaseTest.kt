package com.gozio.weather.weapose.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.rules.TestRule

open class BaseTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)
}
