package com.test;

import com.test.common.CommonUtils;
import com.test.detail.controller.nosql.SpyMemcachedClientController;
import com.test.detail.controller.nosql.SpyMemcachedServer;
import com.test.detail.vo.ResultVO;
import net.spy.memcached.CASValue;
import net.spy.memcached.ConnectionObserver;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/spymemcached")
public class TestSpyMemcachedClientServlet extends HttpServlet {
    SpyMemcachedClientController controller;

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String outStr = "";
            ResultVO resultVO = null;
            init();
            String method = request.getParameter("method");
            if (!method.equals("set")) {
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
                resultVO = flush();
            }
            if (resultVO != null) {
                outStr = "success  everyone ******************************** ^_^";
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

    public void init() {
        String[][] servs = new String[][]{{"192.168.2.130", "11211"},
                //{"localhost", "11212"}
        };
        List<SpyMemcachedServer> servers = new ArrayList<SpyMemcachedServer>();
        for (int i = 0; i < servs.length; i++) {
            SpyMemcachedServer server = new SpyMemcachedServer();
            server.setIp(servs[i][0]);
            server.setPort(Integer.parseInt(servs[i][1]));
            servers.add(server);
        }
        controller = new SpyMemcachedClientController(servers);
        try {
            controller.connect();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        addObserver();
    }

    private void addObserver() {
        ConnectionObserver obs = new ConnectionObserver() {
            public void connectionEstablished(SocketAddress sa, int reconnectCount) {
                System.out.println("Established " + sa.toString());
            }

            public void connectionLost(SocketAddress sa) {
                System.out.println("Lost " + sa.toString());
            }
        };
        controller.addObserver(obs);
    }

    protected void tearDown() throws Exception {
        controller.disConnect();
    }

    private ResultVO set() {
        ResultVO resultVO = controller.set("key", 20, "hello");
        return resultVO;
    }

    private ResultVO get() {
        controller.set("key2", 20, "hello2");
        ResultVO resultVO = controller.get("key2");
        return resultVO;
    }

    private ResultVO gets() {
        controller.set("key3", 20, "hello3");
        controller.set("key3", 20, "hello3");
        ResultVO resultVO = controller.gets("key3");
        return resultVO;
    }

    private ResultVO cas() {
        ResultVO resultVO = null;
        controller.set("key4", 0, 1);
        controller.set("key4", 0, 2);
        ResultVO vo = controller.gets("key4");
        if (vo != null) {
            CASValue obj = (CASValue) vo.getResultObj();
            System.out.println(obj.getCas());
            resultVO = controller.cas("key4", obj.getCas(), "value4");
        }
        return resultVO;
    }

    private ResultVO add() {
        String key = String.valueOf(System.currentTimeMillis());
        ResultVO resultVO = controller.add(key, 0, "hello5");
        return resultVO;
    }

    private ResultVO delete() {
        controller.set("key6", 0, "hello6");
        ResultVO resultVO = controller.delete("key6");
        return resultVO;
    }

    private ResultVO replace() {
        controller.set("key7", 0, "hello7");
        ResultVO resultVO = controller.replace("key7", 0, "hello77");
        return resultVO;
    }

    private ResultVO append() {
        controller.set("key8", 0, "hello8");
        ResultVO resultVO = controller.append("key8", "hello88");
        return resultVO;
    }

    private ResultVO incr() {
        ResultVO resultVO = controller.increment("counterKey", 5, 4, 30);
        return resultVO;
    }

    private ResultVO decr() {
        ResultVO resultVO = controller.decrement("counterKey", 5, 4, 30);
        return resultVO;
    }

    private ResultVO flush() {
        ResultVO resultVO = controller.flush();
        return resultVO;
    }
}
