package it.wip

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test

class MainActivityTest {

    @Test // test if Main activity is shown correctly
    fun activityShown(){
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.main_layout)).check(matches(isDisplayed()))
        Thread.sleep(1000)
    }
}