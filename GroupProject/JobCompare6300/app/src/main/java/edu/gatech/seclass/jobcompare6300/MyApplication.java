package edu.gatech.seclass.jobcompare6300;

import android.app.Application;

public class MyApplication extends Application {

    private Integer commuteWt=5, salaryWt=5, bonusWt=5, retirementbenefitsWt=5, leaveWt=5;

    public void adjustSettings(Integer commuteWt, Integer salaryWt, Integer bonusWt,
                               Integer retirementbenefitsWt, Integer leaveWt){
        this.commuteWt=commuteWt;
        this.salaryWt=salaryWt;
        this.bonusWt=bonusWt;
        this.retirementbenefitsWt=retirementbenefitsWt;
        this.leaveWt=leaveWt;
    }
    public Integer getCommuteWeight(){
        return this.commuteWt;
    }
    public Integer getSalaryWeight(){
        return this.salaryWt;
    }
    public Integer getBonusWeight(){
        return this.bonusWt;
    }
    public Integer getRetirementbenefitsWeight(){
        return this.retirementbenefitsWt;
    }
    public Integer getLeaveWeight(){
        return this.leaveWt;
    }
}