package co.lab4u.instruments;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class LabViewer extends Activity {
	
	public static final float BASE_FONT_SIZE = 21;
	public static final float INTERVAL_FOR_FONT_SIZE = 2;
	public static final float MAX_FONT_SIZE = 48;
	public static final float MIN_FONT_SIZE = 18;
	
	ZoomControls zoomControls;
	TextView titleTextView;
	TextView contentTextView;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lab_viewer);
		
		Intent intent = getIntent();
	
		initWidgets();
		
		handlingZoomControlsEvents();
		
		Bundle b = intent.getBundleExtra(Const.BUNDLE_GENERIC_KEY);
		
		setTextToWidgetsFrom(b);
	}

	private void setTextToWidgetsFrom(Bundle b) {
		titleTextView.setText(b.getString(Const.LAB_TITLE_KEY));
		contentTextView.setText(Html.fromHtml(b.getString(Const.LAB_CONTENT_KEY)));
	}

	private void initWidgets() {
		titleTextView = (TextView) this.findViewById(R.id.labTitle);
		contentTextView = (TextView) this.findViewById(R.id.labContent);
		zoomControls = (ZoomControls)this.findViewById(R.id.labViewerZoomControls);
	}

	private void handlingZoomControlsEvents() {
		zoomControls.setOnZoomInClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				float currentSize = contentTextView.getTextSize();
				
				if (currentSize < MAX_FONT_SIZE) {
					titleTextView.setTextSize(23);
					contentTextView.setTextSize(23);
				}
			}
		});
		
		zoomControls.setOnZoomOutClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				float currentSize = contentTextView.getTextSize();
				
				if (currentSize > MIN_FONT_SIZE) {
					titleTextView.setTextSize(16);
					contentTextView.setTextSize(16);
				}
			}
		});
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.lab_viewer, menu);
		 
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