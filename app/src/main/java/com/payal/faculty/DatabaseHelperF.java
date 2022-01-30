package com.payal.faculty;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperF extends SQLiteOpenHelper
{

    public final static String DATABASE_NAME = "facultydbsec.db";
    public final static String TABLE_NAME = "tablethr";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "USER_ID";
    public static final String COL_2 = "DATE";
    public static final String COL_3 = "TIME";
    public static final String COL_4 = "PRACTICAL";
    public static final String COL_5 = "PRACTICAL_NO";
    public static final String COL_6 = "PRACTICAL_CHECKED";
    public static final String COL_7 = "LECTURE";
    public static final String COL_8 = "NOTE";
    public static final String COL_9 = "POINTS_COVERED";
    public static final String COL_10 = "ASSIGNMENT";

    public DatabaseHelperF(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, USER_ID, DATE TEXT, TIME TEXT,PRACTICAL TEXT,PRACTICAL_NO TEXT, PRACTICAL_CHECKED TEXT, LECTURE TEXT, NOTE TEXT, POINTS_COVERED TEXT, ASSIGNMENT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String uid, String date, String time, String practical, String practno, String practcheck, String lecture, String notes, String pointcov, String assignment)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, uid);
        cv.put(COL_2, date);
        cv.put(COL_3, time);
        cv.put(COL_4, practical);
        cv.put(COL_5, practno);
        cv.put(COL_6, practcheck);
        cv.put(COL_7, lecture);
        cv.put(COL_8, notes);
        cv.put(COL_9, pointcov);
        cv.put(COL_10, assignment);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) return false;
        else return true;
    }

    public Cursor getData(String uid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME+" WHERE ID='"+uid+"'";
        Cursor  cursor = db.rawQuery(query,null);
        return cursor;
    }

    public Cursor getUidData(String uid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME+" WHERE USER_ID='"+uid+"'";
        Cursor  cursor = db.rawQuery(query,null);
        return cursor;
    }

    public boolean updateData(String id, String uid, String date, String time, String practical, String practno, String practcheck, String lecture, String notes, String pointcov, String assignment)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_0, id);
        contentValues.put(COL_1, uid);
        contentValues.put(COL_2, date);
        contentValues.put(COL_3, time);
        contentValues.put(COL_4, practical);
        contentValues.put(COL_5, practno);
        contentValues.put(COL_6, practcheck);
        contentValues.put(COL_7, lecture);
        contentValues.put(COL_8, notes);
        contentValues.put(COL_9, pointcov);
        contentValues.put(COL_10, assignment);

        db.update(TABLE_NAME, contentValues, "ID=?", new String[]{id});
        return true;
    }

    public Integer deleteData (String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return res;
    }

}
