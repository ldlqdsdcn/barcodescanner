package indi.solomon.zxingscanning;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.List;

import indi.solomon.zxingscanning.adaper.ScannedResultAdaper;
import indi.solomon.zxingscanning.dao.ScannedResultDao;
import indi.solomon.zxingscanning.po.ScannedResult;

public class MainActivity extends AppCompatActivity {
    private static final int ZXING_CAMERA_PERMISSION = 1;
    private Class<?> mClss;
    private ScannedResultDao scannedResultDao;
    private ListView listItem;
    private ScannedResultAdaper scannedResultAdaper = null;
    private CheckBox checkAll;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listItem = (ListView) findViewById(R.id.list_item);
        findViewById(R.id.btn_scanning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(ScanningActivity.class);
            }
        });
        checkAll=(CheckBox) findViewById(R.id.checkAll);
        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                scannedResultAdaper.updateDelFlag(isChecked);
                scannedResultAdaper.notifyDataSetChanged();

            }
        });
        findViewById(R.id.but_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("确认删除")
                        .setMessage("确认删除选择记录吗?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                List<ScannedResult> list= scannedResultAdaper.getListModel();
                                for(ScannedResult scannedResult:list)
                                {
                                    if(scannedResult.isDelFlag())
                                        scannedResultDao.deleteScannedResult(scannedResult.getId());
                                }
                                init();
                            }})
                        .setNegativeButton("No", null)
                        .show();


            }
        });
        scannedResultDao = new ScannedResultDao(this);

    }

    @Override
    protected void onResume() {
        init();
        super.onResume();
    }

    public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClss = clss;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(this, clss);
            startActivity(intent);
        }
    }

    private void init() {
        List<ScannedResult> scannedResultList = scannedResultDao.list();
        Log.d("ZX_MAIN","-------------------scannedResultList.size="+scannedResultList.size());
        if (scannedResultAdaper != null) {
            scannedResultAdaper.updateList(scannedResultList);
            scannedResultAdaper.notifyDataSetInvalidated();
        } else {
             scannedResultAdaper = new ScannedResultAdaper(this, scannedResultList);
            this.listItem.setAdapter(scannedResultAdaper);

        }

    }
}
