package it.wip.test

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import it.wip.ui.activities.StartStoryActivity
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StartStoryActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<StartStoryActivity> = ActivityScenarioRule(StartStoryActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testNewStoryName() {
        
    }
}