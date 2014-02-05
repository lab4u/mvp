package com.lab4u.lab4uphysis.model;

import android.hardware.Sensor;

import com.androidplot.xy.XYPlot;
import com.lab4u.sensors.persistence.FilePersistSensorInfo;
import com.lab4u.sensors.persistence.ILab4uSensorPersistence;
import com.lab4u.sensors.persistence.Lab4uSensorEmptyPersistence;

/**
 * 
 * @author ajperalt
 * Se agrega comentario para verificacion
 * 
 */
public class Lab4uSensorAllXYSeries {

	private Sensor s;

	private Lab4uSimpleXYSeries x, y, z;

	private ILab4uSensorPersistence persistence = Lab4uSensorEmptyPersistence
			.getInstance();

	public void registerSensor(Sensor s) {
		this.s = s;
		String name = "";
		switch (s.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			name = "A";
			break;
		case Sensor.TYPE_LINEAR_ACCELERATION:
			name = "L";
			break;
		case Sensor.TYPE_GRAVITY:
			name = "G";
			break;
		default:
			name = "?";
			break;
		}
		x = new Lab4uSimpleXYSeries("X" + name);
		y = new Lab4uSimpleXYSeries("Y" + name);
		z = new Lab4uSimpleXYSeries("Z" + name);

		persistence = new FilePersistSensorInfo(s.getName());
	}

	public Lab4uSimpleXYSeries getX() {
		return x;
	}

	public void setX(Lab4uSimpleXYSeries x) {
		this.x = x;
	}

	public Lab4uSimpleXYSeries getY() {
		return y;
	}

	public void setY(Lab4uSimpleXYSeries y) {
		this.y = y;
	}

	public Lab4uSimpleXYSeries getZ() {
		return z;
	}

	public void setZ(Lab4uSimpleXYSeries z) {
		this.z = z;
	}

	public void verifiedSizeToPlot() {
		// get rid the oldest sample in history:
		if (x.size() > SensorListViewItemModel.HISTORY_SIZE) {
			x.removeFirst();
			y.removeFirst();
			z.removeFirst();
		}
	}

	public void addValues(float[] values) {
		// add the latest history sample:
		x.addLast(null, values[0]);
		y.addLast(null, values[1]);
		z.addLast(null, values[2]);

		persistence.print(values);
	}

	public void addMeToXYPlot(XYPlot xyplot) {
		x.addMeToXYPlot(xyplot);
		y.addMeToXYPlot(xyplot);
		z.addMeToXYPlot(xyplot);
	}

	public void close() {
		persistence.close();
		persistence = Lab4uSensorEmptyPersistence.getInstance();
	}

}
