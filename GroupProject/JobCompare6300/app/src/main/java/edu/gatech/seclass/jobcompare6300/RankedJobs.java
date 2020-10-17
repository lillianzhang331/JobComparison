package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.model.CurrentJob;
import edu.gatech.seclass.jobcompare6300.model.JobCompareDbHelper;
import edu.gatech.seclass.jobcompare6300.model.JobManager;
import edu.gatech.seclass.jobcompare6300.model.JobOffer;

public class RankedJobs extends AppCompatActivity {
    private TableLayout table_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranked_jobs);
        MyApplication myApplication = (MyApplication) getApplication();
        JobCompareDbHelper dbHelper = new JobCompareDbHelper(this);
        JobManager job = new JobManager(dbHelper);

        Cursor jobs = dbHelper.getAllJobs();
        table_layout = findViewById(R.id.tableLayout);
        BuildTable(jobs);

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

        Integer numJobs = dbHelper.getJobOfferNumRowIDs();
        for (int row = 1; row <= numJobs; row++) {
            JobOffer jo = job.getJobOffer(row);
            Float jobScore = calcJobOfferScore(jo);
            dbHelper.updateJobOfferScore(row, jobScore);
        }
        CurrentJob cj = job.getCurrentJob();
        Float jobScore = calcCurrentJobScore(cj);
        dbHelper.updateCurrentJobScore(jobScore);
    }

    public float calcJobOfferScore(JobOffer cj){
        MyApplication myApplication = (MyApplication) getApplication();
        JobCompareDbHelper dbHelper = new JobCompareDbHelper(this);
        JobManager job = new JobManager(dbHelper);
        Float commuteWt = (float) myApplication.getCommuteWeight();
        Float salaryWt = (float) myApplication.getSalaryWeight();
        Float bonusWt = (float) myApplication.getBonusWeight();
        Float retirementWt = (float) myApplication.getRetirementbenefitsWeight();
        Float leaveWt = (float) myApplication.getLeaveWeight();
        Float sumWt = commuteWt + salaryWt + bonusWt + retirementWt + leaveWt;
        Float adjustedSalary = Float.parseFloat(myApplication.adjustedYearlySalary(dbHelper, cj.getCostOfLiving().toString(), cj.getSalary().toString()));
        Float adjustedBonus = Float.parseFloat(myApplication.adjustedYearlyBonus(dbHelper, cj.getCostOfLiving().toString(), cj.getBonus().toString()));
        Float a = (salaryWt/sumWt)*adjustedSalary;
        Float b = (bonusWt/sumWt)*adjustedBonus;
        Float c = (retirementWt/sumWt)*(Float.parseFloat(cj.getRetirementBenefits().toString())*adjustedSalary/100);
        Float d = (leaveWt/sumWt)*(Float.parseFloat(cj.getLeaveTime().toString())*adjustedSalary/260);
        Float e = (commuteWt/sumWt)*(Float.parseFloat(cj.getCommute().toString())*adjustedSalary/8);
        Float jobScore = a + b + c + d - e;
        return jobScore;
    }
    public float calcCurrentJobScore(CurrentJob cj){
        MyApplication myApplication = (MyApplication) getApplication();
        JobCompareDbHelper dbHelper = new JobCompareDbHelper(this);
        JobManager job = new JobManager(dbHelper);
        Float commuteWt = (float) myApplication.getCommuteWeight();
        Float salaryWt = (float) myApplication.getSalaryWeight();
        Float bonusWt = (float) myApplication.getBonusWeight();
        Float retirementWt = (float) myApplication.getRetirementbenefitsWeight();
        Float leaveWt = (float) myApplication.getLeaveWeight();
        Float sumWt = commuteWt + salaryWt + bonusWt + retirementWt + leaveWt;
        Float adjustedSalary = Float.parseFloat(myApplication.adjustedYearlySalary(dbHelper, cj.getCostOfLiving().toString(), cj.getSalary().toString()));
        Float adjustedBonus = Float.parseFloat(myApplication.adjustedYearlyBonus(dbHelper, cj.getCostOfLiving().toString(), cj.getBonus().toString()));
        Float a = (salaryWt/sumWt)*adjustedSalary;
        Float b = (bonusWt/sumWt)*adjustedBonus;
        Float c = (retirementWt/sumWt)*(Float.parseFloat(cj.getRetirementBenefits().toString())*adjustedSalary/100);
        Float d = (leaveWt/sumWt)*(Float.parseFloat(cj.getLeaveTime().toString())*adjustedSalary/260);
        Float e = (commuteWt/sumWt)*(Float.parseFloat(cj.getCommute().toString())*adjustedSalary/8);
        Float jobScore = a + b + c + d - e;
        return jobScore;
    }


    private void BuildTable(Cursor jobs){
        jobs.moveToFirst();
        do {
            TableRow dataRow = new TableRow(this);
            dataRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT);
            int cols = 2;
            int colCount = jobs.getColumnCount();

            for (int j = 1; j < cols+1; j++) {
                if(j==1) {
                    CheckBox cb = new CheckBox(this);
                    cb.setLayoutParams(params);
                    cb.setGravity(Gravity.CENTER_VERTICAL);
                    cb.setPadding(0, 5, 200, 5);

                    dataRow.addView(cb);
                    cb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
                }
                TextView tv = new TextView(this);
                tv.setLayoutParams(params);
                tv.setGravity(Gravity.CENTER_VERTICAL);
                tv.setTextSize(24);
                tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                tv.setPadding(0, 5, 300, 5);
                tv.setText(jobs.getString(j));
                String title_company = jobs.getString(1) + jobs.getString(2);
                String score = jobs.getString(colCount-1);
                dataRow.addView(tv);
            }
            table_layout.addView(dataRow);
        } while (jobs.moveToNext());
    }
}
