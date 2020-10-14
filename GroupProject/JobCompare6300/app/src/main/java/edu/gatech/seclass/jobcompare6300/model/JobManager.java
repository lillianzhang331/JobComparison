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

    public void enterCurrentJob(String inputTitle, String inputCompany, String inputCity, String inputState, Integer inputCostOfLiving,
                                Float inputCommute, Float inputSalary, Float inputBonus, Integer inputRetirementBenefits, Integer inputLeaveTime){
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
        cj.setCostOfLiving(job.getInt(4));
        cj.setCommute(job.getFloat(5));
        cj.setSalary(job.getFloat(6));
        cj.setBonus(job.getFloat(7));
        cj.setRetirementBenefits(job.getInt(8));
        cj.setLeaveTime(job.getInt(9));

        return cj;
    }

    public void enterJobOffer(String inputTitle, String inputCompany, String inputCity, String inputState, Integer inputCostOfLiving,
                              Float inputCommute, Float inputSalary, Float inputBonus, Integer inputRetirementBenefits, Integer inputLeaveTime){
        JobOffer jo = new JobOffer(dbHelper);
        jo.setTitle(inputTitle);
        jo.setCompany(inputCompany);
        jo.setCity(inputCity);
        jo.setState(inputState);
        jo.setCostOfLiving(inputCostOfLiving);
        jo.setCommute(inputCommute);
        jo.setSalary(inputSalary);
        jo.setBonus(inputBonus);
        jo.setRetirementBenefits(inputRetirementBenefits);
        jo.setLeaveTime(inputLeaveTime);
        jo.save();
    }

    public JobOffer getLastJobOffer() {
        Cursor job = dbHelper.getLastJobOffer();
        job.moveToFirst();
        JobOffer jo = new JobOffer(dbHelper);

        jo.setTitle(job.getString(0));
        jo.setCompany(job.getString(1));
        jo.setCity(job.getString(2));
        jo.setState(job.getString(3));
        jo.setCostOfLiving(job.getInt(4));
        jo.setCommute(job.getFloat(5));
        jo.setSalary(job.getFloat(6));
        jo.setBonus(job.getFloat(7));
        jo.setRetirementBenefits(job.getInt(8));
        jo.setLeaveTime(job.getInt(9));

        return jo;
    }

    public void compareJobOffers(){

    }
}
