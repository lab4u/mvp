package co.lab4u.instruments.helpers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonParser {
	private static JsonParser instance;
	
    static JSONObject jObj = null;
    static String json = "";
 
    public static JsonParser getInstance() {
    	if (instance == null) instance = new JsonParser();
    	
    	return instance;
    }
    
    private JsonParser() {}
 
    public JSONObject getJSONFrom(String str) {
          
    	// convert String into InputStream
    	InputStream is = new ByteArrayInputStream(str.getBytes());
    	
    	try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
 
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
 
        // return JSON String
        return jObj;
    }	
}
