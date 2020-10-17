package edu.gatech.seclass.jobcompare6300;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.gatech.seclass.jobcompare6300.model.CurrentJob;
import edu.gatech.seclass.jobcompare6300.model.JobCompareDbHelper;
import edu.gatech.seclass.jobcompare6300.model.JobDetails;
import edu.gatech.seclass.jobcompare6300.model.JobManager;
import edu.gatech.seclass.jobcompare6300.model.JobOffer;

public class UnitTests {

    //Test job score calculated for current job details from default weights
    @Test
    public void test1() {
        // INCOMPLETE
        // INCOMPLETE


        RankedJobs rankedJobs = new RankedJobs();
        CurrentJobDetails currentJob = new CurrentJobDetails();
        currentJob.setTitle("ML Engineer");
        currentJob.setCompany("Tesla");
        currentJob.setCity("San Francisco");
        currentJob.setState("CA");
        currentJob.setCostOfLiving(241);
        currentJob.setCommute(1.4);
        currentJob.setSalary(240000);
        currentJob.setBonus(16000);
        currentJob.setRetirementBenefits(50);
        currentJob.setLeaveTime(20);


        ComparisonSettings comparisonSettings = new ComparisonSettings();
        comparisonSettings.setCommuteWeight(1);
        comparisonSettings.setSalaryWeight(1);
        comparisonSettings.setBonusWeight(1);
        comparisonSettings.setRetirementbenefitsWeight(1);
        comparisonSettings.setLeaveWeight(1);

        double expectedJobScore = 111111.1; //NOT YET CALCULATED (placeholder)
        double score = rankedJobs.getJobScore(currentJob, comparisonSettings); //NO jobScore method yet
        Assert.assertEquals(expectedJobScore, score, 0.0000001);
    }


}
