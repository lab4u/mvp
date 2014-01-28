package com.lab4u.lab4umvp3.view;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.lab4u.lab4umvp3.R;
import com.lab4u.lab4umvp3.bussines.Lab4uBC;

/**
 * 
 * @author alvarojose
 * 
 */
public class ListAllSensorListenerActivity extends Activity {

	private AdapterSensorListView adapterSensorListView = null;
	private ListView lvSensorInfo = null;
	private SensorManager sm = null;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_all_sensor_listener);
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		/*
		 * Create Lab4uBC singleton with controle the state of
		 * information to share with others devices
		 */
		Lab4uBC.getInstance().startSensorListener(sm);
		this.configSensorListView();
		this.addBtnAction();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_all_sensor_listener, menu);
		return true;
	}


	/**
     *
     */
	private void configSensorListView() {
		lvSensorInfo = (ListView) findViewById(R.id.lvSensorInfo);
		adapterSensorListView = new AdapterSensorListView(
				this.getApplicationContext(), R.layout.sensor_list_view_item,
				sm, Sensor.TYPE_ALL);
		lvSensorInfo.setAdapter(adapterSensorListView);
	}

	/**
     *
     */
	private void addBtnAction() {
		ImageButton btnStartSensorListener = (ImageButton) findViewById(R.id.btnStartSensorListener);
		btnStartSensorListener.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				adapterSensorListView.startSensorListener(sm);
			}
		});

		ImageButton btnStopSensorListener = (ImageButton) findViewById(R.id.btnStopSensorListener);
		btnStopSensorListener.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				adapterSensorListView.stopSensorListener(sm);
			}
		});

		lvSensorInfo
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent = new Intent(
								ListAllSensorListenerActivity.this,
								SensorPlotActivity.class);
						int sensorType = adapterSensorListView
								.getItem(position).getControl().getMySensor()
								.getType();

						intent.putExtra(SensorPlotActivity.SENSOR_TYPE,
								sensorType);
						startActivity(intent);
					}
				});
	}

	/**
     *
     */
	@Override
	protected void onResume() {
		adapterSensorListView.startSensorListener(sm);
		super.onResume();
	}

	/**
     *
     */
	@Override
	protected void onPause() {
		adapterSensorListView.stopSensorListener(sm);
		super.onPause();
	}

	/**
     *
     */
	@Override
	protected void onStop() {
		adapterSensorListView.stopSensorListener(sm);
		super.onStop();
	}

}
