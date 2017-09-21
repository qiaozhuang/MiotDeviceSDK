package com.miot.android.utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/9/19 0019.
 */
public class JSONUitls {
	/**
	 *
	 * @param error
	 * @param errorMessage
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String getJSONResult(Integer error,String errorMessage,Object data)throws Exception{
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("error",error);
		jsonObject.put("errorMessage",errorMessage);
		jsonObject.put("data",data);
		return jsonObject.toString();
	}

	public static int getId(String data)throws Exception{
		JSONObject jsonObject=new JSONObject(data);
		return jsonObject.getInt("id");
	}
	public static int getSessionId(String data)throws Exception{
		JSONObject jsonObject=new JSONObject(data);
		return jsonObject.getInt("sessionId");
	}

	public static String getErrorMessage(String errorCode,String errorMessage){
		String s="{\"body\":{\"resultMsg\":\"errorMessage\",\"data\":{\"sceneList\":[],\"puList\":[]},\"resultCode\":\"errorCode\"}}";
		String res="";
		res=s.replaceAll("errorMessage",errorMessage);
		res=res.replaceAll("errorCode",errorCode);
		return res;
	}

	public static String getSceneCu(String sceneId){
		String result="";
		try {
			JSONObject jsonObject=new JSONObject();
			JSONObject data=new JSONObject();
			data.put("sceneId",sceneId);
			jsonObject.put("code","triggerScene");
			jsonObject.put("data",data);
			result=jsonObject.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static void aCacheAll(Context context,String data) throws Exception{
		if (data.equals("")){
			return;
		}
		JSONObject jsonObject=new JSONObject(data);
		if ( jsonObject==null){
			return;
		}
		JSONObject body=new JSONObject(jsonObject.getString("body"));
		if (body.getString("data").equals("")){
			return;
		}
		JSONObject jsonObject1=new JSONObject(body.getString("data"));
		if (jsonObject1==null){
			return;
		}
		ACache ache=ACache.get(context);
		JSONArray jsonArray=new JSONArray(jsonObject1.getString("puList"));
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObjectPu=new JSONObject(jsonArray.get(i).toString());
			ache.put(jsonObjectPu.getString("puId"),jsonObjectPu.getString("state"),2*60);
		}
	}

}
