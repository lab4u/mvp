package com.lab4u.lab4uphysis.view;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.androidplot.Plot;
import com.androidplot.util.Redrawer;
import com.lab4u.LAB4UTAG;
import com.lab4u.lab4physis.R;
import com.lab4u.lab4uphysis.control.SensorListViewItemControl;
import com.lab4u.lab4uphysis.model.SensorListViewItemModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ajperalt on 28/09/13.
 */
public class AdapterSensorListView  extends ArrayAdapter<SensorListViewItemView> {

    private String TAG = LAB4UTAG.T + AdapterSensorListView.class.getSimpleName();
    private int typeSensorList = Sensor.TYPE_ALL;

    //private Redrawer redrawer;

    /**
     * {@link android.hardware.Sensor#TYPE_ALL Sensor.TYPE_ALL} to get all the
     * sensors.
     *
     * @param context
     * @param resource
     * @param sm
     * @param typeSensorList
     */
    public AdapterSensorListView(Context context, int resource, SensorManager sm,int typeSensorList) {
        super(context, resource);

        this.typeSensorList = typeSensorList;
        int sensorSize = sm.getSensorList(this.typeSensorList).size();
        LayoutInflater inf = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        List<SensorListViewItemView> listSlvi = new ArrayList<SensorListViewItemView>();
        for(int isensor=0;isensor < sensorSize ; isensor++){
            listSlvi.add(createNewView(inf,null));
        }
    }

    @Override
    public SensorListViewItemView getItem(int position) {
        //Log.d(TAG, "getItem("+position+")");
        SensorListViewItemView v = super.getItem(position);
        return v;
    }

    @Override
    public View getView(int pos, View v, ViewGroup parent) {
        if (v == null) {
            SensorListViewItemView slvi = this.getItem(pos);
            if (slvi == null) {
                // LayoutInflater inf = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                // slvi = createNewView(inf,parent);
            }else{
                v = slvi.getView();
            }
        }
        return v;
    }

    private SensorListViewItemView createNewView( LayoutInflater inf,ViewGroup parent) {
        View v = inf.inflate(R.layout.sensor_list_view_item, parent, false);
        SensorListViewItemView view = new SensorListViewItemView(v);
        view.configureViewModels();
        this.add(view);
        return view;
    }

    public void startSensorListener(SensorManager sm) {
        List<Sensor> sensors1 = sm.getSensorList(typeSensorList);
        SensorListViewItemView newv = null;
        LayoutInflater inf = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int isensor = 0;
        for(Sensor s : sensors1){
            Log.d(TAG, "Add SensorListener["+s.getName()+"]");
            newv = this.getItem(isensor);
            if(newv != null){
                newv.getControl().registerListener(s, sm);
            }
            isensor++;
        }
        //redrawer.start();
    }

    public void stopSensorListener(SensorManager sm) {
        List<Sensor> sensors1 = sm.getSensorList(typeSensorList);
        SensorListViewItemView newv = null;
        int isensor = 0;
        for(Sensor s : sensors1){
            Log.d(TAG, "Remove SensorListener["+s.getName()+"]");
            newv = this.getItem(isensor);
            if(newv != null){
                newv.getControl().unregisterListener(sm);
            }
            isensor++;
        }
        //redrawer.pause();
    }
}
