package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.model.CurrentJob;
import edu.gatech.seclass.jobcompare6300.model.JobCompareDbHelper;
import edu.gatech.seclass.jobcompare6300.model.JobManager;
import edu.gatech.seclass.jobcompare6300.model.JobOffer;

public class JobComparison extends AppCompatActivity {
    private TextView job1Title, job2Title;
    private TextView job1Company,job2Company;
    private TextView job1Location, job2Location;
    private TextView job1Commute, job2Commute;
    private TextView job1Salary, job2Salary;
    private TextView job1Bonus, job2Bonus;
    private TextView job1RetirementBenefits, job2RetirementBenefits;
    private TextView job1LeaveTime, job2LeaveTime;
    private MyApplication myApplication;
    private JobCompareDbHelper dbHelper;
    private JobManager job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_comparison);

        job1Title = (TextView) findViewById(R.id.job1title);
        job1Company = (TextView) findViewById(R.id.job1company);
        job1Location = (TextView) findViewById(R.id.job1location);
        job1Commute = (TextView) findViewById(R.id.job1commute);
        job1Salary = (TextView) findViewById(R.id.job1salary);
        job1Bonus = (TextView) findViewById(R.id.job1bonus);
        job1RetirementBenefits = (TextView) findViewById(R.id.job1benefits);
        job1LeaveTime = (TextView) findViewById(R.id.job1leave);

        job2Title = (TextView) findViewById(R.id.job2title);
        job2Company = (TextView) findViewById(R.id.job2company);
        job2Location = (TextView) findViewById(R.id.job2location);
        job2Commute = (TextView) findViewById(R.id.job2commute);
        job2Salary = (TextView) findViewById(R.id.job2salary);
        job2Bonus = (TextView) findViewById(R.id.job2bonus);
        job2RetirementBenefits = (TextView) findViewById(R.id.job2benefits);
        job2LeaveTime = (TextView) findViewById(R.id.job2leave);

        myApplication = (MyApplication)getApplication();
        dbHelper = new JobCompareDbHelper(this);
        job = new JobManager(dbHelper);

        Intent fromIntent = getIntent();
        if(fromIntent.getExtras().getString("activity").equals("savedjoboffer")) {
            CurrentJob cj = job.getCurrentJob();
            job1Title.setText(cj.getTitle());
            job1Company.setText(cj.getCompany());
            String location1 = cj.getCity() + "," + cj.getState();
            job1Location.setText(location1);
            job1Commute.setText(cj.getCommute().toString());
            job1Salary.setText(cj.getSalary().toString());
            job1Bonus.setText(cj.getBonus().toString());
            job1RetirementBenefits.setText(cj.getRetirementBenefits().toString());
            job1LeaveTime.setText(cj.getLeaveTime().toString());
            Bundle job2 = fromIntent.getExtras();
            if (job2 != null) {
                job2Title.setText(job2.getString("title"));
                job2Company.setText(job2.getString("company"));
                String location2 = job2.getString("city") + "," + job2.getString("state");
                job2Location.setText(location2);
                job2Commute.setText(job2.getString("commute"));
                String adjustedSalary = myApplication.adjustedYearlySalary(dbHelper, job2.getString("cost"), job2.getString("salary"));
                job2Salary.setText(adjustedSalary);
                String adjustedBonus = myApplication.adjustedYearlyBonus(dbHelper, job2.getString("cost"), job2.getString("bonus"));
                job2Bonus.setText(adjustedBonus);
                job2RetirementBenefits.setText(job2.getString("benefits"));
                job2LeaveTime.setText(job2.getString("leave"));
            }
        }
        else if(fromIntent.getExtras().getString("activity").equals("rankedjobs")){
            String job1Id = fromIntent.getExtras().getString("job1Id");
            String job2Id = fromIntent.getExtras().getString("job2Id");

            if(Integer.parseInt(job1Id)>0) {
                JobOffer job1;
                job1 = job.getJobOffer(Integer.parseInt(job1Id));
                job1Title.setText(job1.getTitle());
                job1Company.setText(job1.getCompany());
                String location1 = job1.getCity() + "," + job1.getState();
                job1Location.setText(location1);
                job1Commute.setText(job1.getCommute().toString());
                String adjustedSalary = myApplication.adjustedYearlySalary(dbHelper,
                        job1.getCostOfLiving().toString(), job1.getSalary().toString());
                job1Salary.setText(adjustedSalary);
                String adjustedBonus = myApplication.adjustedYearlyBonus(dbHelper,
                        job1.getCostOfLiving().toString(), job1.getBonus().toString());
                job1Bonus.setText(adjustedBonus);
                job1RetirementBenefits.setText(job1.getRetirementBenefits().toString());
                job1LeaveTime.setText(job1.getLeaveTime().toString());
            }
            else {
                CurrentJob job1;
                job1 = job.getCurrentJob();
                job1Title.setText(job1.getTitle());
                job1Company.setText(job1.getCompany());
                String location1 = job1.getCity() + "," + job1.getState();
                job1Location.setText(location1);
                job1Commute.setText(job1.getCommute().toString());
                job1Salary.setText(job1.getSalary().toString());
                job1Bonus.setText(job1.getBonus().toString());
                job1RetirementBenefits.setText(job1.getRetirementBenefits().toString());
                job1LeaveTime.setText(job1.getLeaveTime().toString());
            }

            if(Integer.parseInt(job2Id)>0) {
                JobOffer job2 = job.getJobOffer(Integer.parseInt(job2Id));
                job2Title.setText(job2.getTitle());
                job2Company.setText(job2.getCompany());
                String location2 = job2.getCity() + "," + job2.getState();
                job2Location.setText(location2);
                job2Commute.setText(job2.getCommute().toString());
                String adjustedSalary = myApplication.adjustedYearlySalary(dbHelper,
                        job2.getCostOfLiving().toString(), job2.getSalary().toString());
                job2Salary.setText(adjustedSalary);
                String adjustedBonus = myApplication.adjustedYearlyBonus(dbHelper,
                        job2.getCostOfLiving().toString(), job2.getBonus().toString());
                job2Bonus.setText(adjustedBonus);
                job2RetirementBenefits.setText(job2.getRetirementBenefits().toString());
                job2LeaveTime.setText(job2.getLeaveTime().toString());
            }
            else if (Integer.parseInt(job2Id)==0){
                CurrentJob job2 = job.getCurrentJob();
                job2Title.setText(job2.getTitle());
                job2Company.setText(job2.getCompany());
                String location2 = job2.getCity() + "," + job2.getState();
                job2Location.setText(location2);
                job2Commute.setText(job2.getCommute().toString());
                job2Salary.setText(job2.getSalary().toString());
                job2Bonus.setText(job2.getBonus().toString());
                job2RetirementBenefits.setText(job2.getRetirementBenefits().toString());
                job2LeaveTime.setText(job2.getLeaveTime().toString());
            }
        }
        Button returnToMain = (Button) findViewById(R.id.returnToMainMenuButtonID);
        Button compareAnother = (Button) findViewById(R.id.makeAnotherComparisonButtonID);
        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retToMain = new Intent(JobComparison.this, MainMenu.class);
                startActivity(retToMain);
            }
        });
        compareAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToRankedJobs = new Intent(JobComparison.this, RankedJobs.class);
                startActivity(goToRankedJobs);
                JobComparison.this.finish();
            }
        });
    }
}
