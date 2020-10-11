package edu.gatech.seclass.jobcompare6300.model;

public class CurrentJob extends JobDetails {
    JobCompareDbHelper dbHelper;

    public CurrentJob(JobCompareDbHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public void save(){
        if(dbHelper.isCurrentJobAvailable())
            dbHelper.updateCurrentJob(this.getTitle(), this.getCompany(), this.getCity(), this.getState(), this.getCostOfLiving(),
                    this.getCommute(), this.getSalary(), this.getBonus(), this.getRetirementBenefits(), this.getLeaveTime());
        else
            dbHelper.createCurrentJob(this.getTitle(), this.getCompany(), this.getCity(), this.getState(), this.getCostOfLiving(),
                    this.getCommute(), this.getSalary(), this.getBonus(), this.getRetirementBenefits(), this.getLeaveTime());
    }
}
