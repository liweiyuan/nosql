package com.test;

import com.test.util.RedisUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tingyun on 2017/7/19.
 * Redis操作类
 */
@WebServlet(value = "/redis")
public class RedisServlet extends HttpServlet{





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //进行redis的相关操作
        StringBuilder sb=new StringBuilder();
        Jedis jedis= RedisUtil.getJedis();
        sb.append("<html><head><title></title></head><body><h2>redis methods:</h2>");
        sb.append("#####################start###########"+"</br>");
        String status=jedis.set("java","spring");
        sb.append("redis-set:"+status+"</br>");
        status=jedis.get("java");
        sb.append("redis-get:"+status+"</br>");
        Map<String,String> map=new HashMap<String,String>();
        map.put("python","java");
        //status=jedis.hmset("oracle",map);
        //sb.append("redis-hmset:"+status+"</br>");
        //List<String> list=jedis.hmget("python");
        //sb.append("redis-hmget:"+list+"</br>");
        long time=jedis.append("java","hadoop");
        sb.append("redis-appeng:"+time+"</br>");
        time=jedis.decr("key");
        sb.append("redis-decr:"+time+"</br>");
        time=jedis.del("java");
        sb.append("redis-del:"+time+"</br>");
        jedis.incr("java");
        sb.append("redis-incr:"+"</br>");
        jedis.exists("java");
        sb.append("redis-exists:"+"</br>");
        /*jedis.hgetAll("java");
        sb.append("redis-hgetAll:"+"</br>");*/
        /*jedis.lpop("java");
        sb.append("redis-lpop:"+"</br>");*/
        jedis.ping();
        sb.append("redis-ping:"+"</br>");
       /* jedis.rpop("java");
        sb.append("redis-rpop:"+"</br>");*/
        byte[] bytes=new byte[]{'a','c'};
        jedis.sort(bytes);
        sb.append("redis-ping:"+"</br>");
        jedis.keys(bytes);
        sb.append("redis-keys:"+"</br>");
        jedis.watch(bytes);
        sb.append("redis-watch:"+"</br>");
        jedis.zcard(bytes);
        sb.append("redis-zcard:"+"</br>");
        jedis.echo("hadoop");
        sb.append("redis-echo:"+"</br>");
        jedis.expire("hadoop",1);
        sb.append("redis-expire:"+"</br>");
        jedis.flushAll();
        sb.append("redis-flushAll:"+"</br>");
        jedis.hkeys("hadoop");
        sb.append("redis-hkeys:"+"</br>");
        jedis.spop("hadoop");
        sb.append("redis-spop:"+"</br>");
        jedis.hvals("hadoop");
        sb.append("redis-hvals:"+"</br>");
        jedis.sinter("hadoop","java");
        sb.append("redis-sinter:"+"</br>");
        jedis.zrank("hadoop", String.valueOf(23));
        sb.append("redis-zrank:"+"</br>");
        jedis.set("python","spring");
        jedis.renamenx("python","java1");
        sb.append("redis-renamenx:"+"</br>");
        //jedis.rpush("java1","value");
        sb.append("redis-rpush:"+"</br>");
        sb.append(" </body></html>");

        RedisUtil.returnResource(jedis);
        resp.getWriter().println(sb);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
