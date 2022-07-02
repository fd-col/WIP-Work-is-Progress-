package it.wip

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Test

class MainActivityTest {

    @Test
    fun activityShown(){

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.main_layout)).check(matches(isDisplayed()))
    }
}