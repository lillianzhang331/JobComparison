package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.model.ComparisonSettingsModel;
import edu.gatech.seclass.jobcompare6300.model.CurrentJob;
import edu.gatech.seclass.jobcompare6300.model.JobCompareDbHelper;
import edu.gatech.seclass.jobcompare6300.model.JobDetails;
import edu.gatech.seclass.jobcompare6300.model.JobManager;

@RunWith(AndroidJUnit4.class)

public class DatabaseTest {
    private JobCompareDbHelper dbHelper;
    private JobManager job;
    private MyApplication myApplication;
    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        dbHelper = new JobCompareDbHelper(context);
        myApplication = new MyApplication();
    }
    @After
    public void closeDb() throws IOException {
        dbHelper.close();
    }

    // Test jobScore calculated with default comparison weights
    @Test
    public void Test1() {
        job = new JobManager(dbHelper);
        CurrentJob currentJob = new CurrentJob(dbHelper);
        //CurrentJobDetails currentJob = new CurrentJobDetails();
        currentJob.setTitle("ML Engineer");
        currentJob.setCompany("Tesla");
        currentJob.setCity("San Francisco");
        currentJob.setState("CA");
        currentJob.setCostOfLiving(241);
        currentJob.setCommute((float) 1.4);
        currentJob.setSalary((float) 240000);
        currentJob.setBonus((float) 16000.);
        currentJob.setRetirementBenefits(50);
        currentJob.setLeaveTime(20);
        currentJob.save();

        ComparisonSettingsModel comparisonSettingsModel = new ComparisonSettingsModel(dbHelper);
        comparisonSettingsModel.setComparisonSettings(1, 1, 1, 1, 1);
        float score = myApplication.calcCurrentJobScore(dbHelper,currentJob,comparisonSettingsModel);
        float expectedJobScore = (float)70492.305;
        Assert.assertEquals(expectedJobScore, score, 0.0000001);
    }
    // Test jobScore calculated with different comparison weights
    @Test
    public void Test2() {
        job = new JobManager(dbHelper);
        CurrentJob currentJob = new CurrentJob(dbHelper);
        //CurrentJobDetails currentJob = new CurrentJobDetails();
        currentJob.setTitle("ML Engineer");
        currentJob.setCompany("Tesla");
        currentJob.setCity("San Francisco");
        currentJob.setState("CA");
        currentJob.setCostOfLiving(241);
        currentJob.setCommute((float) 1.4);
        currentJob.setSalary((float) 240000);
        currentJob.setBonus((float) 16000.);
        currentJob.setRetirementBenefits(50);
        currentJob.setLeaveTime(20);
        currentJob.save();

        ComparisonSettingsModel comparisonSettingsModel = new ComparisonSettingsModel(dbHelper);
        comparisonSettingsModel.setComparisonSettings(1, 3, 2, 4, 1);
        float score = myApplication.calcCurrentJobScore(dbHelper,currentJob,comparisonSettingsModel);
        float expectedJobScore = (float)109860.141;
        Assert.assertEquals(expectedJobScore, score, 0.0000001);
    }


}
