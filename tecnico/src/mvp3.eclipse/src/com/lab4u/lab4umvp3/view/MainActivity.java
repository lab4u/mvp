package com.lab4u.lab4umvp3.view;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lab4u.LAB4UTAG;
import com.lab4u.lab4umvp3.R;
import com.lab4u.lab4umvp3.bussines.Lab4uBC;

/**
 *
 */
public class MainActivity extends Activity {

	/**
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main_activity);
		this.addBtnAction();
	}

	/**
     *
     */
	private void addBtnAction() {
		Button btnMainActivityStart = (Button) findViewById(R.id.btnMainActivityStart);
		btnMainActivityStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this,
						ListAllSensorListenerActivity.class);
				startActivity(intent);
			}
		});

	}
	
	@Override
	protected void onStop() {
		try{
			SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
			Lab4uBC.getInstance().stopSensorListener(sm);
		}catch(Exception e){
			Log.e(LAB4UTAG.T,e.getMessage());
		}
		super.onStop();
	}

}
