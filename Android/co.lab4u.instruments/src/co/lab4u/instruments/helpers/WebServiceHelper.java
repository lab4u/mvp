package co.lab4u.instruments.helpers;

import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;
import co.lab4u.instruments.Const;

public class WebServiceHelper {
	private static WebServiceHelper instance;
	
	private WebServiceHelper() {}
	
	public static WebServiceHelper getInstance() {
		if (instance == null) instance = new WebServiceHelper();
		
		return instance;
	}
	
	public String getSimpleStringResult(String soapAction, String nameSpace, String methodName, String url, Map<String, String> parms ) {
	    String ret = "";
		
		//Initialize soap request + add parameters
        SoapObject request = new SoapObject(nameSpace, methodName);       
       
        //Use this to add parameters
        for (String s : parms.keySet()) {
        	request.addProperty(s, parms.get(s));	
        }
       
        //Declare the version of the SOAP request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
       
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
       
        try {
        	 HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
             
             //this is the actual part that will call the webservice
             androidHttpTransport.call(soapAction, envelope);
            
             // Get the SoapResult from the envelope body.
             SoapObject result = (SoapObject)envelope.bodyIn;
             
              if(result != null)
              {
            	  //Get the first property and change the label text
                  ret = result.getProperty(0).toString();
              }
        }
        catch (Exception e) {
        	Log.e(Const.TAG_ERROR_LOG, e.toString());
        }
        
		return ret;
	}

}
