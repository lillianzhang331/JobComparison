package edu.gatech.seclass.jobcompare6300.model;

import android.widget.EditText;

import edu.gatech.seclass.jobcompare6300.R;

public abstract class JobDetails {
    String title;
    String company;
    String city;
    String state;
    Integer costOfLiving;
    Float commute;
    Float salary;
    Float bonus;
    Integer retirementBenefits;
    Integer leaveTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCostOfLiving() {
        return costOfLiving;
    }

    public void setCostOfLiving(Integer costOfLiving) {
        this.costOfLiving = costOfLiving;
    }

    public Float getCommute() {
        return commute;
    }

    public void setCommute(Float commute) {
        this.commute = commute;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Float getBonus() {
        return bonus;
    }

    public void setBonus(Float bonus) {
        this.bonus = bonus;
    }

    public Integer getRetirementBenefits() {
        return retirementBenefits;
    }

    public void setRetirementBenefits(Integer retirementBenefits) {
        this.retirementBenefits = retirementBenefits;
    }

    public Integer getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Integer leaveTime) {
        this.leaveTime = leaveTime;
    }

    public abstract void save();
}
