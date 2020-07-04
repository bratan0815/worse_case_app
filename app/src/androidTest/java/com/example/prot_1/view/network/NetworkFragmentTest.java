package com.example.prot_1.view.network;

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

@RunWith(AndroidJUnit4ClassRunner.class)
public class NetworkFragmentTest {

    @Test
    public void testIsDisplayed(){
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnNavMsgToNet)).perform(click());
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.btnNavNetToMsg)).check(matches(isDisplayed()));
        onView(withId(R.id.btnNavNetToNet)).check(matches(isDisplayed()));
        onView(withId(R.id.btnNavNetToMap)).check(matches(isDisplayed()));
    }

    @Test
    public void testHasRightTitle(){
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnNavMsgToNet)).perform(click());

        //onView(withId(R.id.btnNavNetwork)).perform(click());
        onView(withId(R.id.tvTitle)).check(matches(withText("Network Fragment")));
        //onView(withId(R.id.tvTestView)).check(matches(withText("TextView")));
    }

    @Test
    public void testNavBtnToMsg() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnNavMsgToNet)).perform(click());
        onView(withId(R.id.btnNavNetToMsg)).perform(click());
        onView(withId(R.id.rvList)).check(matches(isDisplayed()));
    }
    @Test
    public void testNavBtnToMap() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnNavMsgToNet)).perform(click());
        onView(withId(R.id.btnNavNetToMap)).perform(click());
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }



}