package com.payal.faculty;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.itextpdf.text.pdf.PdfName.AND;

public class DatabaseHelper extends SQLiteOpenHelper
{
    SQLiteDatabase db;

    public final static String DATABASE_NAME = "pppopponammm.db";

    public final static String TABLE_NAME = "payal_table";
    public final static String TABLE_NAME1 = "rutu_table";

    public static final String COL_1 = "USER_ID";
    public static final String COL_2 = "PASSWORD";
    public static final String COL_3 = "CONFIRM_PASSWORD";
    public static final String COL_4 = "FACULTY_NAME";
    public static final String COL_5 = "PHOTO";
    public static final String COL_6 = "DEPT";
    public static final String COL_7 = "YEAR";
    public static final String COL_8 = "SUBJECT";

    public static final String C_0 = "ID";
   // public static final String COL_1 = "USER_ID";
    public static final String C_2 = "DATE";
    public static final String C_3 = "TIME";
    public static final String C_4 = "PRACTICAL";
    public static final String C_5 = "PRACTICAL_NO";
    public static final String C_6 = "PRACTICAL_CHECKED";
    public static final String C_7 = "LECTURE";
    public static final String C_8 = "NOTE";
    public static final String C_9 = "POINTS_COVERED";
    public static final String C_10 = "ASSIGNMENT";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(USER_ID INTEGER PRIMARY KEY,PASSWORD TEXT,CONFIRM_PASSWORD TEXT,FACULTY_NAME TEXT,PHOTO BLOB NOT NULL,DEPT TEXT,YEAR TEXT,SUBJECT TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME1+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, USER_ID INTEGER, DATE TEXT, TIME TEXT,PRACTICAL TEXT,PRACTICAL_NO TEXT, PRACTICAL_CHECKED TEXT, LECTURE TEXT, NOTE TEXT, POINTS_COVERED TEXT, ASSIGNMENT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public DatabaseHelper open() throws SQLException {
        db = this.getWritableDatabase();
        return this;
    }

    public void close() {
        this.close();
    }

    public boolean insertData(String id, String pass, String passc, String fname, byte[] photo, String dept, String year, String sub)
    {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, id);
        cv.put(COL_2, pass);
        cv.put(COL_3, passc);
        cv.put(COL_4, fname);
        cv.put(COL_5, photo);
        cv.put(COL_6, dept);
        cv.put(COL_7, year);
        cv.put(COL_8, sub);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) return false;
        else return true;

    }

    public boolean insertDataF(String uid, String date, String time, String practical, String practno, String practcheck, String lecture, String notes, String pointcov, String assignment)
    {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, uid);
        cv.put(C_2, date);
        cv.put(C_3, time);
        cv.put(C_4, practical);
        cv.put(C_5, practno);
        cv.put(C_6, practcheck);
        cv.put(C_7, lecture);
        cv.put(C_8, notes);
        cv.put(C_9, pointcov);
        cv.put(C_10, assignment);

        long result = db.insert(TABLE_NAME1, null, cv);
        if (result == -1) return false;
        else return true;
    }

    public Cursor getData(String id)
    {
        db = this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME+" WHERE USER_ID='"+id+"'";
        Cursor  cursor = db.rawQuery(query,null);
        return cursor;
    }

    public Cursor getDataF(String uid)
    {
        db = this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME1+" WHERE ID='"+uid+"'";
        Cursor  cursor = db.rawQuery(query,null);
        return cursor;
    }

    public Cursor getUidData(String uid)
    {
        db = this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME1+" WHERE USER_ID='"+uid+"'";
        Cursor  cursor = db.rawQuery(query,null);
        return cursor;
    }
    public boolean updateData(String id, String pass, String passc, String fname, byte[] photo, String dept, String year, String sub)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, pass);
        contentValues.put(COL_3, passc);
        contentValues.put(COL_4, fname);
        contentValues.put(COL_5, photo);
        contentValues.put(COL_6, dept);
        contentValues.put(COL_7, year);
        contentValues.put(COL_8, sub);

        db.update(TABLE_NAME, contentValues, "USER_ID=?", new String[]{id});
        return true;
    }

    public boolean updateDataF(String id, String date, String time, String practical, String practno, String practcheck, String lecture, String notes, String pointcov, String assignment)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_0, id);
        //contentValues.put(COL_1, uid);
        contentValues.put(C_2, date);
        contentValues.put(C_3, time);
        contentValues.put(C_4, practical);
        contentValues.put(C_5, practno);
        contentValues.put(C_6, practcheck);
        contentValues.put(C_7, lecture);
        contentValues.put(C_8, notes);
        contentValues.put(C_9, pointcov);
        contentValues.put(C_10, assignment);

        db.update(TABLE_NAME1, contentValues, "ID=?", new String[]{id});
        return true;
    }

    public Integer deleteData (String id)
    {
        db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "USER_ID = ?", new String[]{id});
    }

    public Integer deleteDataF (String id)
    {
        db = this.getWritableDatabase();
        return db.delete(TABLE_NAME1, "ID = ?", new String[]{id});
    }
    public Cursor getAllData()
    {
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return res;
    }

    public Cursor getAllDataF()
    {
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME1, null);
        return res;
    }

    public String getFName(long id)
    {
        db=this.getReadableDatabase();
        String columns[]=new String[]{COL_1,COL_2,COL_3,COL_4,COL_5,COL_6,COL_7};
        Cursor cursor=db.query(TABLE_NAME,columns,COL_1+"="+id,null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            String fname=cursor.getString(4);
            return fname;
        }
        return null;
    }
    public Cursor getAllBData(String uid,String date,String time)
    {
        db=this.getWritableDatabase();
        final String strQuery="SELECT r.USER_ID, r.PHOTO, r.PASSWORD, r.FACULTY_NAME, r.DEPT, r.YEAR, r.SUBJECT, f.DATE, f.TIME," +
                " f.PRACTICAL, f.PRACTICAL_NO, f.PRACTICAL_CHECKED, f.LECTURE, f.NOTE, f.POINTS_COVERED, f.ASSIGNMENT FROM " +
                "payal_table r INNER JOIN rutu_table f ON r.USER_ID = f.USER_ID WHERE f.DATE='"+date+"' AND f.TIME='"+time+"' AND r.USER_ID='"+uid+"'";
        Cursor cur=db.rawQuery(strQuery,null);
        return cur;

    }
}
