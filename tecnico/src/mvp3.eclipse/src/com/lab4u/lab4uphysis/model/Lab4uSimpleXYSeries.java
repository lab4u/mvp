package com.lab4u.lab4uphysis.model;

import java.util.Random;

import android.graphics.Color;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;

public class Lab4uSimpleXYSeries extends SimpleXYSeries {
	
	PointLabelFormatter f = new PointLabelFormatter(1);
	
	public Lab4uSimpleXYSeries(String arg0) {
		super(arg0);
		this.useImplicitXVals();
	}

	public void addMeToXYPlot(XYPlot xyplot) {
		Random r = new Random();
		xyplot.addSeries(
				this,
				new LineAndPointFormatter(Color.rgb(r.nextInt(255),
						r.nextInt(255), r.nextInt(255)), null, null, null));
	}

	public void addMeToXYPlot(XYPlot xyplot, Color c) {
		xyplot.addSeries(this, new LineAndPointFormatter(c.red(1), c.green(1),
				c.blue(1), null));
	}
}
