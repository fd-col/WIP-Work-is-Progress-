package it.wip

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import it.wip.ui.activities.StartStoryActivity
import org.hamcrest.Matchers

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
    fun newStoryName() {
        Espresso.onView(Matchers.allOf(ViewMatchers.withText("Story Title"))).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        /* DECOMMMENTARE E CONTROLLARE SE RUNNANDOLO IL TEST PRODUCE UN RISULTATO
        onView(Matchers.allOf(withText("Story Title"))).check(matches((isDisplayed())))
        onView(withId(R.id.story_title)).perform(clearText(),typeText("Amr"))
        onView(withId(R.id.story_title)).check(matches(withText("Amr")))
        */
    }
}