package edu.gatech.seclass.jobcompare6300;
import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class InputValuesTest {
    @Rule
    public ActivityTestRule<MainMenu> tActivityRule = new ActivityTestRule<>(
            MainMenu.class);

    @Test
    public void test14() {
        onView(withId(R.id.editCurrentJobButtonID)).perform(click());
        onView(withId(R.id.currentTitleID)).perform(clearText(), replaceText("TA"));
        onView(withId(R.id.currentCompanyID)).perform(clearText(), replaceText("GATECH"));
        onView(withId(R.id.currentCityID)).perform(clearText(), replaceText(""));
        // Espresso.closeSoftKeyboard();
        onView(withId(R.id.currentSaveButtonID)).perform(click());
        onView(withId(R.id.currentCityID)).check(matches(hasErrorText("No City Input")));
    }
    @Test
    public void test18() {
        onView(withId(R.id.enterJobOfferButtonID)).perform(click());
        onView(withId(R.id.offerTitleID)).perform(clearText(), replaceText("TA"));
        onView(withId(R.id.offerCompanyID)).perform(clearText(), replaceText("GATECH"));
        onView(withId(R.id.offerCostID)).perform(clearText(), replaceText("sdadfa5"));
        // Espresso.closeSoftKeyboard();
        onView(withId(R.id.offerSaveButtonID)).perform(click());
        onView(withId(R.id.offerCostID)).check(matches(hasErrorText("Invalid Cost of Living Index Input")));
    }
}
