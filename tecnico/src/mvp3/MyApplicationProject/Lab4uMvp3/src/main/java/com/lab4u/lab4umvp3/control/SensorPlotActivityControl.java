package com.lab4u.lab4umvp3.control;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

import com.androidplot.util.Redrawer;
import com.lab4u.LAB4UTAG;
import com.lab4u.lab4umvp3.model.SensorListViewItemModel;
import com.lab4u.lab4umvp3.model.SensorPlotActivityModel;
import com.lab4u.sensors.listenersensors.Lab4uSensorEventListener;
import com.lab4u.sensors.persistence.FilePersistSensorInfo;

/**
 * Created by ajperalt on 3/10/13.
 */
public class SensorPlotActivityControl implements Lab4uSensorEventListener {
    private static String TAG = LAB4UTAG.T + SensorPlotActivityControl.class.getSimpleName();

    private SensorPlotActivityModel model;

    private static long delayToRedraw = 5 * 1000;
    private long actualTime = 0l;

    private Sensor mySensor;

    private Redrawer redrawer;

    @Override
    public String getName() {
        return "SensorPlotActivityControl";
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // get rid the oldest sample in history:
        if (model.getRollHistorySeries().size() > SensorListViewItemModel.HISTORY_SIZE) {
            model.getRollHistorySeries().removeFirst();
            model.getPitchHistorySeries().removeFirst();
            model.getAzimuthHistorySeries().removeFirst();
        }

        // add the latest history sample:
        model.getAzimuthHistorySeries().addLast(null, event.values[0]);
        model.getPitchHistorySeries().addLast(null, event.values[1]);
        model.getRollHistorySeries().addLast(null, event.values[2]);

        //redrawPlot();
    }


    /**
     *
     */
    private void redrawPlot() {
        if(System.currentTimeMillis() - actualTime > delayToRedraw){
            actualTime = System.currentTimeMillis();
            this.getModel().getAprHistoryPlot().redraw();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void registerListener(Sensor s, SensorManager sm) {
        this.mySensor = s;
        this.model.getTxtSensorName().setText(this.mySensor.getName());
        sm.registerListener(this,s,SensorModelControl.SENSOR_DELAY);
        redrawer = new Redrawer(this.getModel().getAprHistoryPlot(),500,false);
        redrawer.start();
    }

    public void unregisterListener(SensorManager sm) {
        sm.unregisterListener(this,this.mySensor);
        if(redrawer != null){
            redrawer.finish();
            redrawer = null;
        }
    }

    public SensorPlotActivityModel getModel() {
        return model;
    }

    public void setModel(SensorPlotActivityModel model) {
        this.model = model;
    }
}
