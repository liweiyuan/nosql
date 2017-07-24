package com.test;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.rubyeye.xmemcached.GetsResponse;
import com.test.common.CommonUtils;
import com.test.detail.controller.nosql.XMemcachedClientController;
import com.test.detail.vo.ResultVO;

public class TestXMemcachedClientServlet extends HttpServlet{
	XMemcachedClientController controller=new XMemcachedClientController();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String outStr="";
			ResultVO resultVO=null;
			controller.init();
			String method=request.getParameter("method");
			if(!method.equals("set")){
				resultVO=set();
			}
			if(!method.equals("get")){
				resultVO=get();
			}
			if(!method.equals("gets")){
				resultVO=gets();
			}
			if(!method.equals("cas")){
			    resultVO=cas();
			}
			if(!method.equals("add")){
				resultVO=add();
			}
			if(!method.equals("replace")){
				resultVO=replace();
			}
			if(!method.equals("append")){
				resultVO=append();
			}
			if(!method.equals("delete")){
				resultVO=delete();
			}
			if(!method.equals("incr")){
				resultVO=incr();
			}
			if(!method.equals("decr")){
				resultVO=decr();
			}
			if(!method.equals("flushAll")){
			    resultVO=flushAll();
			}
			if(resultVO!=null){
				outStr="success";
			}
			CommonUtils.printToPage(response, outStr);
		} catch (Exception e) {
			try {
				response.getWriter().write("Error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}  
	 }
	private ResultVO set(){
		ResultVO resultVO=controller.set("key", 0, "hello");
		return resultVO;
	}
	private ResultVO get(){
		controller.set("key2", 0, "hello2");
		ResultVO resultVO=controller.get("key2");
	 	return resultVO;
	}
	private ResultVO gets(){
		controller.set("foo3",0,"hello2");
		controller.set("foo3",0, "hello3");
		ResultVO resultVO=controller.gets("foo3");
	    return resultVO;
	}
	private ResultVO cas(){
		ResultVO resultVO=null;
		controller.set("foo2",0, 1);
		controller.set("foo2",0, 2);
		ResultVO vo=controller.gets("foo2");
		if(vo!=null){
			GetsResponse<Object> obj=(GetsResponse<Object>)vo.getResultObj();
//			System.out.println(obj.getCas());
			resultVO=controller.cas("foo2",obj.getCas());
			
	    }
	   return vo;
	}
	private ResultVO add(){
		String key=String.valueOf(System.currentTimeMillis());
		ResultVO resultVO=controller.add(key,0,"ccc");//key值如果存在，就返回false
		return resultVO;
	}
	private ResultVO delete(){
		controller.set("foo4",0,"ddd");
		ResultVO resultVO=controller.delete("foo4");
		return resultVO;
	}
	private ResultVO replace(){
		controller.set("foo5",0,"eee");
		ResultVO resultVO=controller.replace("foo5",0, "eee2");
		return resultVO;
	}
	private ResultVO append(){
		controller.set("foo6",0,"fff");
		ResultVO resultVO=controller.append("foo6", "eee2");
		return resultVO;
	}
	private ResultVO incr(){
		ResultVO resultVO=controller.incr("counterKey",5,4);
		return resultVO;
	}
	private ResultVO decr(){
		ResultVO resultVO=controller.decr("counterKey",5,4);
		return resultVO;
	}
	private ResultVO flushAll(){
		ResultVO resultVO=controller.flushAll();
		return resultVO;
	}
}
