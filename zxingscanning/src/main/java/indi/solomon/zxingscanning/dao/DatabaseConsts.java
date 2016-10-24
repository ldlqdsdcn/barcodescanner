package indi.solomon.zxingscanning.dao;

import android.provider.SyncStateContract;

/**
 * Created by admin on 2016/10/24.
 */

public class DatabaseConsts {
    public static final class  SANNED_RESULT_TABLE
    {
        public static final String TABLE_NAME="scanned_result";
        public static final String COLUMN_NAME_ID= "id" ;
        public static final String COLUMN_NAME_SCANNED_RESULT="scanned_text";
        public static final String COLUMN_NAME_FORMAT="scan_format";
        public static final String COLUMN_NAME_IN_DATE="in_date";
        public static final String COLUMN_TYPE_ID="INTEGER";
        public static final String COLUMN_TYPE_SCANNED_RESULT="TEXT";
        public static final String COLUMN_TYPE_FORMAT="TEXT";
        public static final String COLUMN_IN_DATE="INTEGER";

        public static final String CREATE_TABLE = "Create  TABLE scanned_result(\n" +
                "[id] integer PRIMARY KEY AUTOINCREMENT\n" +
                ",[in_date] integer\n" +
                ",[scan_format] text\n" +
                ",[scanned_text] text\n" +
                "   \n" +
                ")";

    }
}
