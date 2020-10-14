package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JobComparison extends AppCompatActivity {
    private TextView job1Title, job2Title;
    private TextView job1Company,job2Company;
    private TextView job1Location, job2Location;
    private TextView job1CostOfLiving, job2CostOfLiving;
    private TextView job1Commute, job2Commute;
    private TextView job1Salary, job2Salary;
    private TextView job1Bonus, job2Bonus;
    private TextView job1RetirementBenefits, job2RetirementBenefits;
    private TextView job1LeaveTime, job2LeaveTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_comparison);

        job1Title = (TextView) findViewById(R.id.job1title);
        job1Company = (TextView) findViewById(R.id.job1company);
        job1Location = (TextView) findViewById(R.id.job1location);
        job1CostOfLiving = (TextView) findViewById(R.id.job1cost);
        job1Commute = (TextView) findViewById(R.id.job1commute);
        job1Salary = (TextView) findViewById(R.id.job1salary);
        job1Bonus = (TextView) findViewById(R.id.job1bonus);
        job1RetirementBenefits = (TextView) findViewById(R.id.job1benefits);
        job1LeaveTime = (TextView) findViewById(R.id.job1leave);

        job2Title = (TextView) findViewById(R.id.job2title);
        job2Company = (TextView) findViewById(R.id.job2company);
        job2Location = (TextView) findViewById(R.id.job2location);
        job2CostOfLiving = (TextView) findViewById(R.id.job2cost);
        job2Commute = (TextView) findViewById(R.id.job2commute);
        job2Salary = (TextView) findViewById(R.id.job2salary);
        job2Bonus = (TextView) findViewById(R.id.job2bonus);
        job2RetirementBenefits = (TextView) findViewById(R.id.job2benefits);
        job2LeaveTime = (TextView) findViewById(R.id.job2leave);


        Intent savedOfferValues = getIntent();
        Bundle job1 = savedOfferValues.getExtras();
        if (job1 != null) {
            job1Title.setText(job1.getString("title"));
            job1Company.setText(job1.getString("company"));
            job1Location.setText(job1.getString("city"));
            job1Commute.setText(job1.getString("commute"));
            job1Salary.setText(job1.getString("salary"));
            job1Bonus.setText(job1.getString("bonus"));
            job1RetirementBenefits.setText(job1.getString("benefits"));
            job1LeaveTime.setText(job1.getString("leave"));

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
