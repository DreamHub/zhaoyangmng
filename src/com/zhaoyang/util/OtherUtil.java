package com.zhaoyang.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
		//JSESSIONID=CD8F7F46F76862E2A9DE69900CBD3DC4
		String id=ServletActionContext.getRequest().getSession().getId();
		if(id!=null){
			conn2.setRequestProperty("Cookie", "JSESSIONID="+id);
		}
		if (conn2.getResponseCode() == 200) {
			InputStream is = conn2.getInputStream();
			String str = new String(OtherUtil.read(is), "UTF-8");
			//System.out.println(str);
			PrintWriter pw = new PrintWriter(toFile, "UTF-8");
			pw.print(str);
			pw.close();
		}
	}
	public static void sendHttpRequestWithNoReturn(String url) throws Exception {
		HttpURLConnection conn2 = (HttpURLConnection)new URL(url).openConnection();
		String id=ServletActionContext.getRequest().getSession().getId();
		if(id!=null){
			conn2.setRequestProperty("Cookie", "JSESSIONID="+id);
		}
		if (conn2.getResponseCode() == 200) {
			System.out.println("send["+url+"]Yes.");
		}
	}
	public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }
	public static void copyFile(File sourceFile, File targetFile) throws IOException {
		String filename=sourceFile.getName();
		String exp=filename.substring(filename.lastIndexOf(".")+1, filename.length());
		//System.out.println(exp);
		if("jsp".equals(exp)){
			return;
		}
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

}
