package com.lab4u.lab4uphysis.model;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.widget.TextView;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYStepMode;
import com.lab4u.lab4physis.R;

/**
 * Created by ajperalt on 3/10/13.
 */
public class SensorPlotActivityModel {

    public static final int HISTORY_SIZE = 300;            // number of points to plot in history


    private XYPlot aprHistoryPlot = null;

    private SimpleXYSeries azimuthHistorySeries = null;
    private SimpleXYSeries pitchHistorySeries = null;
    private SimpleXYSeries rollHistorySeries = null;
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
        this.aprHistoryPlot = (XYPlot) this.myActivity.findViewById(R.id.aprHistoryPlot);
        this.txtSensorName = (TextView)this.myActivity.findViewById(R.id.txtSensorName);

        azimuthHistorySeries = new SimpleXYSeries("X");
        azimuthHistorySeries.useImplicitXVals();
        pitchHistorySeries = new SimpleXYSeries("Y");
        pitchHistorySeries.useImplicitXVals();
        rollHistorySeries = new SimpleXYSeries("Z");
        rollHistorySeries.useImplicitXVals();


        aprHistoryPlot.setRangeBoundaries(rangeBoundaries.intValue() * -1 ,rangeBoundaries, BoundaryMode.FIXED);
        aprHistoryPlot.setDomainBoundaries(0, HISTORY_SIZE, BoundaryMode.FIXED);
        aprHistoryPlot.addSeries(azimuthHistorySeries,
                new LineAndPointFormatter(
                        Color.rgb(100, 100, 200), null, null, null));
        aprHistoryPlot.addSeries(pitchHistorySeries,
                new LineAndPointFormatter(
                        Color.rgb(100, 200, 100), null, null, null));
        aprHistoryPlot.addSeries(rollHistorySeries,
                new LineAndPointFormatter(
                        Color.rgb(200, 100, 100), null, null, null));
        aprHistoryPlot.setDomainStepMode(XYStepMode.INCREMENT_BY_VAL);
        aprHistoryPlot.setDomainStepValue(HISTORY_SIZE/10);
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

    public SimpleXYSeries getAzimuthHistorySeries() {
        return azimuthHistorySeries;
    }

    public void setAzimuthHistorySeries(SimpleXYSeries azimuthHistorySeries) {
        this.azimuthHistorySeries = azimuthHistorySeries;
    }

    public SimpleXYSeries getPitchHistorySeries() {
        return pitchHistorySeries;
    }

    public void setPitchHistorySeries(SimpleXYSeries pitchHistorySeries) {
        this.pitchHistorySeries = pitchHistorySeries;
    }

    public SimpleXYSeries getRollHistorySeries() {
        return rollHistorySeries;
    }

    public void setRollHistorySeries(SimpleXYSeries rollHistorySeries) {
        this.rollHistorySeries = rollHistorySeries;
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
		aprHistoryPlot.setRangeBoundaries(rangeBoundaries.intValue() * -1 ,rangeBoundaries, BoundaryMode.FIXED);
	}
    
    
}
