package edu.gatech.seclass.jobcompare6300.model;

import android.database.Cursor;


public class ComparisonSettingsModel {

    JobCompareDbHelper dbHelper;

    public ComparisonSettingsModel(JobCompareDbHelper dbHelper){
        this.dbHelper = dbHelper;
    }
    public Integer getCommuteWeight(){
        if (dbHelper.isSettingsAvailable()) {
            Cursor settings = dbHelper.getSettings();
            settings.moveToFirst();
            return Integer.parseInt(settings.getString(0));
        }
        return 5; //default value
    }

    public Integer getSalaryWeight(){
        if (dbHelper.isSettingsAvailable()) {
            Cursor settings = dbHelper.getSettings();
            settings.moveToFirst();
            return Integer.parseInt(settings.getString(1));
        }
        return 5; //default value
    }

    public Integer getBonusWeight(){
        if (dbHelper.isSettingsAvailable()) {
            Cursor settings = dbHelper.getSettings();
            settings.moveToFirst();
            return Integer.parseInt(settings.getString(2));
        }
        return 5; //default value
    }

    public Integer getRetirementbenefitsWeight(){
        if (dbHelper.isSettingsAvailable()) {
            Cursor settings = dbHelper.getSettings();
            settings.moveToFirst();
            return Integer.parseInt(settings.getString(3));
        }
        return 5; //default value
    }

    public Integer getLeaveWeight(){
        if (dbHelper.isSettingsAvailable()) {
            Cursor settings = dbHelper.getSettings();
            settings.moveToFirst();
            return Integer.parseInt(settings.getString(4));
        }
        return 5; //default value
    }

    public void setComparisonSettings(Integer commuteWt, Integer salaryWt, Integer bonusWt,
                                      Integer retirementbenefitsWt, Integer leaveWt){
        dbHelper.saveSettings(commuteWt,salaryWt, bonusWt, retirementbenefitsWt, leaveWt);
    }

}
