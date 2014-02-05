package com.lab4u.lab4uphysis.control;

import java.util.ArrayList;
import java.util.Collection;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

import com.androidplot.util.Redrawer;
import com.lab4u.LAB4UTAG;
import com.lab4u.lab4uphysis.model.Lab4uSensorAllXYSeries;
import com.lab4u.lab4uphysis.model.SensorPlotActivityModel;
import com.lab4u.sensors.listenersensors.Lab4uSensorEventListener;
import com.lab4u.sensors.persistence.FilePersistSensorInfo;
import com.lab4u.sensors.persistence.ILab4uSensorPersistence;
import com.lab4u.sensors.persistence.Lab4uSensorEmptyPersistence;

/**
 * Created by ajperalt on 3/10/13.
 */
public class SensorPlotActivityControl implements Lab4uSensorEventListener {
	private static String TAG = LAB4UTAG.T
			+ SensorPlotActivityControl.class.getSimpleName();

	private SensorPlotActivityModel model;

	private static long delayToRedraw = 5 * 1000;
	private long actualTime = 0l;

	private Collection<Sensor> mySensors = null;

	private Redrawer redrawer;

	

	public SensorPlotActivityControl() {
		mySensors = new ArrayList<Sensor>();
	}

	@Override
	public String getName() {
		return "SensorPlotActivityControl";
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		Lab4uSensorAllXYSeries sensorAllS = this.model
				.getSensorAllXYSeries(event.sensor);
		sensorAllS.verifiedSizeToPlot();
		sensorAllS.addValues(event.values);
	}

	/**
     *
     */
	private void redrawPlot() {
		if (System.currentTimeMillis() - actualTime > delayToRedraw) {
			actualTime = System.currentTimeMillis();
			this.getModel().getAprHistoryPlot().redraw();
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	public void registerListener(Sensor s, SensorManager sm) {
		this.mySensors.add(s);
		this.model.getTxtSensorName().setText(s.getName());
		sm.registerListener(this, s, SensorModelControl.SENSOR_DELAY);
		redrawer = new Redrawer(this.getModel().getAprHistoryPlot(), 500, false);
		redrawer.start();
		
	}

	public void unregisterListener(SensorManager sm) {
		for (Sensor s : this.mySensors) {
			sm.unregisterListener(this, s);
			Lab4uSensorAllXYSeries sensorAllS = this.model
					.getSensorAllXYSeries(s);
			sensorAllS.close();
		}
		if (redrawer != null) {
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
