package com.test.detail.controller.nosql;

import com.test.detail.vo.ResultVO;
import net.spy.memcached.*;
import net.spy.memcached.transcoders.Transcoder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketAddress;
import java.util.*;
import java.util.concurrent.Future;

public class SpyMemcachedClientController {
    private List<SpyMemcachedServer> servers;

    private MemcachedClient memClient;

    public SpyMemcachedClientController(List<SpyMemcachedServer> servers) {
        this.servers = servers;
    }

    public void connect() throws IOException {
        if (memClient != null) {
            return;
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < servers.size(); i++) {
            SpyMemcachedServer server = servers.get(i);
            buf.append(server.toString()).append(" ");
        }
        memClient = new MemcachedClient(
                AddrUtil.getAddresses(buf.toString()));
    }

    public void disConnect() {
        if (memClient == null) {
            return;
        }
        memClient.shutdown();
    }

    public void addObserver(ConnectionObserver obs) {
        memClient.addObserver(obs);
    }

    public void removeObserver(ConnectionObserver obs) {
        memClient.removeObserver(obs);
    }

    //---- Basic Operation Start ----//
    public ResultVO set(String key, int expire, Object value) {
        if (memClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        Future<Boolean> f = memClient.set(key, expire, value);
        boolean flag = CommonUtils.getBooleanValue(f);
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
        if (memClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        Object obj = memClient.get(key);
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
        if (memClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        CASValue obj = memClient.gets(key);
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

    public ResultVO cas(String key, long casId, Object value) {
        if (memClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        CASResponse obj = memClient.cas(key, casId, value);
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

    public ResultVO add(String key, int expire, String value) {
        if (memClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        Future<Boolean> f = memClient.add(key, expire, value);
        boolean flag = CommonUtils.getBooleanValue(f);
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

    public ResultVO replace(String key, int expire, String value) {
        if (memClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        Future<Boolean> f = memClient.replace(key, expire, value);
        boolean flag = CommonUtils.getBooleanValue(f);
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
        if (memClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        Future<Boolean> f = memClient.delete(key);
        boolean flag = CommonUtils.getBooleanValue(f);
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

    public ResultVO append(String key, Object value) {
        if (memClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        Future<Boolean> f = memClient.append(key, value);
        boolean flag = CommonUtils.getBooleanValue(f);
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

    public ResultVO flush() {
        if (memClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        Future<Boolean> f = memClient.flush();
        boolean flag = CommonUtils.getBooleanValue(f);
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

    //---- increment & decrement Start ----//
    public ResultVO increment(String key, int by, long defaultValue, int expire) {
        if (memClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        long result = memClient.incr(key, by, defaultValue, expire);
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

    public ResultVO increment(String key, int by) {
        if (memClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        long result = memClient.incr(key, by);
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

    public ResultVO decrement(String key, int by, long defaultValue, int expire) {
        if (memClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        long result = memClient.decr(key, by, defaultValue, expire);
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

    public ResultVO decrement(String key, int by) {
        if (memClient == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        long result = memClient.decr(key, by);
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


    public Object asyncGet(String key) {
        Object obj = null;
        Future<Object> f = memClient.asyncGet(key);
        try {
            obj = f.get(CommonUtils.DEFAULT_TIMEOUT,
                    CommonUtils.DEFAULT_TIMEUNIT);
        } catch (Exception e) {
            f.cancel(false);
        }
        return obj;
    }

    public Map<String, Object> getMulti(Collection<String> keys) {
        return memClient.getBulk(keys);
    }

    public Map<String, Object> getMulti(String[] keys) {
        return memClient.getBulk(keys);
    }

    public Map<String, Object> asyncGetMulti(Collection<String> keys) {
        Map map = null;
        Future<Map<String, Object>> f = memClient.asyncGetBulk(keys);
        try {
            map = f.get(CommonUtils.DEFAULT_TIMEOUT,
                    CommonUtils.DEFAULT_TIMEUNIT);
        } catch (Exception e) {
            f.cancel(false);
        }
        return map;
    }

    public Map<String, Object> asyncGetMulti(String keys[]) {
        Map map = null;
        Future<Map<String, Object>> f = memClient.asyncGetBulk(keys);
        try {
            map = f.get(CommonUtils.DEFAULT_TIMEOUT,
                    CommonUtils.DEFAULT_TIMEUNIT);
        } catch (Exception e) {
            f.cancel(false);
        }
        return map;
    }
    //---- Basic Operation End ----//


    public long asyncIncrement(String key, int by) {
        Future<Long> f = memClient.asyncIncr(key, by);
        return getLongValue(f);
    }

    public long asyncDecrement(String key, int by) {
        Future<Long> f = memClient.asyncDecr(key, by);
        return getLongValue(f);
    }
    //  ---- increment & decrement End ----//

    public void printStats() throws IOException {
        printStats(null);
    }

    public void printStats(OutputStream stream) throws IOException {
        Map<SocketAddress, Map<String, String>> statMap =
                memClient.getStats();
        if (stream == null) {
            stream = System.out;
        }
        StringBuffer buf = new StringBuffer();
        Set<SocketAddress> addrSet = statMap.keySet();
        Iterator<SocketAddress> iter = addrSet.iterator();
        while (iter.hasNext()) {
            SocketAddress addr = iter.next();
            buf.append(addr.toString() + "/n");
            Map<String, String> stat = statMap.get(addr);
            Set<String> keys = stat.keySet();
            Iterator<String> keyIter = keys.iterator();
            while (keyIter.hasNext()) {
                String key = keyIter.next();
                String value = stat.get(key);
                buf.append("  key=" + key + ";value=" + value + "/n");
            }
            buf.append("/n");
        }
        stream.write(buf.toString().getBytes());
        stream.flush();
    }

    public Transcoder getTranscoder() {
        return memClient.getTranscoder();
    }

    private long getLongValue(Future<Long> f) {
        try {
            Long l = f.get(CommonUtils.DEFAULT_TIMEOUT,
                    CommonUtils.DEFAULT_TIMEUNIT);
            return l.longValue();
        } catch (Exception e) {
            f.cancel(false);
        }
        return -1;
    }


}
