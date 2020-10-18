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
import android.widget.Toast;

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
    private ArrayList<Integer> checkedJobIdList = new ArrayList<Integer>();
    private Integer checkboxCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranked_jobs);
        MyApplication myApplication = (MyApplication) getApplication();
        JobCompareDbHelper dbHelper = new JobCompareDbHelper(this);
        JobManager job = new JobManager(dbHelper);

        Button rankedMakeComparison = (Button) findViewById(R.id.rankedMakeComparisonButtonID);

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
        ArrayList<Integer> jobIdList = new ArrayList<Integer>();

        do {
            int colCount = jobs.getColumnCount();
            final TableRow dataRow = new TableRow(this);
            dataRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                    );
            Log.v(TAG, "ID:" + jobs.getString(0) + " JO Score:" +
                    jobs.getString(colCount-1) + " Title:" + jobs.getString(1) +
                    " Col:" + jobs.getString(5) + " Com:" + jobs.getString(6)+ " Sal:" +
                    jobs.getString(7) + " Bon:" + jobs.getString(8) + " Ben:" +
                    jobs.getString(9) + "% Leave:" + jobs.getString(10));

            int cols = 2;
            final Integer jobId = jobs.getInt(0);
            for (int j = 1; j < cols+1; j++) {
                if(j==1) {
                    final CheckBox cb = new CheckBox(this);
                    //params.setMargins(0,0,0,0);

                    //cb.setLayoutParams(params);
                    //cb.setGravity(Gravity.CENTER);
                    //cb.setPadding(0, 0, 0, 0);

                    dataRow.addView(cb);
                    cb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (cb.isChecked()) {
                                checkboxCounter+=1;
                                if (!checkedJobIdList.contains(jobId))
                                    checkedJobIdList.add(jobId);
                                if (checkboxCounter > 2) {
                                    cb.toggle();
                                    checkboxCounter -= 1;
                                    checkedJobIdList.remove(jobId);
                                }
                            }
                            else {
                                checkboxCounter -= 1;
                                checkedJobIdList.remove(jobId);
                            }
                            Log.v(TAG, "Checkbox Counter:" + checkboxCounter.toString());
                            Log.v(TAG, "Checked Job ID List:" + checkedJobIdList.toString());
                        }
                    });
                }
                TextView tv = new TextView(this);
                params.setMargins(25,10,0,0);
                if (j==1)
                    tv.setWidth(500);
                tv.setLayoutParams(params);
                //tv.setGravity(Gravity.CENTER);
                tv.setTextSize(24);
                tv.setSingleLine(false);
                tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                //tv.setPadding(0, 0, 0, 0);
                if (jobId == 0 && j == 1) {
                    String str = jobs.getString(j) + " (Current)";
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
