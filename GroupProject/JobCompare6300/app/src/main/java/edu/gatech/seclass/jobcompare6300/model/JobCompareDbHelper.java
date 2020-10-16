package edu.gatech.seclass.jobcompare6300.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JobCompareDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "JobCompare.db";

    private static final String CREATE_TABLE_CURRENTJOB =
            "CREATE TABLE " + JobCompareContract.CurrentJob.TABLE_NAME + " (" +
                    JobCompareContract.CurrentJob._ID + " INTEGER PRIMARY KEY," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_TITLE + " TEXT," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_COMPANY + " TEXT," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_CITY + " TEXT," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_STATE + " TEXT," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_COSTOFLIVING + " NUMBER," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_COMMUTE + " NUMBER," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_SALARY + " NUMBER," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_BONUS + " NUMBER," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_RETIREMENTBENEFITS + " NUMBER," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_LEAVETIME + " NUMBER," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_JOBSCORE + " NUMBER)";

    private static final String CREATE_TABLE_JOBOFFER =
            "CREATE TABLE " + JobCompareContract.JobOffer.TABLE_NAME + " (" +
                    JobCompareContract.JobOffer._ID + " INTEGER PRIMARY KEY," +
                    JobCompareContract.JobOffer.COLUMN_NAME_TITLE + " TEXT," +
                    JobCompareContract.JobOffer.COLUMN_NAME_COMPANY + " TEXT," +
                    JobCompareContract.JobOffer.COLUMN_NAME_CITY + " TEXT," +
                    JobCompareContract.JobOffer.COLUMN_NAME_STATE + " TEXT," +
                    JobCompareContract.JobOffer.COLUMN_NAME_COSTOFLIVING + " NUMBER," +
                    JobCompareContract.JobOffer.COLUMN_NAME_COMMUTE + " NUMBER," +
                    JobCompareContract.JobOffer.COLUMN_NAME_SALARY + " NUMBER," +
                    JobCompareContract.JobOffer.COLUMN_NAME_BONUS + " NUMBER," +
                    JobCompareContract.JobOffer.COLUMN_NAME_RETIREMENTBENEFITS + " NUMBER," +
                    JobCompareContract.JobOffer.COLUMN_NAME_LEAVETIME + " NUMBER," +
                    JobCompareContract.JobOffer.COLUMN_NAME_JOBSCORE + " NUMBER)";

    private static final String CREATE_TABLE_SETTINGS =
            "CREATE TABLE " + JobCompareContract.ComparisonSettings.TABLE_NAME + " (" +
                    JobCompareContract.ComparisonSettings._ID + " INTEGER PRIMARY KEY," +
                    JobCompareContract.ComparisonSettings.COLUMN_NAME_COMMUTEWT + " NUMBER," +
                    JobCompareContract.ComparisonSettings.COLUMN_NAME_SALARYWT + " NUMBER," +
                    JobCompareContract.ComparisonSettings.COLUMN_NAME_BONUSWT + " NUMBER," +
                    JobCompareContract.ComparisonSettings.COLUMN_NAME_RETIREMENTWT + " NUMBER," +
                    JobCompareContract.ComparisonSettings.COLUMN_NAME_LEAVEWT + " NUMBER)";

    private static final String DELETE_TABLE_CURRENTJOB =
            "DROP TABLE IF EXISTS " + JobCompareContract.CurrentJob.TABLE_NAME;

    private static final String DELETE_TABLE_JOBOFFER =
            "DROP TABLE IF EXISTS " + JobCompareContract.JobOffer.TABLE_NAME;

    private static final String DELETE_TABLE_SETTINGS =
            "DROP TABLE IF EXISTS " + JobCompareContract.ComparisonSettings.TABLE_NAME;

    public JobCompareDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CURRENTJOB);
        db.execSQL(CREATE_TABLE_JOBOFFER);
        db.execSQL(CREATE_TABLE_SETTINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DELETE_TABLE_CURRENTJOB);
//        db.execSQL(DELETE_TABLE_JOBOFFER);
        db.execSQL(DELETE_TABLE_SETTINGS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public void createCurrentJob (String title, String company, String city, String state, Integer costofliving, Float commute, Float salary, Float bonus, Integer retirementbenefits, Integer leavetime, Float jobscore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id",0);
        contentValues.put("title", title);
        contentValues.put("company", company);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("costofliving",costofliving);
        contentValues.put("commute", commute);
        contentValues.put("salary", salary);
        contentValues.put("bonus", bonus);
        contentValues.put("retirementbenefits", retirementbenefits);
        contentValues.put("leavetime", leavetime);
        contentValues.put("jobscore", jobscore);

        db.insert(JobCompareContract.CurrentJob.TABLE_NAME, null, contentValues);
        db.close();
    }

    public void addJobOffer (String title, String company, String city, String state, Integer costofliving, Float commute, Float salary, Float bonus, Integer retirementbenefits, Integer leavetime, Float jobscore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", title);
        contentValues.put("company", company);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("costofliving",costofliving);
        contentValues.put("commute", commute);
        contentValues.put("salary", salary);
        contentValues.put("bonus", bonus);
        contentValues.put("retirementbenefits", retirementbenefits);
        contentValues.put("leavetime", leavetime);
        contentValues.put("jobscore", jobscore);

        long id = db.insert(JobCompareContract.JobOffer.TABLE_NAME, null, contentValues);
        db.close();
    }

    public Cursor getLastJobOffer () {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select max(rowid) from joboffer", null);
        res.moveToFirst();
        int lastID = res.getInt(0);
        Cursor lastJobOffer = getJobOfferByID(lastID);
        return lastJobOffer;
    }

    public Cursor getJobOfferByID(Integer rowid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select title, company, city, state, costofliving, commute, salary, bonus, retirementbenefits, leavetime, jobscore from joboffer where rowid=?", new String[]{String.valueOf(rowid)});
        return res;
    }

    public Integer getJobOfferNumRowIDs() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(*) from joboffer", null);
        res.moveToFirst();
        return res.getInt(0);
    }
    public void updateJobOfferScore(Integer rowid, Float jobScore) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("jobscore",jobScore);
        db.update(JobCompareContract.JobOffer.TABLE_NAME, contentValues, "rowid="+rowid, null);
        db.close();
    }

    public void updateCurrentJobScore(Float jobScore) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("jobscore",jobScore);
        db.update(JobCompareContract.CurrentJob.TABLE_NAME, contentValues, null, null);
        db.close();
    }

    public boolean isCurrentJobAvailable () {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select count(*) from currentjob", null );
        res.moveToFirst();
        int count = res.getInt(0);
        res.close();
        return count > 0;
    }

    public Cursor getCurrentJob () {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select title, company, city, state, costofliving, commute, " +
                "salary, bonus, retirementbenefits, leavetime, jobscore from currentjob", null );
    }

    public void updateCurrentJob (String title, String company, String city, String state, Integer costofliving, Float commute, Float salary, Float bonus, Integer retirementbenefits, Integer leavetime, Float jobscore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", title);
        contentValues.put("company", company);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("costofliving",costofliving);
        contentValues.put("commute", commute);
        contentValues.put("salary", salary);
        contentValues.put("bonus", bonus);
        contentValues.put("retirementbenefits", retirementbenefits);
        contentValues.put("leavetime", leavetime);
        contentValues.put("jobscore", jobscore);

        db.update(JobCompareContract.CurrentJob.TABLE_NAME, contentValues, null, null);

    }

    public boolean isSettingsAvailable () {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select count(*) from comparisonsettings", null );
        res.moveToFirst();
        int count = res.getInt(0);
        res.close();
        return count > 0;
    }

    public Cursor getSettings () {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select commuteweight, salaryweight, bonusweight, " +
                "retirementweight, leaveweight from comparisonsettings", null );
    }

    public void saveSettings(Integer commutewt, Integer salarywt, Integer bonuswt, Integer retirementwt, Integer leavewt){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("commuteweight", commutewt);
        contentValues.put("salaryweight", salarywt);
        contentValues.put("bonusweight", bonuswt);
        contentValues.put("retirementweight", retirementwt);
        contentValues.put("leaveweight", leavewt);
        Cursor res =  db.rawQuery( "select count(*) from comparisonsettings", null );
        res.moveToFirst();
        if (res.getInt(0) > 0)
            db.update(JobCompareContract.ComparisonSettings.TABLE_NAME, contentValues, null, null);
        else
            db.insert(JobCompareContract.ComparisonSettings.TABLE_NAME, null,contentValues);
        res.close();
        db.close();
    }
    public Integer getCurrentCostOfLiving(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select costofliving from currentjob", null );
        res.moveToFirst();
        if (res!= null)
            return res.getInt(0);
        else
            return 0;
    }
    public Cursor getAllJobs() {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("select * from joboffer union all select * from currentjob order by jobscore desc", null);
            return res;
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
    }
}
