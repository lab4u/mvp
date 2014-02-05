package com.lab4u.lab4uphysis.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.widget.TextView;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYStepMode;
import com.lab4u.lab4physis.R;

/**
 * Created by ajperalt on 3/10/13.
 */
public class SensorPlotActivityModel {

	public static final int HISTORY_SIZE = 300; // number of points to plot in
												// history

	private XYPlot aprHistoryPlot = null;

	private Map<String, Lab4uSensorAllXYSeries> lstSensorSeries = new HashMap<String, Lab4uSensorAllXYSeries>();

	private TextView txtSensorName = null;

	private Activity myActivity;

	private Number rangeBoundaries = 10;

	/**
	 * 
	 * @param a
	 */
	public void configureViewModels(Activity a) {

		this.myActivity = a;
		Resources r = a.getResources();
		this.aprHistoryPlot = (XYPlot) this.myActivity
				.findViewById(R.id.aprHistoryPlot);
		this.txtSensorName = (TextView) this.myActivity
				.findViewById(R.id.txtSensorName);

		aprHistoryPlot.setRangeBoundaries(rangeBoundaries.intValue() * -1,
				rangeBoundaries, BoundaryMode.FIXED);
		aprHistoryPlot.setDomainBoundaries(0, HISTORY_SIZE, BoundaryMode.FIXED);

		aprHistoryPlot.setDomainStepMode(XYStepMode.INCREMENT_BY_VAL);
		aprHistoryPlot.setDomainStepValue(HISTORY_SIZE / 10);
		aprHistoryPlot.setTicksPerRangeLabel(3);
		aprHistoryPlot.setDomainLabel(r.getString(R.string.sensorPlotLabelX));
		aprHistoryPlot.getDomainLabelWidget().pack();
		aprHistoryPlot.setRangeLabel(r.getString(R.string.sensorPlotLabelY));
		aprHistoryPlot.getRangeLabelWidget().pack();

		aprHistoryPlot.setRangeValueFormat(new DecimalFormat("#"));
		aprHistoryPlot.setDomainValueFormat(new DecimalFormat("#"));
	}

	public XYPlot getAprHistoryPlot() {
		return aprHistoryPlot;
	}

	public void setAprHistoryPlot(XYPlot aprHistoryPlot) {
		this.aprHistoryPlot = aprHistoryPlot;
	}

	public TextView getTxtSensorName() {
		return txtSensorName;
	}

	public void setTxtSensorName(TextView txtSensorName) {
		this.txtSensorName = txtSensorName;
	}

	public Number getRangeBoundaries() {
		return rangeBoundaries;
	}

	public void setRangeBoundaries(Number rangeBoundaries) {
		this.rangeBoundaries = rangeBoundaries;
		aprHistoryPlot.setRangeBoundaries(rangeBoundaries.intValue() * -1,
				rangeBoundaries, BoundaryMode.FIXED);
	}

	public Lab4uSensorAllXYSeries getSensorAllXYSeries(Sensor sensor) {
		Lab4uSensorAllXYSeries serie = lstSensorSeries.get(sensor.getName());
		if (serie == null) {
			serie = new Lab4uSensorAllXYSeries();
			serie.registerSensor(sensor);
			serie.addMeToXYPlot(aprHistoryPlot);
			lstSensorSeries.put(sensor.getName(), serie);
			
		}
		return serie;
	}
}
