package co.lab4u.instruments.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;
import co.lab4u.instruments.LabFinder;
import co.lab4u.instruments.R;
import co.lab4u.instruments.adapters.LabItemAdapter;
import co.lab4u.instruments.models.ILaboratory;
import co.lab4u.instruments.models.LaboratoryStaticFactory;

public class LabItemAdapterUnitTest extends android.test.ActivityUnitTestCase<LabFinder> {
	
	List<ILaboratory> labs;
	Activity mockActivity;
	
	public LabItemAdapterUnitTest() {
		super(LabFinder.class);
	}
	
	public LabItemAdapterUnitTest(Class<LabFinder> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		this.initLabs();
		this.initMockActivity();
	}
	
	private void initMockActivity() {
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
		        LabFinder.class);
		    
		startActivity(intent, null, null);
		
		mockActivity = getActivity();
	}
	
	private void initLabs() {
		labs = new ArrayList<ILaboratory>() {{
	    	add(LaboratoryStaticFactory.getInstance().CreateLaboratory(1, "Some title 1", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat", Calendar.getInstance(), Calendar.getInstance()));
	    	add(LaboratoryStaticFactory.getInstance().CreateLaboratory(2, "Some title 2", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat", Calendar.getInstance(), Calendar.getInstance()));
	    	add(LaboratoryStaticFactory.getInstance().CreateLaboratory(3, "Some title 3", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat", Calendar.getInstance(), Calendar.getInstance()));
	    	add(LaboratoryStaticFactory.getInstance().CreateLaboratory(4, "Some title 4", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat", Calendar.getInstance(), Calendar.getInstance()));
	    }};
        
//        ListAdapter adapter = new LabItemAdapter(this, labs);
	}
	
	@SmallTest
	public void testGetView() {
		LabItemAdapter adapter = new LabItemAdapter(mockActivity, labs);
		
		ViewGroup listView = new ListView(mockActivity);
		
		int currentIndex = 0;
		
		for ( ILaboratory item : labs ) {
			View view = adapter.getView(currentIndex, new View(mockActivity), listView);
			
			TextView textViewTitle = (TextView)view.findViewById(R.id.labTitle);
			TextView textViewContent = (TextView)view.findViewById(R.id.labContent);
			
			assertEquals("Title value is not the same", textViewTitle.getText(), item.getTitle());
			assertEquals("Content value is not the same", textViewContent.getText(), item.getContent());
		
			currentIndex = currentIndex + 1;
		}	
	}
	


}
