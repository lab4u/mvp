package co.lab4u.instruments.test;

import android.app.Activity;
import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import co.lab4u.instruments.LabFinderItem;

public class LabFinderItemUnitTest extends android.test.ActivityUnitTestCase<LabFinderItem> {
	
	private LabFinderItem activityUnderTest;
	
	public LabFinderItemUnitTest() { 
		super(LabFinderItem.class);
	}
	
	public LabFinderItemUnitTest(Class<LabFinderItem> activityClass) {
		super(activityClass);
	}
	
	@Override 
	protected void setUp() throws Exception {
		super.setUp();
	   
	    Intent intent = new Intent(getInstrumentation().getTargetContext(), LabFinderItem.class);
	    startActivity(intent, null, null);
	    activityUnderTest = getActivity();
	}
	
	@SmallTest
    public void testLayout() {
		
		this.assertIconLabImage(activityUnderTest);
		this.assertTextViewContent(activityUnderTest);
		this.assertTextViewTitle(activityUnderTest);
		this.assertFakeViewNotAllowed(activityUnderTest);
			
    }
	
	private void assertFakeViewNotAllowed(Activity activity) {
		int fakeActivityId = -777;
		View view = activity.findViewById(fakeActivityId);
		assertNull("Fakes activities are not allowed", view);
	}

	private void assertTextViewTitle(Activity activity) {
		int textViewTitleId = co.lab4u.instruments.R.id.labTitle;
		View view = activity.findViewById(textViewTitleId);
		assertNotNull("Item title not allowed to be null", view);
	}

	private void assertTextViewContent(Activity activity) {
		int textViewContentId = co.lab4u.instruments.R.id.labContent;
		View view = activity.findViewById(textViewContentId);
	    assertNotNull("Item content not allowed to be null", view);
	}

	private void assertIconLabImage(Activity activity) {
		int iconId = co.lab4u.instruments.R.id.icon;
		View view = activity.findViewById(iconId);
	    assertNotNull("Lab icon image not allowed to be null", view);
	}
}
