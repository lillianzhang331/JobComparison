package edu.gatech.seclass.jobcompare6300.model;

import android.database.Cursor;

public class JobManager {

    JobCompareDbHelper dbHelper;

    public JobManager(JobCompareDbHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public boolean isCurrentJobAvailable() {
        return dbHelper.isCurrentJobAvailable();
    }

    public void enterCurrentJob(String inputTitle, String inputCompany, String inputCity, String inputState, String inputCostOfLiving,
                                float inputCommute, float inputSalary, float inputBonus, int inputRetirementBenefits, int inputLeaveTime){
        CurrentJob cj = new CurrentJob(dbHelper);
        cj.setTitle(inputTitle);
        cj.setCompany(inputCompany);
        cj.setCity(inputCity);
        cj.setState(inputState);
        cj.setCostOfLiving(inputCostOfLiving);
        cj.setCommute(inputCommute);
        cj.setSalary(inputSalary);
        cj.setBonus(inputBonus);
        cj.setRetirementBenefits(inputRetirementBenefits);
        cj.setLeaveTime(inputLeaveTime);
        cj.save();
    }

    public CurrentJob getCurrentJob() {
        Cursor job = dbHelper.getCurrentJob();
        job.moveToFirst();
        CurrentJob cj = new CurrentJob(dbHelper);

        cj.setTitle(job.getString(0));
        cj.setCompany(job.getString(1));
        cj.setCity(job.getString(2));
        cj.setState(job.getString(3));
        cj.setCostOfLiving(job.getString(4));
        cj.setCommute(job.getFloat(5));
        cj.setSalary(job.getFloat(6));
        cj.setBonus(job.getFloat(7));
        cj.setRetirementBenefits(job.getInt(8));
        cj.setLeaveTime(job.getInt(9));

        return cj;
    }

    public void enterJobOffer(String inputTitle, String inputCompany, String inputCity, String inputState, String inputCostOfLiving,
                              float inputCommute, float inputSalary, float inputBonus, int inputRetirementBenefits, int inputLeaveTime){

    }

    public void compareJobOffers(){

    }
}
