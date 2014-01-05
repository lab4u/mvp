package com.lab4u.lab4umvp3.bussines;

import java.util.List;

import lab4u.network.integration.socketmngr.ISocketNewConnectionEvent;
import lab4u.network.integration.socketmngr.SocketThread;
import lab4u.network.integration.socketmngr.TcpMultiServer;

import org.json.JSONException;
import org.json.JSONObject;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.util.Log;

import com.lab4u.LAB4UTAG;
import com.lab4u.lab4umvp3.control.SensorModelControl;
import com.lab4u.sensors.listenersensors.Lab4uSensorEventListener;

/**
 * Created by ajperalt on 11/10/13.
 */
public final class Lab4uBC implements ISocketNewConnectionEvent,
		Lab4uSensorEventListener {

	private String TAG = LAB4UTAG.T + Lab4uBC.class.getSimpleName();

	private TcpMultiServer tcpMultiServer;

	private static Lab4uBC instance;

	private ThreadInitServices threadInitServices;

	private Lab4uBC() {
		threadInitServices = new ThreadInitServices();
		threadInitServices.start();
	}

	public static Lab4uBC getInstance() {
		if (instance == null) {
			instance = new Lab4uBC();
		}
		return instance;
	}

	@Override
	public void onConnectionStart(SocketThread socketThread) {

	}

	@Override
	public String getName() {
		return Lab4uBC.class.getSimpleName();
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		JSONObject item = null;
		try {
			if (tcpMultiServer != null) {
				for (SocketThread st : tcpMultiServer.getSocketList()) {
					item = new JSONObject();
					item.put("_id", event.sensor.getName()).put("values",
							event.values);
					st.sendMessage(item.toString());
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	private class ThreadInitServices extends Thread {
		@Override
		public void run() {
			instance.initServerListener();
		}
	}

	public void initServerListener() {
		tcpMultiServer = new TcpMultiServer();
		tcpMultiServer.start();

		// CONTINUE WITH THE LISTENER FOR NEW CLIENTS
		tcpMultiServer.addEventNewConnection(this);
	}

	public void startSensorListener(SensorManager sm) {
		List<Sensor> sensors1 = sm.getSensorList(Sensor.TYPE_ALL);
		for (Sensor s : sensors1) {
			Log.d(TAG, "Add SensorListener[" + s.getName() + "]");
			sm.registerListener(this, s, SensorModelControl.SENSOR_DELAY);
		}
	}

	public void stopSensorListener(SensorManager sm) {
		List<Sensor> sensors1 = sm.getSensorList(Sensor.TYPE_ALL);
		for (Sensor s : sensors1) {
			Log.d(TAG, "Remove SensorListener[" + s.getName() + "]");
			sm.unregisterListener(this);
		}
	}

}
