package co.lab4u.instruments.test;

import android.app.Activity;
import android.content.Intent;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.text.TextUtils.TruncateAt;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
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
		
		this.assertNotNullLabImage(activityUnderTest);
		this.assertNotNullLabContent(activityUnderTest);
		this.assertNotNullLabTitle(activityUnderTest);
		
		this.assertFakeViewNotAllowed(activityUnderTest);
		
		this.assertLabTitleLayoutParams(activityUnderTest);
		this.assertLabContentLayoutParams(activityUnderTest);
		
    }
	
	private void assertFakeViewNotAllowed(Activity activity) {
		int fakeActivityId = -777;
		View view = activity.findViewById(fakeActivityId);
		assertNull("Fakes activities are not allowed", view);
	}

	private TextView getTitleTextView(Activity activity) {
		int textViewTitleId = co.lab4u.instruments.R.id.labTitle;
		return (TextView)activity.findViewById(textViewTitleId);
	}
	
	private TextView getContentTextView(Activity activity) {
		int textViewContentId = co.lab4u.instruments.R.id.labContent;
		return (TextView)activity.findViewById(textViewContentId); 
	}
	
	private void assertLabTitleLayoutParams(Activity activity) {
		TextView view = this.getTitleTextView(activity);
		
		final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	}
	
	private void assertLabContentLayoutParams(Activity activity) {
		TextView view = this.getContentTextView(activity);
		
		final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
	    
		assertNotNull(layoutParams);
	    
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 26, activity.getResources().getDisplayMetrics());
	    assertEquals(layoutParams.height, dip);
	    
	    // relative layout
	    assertEquals(TruncateAt.MARQUEE, view.getEllipsize());
	    int sp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, activity.getResources().getDisplayMetrics());
	    assertEquals((int)view.getTextSize(), sp);
	    
	    //TODO: singleline, layout_alignParentBottom, layout_alignParentRight, layout_toRightOf tests
	}
	
	private void assertNotNullLabTitle(Activity activity) {
		View view = this.getTitleTextView(activity);
		assertNotNull("Item title not allowed to be null", view);
	}
	
	private void assertNotNullLabContent(Activity activity) {
		View contentTextView = this.getContentTextView(activity);
	    assertNotNull("Item content not allowed to be null", contentTextView);
	}

	private void assertNotNullLabImage(Activity activity) {
		int iconId = co.lab4u.instruments.R.id.icon;
		View view = activity.findViewById(iconId);
	    assertNotNull("Lab icon image not allowed to be null", view);
	}
}
