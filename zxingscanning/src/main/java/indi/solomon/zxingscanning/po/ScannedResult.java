package indi.solomon.zxingscanning.po;

import android.content.ContentValues;

import indi.solomon.zxingscanning.dao.DatabaseConsts;

/**
 * Created by admin on 2016/10/24.
 */

public class ScannedResult {
    private Integer id;
    private String resultText;
    private Long inDate;
    private String format;
    private boolean delFlag;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResultText() {
        return resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    public Long getInDate() {
        return inDate;
    }

    public void setInDate(Long inDate) {
        this.inDate = inDate;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public ContentValues toInsertValues()
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseConsts.SANNED_RESULT_TABLE.COLUMN_NAME_SCANNED_RESULT,resultText);
        contentValues.put(DatabaseConsts.SANNED_RESULT_TABLE.COLUMN_NAME_FORMAT,format);
        contentValues.put(DatabaseConsts.SANNED_RESULT_TABLE.COLUMN_NAME_IN_DATE,inDate);
        return contentValues;
    }
}
