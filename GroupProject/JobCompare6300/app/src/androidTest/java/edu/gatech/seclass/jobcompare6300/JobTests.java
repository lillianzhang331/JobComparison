package edu.gatech.seclass.jobcompare6300;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.intent.Intents;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.ArrayList;
import java.util.List;

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
        onView(withId(R.id.currentCostID)).perform(clearText(), replaceText("145145 "));
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

    //Test #17: saving job details in enter job offer with all fields filled with valid data
    @Test
    public void Test17() {
        onView(withId(R.id.enterJobOfferButtonID)).perform(click());
        onView(withId(R.id.offerTitleID)).perform(clearText(), replaceText("Software Engineer"));
        onView(withId(R.id.offerCompanyID)).perform(clearText(), replaceText("Virtual Oil"));
        onView(withId(R.id.offerCityID)).perform(clearText(), replaceText("San Jose"));
        onView(withId(R.id.offerStateID)).perform(clearText(), replaceText("CA"));
        onView(withId(R.id.offerCostID)).perform(clearText(), replaceText("201"));
        onView(withId(R.id.offerCommuteID)).perform(clearText(), replaceText("1.1"));
        onView(withId(R.id.offerSalaryID)).perform(clearText(), replaceText("150000"));
        onView(withId(R.id.offerBonusID)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.offerRetirementID)).perform(clearText(), replaceText("30"));
        onView(withId(R.id.offerLeaveID)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.offerSaveButtonID)).perform(click());
        onView(withText("Job details saved")).inRoot(new Toastmatcher()).check(matches((isDisplayed())));
    }

    //Test #18: saving job details in enter job offer with fields partially filled with valid data
    @Test
    public void Test18() {
        onView(withId(R.id.enterJobOfferButtonID)).perform(click());
        onView(withId(R.id.offerTitleID)).perform(clearText(), replaceText("Software Engineer"));
        onView(withId(R.id.offerCompanyID)).perform(clearText(), replaceText("Virtual Oil"));
        onView(withId(R.id.offerCityID)).perform(clearText(), replaceText("San Jose"));
        onView(withId(R.id.offerStateID)).perform(clearText(), replaceText("CA"));
        onView(withId(R.id.offerCostID)).perform(clearText(), replaceText("201"));
        //onView(withId(R.id.offerCommuteID)).perform(clearText(), replaceText("1.1"));
        onView(withId(R.id.offerSalaryID)).perform(clearText(), replaceText("150000"));
        onView(withId(R.id.offerBonusID)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.offerRetirementID)).perform(clearText(), replaceText("30"));
        onView(withId(R.id.offerLeaveID)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.offerSaveButtonID)).perform(click());
        onView(withId(R.id.offerCommuteID)).check(matches(hasErrorText("No Commute Input")));
    }

    //Test #19a: saving invalid data entry in enter job offer - number in City string
    @Test
    public void Test19a() {
        onView(withId(R.id.enterJobOfferButtonID)).perform(click());
        onView(withId(R.id.offerTitleID)).perform(clearText(), replaceText("Software Engineer"));
        onView(withId(R.id.offerCompanyID)).perform(clearText(), replaceText("Virtual Oil"));
        onView(withId(R.id.offerCityID)).perform(clearText(), replaceText("San J0s3"));
        onView(withId(R.id.offerStateID)).perform(clearText(), replaceText("CA"));
        onView(withId(R.id.offerCostID)).perform(clearText(), replaceText("201"));
        onView(withId(R.id.offerCommuteID)).perform(clearText(), replaceText("1.1"));
        onView(withId(R.id.offerSalaryID)).perform(clearText(), replaceText("150000"));
        onView(withId(R.id.offerBonusID)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.offerRetirementID)).perform(clearText(), replaceText("30"));
        onView(withId(R.id.offerLeaveID)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.offerSaveButtonID)).perform(click());
        onView(withId(R.id.offerCityID)).check(matches(hasErrorText("Invalid City Input")));
    }

    //Test #19b: saving invalid data entry in enter job offer - alphabet in salary
    @Test
    public void Test19b() {
        onView(withId(R.id.enterJobOfferButtonID)).perform(click());
        onView(withId(R.id.offerTitleID)).perform(clearText(), replaceText("Software Engineer"));
        onView(withId(R.id.offerCompanyID)).perform(clearText(), replaceText("Virtual Oil"));
        onView(withId(R.id.offerCityID)).perform(clearText(), replaceText("San Jose"));
        onView(withId(R.id.offerStateID)).perform(clearText(), replaceText("CA"));
        onView(withId(R.id.offerCostID)).perform(clearText(), replaceText("201"));
        onView(withId(R.id.offerCommuteID)).perform(clearText(), replaceText("1.1"));
        onView(withId(R.id.offerSalaryID)).perform(clearText(), replaceText("150000"));
        onView(withId(R.id.offerBonusID)).perform(clearText(), replaceText("10ooo"));
        onView(withId(R.id.offerRetirementID)).perform(clearText(), replaceText("30"));
        onView(withId(R.id.offerLeaveID)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.offerSaveButtonID)).perform(click());
        onView(withId(R.id.offerBonusID)).check(matches(hasErrorText("Invalid Bonus Input")));
    }

    //Test #20: exit without saving of job details of enter job offer when canceled
    @Test
    public void Test20() {
        onView(withId(R.id.enterJobOfferButtonID)).perform(click());
        onView(withId(R.id.offerTitleID)).perform(clearText(), replaceText("Software Engineer"));
        onView(withId(R.id.offerCompanyID)).perform(clearText(), replaceText("Virtual Oil"));
        onView(withId(R.id.offerCityID)).perform(clearText(), replaceText("San Jose"));
        onView(withId(R.id.offerStateID)).perform(clearText(), replaceText("CA"));
        onView(withId(R.id.offerCostID)).perform(clearText(), replaceText("201"));
        onView(withId(R.id.offerCommuteID)).perform(clearText(), replaceText("1.1"));
        onView(withId(R.id.offerSalaryID)).perform(clearText(), replaceText("150000"));
        onView(withId(R.id.offerBonusID)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.offerRetirementID)).perform(clearText(), replaceText("30"));
        onView(withId(R.id.offerLeaveID)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.offerCancelButtonID)).perform(click());

        intended(hasComponent(MainMenu.class.getName()));

        //onView(withText("Job details saved")).inRoot(new Toastmatcher()).withFailureHandler(new NoToastMessage())
        //        .check(matches(not(isDisplayed())));
    }
    @Test
    public void Test4() {
        InstrumentationRegistry.getInstrumentation().getTargetContext().deleteDatabase("JobCompare.db");
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
        onView(withId(R.id.compareJobsButtonID)).perform(click());
        onView(withText("Not enough Job Offers\nAdd jobs using ENTER JOB OFFER"))
                .inRoot(new Toastmatcher()).check(matches((isDisplayed())));
    }
    @Test
    public void Test5() {
        InstrumentationRegistry.getInstrumentation().getTargetContext().deleteDatabase("JobCompare.db");
        onView(withId(R.id.compareJobsButtonID)).perform(click());
        onView(withText("At least 1 Job Offer and Current. Job are required to Compare"))
                .inRoot(new Toastmatcher()).check(matches((isDisplayed())));
    }
    /*@Test
    public void Test6() {
        InstrumentationRegistry.getInstrumentation().getTargetContext().deleteDatabase("JobCompare.db");
        onView(withId(R.id.enterJobOfferButtonID)).perform(click());
        onView(withId(R.id.offerTitleID)).perform(clearText(), replaceText("Software Engineer"));
        onView(withId(R.id.offerCompanyID)).perform(clearText(), replaceText("Virtual Oil"));
        onView(withId(R.id.offerCityID)).perform(clearText(), replaceText("San Jose"));
        onView(withId(R.id.offerStateID)).perform(clearText(), replaceText("CA"));
        onView(withId(R.id.offerCostID)).perform(clearText(), replaceText("201"));
        onView(withId(R.id.offerCommuteID)).perform(clearText(), replaceText("1.1"));
        onView(withId(R.id.offerSalaryID)).perform(clearText(), replaceText("150000"));
        onView(withId(R.id.offerBonusID)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.offerRetirementID)).perform(clearText(), replaceText("30"));
        onView(withId(R.id.offerLeaveID)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.offerSaveButtonID)).perform(click());
        onView(withId(R.id.enterAnotherOfferButtonID)).perform(click());
        onView(withId(R.id.offerTitleID)).perform(clearText(), replaceText("Hardware Engineer"));
        onView(withId(R.id.offerCompanyID)).perform(clearText(), replaceText("Virtual Oil"));
        onView(withId(R.id.offerCityID)).perform(clearText(), replaceText("San Jose"));
        onView(withId(R.id.offerStateID)).perform(clearText(), replaceText("CA"));
        onView(withId(R.id.offerCostID)).perform(clearText(), replaceText("201"));
        onView(withId(R.id.offerCommuteID)).perform(clearText(), replaceText("1.1"));
        onView(withId(R.id.offerSalaryID)).perform(clearText(), replaceText("150000"));
        onView(withId(R.id.offerBonusID)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.offerRetirementID)).perform(clearText(), replaceText("30"));
        onView(withId(R.id.offerLeaveID)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.offerSaveButtonID)).perform(click());
        onView(withId(R.id.returnToMainButtonID)).perform(click());
        onView(withId(R.id.compareJobsButtonID)).perform(click());
        MyApplication myApplication = new MyApplication();
        int [] actuals = convertIntegers(myApplication.getJobIdList());
        int[] expecteds = new int[]{1,2,3,4,0};
        Assert.assertArrayEquals(expecteds,actuals);
    }
    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }*/
}