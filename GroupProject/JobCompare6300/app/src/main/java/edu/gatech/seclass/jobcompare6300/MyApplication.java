package edu.gatech.seclass.jobcompare6300;

import android.app.Application;
import android.icu.math.BigDecimal;

import edu.gatech.seclass.jobcompare6300.model.JobCompareDbHelper;


public class MyApplication extends Application {

    /***********************************************************************************************
     * Deprecated variables and methods for comparison settings.
     * Settings are now in the Job Compare database, in order to retain the values even after the
     * app is closed.
     */
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
}