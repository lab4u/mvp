package co.lab4u.instruments.proxies;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import co.lab4u.instruments.Const;
import co.lab4u.instruments.helpers.JsonParser;
import co.lab4u.instruments.helpers.WebServiceHelper;
import co.lab4u.instruments.models.EmptyLaboratory;
import co.lab4u.instruments.models.ILaboratory;
import co.lab4u.instruments.models.Laboratory;

public class LabPlatformProxy implements ILabPlatformProxy {
    
	private static String SOAP_ACTION1 = "http://lab4u.cl/Services/GetLaboratory";
    private static String NAMESPACE = "http://lab4u.cl/Services";
    private static String METHOD_NAME1 = "GetLaboratory";
    private static String URL = "http://lab4uservices.cloudapp.net/LabService.asmx?WSDL";
	
    private static String TAG_CREATION_DATE = "CreationDate";
    private static String TAG_ID = "Id";
    private static String TAG_LAST_MODIFIED_DATE = "LastModifiedDate";
    private static String TAG_TITLE = "Title";
    private static String TAG_LAB_CONTENT = "LabContent";
    private static String TAG_LAB_CONTENT_TEXT = "Text";
    
    private String getJsonResultFromWebService(int id) {
    	String jsonResult = "";
    	
    	Map<String, String> parms = new HashMap<String, String>();
	    parms.put("id", String.valueOf(id));
	    
    	jsonResult = WebServiceHelper.getInstance().getSimpleStringResult(SOAP_ACTION1, NAMESPACE, METHOD_NAME1, URL, parms);
    	
    	return jsonResult;
    }
    
    private ILaboratory mapLaboratoryFromJsonFormattedInString(String str) {
    	ILaboratory lab = new EmptyLaboratory();;
    	
    	try {
            JSONObject jsonObj = JsonParser.getInstance().getJSONFrom(str);
        	
        	int id = Integer.parseInt(jsonObj.getString(TAG_ID));
        	
        	Calendar creationDate = Calendar.getInstance();
        	Calendar lastModifiedDate = Calendar.getInstance();
        	
        	long longCreationDate = Long.parseLong(jsonObj.getString(TAG_CREATION_DATE).replace("/Date(", "").replace(")/",""));
        	String strLastModifiedDate = jsonObj.getString(TAG_LAST_MODIFIED_DATE);
        	long longLastModifiedDate = 0;
        	
        	if (strLastModifiedDate.length() > 6) 
        		longLastModifiedDate =  Long.parseLong(strLastModifiedDate.replace("/Date(", "").replace(")/", ""));
        	
        	if (longCreationDate > 0)
        		creationDate.setTimeInMillis(longCreationDate);
        	
        	if (longLastModifiedDate > 0) 
        		lastModifiedDate.setTimeInMillis(longLastModifiedDate);
        	
        	String content = jsonObj.getJSONObject(TAG_LAB_CONTENT).getString(TAG_LAB_CONTENT_TEXT);
        	
        	String title = jsonObj.getString(TAG_TITLE);
        	
        	return new Laboratory(id, title, content, creationDate , lastModifiedDate);
        	            
        } catch (JSONException e) {
        	Log.e(Const.TAG_ERROR_LOG, e.toString());
        } catch (Exception e) {
        	Log.e(Const.TAG_ERROR_LOG, e.toString());
        }
    	
    	return lab;
    } 
    
    @Override
	public ILaboratory getLaboratory(int idLab) {
    	String jsonStr = this.getJsonResultFromWebService(idLab);
    	
    	ILaboratory lab = this.mapLaboratoryFromJsonFormattedInString(jsonStr);
        
        return lab;
	}
}