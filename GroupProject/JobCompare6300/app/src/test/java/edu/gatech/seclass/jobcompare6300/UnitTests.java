package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.gatech.seclass.jobcompare6300.model.ComparisonSettingsModel;
import edu.gatech.seclass.jobcompare6300.model.CurrentJob;
import edu.gatech.seclass.jobcompare6300.model.JobCompareDbHelper;
import edu.gatech.seclass.jobcompare6300.model.JobDetails;
import edu.gatech.seclass.jobcompare6300.model.JobManager;
import edu.gatech.seclass.jobcompare6300.model.JobOffer;

import androidx.appcompat.app.AppCompatActivity;

public class UnitTests extends AppCompatActivity {

    //private MyApplication myApplication;
    private JobCompareDbHelper dbHelper;
    //private JobManager job;
    //private CurrentJob currentJob;
    //private ComparisonSettingsModel comparisonSettingsModel;

    //Test job score calculated for current job details from default weights
    @Test
    public void test1() {
        CurrentJob currentJob = new CurrentJob(dbHelper);
        RankedJobs rankedJobs = new RankedJobs();
        //CurrentJobDetails currentJob = new CurrentJobDetails();
        JobManager job = new JobManager(dbHelper);
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

        ComparisonSettingsModel comparisonSettingsModel = new ComparisonSettingsModel(dbHelper);
        comparisonSettingsModel.setComparisonSettings(1,1,1, 1,1);

        double expectedJobScore = 1016137.887;
        double score = rankedJobs.calcCurrentJobScore(currentJob);
        Assert.assertEquals(expectedJobScore, score, 0.0000001);
    }


}
