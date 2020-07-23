package com.example.appiness

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.appiness.splash.SplashActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private var stringToBetyped: String = ""
    private val userViewModel = SearchViewModel()

    @Rule
    var rule =
        ActivityTestRule(MainActivity::class.java)

    @Rule
    var ruleSpals =
        ActivityTestRule(SplashActivity::class.java)

    @Before
    fun initValidString() {
        stringToBetyped = "flickr"
    }

    @Test
    fun useAppContext() {
        val activity = rule.activity
    }

    @Test
    fun changeText_sameActivity() {
        // Type text and then press the button.
    }

    @Test
    public fun activityLaunch() {

    }


}