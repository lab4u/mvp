package co.lab4u.instruments.test;

import android.content.Intent;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Adapter;
import android.widget.ListView;
import co.lab4u.instruments.LabFinder;
import co.lab4u.instruments.LabFinderItem;
import junit.framework.TestCase;

public class LabFinderUnitTest extends android.test.ActivityUnitTestCase<LabFinder> {

	private LabFinder labFinderFixture;
	
	public LabFinderUnitTest() {
		super(LabFinder.class);
	}
	
	public LabFinderUnitTest(Class<LabFinder> activityClass) {
		super(activityClass);
	}
	
	@Override
    protected void setUp() throws Exception {
		super.setUp();
	    Intent intent = new Intent(getInstrumentation().getTargetContext(),
	        LabFinder.class);
	    
	    startActivity(intent, null, null);
	    labFinderFixture = getActivity();
	}
	
	@SmallTest
	public void testListItemOnClick() {
//		TouchUtils.clickView(this, listViewFixture);
		
	}
	

}
