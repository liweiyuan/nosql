package com.test;


import com.schooner.MemCached.MemcachedItem;
import com.test.common.CommonUtils;
import com.test.detail.controller.nosql.MemcachedClientController;
import com.test.detail.vo.ResultVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/xmemcached")
public class TestMemcachedClientServlet extends HttpServlet {
    MemcachedClientController controller = new MemcachedClientController();

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String outStr = "";
            ResultVO resultVO = null;
            controller.init();
            String method = request.getParameter("method");
            if (!method.equals("set")) {
//				resultVO=set(response);
                resultVO = set();
            }
            if (!method.equals("get")) {
                resultVO = get();
            }
            if (!method.equals("gets")) {
                resultVO = gets();
            }
            if (!method.equals("cas")) {
                resultVO = cas();
            }
            if (!method.equals("add")) {
                resultVO = add();
            }
            if (!method.equals("replace")) {
                resultVO = replace();
            }
            if (!method.equals("append")) {
                resultVO = append();
            }
            if (!method.equals("delete")) {
                resultVO = delete();
            }
            if (!method.equals("incr")) {
                resultVO = incr();
            }
            if (!method.equals("decr")) {
                resultVO = decr();
            }
            if (!method.equals("flushAll")) {
                resultVO = flushAll();
            }
            if (resultVO != null) {
                outStr = "success ^_^";
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

    private ResultVO set() {
        ResultVO resultVO = controller.set("foo1", "aaa");
        return resultVO;
    }

    private ResultVO get() {
        controller.set("foo2", "bbb");
//		String tmp="";
//		for(int i=1;i<=20000;i++){
//			tmp += "asfdjflkdjasflkasjfldsjfafjdasjfjdkasfafjdlsfjdlasfjasfjasfjsf";//key 长一些为了时间准确
//	    }	
        ResultVO resultVO = controller.get("foo2");
        return resultVO;
    }

    private ResultVO gets() {
//		String tmp="";
//		for(int i=1;i<=20000;i++){
//			tmp += "asfdjflkdjasflkasjfldsjfafjdasjfjdkasfafjdlsfjdlasfjasfjasfjsf";//key 长一些为了时间准确
//	    }	
        controller.set("foo1", "aaa");
        controller.set("foo1", "bbb");
        ResultVO resultVO = controller.gets("foo1");
        return resultVO;
    }

    private ResultVO cas() {
        ResultVO resultVO2 = null;
        controller.set("foo2", "ccc");
        controller.set("foo2", "ddd");
        ResultVO resultVO1 = controller.gets("foo2");
        if (resultVO1 != null && resultVO1.getResultObj() != null) {
            MemcachedItem item = (MemcachedItem) resultVO1.getResultObj();
            resultVO2 = controller.cas("foo2", item);
        }
        return resultVO2;
    }

    private ResultVO add() {
        Date expire = new Date(1000 * 60);
        String key = String.valueOf(System.currentTimeMillis());
        ResultVO resultVO = controller.add(key, "ccc", expire);
        return resultVO;
    }

    private ResultVO delete() {
        controller.set("foo4", "ddd");
        ResultVO resultVO = controller.delete("foo4");
        return resultVO;
    }

    private ResultVO replace() {
        controller.set("foo5", "eee");
        ResultVO resultVO = controller.replace("foo5", "eee2");
        return resultVO;
    }

    private ResultVO append() {
        controller.set("foo6", "fff");
        ResultVO resultVO = controller.append("foo6", "eee2");
        return resultVO;
    }

    private ResultVO incr() {
//		controller.set("counterKey",900,6);
        controller.set("counterKey", "incrhello");
        ResultVO resultVO = controller.incr("counterKey");
        return resultVO;//总是返回-1，不知道为什么,key存在
    }

    private ResultVO decr() {
        controller.set("counterKey", 900, 8);//900是32位无符号数字
        ResultVO resultVO = controller.decr("counterKey");
        return resultVO;
    }

    private ResultVO flushAll() {
        ResultVO resultVO = controller.flushAll();
        return resultVO;
    }
}
