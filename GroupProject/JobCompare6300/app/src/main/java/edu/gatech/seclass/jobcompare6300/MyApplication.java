package edu.gatech.seclass.jobcompare6300;

import android.app.Application;
import android.icu.math.BigDecimal;

import java.util.ArrayList;

import edu.gatech.seclass.jobcompare6300.model.ComparisonSettingsModel;
import edu.gatech.seclass.jobcompare6300.model.CurrentJob;
import edu.gatech.seclass.jobcompare6300.model.JobCompareDbHelper;
import edu.gatech.seclass.jobcompare6300.model.JobManager;
import edu.gatech.seclass.jobcompare6300.model.JobOffer;


public class MyApplication extends Application {

    /***********************************************************************************************
     * Deprecated variables and methods for comparison settings.
     * Settings are now in the Job Compare database, in order to retain the values even after the
     * app is closed.
     */
    private Integer commuteWt=5, salaryWt=5, bonusWt=5, retirementbenefitsWt=5, leaveWt=5;
    public static ArrayList<Integer> jobIdList = new ArrayList<>();
    public void setJobIdList (ArrayList<Integer> list) {
        for (int i=0; i<list.size(); i++) {
            this.jobIdList.add(list.get(i));
        }
    }
    public ArrayList<Integer> getJobIdList () {
        return this.jobIdList;
    }
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

    /***********************************************************************************************
     * adjustedYearlySalary -
     * Utility function to calculate AdjustedYearlySalary
     */
    private Integer baseCostOfLiving = 100;

    public String adjustedYearlySalary(JobCompareDbHelper dbHelper, String costOfLiving,
                                       String salary){
        if (dbHelper.getCurrentCostOfLiving() > 0)
            this.baseCostOfLiving=dbHelper.getCurrentCostOfLiving();
        float adjustedSalary = ((float) this.baseCostOfLiving / Integer.parseInt(costOfLiving))
                * Float.parseFloat(salary);
        adjustedSalary = round(adjustedSalary,2);
        return Float.toString(adjustedSalary);
    }
    /***********************************************************************************************
     * adjustedYearlyBonus -
     * Utility function to calculate AdjustedYearlyBonus
     */
    public String adjustedYearlyBonus(JobCompareDbHelper dbHelper, String costOfLiving,
                                      String bonus){
        if (dbHelper.getCurrentCostOfLiving() > 0)
            baseCostOfLiving=dbHelper.getCurrentCostOfLiving();
        float adjustedBonus = ((float)this.baseCostOfLiving / Integer.parseInt(costOfLiving))
                * Float.parseFloat(bonus);
        adjustedBonus = round(adjustedBonus,2);
        return Float.toString(adjustedBonus);
    }
    private static float round(float value, int decimals) {
        BigDecimal bd = new BigDecimal(Float.toString(value));
        bd = bd.setScale(decimals, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    public float calcJobOfferScore(JobCompareDbHelper dbHelper, JobOffer jo,
                                     ComparisonSettingsModel settings){

        Float commuteWt = (float) settings.getCommuteWeight();
        Float salaryWt = (float) settings.getSalaryWeight();
        Float bonusWt = (float) settings.getBonusWeight();
        Float retirementWt = (float) settings.getRetirementbenefitsWeight();
        Float leaveWt = (float) settings.getLeaveWeight();
        Float sumWt = commuteWt + salaryWt + bonusWt + retirementWt + leaveWt;
        Float adjustedSalary = Float.parseFloat(adjustedYearlySalary(dbHelper,
                jo.getCostOfLiving().toString(), jo.getSalary().toString()));
        Float adjustedBonus = Float.parseFloat(adjustedYearlyBonus(dbHelper,
                jo.getCostOfLiving().toString(), jo.getBonus().toString()));
        Float a = (salaryWt/sumWt)*adjustedSalary;
        Float b = (bonusWt/sumWt)*adjustedBonus;
        Float c = (retirementWt/sumWt)*(Float.parseFloat(jo.getRetirementBenefits().toString())*
                adjustedSalary/100);
        Float d = (leaveWt/sumWt)*(Float.parseFloat(jo.getLeaveTime().toString())*
                adjustedSalary/260);
        Float e = (commuteWt/sumWt)*(Float.parseFloat(jo.getCommute().toString())*
                adjustedSalary/8);
        Float jobScore = a + b + c + d - e;
        return jobScore;
    }

    public float calcCurrentJobScore(JobCompareDbHelper dbHelper, CurrentJob cj,
                                     ComparisonSettingsModel settings){

        Float commuteWt = (float) settings.getCommuteWeight();
        Float salaryWt = (float) settings.getSalaryWeight();
        Float bonusWt = (float) settings.getBonusWeight();
        Float retirementWt = (float) settings.getRetirementbenefitsWeight();
        Float leaveWt = (float) settings.getLeaveWeight();
        Float sumWt = commuteWt + salaryWt + bonusWt + retirementWt + leaveWt;
        Float adjustedSalary = Float.parseFloat(adjustedYearlySalary(dbHelper,
                cj.getCostOfLiving().toString(), cj.getSalary().toString()));
        Float adjustedBonus = Float.parseFloat(adjustedYearlyBonus(dbHelper,
                cj.getCostOfLiving().toString(), cj.getBonus().toString()));
        Float a = (salaryWt/sumWt)*adjustedSalary;
        Float b = (bonusWt/sumWt)*adjustedBonus;
        Float c = (retirementWt/sumWt)*(Float.parseFloat(cj.getRetirementBenefits().toString())*
                adjustedSalary/100);
        Float d = (leaveWt/sumWt)*(Float.parseFloat(cj.getLeaveTime().toString())*
                adjustedSalary/260);
        Float e = (commuteWt/sumWt)*(Float.parseFloat(cj.getCommute().toString())*
                adjustedSalary/8);
        Float jobScore = a + b + c + d - e;
        return jobScore;
    }
}