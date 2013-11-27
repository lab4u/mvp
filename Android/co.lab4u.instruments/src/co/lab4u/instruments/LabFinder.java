package co.lab4u.instruments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import co.lab4u.instruments.adapters.LabItemAdapter;
import co.lab4u.instruments.helpers.JsonParser;
import co.lab4u.instruments.models.ILaboratory;
import co.lab4u.instruments.models.LaboratoryStaticFactory;
import co.lab4u.instruments.proxies.ILabPlatformProxy;
import co.lab4u.instruments.proxies.LabPlatformProxy;

public class LabFinder extends ListActivity {

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.options_menu, menu);

	    // Associate searchable configuration with the SearchView
	    SearchManager searchManager =
	           (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	    SearchView searchView =
	            (SearchView) menu.findItem(R.id.search).getActionView();
	    searchView.setSearchableInfo(
	            searchManager.getSearchableInfo(getComponentName()));

	    return true;
	}	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        
        doIntentAction(intent);
    }

	private void doIntentAction(Intent intent) {

		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			int idLab = this.geLabId(intent);
			
			if (idLab < 0) { 
				this.showNotANumberErrorMessage();
			    
				return;
			}

			this.showLabInList(idLab);
	    }
	}

	private void showNotANumberErrorMessage() {
		Toast.makeText(this, R.string.errorNotANumber, Toast.LENGTH_LONG).show();
	}

	private int geLabId(Intent intent) {
		try {
			String query = intent.getStringExtra(SearchManager.QUERY);
			return Integer.parseInt(query);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	private void showLabInList(int id) {

		List<ILaboratory> labs = this.getLabsById(id);
		
		if (labs.isEmpty()) this.showLabNotFoundWarning();
        
		this.setListLabItemAdapterOnScreen(labs);
	}

	private void showLabNotFoundWarning() {
		Toast.makeText(this, R.string.warningNotANumber, Toast.LENGTH_LONG).show();
	}

	private void setListLabItemAdapterOnScreen(List<ILaboratory> labs) {
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

	private ArrayList<ILaboratory> getLabsById(int id) {
		ArrayList<ILaboratory> labs = new ArrayList<ILaboratory>();
		
		ILabPlatformProxy proxy = new LabPlatformProxy();
        ILaboratory lab = proxy.getLaboratory(id);
        
        if (lab.isEmpty() == false)
        	labs.add(lab);
        
        return labs;
	}           
}
