package co.lab4u.instruments.proxies;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import co.lab4u.instruments.Const;
import co.lab4u.instruments.helpers.JsonParser;
import co.lab4u.instruments.models.ILaboratory;
import co.lab4u.instruments.models.Laboratory;

public class LabPlatformProxy implements ILabPlatformProxy {
    
	private static String SOAP_ACTION1 = "http://lab4u.cl/Services/GetLaboratory";
    private static String NAMESPACE = "http://lab4u.cl/Services";
    private static String METHOD_NAME1 = "GetLaboratory";
    private static String URL = "http://lab4uservices.cloudapp.net/LabService.asmx?WSDL";
	
//	private static String SOAP_ACTION1 = "http://www.w3schools.com/webservices/FahrenheitToCelsius";
//    private static String NAMESPACE = "http://www.w3schools.com/webservices/";
//    private static String METHOD_NAME1 = "FahrenheitToCelsius";
//    private static String URL = "http://www.w3schools.com/webservices/tempconvert.asmx?WSDL";
//

    private static String TAG_CREATION_DATE = "CreationDate";
    private static String TAG_ID = "Id";
    private static String TAG_LAST_MODIFIED_DATE = "LastModifiedDate";
    private static String TAG_TITLE = "Title";
    private static String TAG_CONTENT = "CreationDate";
    private static String TAG_ROOT = "Laboratory";
    
    @Override
	public ILaboratory getLaboratory(int idLab) {
	    String jsonStr  = "";
    	
	     //Initialize soap request + add parameters
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);       
       
        //Use this to add parameters
        request.addProperty("id", String.valueOf(idLab));
       
        //Declare the version of the SOAP request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
       
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
       
        try {
        	 HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
             
             //this is the actual part that will call the webservice
             androidHttpTransport.call(SOAP_ACTION1, envelope);
            
             // Get the SoapResult from the envelope body.
             SoapObject result = (SoapObject)envelope.bodyIn;
             
              if(result != null)
              {
                    //Get the first property and change the label text
                    jsonStr = result.getProperty(0).toString();
              }
              
        	JSONObject jsonObj = JsonParser.getInstance().getJSONFrom(jsonStr);
        	
        	int id = Integer.parseInt(jsonObj.getString(TAG_ID));
        	
        	Calendar creationDate = Calendar.getInstance();
        	Calendar lastModifiedDate = Calendar.getInstance();
        	
//        	creationDate.setTimeInMillis(Long.parseLong(jsonObj.getString(TAG_CREATION_DATE))); 
        	creationDate.setTimeInMillis(Long.parseLong("1383742297900")); 
        	
        	
        	
//        	creationDate.setTime(sdf.parse(jsonObj.getString(TAG_CREATION_DATE)));
//        	lastModifiedDate.setTime(sdf.parse(jsonObj.getString(TAG_CREATION_DATE)));
        	
        	String title = jsonObj.getString(TAG_TITLE);
        	String content = "";
        	
        	return new Laboratory(id, title, content, creationDate , lastModifiedDate);
        	            
        } catch (JSONException e) {
        	Log.e(Const.TAG_ERROR_LOG, e.toString());
        } catch (Exception e) {
        	Log.e(Const.TAG_ERROR_LOG, e.toString());
        }
	
        
        return null;
	}

}
