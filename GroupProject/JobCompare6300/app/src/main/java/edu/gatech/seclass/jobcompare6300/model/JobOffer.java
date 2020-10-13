package edu.gatech.seclass.jobcompare6300.model;

public class JobOffer extends JobDetails {
    JobCompareDbHelper dbHelper;

    public JobOffer(JobCompareDbHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public void save(){
        dbHelper.addJobOffer(this.getTitle(), this.getCompany(), this.getCity(), this.getState(), this.getCostOfLiving(),
                this.getCommute(), this.getSalary(), this.getBonus(), this.getRetirementBenefits(), this.getLeaveTime());
    }
}
