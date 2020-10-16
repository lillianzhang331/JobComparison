package edu.gatech.seclass.jobcompare6300;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.intent.Intents;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class JobTests {
    @Rule
    public ActivityTestRule<MainMenu> rule = new ActivityTestRule<>(
            MainMenu.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
        InstrumentationRegistry.getInstrumentation().getTargetContext().deleteDatabase("JobCompare.db");
    }

    //Test #13: saving job details in enter current job with all fields filled with valid data
    @Test
    public void Test13() {
        onView(withId(R.id.editCurrentJobButtonID)).perform(click());
        onView(withId(R.id.currentTitleID)).perform(clearText(), replaceText("Petroleum Engineer"));
        onView(withId(R.id.currentCompanyID)).perform(clearText(), replaceText("Chevron"));
        onView(withId(R.id.currentCityID)).perform(clearText(), replaceText("Houston"));
        onView(withId(R.id.currentStateID)).perform(clearText(), replaceText("TX"));
        onView(withId(R.id.currentCostID)).perform(clearText(), replaceText("145"));
        onView(withId(R.id.currentCommuteID)).perform(clearText(), replaceText("1.5"));
        onView(withId(R.id.currentSalaryID)).perform(clearText(), replaceText("250000"));
        onView(withId(R.id.currentBonusID)).perform(clearText(), replaceText("15000"));
        onView(withId(R.id.currentRetirementID)).perform(clearText(), replaceText("40"));
        onView(withId(R.id.currentLeaveID)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.currentSaveButtonID)).perform(click());
        onView(withText("Job details saved")).inRoot(new Toastmatcher()).check(matches((isDisplayed())));
    }

    //Test #14: saving job details in enter current job with fields partially filled with valid data
    @Test
    public void Test14() {
        onView(withId(R.id.editCurrentJobButtonID)).perform(click());
        onView(withId(R.id.currentTitleID)).perform(clearText(), replaceText("Petroleum Engineer"));
        onView(withId(R.id.currentCompanyID)).perform(clearText(), replaceText("Chevron"));
        onView(withId(R.id.currentCityID)).perform(clearText(), replaceText("Houston"));
        onView(withId(R.id.currentStateID)).perform(clearText(), replaceText("TX"));
        onView(withId(R.id.currentCostID)).perform(clearText(), replaceText("145"));
        //onView(withId(R.id.currentCommuteID)).perform(clearText(), replaceText("1.5"));
        onView(withId(R.id.currentSalaryID)).perform(clearText(), replaceText("250000"));
        onView(withId(R.id.currentBonusID)).perform(clearText(), replaceText("15000"));
        onView(withId(R.id.currentRetirementID)).perform(clearText(), replaceText("40"));
        onView(withId(R.id.currentLeaveID)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.currentSaveButtonID)).perform(click());
        onView(withId(R.id.currentCommuteID)).check(matches(hasErrorText("No Commute Input")));
    }

    //Test #15a: saving invalid data entry in enter current job - number in City string
    @Test
    public void Test15a() {
        onView(withId(R.id.editCurrentJobButtonID)).perform(click());
        onView(withId(R.id.currentTitleID)).perform(clearText(), replaceText("Petroleum Engineer"));
        onView(withId(R.id.currentCompanyID)).perform(clearText(), replaceText("Chevron"));
        onView(withId(R.id.currentCityID)).perform(clearText(), replaceText("Houst00n"));
        onView(withId(R.id.currentStateID)).perform(clearText(), replaceText("TX"));
        onView(withId(R.id.currentCostID)).perform(clearText(), replaceText("145"));
        onView(withId(R.id.currentCommuteID)).perform(clearText(), replaceText("1.5"));
        onView(withId(R.id.currentSalaryID)).perform(clearText(), replaceText("250000"));
        onView(withId(R.id.currentBonusID)).perform(clearText(), replaceText("15000"));
        onView(withId(R.id.currentRetirementID)).perform(clearText(), replaceText("40"));
        onView(withId(R.id.currentLeaveID)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.currentSaveButtonID)).perform(click());
        onView(withId(R.id.currentCityID)).check(matches(hasErrorText("Invalid City Input")));
    }

    //Test #15b: saving invalid data entry in enter current job - alphabet in salary
    @Test
    public void Test15b() {
        onView(withId(R.id.editCurrentJobButtonID)).perform(click());
        onView(withId(R.id.currentTitleID)).perform(clearText(), replaceText("Petroleum Engineer"));
        onView(withId(R.id.currentCompanyID)).perform(clearText(), replaceText("Chevron"));
        onView(withId(R.id.currentCityID)).perform(clearText(), replaceText("Houston"));
        onView(withId(R.id.currentStateID)).perform(clearText(), replaceText("TX"));
        onView(withId(R.id.currentCostID)).perform(clearText(), replaceText("145"));
        onView(withId(R.id.currentCommuteID)).perform(clearText(), replaceText("1.5"));
        onView(withId(R.id.currentSalaryID)).perform(clearText(), replaceText("25oooo"));
        onView(withId(R.id.currentBonusID)).perform(clearText(), replaceText("15000"));
        onView(withId(R.id.currentRetirementID)).perform(clearText(), replaceText("40"));
        onView(withId(R.id.currentLeaveID)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.currentSaveButtonID)).perform(click());
        onView(withId(R.id.currentSalaryID)).check(matches(hasErrorText("Invalid Salary Input")));
    }

    //Test #16: exit without saving of job details of enter current job when canceled
    @Test
    public void Test16() {
        onView(withId(R.id.editCurrentJobButtonID)).perform(click());
        onView(withId(R.id.currentTitleID)).perform(clearText(), replaceText("Petroleum Engineer"));
        onView(withId(R.id.currentCompanyID)).perform(clearText(), replaceText("Chevron"));
        onView(withId(R.id.currentCityID)).perform(clearText(), replaceText("Houston"));
        onView(withId(R.id.currentStateID)).perform(clearText(), replaceText("TX"));
        onView(withId(R.id.currentCostID)).perform(clearText(), replaceText("14"));
        onView(withId(R.id.currentCommuteID)).perform(clearText(), replaceText("1.5"));
        onView(withId(R.id.currentSalaryID)).perform(clearText(), replaceText("250000"));
        onView(withId(R.id.currentBonusID)).perform(clearText(), replaceText("15000"));
        onView(withId(R.id.currentRetirementID)).perform(clearText(), replaceText("40"));
        onView(withId(R.id.currentLeaveID)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.currentCancelButtonID)).perform(click());

        intended(hasComponent(MainMenu.class.getName()));

        onView(withText("Job details saved")).inRoot(new Toastmatcher()).withFailureHandler(new NoToastMessage())
                .check(matches(not(isDisplayed())));
    }

    /* NEXT TEST SET: WRITE SAME TEST FOR JOB OFFERS */

}