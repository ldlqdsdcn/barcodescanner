package indi.solomon.zxingscanning.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by admin on 2016/10/24.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper databaseHelper;
    private static final String DATABASE_NAME = "zxing_scanning.db";
    private static final int  DATABASE_VERSION= 1;
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DatabaseHelper",DatabaseConsts.SANNED_RESULT_TABLE.CREATE_TABLE);
        db.execSQL(DatabaseConsts.SANNED_RESULT_TABLE.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public static synchronized DatabaseHelper getDatabaseHelper(Context context)
    {

        if(databaseHelper==null)
        {
            databaseHelper=new DatabaseHelper(context);
        }
        return databaseHelper;
    }

}
