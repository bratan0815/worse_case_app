package com.example.prot_1.view.messages;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.prot_1.R;
import com.example.prot_1.view.main.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class WriteMessageActivityTest {
    @Test
    public void testIsDisplayed() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnNavActivityWrite)).perform(click());
        onView(withId(R.id.etActivityWriteTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.etActivityWriteAuthor)).check(matches(isDisplayed()));
        onView(withId(R.id.etActivityWriteDescription)).check(matches(isDisplayed()));
        onView(withId(R.id.etActivityWriteText)).check(matches(isDisplayed()));
        onView(withId(R.id.btnActivityWriteSave)).check(matches(isDisplayed()));
    }

    @Test
    public void testPerformsNavOnSave() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnNavActivityWrite)).perform(click());
        onView(withId(R.id.etActivityWriteTitle)).perform(clearText(), typeText("UnbelievableTest"));
        onView(withId(R.id.etActivityWriteAuthor)).perform(clearText(), typeText("Known Un"));
        onView(withId(R.id.etActivityWriteDescription)).perform(clearText(), typeText("Made and Saved my day"));
        onView(withId(R.id.etActivityWriteText)).perform(clearText(), typeText("Schakalakaschakalak!"));
        onView(withId(R.id.btnActivityWriteSave)).perform(click());
        onView(withId(R.id.btnNavActivityWrite)).check(matches(isDisplayed()));
    }
}