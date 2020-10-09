package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentJobDetails extends AppCompatActivity {
    private EditText jobTitle;
    private EditText jobCompany;
    private EditText jobCity;
    private EditText jobState;
    private EditText jobCostOfLiving;
    private EditText jobCommute;
    private EditText jobSalary;
    private EditText jobBonus;
    private EditText jobRetirementBenefits;
    private EditText jobLeaveTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_job_details);

        jobTitle = (EditText) findViewById(R.id.currentTitleID);
        jobCompany = (EditText) findViewById(R.id.currentCompanyID);
        jobCity = (EditText) findViewById(R.id.currentCityID);
        jobState = (EditText) findViewById(R.id.currentStateID);
        jobCostOfLiving = (EditText) findViewById(R.id.currentCostID);
        jobCommute = (EditText) findViewById(R.id.currentCommuteID);
        jobSalary = (EditText) findViewById(R.id.currentSalaryID);
        jobBonus = (EditText) findViewById(R.id.currentBonusID);
        jobRetirementBenefits = (EditText) findViewById(R.id.currentRetirementID);
        jobLeaveTime = (EditText) findViewById(R.id.currentLeaveID);

        //Button currentSave = (Button) findViewById(R.id.currentSaveButtonID);
        Button currentCancel = (Button) findViewById(R.id.currentCancelButtonID);
        currentCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retToMain = new Intent(CurrentJobDetails.this, MainMenu.class);
                startActivity(retToMain);
                CurrentJobDetails.this.finish();
            }
        });
    }
    public void handleClickSaveCurrent (View view){
        String inputTitle = jobTitle.getText().toString();
        String inputCompany = jobCompany.getText().toString();
        String inputCity = jobCity.getText().toString();
        String inputState = jobState.getText().toString();
        String inputCostOfLiving = jobCostOfLiving.getText().toString();
        String inputCommute = jobCommute.getText().toString();
        String inputSalary = jobSalary.getText().toString();
        String inputBonus = jobBonus.getText().toString();
        String inputRetirementBenefits = jobRetirementBenefits.getText().toString();
        String inputLeaveTime = jobLeaveTime.getText().toString();

        boolean validTitle = inputTitle.length() > 0;
        boolean validCompany = inputCompany.length() > 0;
        boolean validCity = inputCity.length() > 0;
        boolean validState = inputState.length() > 0;
        boolean validCostOfLiving = inputCostOfLiving.length() > 0;
        boolean validCommute = inputCommute.length() > 0;
        boolean validSalary = inputSalary.length() > 0;
        boolean validBonus = inputBonus.length() > 0;
        boolean validRetirementBenefits = inputRetirementBenefits.length() > 0;
        boolean validLeaveTime = inputLeaveTime.length() > 0;

        boolean isCityAllAlphabet = isAlpha(inputCity);
        boolean isStateAllAlphabet = isAlpha(inputState);

        if (!validTitle){jobTitle.setError("No Title Input");}
        if (!validCompany){jobCompany.setError("No Company Input");}
        if (!validCity){jobCity.setError("No City Input");}
        if (!validState){jobState.setError("No State Input");}
        if (!validCostOfLiving){jobCostOfLiving.setError("No Cost of Living Index Input");}
        if (!validCommute){jobCommute.setError("No Commute Input");}
        if (!validSalary){jobSalary.setError("No Salary Input");}
        if (!validBonus){jobBonus.setError("No Bonus Input");}
        if (!validRetirementBenefits){jobRetirementBenefits.setError("No Retirement Benefits Input");}
        if (!validLeaveTime){jobLeaveTime.setError("No Leave Time Input");}
        if (!isCityAllAlphabet){jobCity.setError("Invalid City Input");}
        if (!isStateAllAlphabet){jobState.setError("Invalid State Input");}

    }

    // To check if all characters in string are alphabets
    public boolean isAlpha(String name) {
        return name.matches("^[a-zA-Z\\s\\-\']*$");
    }

}
