package com.test.detail.controller.nosql;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CommonUtils {
	public static int DEFAULT_TIMEOUT = 5;  
	  
    public static TimeUnit DEFAULT_TIMEUNIT = TimeUnit.SECONDS;  
    public static String successFlag="success";
    public static String failedFlag="failed";
	/**
	 * 判断字符串是否是空或者空字符串,是空字符串返回true,否则返回false
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		if(str==null||str.equals("")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 向结果页面写标识
	 * @param response
	 */
	public static void printToPage(HttpServletResponse response, String str){
		if(response==null||isBlank(str)){
			return;
		}
		try {
		    response.getWriter().write(str);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * 获取项目路径
	 * @param request
	 * @return
	 */
	public static String getDocumentRoot(HttpServletRequest request){
        String webRoot = request.getSession().getServletContext().getRealPath("/");
        if(webRoot == null){
            webRoot = com.test.common.CommonUtils.class.getClassLoader().getResource("/").getPath();
            webRoot = webRoot.substring(0,webRoot.indexOf("WEB-INF"));
        }
        return webRoot;
    }
	/**
	 * 通过ServletContext获取properties文件
	 * @param path
	 * @param context
	 * @return Properties
	 */
	public static Properties getPropertyFile(String path,ServletContext context){
		Properties p=new Properties();
		if(context==null){
			return null;
		}
		InputStream in = context.getResourceAsStream(path);
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	public static boolean getBooleanValue(Future<Boolean> f) {  
        try {  
            Boolean bool = f.get(CommonUtils.DEFAULT_TIMEOUT,
            		CommonUtils.DEFAULT_TIMEUNIT);
            return bool.booleanValue();  
        } catch (Exception e) {  
            f.cancel(false);  
            return false;  
        }  
    }  
	
}
