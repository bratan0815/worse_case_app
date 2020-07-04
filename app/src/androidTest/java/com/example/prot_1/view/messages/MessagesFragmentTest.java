package com.example.prot_1.view.messages;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.prot_1.R;
import com.example.prot_1.control.MessageManager;
import com.example.prot_1.view.main.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.pressBackUnconditionally;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MessagesFragmentTest {
    private String testTitle ="UnbelievableTesticles";


    @Test
    public void testNavBtnToNet() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.btnNavMsgToNet)).perform(click());
        onView(withId(R.id.tvTitle)).check(matches(withText("Network Fragment")));
    }

    @Test
    public void testNavBtnToMap() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.btnNavMsgToMap)).perform(click());
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    /**
     * Recyclerview in view
     */
    @Test
    public void testIsListFragmentVisible(){
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.rvList)).check(matches(isDisplayed()));
    }

    /**
      * Select list item, nav to list Item, correct item
     */
    @Test
    public void testNavToItem() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.rvList)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tvActivityMessageTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.tvActivityMessageTitle)).check(matches(withText(MessageManager.getViewMessageList().getElementAt(0).getHeader().getTitle())));
    }

    /**
     * select list item nav to list item
     * press back
     */
    @Test
    public void testBackNavigation() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.rvList)).perform(actionOnItemAtPosition(0, click()));
        pressBack();
        onView(withId(R.id.rvList)).check(matches(isDisplayed()));
    }

    /**
     * item added - adds an item, restarts the app and checks if db saved new item
     */
    @Test
    public void testAddItem() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);

        int listSize;
        MessageManager.getViewMessageList().deleteItemWithTitle(testTitle);

        onView(withId(R.id.btnNavActivityWrite)).perform(click());
        onView(withId(R.id.etActivityWriteTitle)).perform(clearText(), typeText(testTitle));
        onView(withId(R.id.etActivityWriteAuthor)).perform(clearText(), typeText("Known Un"));
        onView(withId(R.id.etActivityWriteDescription)).perform(clearText(), typeText("Made and Saved my day"));
        onView(withId(R.id.etActivityWriteText)).perform(clearText(), typeText("Schakalakaschakalak!"));
        onView(withId(R.id.btnActivityWriteSave)).perform(click());

        listSize = MessageManager.getViewMessageList().getListOfElements().size();

        onView(withId(R.id.rvList)).perform(actionOnItemAtPosition(listSize -1, click()));
        onView(withId(R.id.tvActivityMessageTitle)).check(matches(withText(testTitle)));

        pressBack();
        pressBackUnconditionally();
        ActivityScenario activityScenario2 = ActivityScenario.launch(MainActivity.class);
        listSize = MessageManager.getViewMessageList().getListOfElements().size();
        onView(withId(R.id.rvList)).perform(actionOnItemAtPosition(listSize -1, click()));
        onView(withId(R.id.tvActivityMessageTitle)).check(matches(withText(testTitle)));
    }

    /**
     * item deleted - delete item, restarts the app and checks if item was deleted from db to
     */
    @Test
    public void testDeleteItem() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);

        int listSize;

        onView(withId(R.id.btnNavActivityWrite)).perform(click());
        onView(withId(R.id.etActivityWriteTitle)).perform(clearText(), typeText(testTitle));
        onView(withId(R.id.etActivityWriteAuthor)).perform(clearText(), typeText("Known Un"));
        onView(withId(R.id.etActivityWriteDescription)).perform(clearText(), typeText("Made and Saved my day"));
        onView(withId(R.id.etActivityWriteText)).perform(clearText(), typeText("Schakalakaschakalak!"));
        onView(withId(R.id.btnActivityWriteSave)).perform(click());

        listSize = MessageManager.getViewMessageList().getListOfElements().size();

        onView(withId(R.id.rvList)).perform(actionOnItemAtPosition(listSize -1, click()));
        onView(withId(R.id.tvActivityMessageTitle)).check(matches(withText(testTitle)));
        onView(withId(R.id.btnActivityMessageDelete)).perform(click());
        pressBack();

        listSize = MessageManager.getViewMessageList().getListOfElements().size();
        onView(withId(R.id.rvList)).perform(actionOnItemAtPosition(listSize -1, click()));
        onView(withId(R.id.tvActivityMessageTitle)).check(matches(withText(not(testTitle))));

        pressBack();
        pressBackUnconditionally();
        ActivityScenario activityScenario2 = ActivityScenario.launch(MainActivity.class);
        listSize = MessageManager.getViewMessageList().getListOfElements().size();
        onView(withId(R.id.rvList)).perform(actionOnItemAtPosition(listSize -1, click()));
        onView(withId(R.id.tvActivityMessageTitle)).check(matches(withText(not(testTitle))));
    }


}