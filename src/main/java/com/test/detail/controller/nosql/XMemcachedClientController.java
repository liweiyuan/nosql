package com.test.detail.controller.nosql;


import java.io.IOException;

import com.test.common.CommonUtils;
import com.test.detail.vo.ResultVO;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class XMemcachedClientController {
    MemcachedClient memCachedClient = null;

    public void init() {
        try {
            if (memCachedClient == null) {
                MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("192.168.2.130:11211"));
                memCachedClient = builder.build();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ResultVO set(String arg0, int arg1, Object arg2) {
        if (memCachedClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        boolean flag = false;
        long startTime = System.currentTimeMillis();
        try {
            flag = memCachedClient.set(arg0, arg1, arg2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(flag);
        if (flag) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    public ResultVO get(String key) {
        if (memCachedClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        Object obj = null;
        long startTime = System.currentTimeMillis();
        try {
            obj = memCachedClient.get(key);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(obj);
        if (obj != null) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    public ResultVO gets(String key) {
        if (memCachedClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        GetsResponse<Object> obj = null;
        long startTime = System.currentTimeMillis();
        try {
            obj = memCachedClient.gets(key);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(obj);
        if (obj != null) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    public ResultVO cas(String key, long cas) {
        if (memCachedClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        boolean flag = false;
        long startTime = System.currentTimeMillis();
        try {
            flag = memCachedClient.cas(key, 0, 2, cas);
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(flag);
        if (flag) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    /**
     * 向缓存添加键值对并为该键值对设定逾期时间（即多长时间后该键值对从Memcached内存缓存中删除，比如： new Date(1000*10)，则表示十秒之后从Memcached内存缓存中删除）。
     *
     * @author GaoHuanjie
     */
    public ResultVO add(String arg0, int arg1, String arg2) {
        if (memCachedClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        boolean flag = false;
        long startTime = System.currentTimeMillis();
        try {
            flag = memCachedClient.add(arg0, arg1, arg2);
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(flag);
        if (flag) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    public ResultVO delete(String key) {
        if (memCachedClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        boolean flag = false;
        long startTime = System.currentTimeMillis();
        try {
            flag = memCachedClient.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(flag);
        if (flag) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    public ResultVO replace(String arg0, int arg1, String arg2) {
        if (memCachedClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        boolean flag = false;
        long startTime = System.currentTimeMillis();
        try {
            flag = memCachedClient.replace(arg0, arg1, arg2);
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(flag);
        if (flag) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    public ResultVO append(String key, String target) {
        if (memCachedClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        boolean flag = false;
        long startTime = System.currentTimeMillis();
        try {
            flag = memCachedClient.append(key, target);
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(flag);
        if (flag) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    /**
     * 第一个参数指定递增的 key 名称，第二个参数指定递增的幅度大小，第三个参数指定当 key 不存在的情况下的初始值。两个参数的重载方法省略了第三个参数，默认指定为 0 。
     *
     * @param arg0
     * @param arg1
     * @return
     */
    public ResultVO incr(String arg0, int arg1, int arg2) {
        if (memCachedClient == null) {
            return null;
        }
        long result = 0;
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        try {
            result = memCachedClient.incr(arg0, arg1, arg2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(result);
        if (result == -1) {
            vo.setStr(CommonUtils.failedFlag);
        } else {
            vo.setStr(CommonUtils.successFlag);
        }
        return vo;
    }

    public ResultVO decr(String arg0, int arg1, int arg2) {
        if (memCachedClient == null) {
            return null;
        }
        long result = 0;
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        try {
            result = memCachedClient.decr(arg0, arg1, arg2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(result);
        if (result == -1) {
            vo.setStr(CommonUtils.failedFlag);
        } else {
            vo.setStr(CommonUtils.successFlag);
        }
        return vo;
    }

    public ResultVO flushAll() {
        if (memCachedClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        boolean flag = false;
        long startTime = System.currentTimeMillis();
        try {
            memCachedClient.flushAll();
            flag = true;
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(flag);
        if (flag) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }
//	 public static void main(String[] args) throws IOException {
//	        MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("192.168.2.130:11211"));
//	        MemcachedClient memcachedClient = builder.build();
//	        
//	        try {
//	            memcachedClient.set("key", 0, "Hello World!");
//	            String value = memcachedClient.get("key");
//	            System.out.println("key值：" + value);
//	        }
//	        catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        try {
//	            memcachedClient.shutdown();
//	        }
//	        catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }

}
