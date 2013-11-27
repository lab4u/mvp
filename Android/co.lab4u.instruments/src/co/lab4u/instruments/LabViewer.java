package co.lab4u.instruments;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ZoomControls;
import android.support.v4.app.NavUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class LabViewer extends Activity {
	
	public static final float BASE_FONT_SIZE = 10;
	public static final float INTERVAL_FOR_FONT_SIZE = 2;
	public static final float MAX_FONT_SIZE = 24;
	public static final float MIN_FONT_SIZE = 8;
	
	ZoomControls zoomControls;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lab_viewer);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Intent intent = getIntent();
	
		zoomControls = (ZoomControls)this.findViewById(R.id.labViewerZoomControls); 
		TextView titleTextView = (TextView) this.findViewById(R.id.labTitle);
		final TextView contentTextView = (TextView) this.findViewById(R.id.labContent);
		
		Bundle b = intent.getBundleExtra(Const.BUNDLE_GENERIC_KEY);
		
		titleTextView.setText(b.getString(Const.LAB_TITLE_KEY));
		contentTextView.setText(b.getString(Const.LAB_CONTENT_KEY));
		
		zoomControls.setOnZoomInClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				float currentSize = contentTextView.getTextSize();
				
				if (currentSize < MAX_FONT_SIZE)
					contentTextView.setTextSize(currentSize + INTERVAL_FOR_FONT_SIZE);
			}
		});
		
		zoomControls.setOnZoomOutClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				float currentSize = contentTextView.getTextSize();
				
				if (currentSize < MIN_FONT_SIZE)
					contentTextView.setTextSize(currentSize - INTERVAL_FOR_FONT_SIZE);
			}
		});
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lab_viewer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
