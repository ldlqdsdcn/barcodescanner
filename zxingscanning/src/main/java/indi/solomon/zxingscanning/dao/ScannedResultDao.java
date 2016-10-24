package indi.solomon.zxingscanning.dao;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import indi.solomon.zxingscanning.po.ScannedResult;

/**
 * Created by admin on 2016/10/24.
 */

public class ScannedResultDao {
    DatabaseHelper databaseHelper;
    public ScannedResultDao(Context context)
    {
        databaseHelper=DatabaseHelper.getDatabaseHelper(context);
    }


    public void insertScannedResult(ScannedResult scannedResult)
    {
        Log.d("ScannedResultDao","insert to table bgn");
        SQLiteDatabase sqLiteDatabase= databaseHelper.getWritableDatabase();
        long id=sqLiteDatabase.insert(DatabaseConsts.SANNED_RESULT_TABLE.TABLE_NAME,null,scannedResult.toInsertValues());

        Log.d("ScannedResultDao","insert to table end id="+id);

    }

    public void deleteScannedResult(Integer id)
    {
        SQLiteDatabase sqLiteDatabase= databaseHelper.getWritableDatabase();
        String[] whereARgs=new String[]{String.valueOf(id)};
        sqLiteDatabase.delete(DatabaseConsts.SANNED_RESULT_TABLE.TABLE_NAME,"  id=?",whereARgs);
    }
    public List<ScannedResult> list()
    {
        List<ScannedResult> result=new ArrayList();
        SQLiteDatabase sqLiteDatabase= databaseHelper.getWritableDatabase();
        Cursor cursor =sqLiteDatabase.query(DatabaseConsts.SANNED_RESULT_TABLE.TABLE_NAME,new String[]{DatabaseConsts.SANNED_RESULT_TABLE.COLUMN_NAME_ID,DatabaseConsts.SANNED_RESULT_TABLE.COLUMN_NAME_IN_DATE,DatabaseConsts.SANNED_RESULT_TABLE.COLUMN_NAME_SCANNED_RESULT
                        ,DatabaseConsts.SANNED_RESULT_TABLE.COLUMN_NAME_FORMAT},
                null,null,null,null," id desc");
        while (cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex(DatabaseConsts.SANNED_RESULT_TABLE.COLUMN_NAME_ID));
            long inDate=cursor.getLong(cursor.getColumnIndex(DatabaseConsts.SANNED_RESULT_TABLE.COLUMN_NAME_IN_DATE));
            String resultText=cursor.getString(cursor.getColumnIndex(DatabaseConsts.SANNED_RESULT_TABLE.COLUMN_NAME_SCANNED_RESULT));
            String format=cursor.getString(cursor.getColumnIndex(DatabaseConsts.SANNED_RESULT_TABLE.COLUMN_NAME_FORMAT));
            ScannedResult sr=new ScannedResult();
            sr.setId(id);
            sr.setInDate(inDate);
            sr.setResultText(resultText);
            sr.setFormat(format);
            result.add(sr);
        }
        cursor.close();
        return result;
    }
}
