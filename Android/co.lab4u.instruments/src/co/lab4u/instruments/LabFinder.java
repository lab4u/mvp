package co.lab4u.instruments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import co.lab4u.instruments.adapters.LabItemAdapter;
import co.lab4u.instruments.models.ILaboratory;
import co.lab4u.instruments.models.Laboratory;
import co.lab4u.instruments.models.LaboratoryStaticFactory;

public class LabFinder extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lab_finder_item);
        
        List<ILaboratory> labs = new ArrayList<ILaboratory>() {{
        	add(LaboratoryStaticFactory.getInstance().CreateLaboratory(1, "Some title 1", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat", Calendar.getInstance(), Calendar.getInstance()));
        	add(LaboratoryStaticFactory.getInstance().CreateLaboratory(1, "Some title 2", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat", Calendar.getInstance(), Calendar.getInstance()));
        	add(LaboratoryStaticFactory.getInstance().CreateLaboratory(1, "Some title 3", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat", Calendar.getInstance(), Calendar.getInstance()));
        	add(LaboratoryStaticFactory.getInstance().CreateLaboratory(1, "Some title 4", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat", Calendar.getInstance(), Calendar.getInstance()));
        }};
        
        ListAdapter adapter = new LabItemAdapter(this, labs);
        
        // bind to adapter
        setListAdapter(adapter);
        
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }        
}
