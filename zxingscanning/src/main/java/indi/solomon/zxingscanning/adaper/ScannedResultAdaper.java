package indi.solomon.zxingscanning.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import indi.solomon.zxingscanning.R;
import indi.solomon.zxingscanning.po.ScannedResult;

/**
 * Created by admin on 2016/10/24.
 */

public class ScannedResultAdaper extends BaseAdapter {
    private LayoutInflater mInflater = null;
    private List<ScannedResult> list;
    private static final DateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ScannedResultAdaper(Context context, List<ScannedResult> list) {
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    public void updateList(List<ScannedResult> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View item = mInflater.inflate(R.layout.adaper_scanned_result, null);
        TextView inDate = (TextView) item.findViewById(R.id.in_date);
        ScannedResult scannedResult = list.get(i);
        inDate.setText(yyyyMMddHHmmss.format(new Date(scannedResult.getInDate())));
        TextView scannedText = (TextView) item.findViewById(R.id.scanned_text);
        scannedText.setText(scannedResult.getResultText());
        TextView format = (TextView) item.findViewById(R.id.format);
        format.setText(scannedResult.getFormat());
        return item;
    }
}
