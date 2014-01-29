package com.lab4u.lab4uphysis.model;

import android.graphics.Color;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYStepMode;
import com.lab4u.lab4physis.R;

import java.text.DecimalFormat;

/**
 * Created by ajperalt on 29/09/13.
 */
public class SensorListViewItemModel {

    public static final int HISTORY_SIZE = 300;            // number of points to plot in history

    private TextView txtX, txtY, txtZ,txtSensorName;
    private View myView;


    public TextView getTxtX() {
        return txtX;
    }

    public void setTxtX(TextView txtX) {
        this.txtX = txtX;
    }

    public TextView getTxtY() {
        return txtY;
    }

    public void setTxtY(TextView txtY) {
        this.txtY = txtY;
    }

    public TextView getTxtZ() {
        return txtZ;
    }

    public void setTxtZ(TextView txtZ) {
        this.txtZ = txtZ;
    }

    public TextView getTxtSensorName() {
        return txtSensorName;
    }

    public void setTxtSensorName(TextView txtSensorName) {
        this.txtSensorName = txtSensorName;
    }

    public void configureViewModels(View v) {
        this.myView = v;
        this.setTxtSensorName((TextView) this.myView.findViewById(R.id.txtSensorName));
        this.setTxtX((TextView) this.myView.findViewById(R.id.txtX));
        this.setTxtY((TextView) this.myView.findViewById(R.id.txtY));
        this.setTxtZ((TextView) this.myView.findViewById(R.id.txtZ));

    }

    public View getMyView(){
        return myView;
    }

    public void setMyView(View myView) {
        this.myView = myView;
    }


}
