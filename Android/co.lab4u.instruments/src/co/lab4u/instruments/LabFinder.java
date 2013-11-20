package co.lab4u.instruments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import co.lab4u.instruments.adapters.LabItemAdapter;
import co.lab4u.instruments.helpers.JsonParser;
import co.lab4u.instruments.models.ILaboratory;
import co.lab4u.instruments.models.LaboratoryStaticFactory;
import co.lab4u.instruments.proxies.ILabPlatformProxy;
import co.lab4u.instruments.proxies.LabPlatformProxy;

public class LabFinder extends ListActivity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ILabPlatformProxy proxy = new LabPlatformProxy();
        ILaboratory lab = proxy.getLaboratory(1045);
        
        List<ILaboratory> labs = new ArrayList<ILaboratory>() {{
        	add(LaboratoryStaticFactory.getInstance().CreateLaboratory(1, "Some title 1", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat", Calendar.getInstance(), Calendar.getInstance()));
        	add(LaboratoryStaticFactory.getInstance().CreateLaboratory(2, "Some title 2", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat", Calendar.getInstance(), Calendar.getInstance()));
        	add(LaboratoryStaticFactory.getInstance().CreateLaboratory(3, "Some title 3", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat", Calendar.getInstance(), Calendar.getInstance()));
        	add(LaboratoryStaticFactory.getInstance().CreateLaboratory(4, "Some title 4", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat", Calendar.getInstance(), Calendar.getInstance()));
        }};
        
        ListAdapter adapter = new LabItemAdapter(this, labs);
        
        // bind to adapter
        setListAdapter(adapter);
        
        ListView listView = getListView();
        
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				Intent intent = new Intent(LabFinder.this, LabViewer.class);
				
				TextView titleView = (TextView)view.findViewById(R.id.labTitle);
				TextView contentView = (TextView)view.findViewById(R.id.labContent);
				
			    Bundle bundle = new Bundle();
			    bundle.putString(Const.LAB_TITLE_KEY, titleView.getText().toString());
			    bundle.putString(Const.LAB_CONTENT_KEY, contentView.getText().toString());
			    
			    intent.putExtra(Const.BUNDLE_GENERIC_KEY, bundle);
			    
				startActivity(intent);
			}
        });
        
    }       
    
    
}
