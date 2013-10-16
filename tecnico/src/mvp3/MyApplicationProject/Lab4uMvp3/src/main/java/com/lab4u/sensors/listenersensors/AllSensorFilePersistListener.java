package com.lab4u.sensors.listenersensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import com.lab4u.sensors.persistence.FilePersistSensorInfo;
import com.lab4u.sensors.persistence.ILab4uSensorPersistence;
import com.lab4u.sensors.persistence.Lab4uSensorEmptyPersistence;

/**
 * Created by ajperalt on 15/09/13.
 */
public class AllSensorFilePersistListener implements Lab4uSensorEventListener {

    private ILab4uSensorPersistence persistence = Lab4uSensorEmptyPersistence.getInstance();

    private String name = "Lab4U";

    public AllSensorFilePersistListener(Sensor sensor){
        name += sensor.getName().trim();
        name = name.replaceAll(" ","");
        this.persistence = new FilePersistSensorInfo(name);
    }

    public void onSensorChanged(SensorEvent event){
        float values[] = event.values;
        persistence.print(values);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    public void close(){
        this.persistence.close();
    }

    @Override
    public String getName() {
        return AllSensorFilePersistListener.class.getName();
    }
}
