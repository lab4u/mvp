package co.lab4u.instruments.test;

import com.jayway.android.robotium.solo.Solo;

import co.lab4u.instruments.LabViewer;
import android.test.ActivityInstrumentationTestCase2;


public class LabViewerFunctionalTest extends
		ActivityInstrumentationTestCase2<LabViewer> {

	private Solo solo;
	
	public LabViewerFunctionalTest(Class<LabViewer> activityClass) {
		super(activityClass);
	}

	protected void setUp() throws Exception {
		solo = new Solo(this.getInstrumentation(), this.getActivity());
	}
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

//	public final void testOnCreateBundle() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	public final void testOnCreateOptionsMenuMenu() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	public final void testOnOptionsItemSelectedMenuItem() {
//		fail("Not yet implemented"); // TODO
//	}

}
