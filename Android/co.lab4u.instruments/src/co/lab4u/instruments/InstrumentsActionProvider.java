package co.lab4u.instruments;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class InstrumentsActionProvider extends ActionProvider {

	private Context context;
	
	public InstrumentsActionProvider(Context context) {
		super(context);
		
		this.context = context;
	}

	public View onCreateActionView(Menu forItem) {
		// Inflate the action view to be shown on the action bar.
	    LayoutInflater layoutInflater = LayoutInflater.from(this.context);
	    
	    View view = layoutInflater.inflate(R.layout.instruments_action_provider, null);
	    ImageButton button = (ImageButton) view.findViewById(R.id.instrumentImageButton);
	    
	    button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View imgView) {
				Toast.makeText(context, "Funciona", Toast.LENGTH_SHORT);
			}
	    	
	    });
	    
	    button.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            // Do something...
	        }
	    });
	    return view;
	}

	@Override
	@Deprecated
	public View onCreateActionView() {
		// TODO Auto-generated method stub
		return null;
	}

}
