package edu.gatech.seclass.jobcompare6300;

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
    }
}
