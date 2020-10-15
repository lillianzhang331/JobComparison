package edu.gatech.seclass.jobcompare6300.model;

import android.provider.BaseColumns;

public final class JobCompareContract {
    private JobCompareContract(){}
    public static class CurrentJob implements BaseColumns {
        public static final String TABLE_NAME = "currentjob";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_COMPANY = "company";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_STATE = "state";
        public static final String COLUMN_NAME_COSTOFLIVING = "costofliving";
        public static final String COLUMN_NAME_COMMUTE = "commute";
        public static final String COLUMN_NAME_SALARY = "salary";
        public static final String COLUMN_NAME_BONUS = "bonus";
        public static final String COLUMN_NAME_RETIREMENTBENEFITS = "retirementbenefits";
        public static final String COLUMN_NAME_LEAVETIME = "leavetime";
        public static final String COLUMN_NAME_JOBSCORE = "jobscore";

    }
    public static class JobOffer implements BaseColumns {
        public static final String TABLE_NAME = "joboffer";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_COMPANY = "company";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_STATE = "state";
        public static final String COLUMN_NAME_COSTOFLIVING = "costofliving";
        public static final String COLUMN_NAME_COMMUTE = "commute";
        public static final String COLUMN_NAME_SALARY = "salary";
        public static final String COLUMN_NAME_BONUS = "bonus";
        public static final String COLUMN_NAME_RETIREMENTBENEFITS = "retirementbenefits";
        public static final String COLUMN_NAME_LEAVETIME = "leavetime";
        public static final String COLUMN_NAME_JOBSCORE = "jobscore";
    }
    public static class ComparisonSettings implements BaseColumns {
        public static final String TABLE_NAME = "comparisonsettings";
        public static final String COLUMN_NAME_COMMUTEWT = "commuteweight";
        public static final String COLUMN_NAME_SALARYWT = "salaryweight";
        public static final String COLUMN_NAME_BONUSWT = "bonusweight";
        public static final String COLUMN_NAME_RETIREMENTWT = "retirementweight";
        public static final String COLUMN_NAME_LEAVEWT = "leaveweight";
    }
}
