package com.lab4u.lab4uphysis.view;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

import com.lab4u.lab4physis.R;
import com.lab4u.lab4uphysis.control.SensorPlotActivityControl;
import com.lab4u.lab4uphysis.model.SensorPlotActivityModel;

public class SensorPlotActivity extends Activity {

    public static final String SENSOR_TYPE = "SENSOR_TYPE";
    private SensorPlotActivityControl control;
    private SensorPlotActivityModel model;
    private SensorManager sm = null;
    private int sensorType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_plot_activity);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        Bundle extras = getIntent().getExtras();
        sensorType  = extras.getInt(SENSOR_TYPE);

        model  = new SensorPlotActivityModel();
        model.configureViewModels(this);
        control = new SensorPlotActivityControl();
        control.setModel(model);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sensor_plot, menu);
        return true;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        for(Sensor s : sm.getSensorList(sensorType)){
            control.registerListener(s,sm);
        }
        super.onResume();
    }
    @Override
    protected void onPause() {
        control.unregisterListener(sm);
        super.onPause();
    }
    @Override
    protected void onStop() {
        control.unregisterListener(sm);
        super.onStop();
    }
}
