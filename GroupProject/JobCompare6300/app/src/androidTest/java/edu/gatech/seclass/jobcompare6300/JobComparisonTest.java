package edu.gatech.seclass.jobcompare6300;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class JobComparisonTest {
    @Rule
    public ActivityTestRule<RankedJobs> tActivityRule = new ActivityTestRule<>(
            RankedJobs.class);
    @Before
    public void deleteDatabase() {
        InstrumentationRegistry.getInstrumentation().getTargetContext().deleteDatabase("JobCompare.db");
    }
    @Test
    public void compareWithoutSelection() {
        onView(withId(R.id.rankedMakeComparisonButtonID)).perform(click());
        onView(withText("Select at least 2 Jobs from the list to Compare"))
                .inRoot(new Toastmatcher()).check(matches((isDisplayed())));
    }
}
