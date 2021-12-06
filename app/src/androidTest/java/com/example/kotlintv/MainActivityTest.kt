package com.example.kotlintv

import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.junit.*
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner


import androidx.test.rule.ActivityTestRule

import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest : TestCase() {
    val mHeaderMenu = arrayOf(
        "Home",
        "Movies",
        "Sports",
        "Channels"
    )
    private var mHeaderPosition = 0
    private var rowFragment: RowFragment? = null

    @get:Rule
    public val activityRule1: ActivityTestRule<*> = ActivityTestRule<MainActivity>(
        MainActivity::class.java
    )

    @Test
    fun testfragment_instantiated() {
        if (activityRule1.getActivity() != null)
            activityRule1.getActivity()
                .runOnUiThread(Runnable { val rowFragment: RowFragment = startRowFragment("Home") })
        onView(withId(R.id.main_browse_fragment)).check(matches(isDisplayed()))

    }

    private fun startRowFragment(HeaderItem: String): RowFragment {
        val activity: MainActivity = activityRule1.getActivity() as MainActivity
        val transaction: FragmentTransaction =
            activity.getSupportFragmentManager().beginTransaction()
        rowFragment = RowFragment(HeaderItem)
        transaction.add(rowFragment!!, "rowFragment")
        transaction.commit()
        return rowFragment as RowFragment
    }

    @Test
    fun test_HeaderRowFragmentDisplayed() {
        onView(withId(R.id.header))
            .check(matches(isDisplayed()))
        onView(isRoot()).perform(setDelay(200))
    }


    @Test
    fun test_firstHeaderAdded() {
        onView(allOf(withId(R.id.home_tv), withText(mHeaderMenu.get(mHeaderPosition)))).perform(
            click()
        )
        onView(isRoot()).perform(setDelay(200))
    }

    @Test
    fun test_secondRowAdded() {
        mHeaderPosition = 1
        if (activityRule1.activity != null) {
            activityRule1.activity.runOnUiThread { startRowFragment(mHeaderMenu.get(mHeaderPosition)) }
            onView(isRoot()).perform(setDelay(500))
            val listRowtext = mHeaderMenu.get(mHeaderPosition)
            val isdisplay =
                onView(withId(R.id.movies_tv)).check(
                    matches(
                        withText(
                            mHeaderMenu.get(
                                mHeaderPosition
                            )
                        )
                    )
                )
            if (listRowtext.equals("Movies")) {
                assertTrue(listRowtext.equals("Movies"))
            } else {
                fail()
            }
        }
    }

    private fun setDelay(delay: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(View::class.java)
            }

            override fun getDescription(): String {
                return "set delay"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadForAtLeast(delay.toLong())
            }
        }
    }


    @Test
    fun onCreate() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.kotlintv", appContext.packageName)
    }

    @Test
    fun getPageName() {
        assertTrue(MovieList.MOVIE_CATEGORY.isNotEmpty())
    }

    @Test
    fun setPageName() {
        assertEquals(mHeaderMenu.get(mHeaderPosition), mHeaderMenu.get(0))
    }
}