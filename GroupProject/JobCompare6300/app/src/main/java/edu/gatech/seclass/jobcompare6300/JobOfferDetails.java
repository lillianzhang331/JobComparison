package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.model.JobCompareDbHelper;
import edu.gatech.seclass.jobcompare6300.model.JobManager;

public class JobOfferDetails extends AppCompatActivity {
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
        setContentView(R.layout.job_offer_details);

        jobTitle = (EditText) findViewById(R.id.offerTitleID);
        jobCompany = (EditText) findViewById(R.id.offerCompanyID);
        jobCity = (EditText) findViewById(R.id.offerCityID);
        jobState = (EditText) findViewById(R.id.offerStateID);
        jobCostOfLiving = (EditText) findViewById(R.id.offerCostID);
        jobCommute = (EditText) findViewById(R.id.offerCommuteID);
        jobSalary = (EditText) findViewById(R.id.offerSalaryID);
        jobBonus = (EditText) findViewById(R.id.offerBonusID);
        jobRetirementBenefits = (EditText) findViewById(R.id.offerRetirementID);
        jobLeaveTime = (EditText) findViewById(R.id.offerLeaveID);

        Button offerCancel = (Button) findViewById(R.id.offerCancelButtonID);
        offerCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retToMain = new Intent(JobOfferDetails.this, MainMenu.class);
                startActivity(retToMain);
                JobOfferDetails.this.finish();
            }
        });

        dbHelper = new JobCompareDbHelper(this);
        job = new JobManager(dbHelper);

        /*Button offerSave = (Button) findViewById(R.id.offerSaveButtonID);
        offerSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSavedOffer = new Intent(JobOfferDetails.this, SavedJobOffer.class);
                startActivity(goToSavedOffer);
                JobOfferDetails.this.finish();
            }
        });*/
    }
    public void handleClickSaveOffer (View view){
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
        boolean isCostNumber = isNumberUserDefined(inputCostOfLiving);
        boolean isCommuteNumber = isNumberUserDefined(inputCommute);
        boolean isSalaryNumber = isNumberUserDefined(inputSalary);
        boolean isBonusNumber = isNumberUserDefined(inputBonus);
        boolean isBenefitsNumber = isNumberUserDefined(inputRetirementBenefits);
        boolean isLeaveNumber = isNumberUserDefined(inputLeaveTime);



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

        boolean allValidInput = validTitle & validCompany & validCity & validState & validCommute
                & validSalary & validBonus & validRetirementBenefits & validLeaveTime
                & isCityAllAlphabet & isStateAllAlphabet & isCostNumber & isCommuteNumber
                & isSalaryNumber & isBonusNumber & isBenefitsNumber & isLeaveNumber;

        if (allValidInput){

            job.enterJobOffer(inputTitle, inputCompany, inputCity, inputState, inputCostOfLiving, Float.parseFloat(inputCommute), Float.parseFloat(inputSalary),
                    Float.parseFloat(inputBonus), Integer.parseInt(inputRetirementBenefits), Integer.parseInt(inputLeaveTime));

            Intent savedIntent = new Intent(JobOfferDetails.this, SavedJobOffer.class);
            startActivity(savedIntent);
            JobOfferDetails.this.finish();
        }
    }

    // To check if all characters in string are alphabets
    public boolean isAlpha(String name) {
        return name.matches("^[a-zA-Z\\s\\-\']*$");
    }

    public boolean isNumberUserDefined(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}
