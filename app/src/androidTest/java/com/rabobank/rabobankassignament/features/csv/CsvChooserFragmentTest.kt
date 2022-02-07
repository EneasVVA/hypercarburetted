package com.rabobank.rabobankassignament.features.csv

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rabobank.rabobankassignament.R
import com.rabobank.rabobankassignament.launchFragmentInHiltContainer
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CsvChooserFragmentTest {

    @Test
    fun selectFirstResourceAndClickParseButton() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<CsvChooserFragment>(action = {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(requireView(), navController)
        })

        onView(withId(R.id.csv_resources_list)).perform(RecyclerViewActions.actionOnItemAtPosition<CsvResourcesAdapter.ViewHolder>(0, click()))
        onView(withId(R.id.btn_parse)).perform(click())
        assertThat(navController.currentDestination?.id, Matchers.equalTo(R.id.CSVParsedFragment))
    }
}