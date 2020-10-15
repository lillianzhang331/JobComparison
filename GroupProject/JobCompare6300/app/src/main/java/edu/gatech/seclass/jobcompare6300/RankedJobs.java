package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.model.CurrentJob;
import edu.gatech.seclass.jobcompare6300.model.JobCompareDbHelper;
import edu.gatech.seclass.jobcompare6300.model.JobManager;
import edu.gatech.seclass.jobcompare6300.model.JobOffer;

public class RankedJobs extends AppCompatActivity {
    private MyApplication myApplication;
    private JobCompareDbHelper dbHelper;
    private JobManager job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranked_jobs);

        Button rankedMakeComparison = (Button) findViewById(R.id.rankedMakeComparisonButtonID);
        rankedMakeComparison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent makeComparison = new Intent(RankedJobs.this, JobComparison.class);
                Bundle rankedJobsValues = new Bundle();
                rankedJobsValues.putString("activity","rankedjobs");
                makeComparison.putExtras(rankedJobsValues);
                startActivity(makeComparison);
                RankedJobs.this.finish();
            }
        });

        myApplication = (MyApplication)getApplication();
        dbHelper = new JobCompareDbHelper(this);
        job = new JobManager(dbHelper);

        Integer numJobs = dbHelper.getJobOfferNumRowIDs();
        Float commuteWt = (float) myApplication.getCommuteWeight();
        Float salaryWt = (float) myApplication.getSalaryWeight();
        Float bonusWt = (float) myApplication.getBonusWeight();
        Float retirementWt = (float) myApplication.getRetirementbenefitsWeight();
        Float leaveWt = (float) myApplication.getLeaveWeight();
        Float sumWt = commuteWt + salaryWt + bonusWt + retirementWt + leaveWt;

        for (int row = 1; row <= numJobs; row++) {
            JobOffer jo = job.getJobOffer(row);

            Float adjustedSalary = Float.parseFloat(myApplication.adjustedYearlySalary(dbHelper, jo.getCostOfLiving().toString(), jo.getSalary().toString()));
            Float adjustedBonus = Float.parseFloat(myApplication.adjustedYearlyBonus(dbHelper, jo.getCostOfLiving().toString(), jo.getBonus().toString()));
            Float a = (salaryWt/sumWt)*adjustedSalary;
            Float b = (bonusWt/sumWt)*adjustedBonus;
            Float c = (retirementWt/sumWt)*(Float.parseFloat(jo.getRetirementBenefits().toString())*adjustedSalary/100);
            Float d = (leaveWt/sumWt)*(Float.parseFloat(jo.getLeaveTime().toString())*adjustedSalary/260);
            Float e = (commuteWt/sumWt)*(Float.parseFloat(jo.getCommute().toString())*adjustedSalary/8);
            Float jobScore = a + b + c + d - e;

            dbHelper.updateJobOfferScore(row, jobScore);
        }
        CurrentJob cj = job.getCurrentJob();
        Float adjustedSalary = Float.parseFloat(myApplication.adjustedYearlySalary(dbHelper, cj.getCostOfLiving().toString(), cj.getSalary().toString()));
        Float adjustedBonus = Float.parseFloat(myApplication.adjustedYearlyBonus(dbHelper, cj.getCostOfLiving().toString(), cj.getBonus().toString()));
        Float a = (salaryWt/sumWt)*adjustedSalary;
        Float b = (bonusWt/sumWt)*adjustedBonus;
        Float c = (retirementWt/sumWt)*(Float.parseFloat(cj.getRetirementBenefits().toString())*adjustedSalary/100);
        Float d = (leaveWt/sumWt)*(Float.parseFloat(cj.getLeaveTime().toString())*adjustedSalary/260);
        Float e = (commuteWt/sumWt)*(Float.parseFloat(cj.getCommute().toString())*adjustedSalary/8);
        Float jobScore = a + b + c + d - e;
        dbHelper.updateCurrentJobScore(jobScore);
    }
}
