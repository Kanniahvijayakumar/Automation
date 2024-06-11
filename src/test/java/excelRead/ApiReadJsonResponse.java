package excelRead;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiReadJsonResponse {

	    public static int findId(JSONArray jsonArray, String keyword, String value) {
	        int id = -1; // Default ID if not found
	        for (int i = 0; i < jsonArray.length(); i++) {
	            JSONObject obj = jsonArray.getJSONObject(i);
	            if (obj.has(keyword) && obj.getString(keyword).equals(value)) {
	                id = obj.getInt("id");
	                break;
	            }
	        }
	        return id;
	    }



}
