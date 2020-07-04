package com.example.prot_1.view.messages;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.prot_1.R;
import com.example.prot_1.view.main.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ViewMessageActivityTest {
    @Test
    public void testIsDisplayed() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.rvList)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tvActivityMessageTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.tvActivityMessageAuthor)).check(matches(isDisplayed()));
        onView(withId(R.id.tvActivityMessageDescription)).check(matches(isDisplayed()));
        onView(withId(R.id.tvActivityMessageText)).check(matches(isDisplayed()));
        onView(withId(R.id.ivActivityMessageIcon)).check(matches(isDisplayed()));
        onView(withId(R.id.btnActivityMessageDelete)).check(matches(isDisplayed()));
    }

}