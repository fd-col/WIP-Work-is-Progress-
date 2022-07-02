package it.wip

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import it.wip.ui.activities.StartStoryActivity

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StartStoryActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<StartStoryActivity> = ActivityScenarioRule(StartStoryActivity::class.java)

    @Test // test to check if a text is inserted inside the auto complite edit text inside StartStory activity
    fun newStoryName() {
        onView(withId(R.id.story_title_set_by_the_user)).check(matches((isDisplayed())))
        onView(withId(R.id.story_title_set_by_the_user)).perform(clearText(),
            typeText("Prova"), closeSoftKeyboard())
        onView(withId(R.id.story_title_set_by_the_user)).check(matches(withText("Prova")))
    }

    @Test // test to check switch selection on silent mode inside StartStory activity
    fun isSilentModeSelected() {
        onView(withId(R.id.switch_silent_mode)).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.switch_silent_mode)).check(matches(isChecked()))

    }

    @Test // test to check switch hardcore on silent mode inside StartStory activity
    fun isHardcoreModeSelect() {
        onView(withId(R.id.switch_hardcore_mode)).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.switch_hardcore_mode)).check(matches(isChecked()))
    }

}