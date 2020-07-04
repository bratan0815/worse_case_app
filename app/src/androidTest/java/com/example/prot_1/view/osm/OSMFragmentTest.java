package com.example.prot_1.view.osm;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.prot_1.R;
import com.example.prot_1.view.main.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class OSMFragmentTest {

    @Test
    public void testIsDisplayed() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnNavMsgToMap)).perform(click());
        onView(withId(R.id.btnNavMapToMsg)).check(matches(isDisplayed()));
        onView(withId(R.id.btnNavMapToNet)).check(matches(isDisplayed()));
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    @Test
    public void testNavBtnToMsg() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnNavMsgToMap)).perform(click());
        onView(withId(R.id.btnNavMapToMsg)).perform(click());
        onView(withId(R.id.rvList)).check(matches(isDisplayed()));
    }
    @Test
    public void testNavBtnToNet() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnNavMsgToMap)).perform(click());
        onView(withId(R.id.btnNavMapToNet)).perform(click());
        onView(withId(R.id.tvTitle)).check(matches(withText("Network Fragment")));
    }


}