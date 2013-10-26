package com.zhaoyang.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.struts2.ServletActionContext;

public class OtherUtil {

	/**
	 * 读取输入流数据
	 * 
	 * @param inStream
	 * @return
	 */
	public static byte[] read(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

	/**
	 * 把url请求的资源拷贝到指定文件中去
	 * @param url
	 * @param toFile
	 * @throws Exception
	 */
	public static void copyResourceFromUrl(String url, File toFile) throws Exception {
		HttpURLConnection conn2 = (HttpURLConnection)new URL(url).openConnection();
		if (conn2.getResponseCode() == 200) {
			InputStream is = conn2.getInputStream();
			String str = new String(OtherUtil.read(is), "UTF-8");
			PrintWriter pw = new PrintWriter(toFile, "UTF-8");
			pw.print(str);
			pw.close();
		}
	}

}
