package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.model.CurrentJob;
import edu.gatech.seclass.jobcompare6300.model.JobCompareDbHelper;
import edu.gatech.seclass.jobcompare6300.model.JobManager;

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
    private JobCompareDbHelper dbHelper;
    private JobManager job;

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

        dbHelper = new JobCompareDbHelper(this);
        job = new JobManager(dbHelper);

        if(job.isCurrentJobAvailable())
        {
            CurrentJob cj = job.getCurrentJob();
            jobTitle.setText(cj.getTitle());
            jobCompany.setText(cj.getCompany());
            jobCity.setText(cj.getCity());
            jobState.setText(cj.getState());
            jobCostOfLiving.setText(cj.getCostOfLiving().toString());
            jobCommute.setText(cj.getCommute().toString());
            jobSalary.setText(cj.getSalary().toString());
            jobBonus.setText(cj.getBonus().toString());
            jobRetirementBenefits.setText(cj.getRetirementBenefits().toString());
            jobLeaveTime.setText(cj.getLeaveTime().toString());
        }
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
        boolean isCostNumber = isInteger(inputCostOfLiving);
        boolean isCommuteNumber = isFloat(inputCommute);
        boolean isSalaryNumber = isFloat(inputSalary);
        boolean isBonusNumber = isFloat(inputBonus);
        boolean isBenefitsNumber = isInteger(inputRetirementBenefits);
        boolean isLeaveNumber = isInteger(inputLeaveTime);

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

        if(!isCostNumber){jobCostOfLiving.setError("Invalid Cost of Living Index Input");}
        if(!isCommuteNumber){jobCommute.setError("Invalid Commute Input");}
        if(!isSalaryNumber){jobSalary.setError("Invalid Salary Input");}
        if(!isBonusNumber){jobBonus.setError("Invalid Bonus Input");}
        if(!isBenefitsNumber){jobRetirementBenefits.setError("Invalid Benefits Input");}
        if(!isLeaveNumber){jobLeaveTime.setError("Invalid Leave Time Input");}

        if(validTitle && validCompany && validCity && validState && validCostOfLiving
                && validCommute && validSalary && validBonus && validRetirementBenefits
                && validLeaveTime && isCityAllAlphabet && isStateAllAlphabet && isCostNumber
                && isCommuteNumber && isSalaryNumber && isBonusNumber && isBenefitsNumber
                && isLeaveNumber){
            job.enterCurrentJob(inputTitle, inputCompany, inputCity, inputState,
                    Integer.parseInt(inputCostOfLiving), Float.parseFloat(inputCommute),
                    Float.parseFloat(inputSalary), Float.parseFloat(inputBonus),
                    Integer.parseInt(inputRetirementBenefits), Integer.parseInt(inputLeaveTime));

            Intent retToMain = new Intent(CurrentJobDetails.this, MainMenu.class);
            startActivity(retToMain);
            CurrentJobDetails.this.finish();
        }
    }

    // To check if all characters in string are alphabets
    public boolean isAlpha(String name) {
        return name.matches("^[a-zA-Z\\s\\-\']*$");
    }

    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
