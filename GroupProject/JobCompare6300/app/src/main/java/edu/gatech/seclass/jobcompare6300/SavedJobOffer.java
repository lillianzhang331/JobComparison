package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.model.JobCompareDbHelper;
import edu.gatech.seclass.jobcompare6300.model.JobManager;
import edu.gatech.seclass.jobcompare6300.model.JobOffer;

public class SavedJobOffer extends AppCompatActivity {
    private TextView jobTitle;
    private TextView jobCompany;
    private TextView jobCity;
    private TextView jobState;
    private TextView jobCostOfLiving;
    private TextView jobCommute;
    private TextView jobSalary;
    private TextView jobBonus;
    private TextView jobRetirementBenefits;
    private TextView jobLeaveTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_job_offer);

        jobTitle = (TextView) findViewById(R.id.offerTitleID);
        jobCompany = (TextView) findViewById(R.id.offerCompanyID);
        jobCity = (TextView) findViewById(R.id.offerCityID);
        jobState = (TextView) findViewById(R.id.offerStateID);
        jobCostOfLiving = (TextView) findViewById(R.id.offerCostID);
        jobCommute = (TextView) findViewById(R.id.offerCommuteID);
        jobSalary = (TextView) findViewById(R.id.offerSalaryID);
        jobBonus = (TextView) findViewById(R.id.offerBonusID);
        jobRetirementBenefits = (TextView) findViewById(R.id.offerRetirementID);
        jobLeaveTime = (TextView) findViewById(R.id.offerLeaveID);

        JobCompareDbHelper dbHelper = new JobCompareDbHelper(this);
        JobManager job = new JobManager(dbHelper);

        Button compareWithCurrent = (Button) findViewById(R.id.compareCurrentButtonID);
        Button enterAnotherOffer = (Button) findViewById(R.id.enterAnotherOfferButtonID);
        Button returnToMain = (Button) findViewById(R.id.returnToMainButtonID);

        if(!job.isCurrentJobAvailable())
            compareWithCurrent.setEnabled(false);
        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retToMain = new Intent(SavedJobOffer.this, MainMenu.class);
                startActivity(retToMain);
                SavedJobOffer.this.finish();
            }
        });
        enterAnotherOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent enterAnotherOffer = new Intent(SavedJobOffer.this, JobOfferDetails.class);
                startActivity(enterAnotherOffer);
                SavedJobOffer.this.finish();
            }
        });
        compareWithCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent compareWithCurrent = new Intent(SavedJobOffer.this,
                        JobComparison.class);
                Bundle savedOfferValues = new Bundle();
                Log.println(Log.VERBOSE,"jobTitle",jobTitle.getText().toString());
                savedOfferValues.putString("title",jobTitle.getText().toString());
                savedOfferValues.putString("company", jobCompany.getText().toString());
                savedOfferValues.putString("city", jobCity.getText().toString());
                savedOfferValues.putString("state", jobState.getText().toString());
                savedOfferValues.putString("cost", jobCostOfLiving.getText().toString());
                savedOfferValues.putString("commute", jobCommute.getText().toString());
                savedOfferValues.putString("salary", jobSalary.getText().toString());
                savedOfferValues.putString("bonus", jobBonus.getText().toString());
                savedOfferValues.putString("benefits", jobRetirementBenefits.getText().toString());
                savedOfferValues.putString("leave", jobLeaveTime.getText().toString());
                savedOfferValues.putString("activity","savedjoboffer");
                compareWithCurrent.putExtras(savedOfferValues);
                startActivity(compareWithCurrent);
                SavedJobOffer.this.finish();
            }
        });



        JobOffer jo = job.getLastJobOffer();
        jobTitle.setText(jo.getTitle());
        jobCompany.setText(jo.getCompany());
        jobCity.setText(jo.getCity());
        jobState.setText(jo.getState());
        jobCostOfLiving.setText(jo.getCostOfLiving().toString());
        jobCommute.setText(jo.getCommute().toString());
        jobSalary.setText(jo.getSalary().toString());
        jobBonus.setText(jo.getBonus().toString());
        jobRetirementBenefits.setText(jo.getRetirementBenefits().toString());
        jobLeaveTime.setText(jo.getLeaveTime().toString());

    }
}
