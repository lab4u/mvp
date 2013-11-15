package co.lab4u.instruments.test;

import com.jayway.android.robotium.solo.Solo;

import co.lab4u.instruments.LabFinder;
import co.lab4u.instruments.LabViewer;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.ListView;

public class LabFinderFunctionalTest extends ActivityInstrumentationTestCase2<LabFinder> {

	private static final long TIME_OUT_IN_MS = 5000;
	private LabFinder labFinderFixture;
	Solo solo;
	
	public LabFinderFunctionalTest() {
		super(LabFinder.class);
	}
	
	public LabFinderFunctionalTest(Class<LabFinder> activityClass) {
		super(activityClass);
	
	}
	
	@Override
	public void setUp() throws Exception	{
		super.setUp();
		
		this.setActivityInitialTouchMode(true);
		labFinderFixture = this.getActivity();
		
	    solo = new Solo(getInstrumentation(), getActivity());
	}	
	
	@SmallTest
	public void testPreconditions() {
		assertNotNull(labFinderFixture);
	}
	
	@LargeTest() 
	public void testSetSelection() {
		
		
	}
	
	@LargeTest
	public void testOnListItemClick() {
//		final ListView listView = labFinderFixture.getListView();
//		
//		Instrumentation.ActivityMonitor receiverActivityMonitor = this.getInstrumentation().addMonitor(LabViewer.class.getName(), null, false);
//		
//		final int targetPosition = listView.getAdapter().getCount() / 2;
//        
//        labFinderFixture.runOnUiThread(new Runnable() {
//            @Override
//        	public void run() {
//            	listView.performItemClick(listView, 0, listView.getSelectedItemId());
//            }
//        });
//        
//		LabViewer labViewer = (LabViewer) receiverActivityMonitor.waitForActivityWithTimeout(TIME_OUT_IN_MS);
//		
//		// Verify that LabViewer was started
//		assertNotNull("LabViewer is null", labViewer);
//		assertEquals("Monitor for LabViewer has not been called", 1, receiverActivityMonitor.getHits());
//	    assertEquals("Activity is of wrong type", LabViewer.class, labViewer.getClass());
//
////	    //Read the message received by ReceiverActivity
////	        final TextView receivedMessage = (TextView) receiverActivity
////	                .findViewById(R.id.received_message_text_view);
////	        //Verify that received message is correct
////	        assertNotNull(receivedMessage);
////	        assertEquals("Wrong received message", TEST_MESSAGE, receivedMessage.getText().toString());
//
//        //Unregister monitor for ReceiverActivity
//        getInstrumentation().removeMonitor(receiverActivityMonitor);
		
		solo.clickInList(0);
		
	}
	

}
