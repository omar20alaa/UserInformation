package app.userinformation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import app.userinformation.ui.activity.RegisterActivity


@RunWith(AndroidJUnit4::class)
class MainActivityUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(RegisterActivity::class.java)

    @Test
    fun enterValidUserData_saveAndCheckInList() {
        onView(withId(R.id.editName)).perform(typeText("Omar Alaa"), closeSoftKeyboard())
        onView(withId(R.id.editAge)).perform(typeText("25"), closeSoftKeyboard())
        onView(withId(R.id.editJobTitle)).perform(typeText("Developer"), closeSoftKeyboard())
        onView(withId(R.id.editGender)).perform(typeText("Male"), closeSoftKeyboard())

        onView(withId(R.id.btnSave)).perform(click())

        onView(withId(R.id.recyclerView)).check(matches(hasDescendant(withText("Name: Omar Alaa"))))
        onView(withId(R.id.recyclerView)).check(matches(hasDescendant(withText("Age: 25"))))
        onView(withId(R.id.recyclerView)).check(matches(hasDescendant(withText("Job Title: Developer"))))
        onView(withId(R.id.recyclerView)).check(matches(hasDescendant(withText("Gender: Male"))))
    }
}