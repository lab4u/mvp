package com.lab4u.lab4umvp3.view;

import com.lab4u.lab4umvp3.R;
import com.lab4u.lab4umvp3.view.AdapterSensorListView;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 *
 */
public class MainActivity extends Activity {

    private AdapterSensorListView adapterSensorListView = null;
    private ListView lvSensorInfo = null;
    private SensorManager sm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        this.setContentView(R.layout.main_activity);
        this.configSensorListView();
        this.addBtnAction();

    }

    private void configSensorListView() {
        lvSensorInfo = (ListView)findViewById(R.id.lvSensorInfo);
        adapterSensorListView = new AdapterSensorListView
                (this.getApplicationContext(),R.layout.sensor_list_view_item,sm, Sensor.TYPE_ALL);
        lvSensorInfo.setAdapter(adapterSensorListView);
    }

    private void addBtnAction() {
        Button btnStartSensorListener = (Button)findViewById(R.id.btnStartSensorListener);
        btnStartSensorListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterSensorListView.startSensorListener(sm);
            }
        });

        Button btnStopSensorListener = (Button)findViewById(R.id.btnStopSensorListener);
        btnStopSensorListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterSensorListView.stopSensorListener(sm);
            }
        });




        lvSensorInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(MainActivity.this, SensorPlotActivity.class);
                int sensorType =  adapterSensorListView.getItem(position).getControl().getMySensor().getType();

                intent.putExtra(SensorPlotActivity.SENSOR_TYPE,sensorType);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        adapterSensorListView.startSensorListener(sm);
        super.onResume();
    }
    @Override
    protected void onPause() {
        adapterSensorListView.stopSensorListener(sm);
        super.onPause();
    }
    @Override
    protected void onStop() {
        adapterSensorListView.stopSensorListener(sm);
        super.onStop();
    }

}
