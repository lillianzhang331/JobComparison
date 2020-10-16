package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.gatech.seclass.jobcompare6300.model.ComparisonSettingsModel;
import edu.gatech.seclass.jobcompare6300.model.CurrentJob;
import edu.gatech.seclass.jobcompare6300.model.JobCompareDbHelper;
import edu.gatech.seclass.jobcompare6300.model.JobManager;
import edu.gatech.seclass.jobcompare6300.model.JobOffer;

public class RankedJobs extends AppCompatActivity {
    private TableLayout table_layout;
    private static String TAG = "MY VALUES";
    final ArrayList<Integer> checkedJobIdList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranked_jobs);
        MyApplication myApplication = (MyApplication) getApplication();
        JobCompareDbHelper dbHelper = new JobCompareDbHelper(this);
        JobManager job = new JobManager(dbHelper);

        Button rankedMakeComparison = (Button) findViewById(R.id.rankedMakeComparisonButtonID);

        ComparisonSettingsModel settings = new ComparisonSettingsModel(dbHelper);
        Float commuteWt = (float) settings.getCommuteWeight();
        Float salaryWt = (float) settings.getSalaryWeight();
        Float bonusWt = (float) settings.getBonusWeight();
        Float retirementWt = (float) settings.getRetirementbenefitsWeight();
        Float leaveWt = (float) settings.getLeaveWeight();
        Float sumWt = commuteWt + salaryWt + bonusWt + retirementWt + leaveWt;

        Integer numJobs = dbHelper.getJobOfferNumRowIDs();
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
            jobScore = jo.getJobScore();
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


        Cursor jobs = job.getAllJobs();
        int totalJobs = jobs.getCount();
        table_layout = findViewById(R.id.tableLayout);
        BuildTable(jobs);

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
    }
    private void BuildTable(final Cursor jobs){
        jobs.moveToFirst();
        ArrayList<Integer> jobIdList = new ArrayList<Integer>();

        do {
            int colCount = jobs.getColumnCount();
            final TableRow dataRow = new TableRow(this);
            dataRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT);
            Log.v(TAG, "ID:" + jobs.getString(0) + "    JO Score:" +
                    jobs.getString(colCount-1) + "       Title:" + jobs.getString(1));

            int cols = 2;
            final Integer jobId = jobs.getInt(0);
            jobIdList.add(jobId);
            Log.v(TAG,"Job ID List:" + jobIdList.toString());

            for (int j = 1; j < cols+1; j++) {
                if(j==1) {
                    final CheckBox cb = new CheckBox(this);
                    cb.setLayoutParams(params);
                    cb.setGravity(Gravity.CENTER_VERTICAL);
                    cb.setPadding(0, 5, 180, 5);

                    dataRow.addView(cb);
                    cb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (cb.isChecked()) {
                                if (!checkedJobIdList.contains(jobId))
                                    checkedJobIdList.add(jobId);
                            }
                            else
                                checkedJobIdList.remove(jobId);
                            Log.v(TAG, "Checked Job ID List:" + checkedJobIdList.toString());
                        }
                    });
                }
                TextView tv = new TextView(this);
                tv.setLayoutParams(params);
                tv.setGravity(Gravity.CENTER_VERTICAL);
                tv.setTextSize(24);
                tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                tv.setPadding(0, 5, 100, 5);
                if (jobId == 0 && j == 1) {
                    String str = jobs.getString(j) + "(Current)";
                    tv.setText(str);
                }
                else
                    tv.setText(jobs.getString(j));
                dataRow.addView(tv);
            }
            table_layout.addView(dataRow);
        } while (jobs.moveToNext());
    }
}
