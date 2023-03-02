package com.studhub.app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.Intent;


import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AuthActivityTest {

    @Rule
    public ActivityScenarioRule<AuthActivity> testRule = new ActivityScenarioRule<>(AuthActivity.class);

    @Test
    public void signInIsWorking() {
        String key = "name";
        String value = "samitepo@gmail.com";

        Intents.init();

        onView(withId(R.id.editTextTextPersonName))
                .perform(typeText(value))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.sign_in_button))
                .perform(click());

        intended(allOf(
                hasData(value),
                hasComponent(AuthActivity.class.getName())));

        Intents.release();
    }

    @Test
    public void uiAutomatorTestIsWorking() throws UiObjectNotFoundException {
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.pressHome();

        UiObject allAppsButton = device
                .findObject(new UiSelector().description("Apps"));


        allAppsButton.clickAndWaitForNewWindow();
    }

}
