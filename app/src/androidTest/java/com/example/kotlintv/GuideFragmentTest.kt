package com.example.kotlintv


import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.FragmentTransaction
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf
import org.json.JSONArray
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4ClassRunner::class)
class GuideFragmentTest : TestCase() {
    @get:Rule
    public val activityRule: ActivityTestRule<*> = ActivityTestRule<MainActivity>(
        MainActivity::class.java
    )
    private var mGuideFragment: GuideFragment? = null
    var jsonString: String = ""

    @Before
    fun onCreate() {
        Espresso.onView(withId(R.id.header))
            .check(matches(isDisplayed()))
        Espresso.onView(isRoot()).perform(setDelay(200))
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
    fun onCreateView() {
        val jsonArray = JSONArray(jsonString)
        val position: Int = 2
        val btnTime = Button(activityRule.activity)
        val param: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            180,
            80
        )
        param.setMargins(0, 0, 12, 0)
        btnTime.layoutParams = param
        btnTime.gravity = Gravity.CENTER
        // btnTime.text = mTimeSlot[index].startTime
        btnTime.textSize = 12F
        btnTime.setPadding(20, 0, 20, 0)
        btnTime.setOnKeyListener(View.OnKeyListener setOnKeyListener@{ v: View, keyCode: Int, event: KeyEvent ->
            if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {

            }
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            }
            return@setOnKeyListener false
        })
        btnTime.setOnClickListener(View.OnClickListener { v: View? ->
            Espresso.onView(
                AllOf.allOf(
                    withId(v!!.id),
                    withText(jsonArray.getJSONObject(position).getString("startTime"))
                )
            ).perform(
                click()
            )
            Espresso.onView(isRoot()).perform(setDelay(200))
        })
    }

    @Test
    fun testgetCurrentTime() {
        val s = "11:00 AM"
        // val sdf = SimpleDateFormat("hh:mm a")
        val currentTime = "11:00 AM"
        val splitted = currentTime.split(":").toTypedArray()[0]
        val jsontime = s.split(":").toTypedArray()[0]
        return assertTrue(splitted.equals(jsontime))
    }

    @Before
    fun getJsonDataFromAsset() {
        try {
            jsonString = loadJSONFromAssets("guide_info.json")!!
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return assertTrue(false)
        }
        return assertTrue(jsonString.isNotEmpty())
    }

    @Test
    fun testJsonDataFromAsset() {
        val jsonArray = JSONArray(jsonString)
        assertEquals(jsonArray.length(), 10)
    }

    fun loadJSONFromAssets(fileName: String): String {
        return activityRule.activity.assets.open(fileName).bufferedReader().use { reader ->
            reader.readText()
        }
    }

    @Test
    fun testsetFocusNext() {
        val jsonArray = JSONArray(jsonString)
        val position: Int = 3
        val btnTime = Button(activityRule.activity)
        val param: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            180,
            80
        )
        param.setMargins(0, 0, 12, 0)
        btnTime.layoutParams = param
        btnTime.gravity = Gravity.CENTER
        btnTime.textSize = 12F
        btnTime.setPadding(20, 0, 20, 0)
        btnTime.setOnKeyListener(View.OnKeyListener setOnKeyListener@{ v: View, keyCode: Int, event: KeyEvent ->
            if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            }
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            }
            return@setOnKeyListener false
        })
        btnTime.setOnClickListener(View.OnClickListener { v: View? ->
            Espresso.onView(withId(btnTime.id))
                .perform(typeText(jsonArray.getJSONObject(position).getString("startTime")))
            Espresso.onView(isRoot()).perform(setDelay(200))
        })
    }

    @Test
    fun updateFocusNext() {
        val jsonArray = JSONArray(jsonString)
        val position: Int = 3
        val btnTime = Button(activityRule.activity)
        val param: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            180,
            80
        )
        param.setMargins(0, 0, 12, 0)
        btnTime.layoutParams = param
        btnTime.gravity = Gravity.CENTER
        btnTime.textSize = 12F
        btnTime.setPadding(20, 0, 20, 0)
        btnTime.setOnKeyListener(View.OnKeyListener setOnKeyListener@{ v: View, keyCode: Int, event: KeyEvent ->
            if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {

            }
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            }
            return@setOnKeyListener false
        })
        btnTime.setOnClickListener(View.OnClickListener { v: View? ->
            Espresso.onView(
                AllOf.allOf(
                    withId(v!!.id),
                    withText(jsonArray.getJSONObject(position).getString("startTime"))
                )
            ).perform(
                click()
            )
            Espresso.onView(isRoot()).perform(setDelay(200))
        })
    }

    @Test
    fun testGuideFragment_Launch() {
        if (activityRule.getActivity() != null)
            activityRule.getActivity()
                .runOnUiThread(Runnable { startGuideFragment() })
        Espresso.onView(withId(R.id.main_browse_fragment))
            .check(ViewAssertions.matches(isDisplayed()))

    }

    private fun startGuideFragment(): GuideFragment {
        val activity: MainActivity = activityRule.getActivity() as MainActivity
        val transaction: FragmentTransaction =
            activity.getSupportFragmentManager().beginTransaction()
        mGuideFragment = GuideFragment()
        transaction.add(mGuideFragment!!, "GuideFragment")
        transaction.commit()
        return mGuideFragment as GuideFragment
    }
}