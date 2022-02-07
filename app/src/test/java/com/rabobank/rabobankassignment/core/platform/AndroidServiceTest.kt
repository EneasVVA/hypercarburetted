package com.rabobank.rabobankassignment.core.platform

import com.rabobank.rabobankassignment.AndroidTest
import org.junit.Before
import org.junit.Test

class AndroidServiceTest : AndroidTest() {

    private lateinit var androidService : AndroidService

    @Before
    fun setUp() {
        androidService = AndroidService(context())
    }

    @Test
    fun `resources list`() {
        // TODO
    }

    @Test
    fun `should set context`() {
        assert(androidService.context == context())
    }
}