package indi.solomon.zxingscanning;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listItem = (ListView) findViewById(R.id.list_item);
        findViewById(R.id.btn_scanning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(ScanningActivity.class);
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
            ScannedResultAdaper scannedResultAdaper = new ScannedResultAdaper(this, scannedResultList);
            this.listItem.setAdapter(scannedResultAdaper);
        }

    }
}
