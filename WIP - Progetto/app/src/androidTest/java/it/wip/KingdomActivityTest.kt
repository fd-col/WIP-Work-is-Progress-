package it.wip

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import it.wip.ui.activities.KingdomActivity

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KingdomActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<KingdomActivity> = ActivityScenarioRule(
        KingdomActivity::class.java)

    @Test //testing if the vertical recycler view inside Kingdom activity is scrolling until the bottom
    fun scrollBottomRecyclerView(){
        onView(withId(R.id.rv_vertical)).perform(swipeUp())
    }

    @Test // testing if items on recycler view inside Kingdom activity are clickable
    fun clickItemOnRecyclerView(){
        onView(withId(R.id.rv_vertical)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5,click()))
    }

}