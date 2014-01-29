package com.lab4u.lab4uphysis.control;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lab4u.LAB4UTAG;
import com.lab4u.lab4physis.R;
import com.lab4u.lab4uphysis.model.SensorListViewItemModel;
import com.lab4u.lab4uphysis.view.AdapterSensorListView;
import com.lab4u.sensors.listenersensors.Lab4uSensorEventListener;
import com.lab4u.sensors.persistence.FilePersistSensorInfo;
import com.lab4u.sensors.persistence.ILab4uSensorPersistence;
import com.lab4u.sensors.persistence.Lab4uSensorEmptyPersistence;

/**
 * Created by ajperalt on 29/09/13.
 */
public class SensorListViewItemControl  implements Lab4uSensorEventListener {
    private static String TAG = LAB4UTAG.T + SensorListViewItemControl.class.getSimpleName();

    private SensorListViewItemModel model;
    private ILab4uSensorPersistence persistence = Lab4uSensorEmptyPersistence.getInstance();
    private Sensor mySensor;

    public SensorListViewItemControl(SensorListViewItemModel model) {
        this.model = model;
    }

    public SensorListViewItemControl() {
        model = new SensorListViewItemModel();
    }

    @Override
    public String getName() {
        return "SensorListViewItemControl";
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        model.getTxtX().setText("X:"+event.values[0]);
        model.getTxtY().setText("Y:"+event.values[1]);
        model.getTxtZ().setText("Z:"+event.values[2]);
        if(persistence == null){
            Log.d(TAG,"persistence = null");
        }else{
            persistence.print(event.values);
        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void configureViewModels(View v){
        if(this.model == null){
            this.model = new SensorListViewItemModel();
        }
        this.model.configureViewModels(v);
    }

    public void registerListener(Sensor s, SensorManager sm) {
        this.mySensor = s;
        this.model.getTxtSensorName().setText(this.mySensor.getName());
        persistence = new FilePersistSensorInfo(this.mySensor.getName());
        sm.registerListener(this,s,SensorModelControl.SENSOR_DELAY);
    }

    public SensorListViewItemModel getModel() {
        return model;
    }

    public void setModel(SensorListViewItemModel model) {
        this.model = model;
    }

    public void unregisterListener(SensorManager sm) {
        sm.unregisterListener(this,this.mySensor);
        if(persistence != null &&
                ! persistence.equals(Lab4uSensorEmptyPersistence.getInstance())){
            persistence.close();
            persistence = Lab4uSensorEmptyPersistence.getInstance();
        }
    }

    public Sensor getMySensor() {
        return mySensor;
    }

    public void setMySensor(Sensor mySensor) {
        this.mySensor = mySensor;
    }
}
