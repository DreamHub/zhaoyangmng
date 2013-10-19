package com.zhaoyang.util;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.json.JSONObject;

public class OtherUtil {

	/**
		 * 读取输入流数据
		 * @param inStream
		 * @return
		 */
		public static byte[] read(InputStream inStream) throws Exception{
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while( (len = inStream.read(buffer)) != -1 ){
				outStream.write(buffer, 0, len);
			}
			inStream.close();
			return outStream.toByteArray();
		}
		public static String getLocalityName(String googleJsonStr) throws Exception{
			String str=new JSONObject(googleJsonStr).getJSONArray("Placemark").getJSONObject(0).getJSONObject("AddressDetails").getJSONObject("Country").getJSONObject("AdministrativeArea").getJSONObject("Locality").getString("LocalityName");
			StringBuffer sb=new StringBuffer(str);
			sb.deleteCharAt(sb.length()-1);
			//String picture=new JSONObject(googleJsonStr).getJSONArray("seeRecords").getJSONObject(0).getJSONObject("dbCollection").getString("picture");
			return sb.toString();
			
		}
}
