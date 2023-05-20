package com.example.news.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import com.example.news.R

@RunWith(AndroidJUnit4::class)
class RegistrationUserFragmentTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    fun shortPassword_causesError() {
        onView(withId(R.id.et_registration_email))
            .perform(ViewActions.typeText("samsung@gmail.com"))
        onView(withId(R.id.et_registration_password))
            .perform(ViewActions.typeText(""))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btRegistrationAccount))
            .perform(ViewActions.click())
        onView(withId(R.id.et_registration_password))
            .check(ViewAssertions.matches(hasErrorText("Пароль должен содержать хотя бы 6 символов")))
    }


    fun shortEmail_causesError() {
        onView(withId(R.id.et_registration_email))
            .perform(ViewActions.typeText(""))
        onView(withId(R.id.et_registration_password))
            .perform(ViewActions.typeText("123456"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btRegistrationAccount))
            .perform(ViewActions.click())
        onView(withId(R.id.et_registration_email))
            .check(ViewAssertions.matches(hasErrorText("Логин отсутствует")))
    }
}