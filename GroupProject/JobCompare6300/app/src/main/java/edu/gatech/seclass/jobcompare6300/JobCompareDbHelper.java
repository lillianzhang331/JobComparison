package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JobCompareDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "JobCompare.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + JobCompareContract.CurrentJob.TABLE_NAME + " (" +
                    JobCompareContract.CurrentJob._ID + " INTEGER PRIMARY KEY," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_TITLE + " TEXT," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_COMPANY + " TEXT," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_CITY + " TEXT," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_STATE + " TEXT," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_COSTOFLIVING + " TEXT," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_COMMUTE + " NUMBER," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_SALARY + " NUMBER," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_BONUS + " NUMBER," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_RETIREMENTBENEFITS + " NUMBER," +
                    JobCompareContract.CurrentJob.COLUMN_NAME_LEAVETIME + " NUMBER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + JobCompareContract.CurrentJob.TABLE_NAME;

    public JobCompareDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public boolean insertCurrentJob (String title, String company, String city, String state, String costofliving, Float commute, Float salary, Float bonus, Integer retirementbenefits, Integer leavetime) {
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

        db.insert(JobCompareContract.CurrentJob.TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean isCurrentJobAvailable () {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select count(*) from currentjob", null );
        res.moveToFirst();
        int count = res.getInt(0);
        if(count > 0)
            return true;
        else
            return false;
    }

    public Cursor getCurrentJob () {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select title, company, city, state, costofliving, commute, salary, bonus, retirementbenefits, leavetime from currentjob", null );
        return res;
    }

    public boolean updateCurrentJob (String title, String company, String city, String state, String costofliving, Float commute, Float salary, Float bonus, Integer retirementbenefits, Integer leavetime) {
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

        db.update(JobCompareContract.CurrentJob.TABLE_NAME, contentValues, null, null);

        return true;
    }
}
