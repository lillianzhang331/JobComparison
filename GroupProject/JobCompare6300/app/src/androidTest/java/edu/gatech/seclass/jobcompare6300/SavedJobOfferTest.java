package edu.gatech.seclass.jobcompare6300;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class SavedJobOfferTest {
    @Rule
    public ActivityTestRule<SavedJobOffer> tActivityRule = new ActivityTestRule<>(
            SavedJobOffer.class);
    @Before
    public void deleteDatabase() {
        InstrumentationRegistry.getInstrumentation().getTargetContext().deleteDatabase("JobCompare.db");
    }
    @Test
    public void compareWithCurrentJobTest() {
        onView(withId(R.id.compareCurrentButtonID)).perform(click());
        onView(withText("Job offer details were not entered")).inRoot(new Toastmatcher()).check(matches((isDisplayed())));
    }
}
