package com.lab4u.lab4uphysis.view;

import android.content.Context;
import android.view.View;

import com.lab4u.lab4uphysis.control.SensorListViewItemControl;
import com.lab4u.lab4uphysis.model.SensorListViewItemModel;

/**
 * Created by ajperalt on 29/09/13.
 */
public class SensorListViewItemView {

    private SensorListViewItemControl control;
    private View view;
    private SensorListViewItemModel model;

    public SensorListViewItemView(View v) {
        this.view = v;
        this.control = new SensorListViewItemControl();
        this.model = getControl().getModel();
    }


    public SensorListViewItemControl getControl() {
        return control;
    }

    public void setControl(SensorListViewItemControl control) {
        this.control = control;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void configureViewModels() {
        this.getControl().configureViewModels(getView());
    }

    public SensorListViewItemModel getModel() {
        return model;
    }

    public void setModel(SensorListViewItemModel model) {
        this.model = model;
    }
}
