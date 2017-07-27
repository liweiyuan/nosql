package com.test.detail.controller.nosql;


import com.schooner.MemCached.MemcachedItem;
import com.test.common.CommonUtils;
import com.test.detail.vo.ResultVO;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import java.util.Date;

public class MemcachedClientController {
    MemCachedClient memCachedClient=null;
//    static MemCachedClient memCachedClient=null;
    public  void init(){
//	public static void init(){
		String[] servers = {"192.168.2.130:11211"};
        SockIOPool pool = SockIOPool.getInstance();
//        Integer[] weights = {3};
//        pool.setWeights(weights);
        pool.setServers(servers);
        pool.setFailover(true);
        pool.setInitConn(10);
        pool.setMinConn(5);
        pool.setMaxConn(250);
        pool.setMaintSleep(30);
        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setAliveCheck(true);
        pool.initialize();
		if(memCachedClient==null){
			memCachedClient = new MemCachedClient();
		}
//		System.out.println("***********************************"+memCachedClient);
	}
//    public  void set(String key,String value, Date expire){
    public ResultVO set(String key, String value){
    	if(memCachedClient==null){
    		return null;
    	}
    	ResultVO vo=new ResultVO();
    	long startTime=System.currentTimeMillis();
    	boolean flag=memCachedClient.set(key, value);
    	long endTime=System.currentTimeMillis();
		long duration=endTime-startTime;
		vo.setDuration(duration);
		vo.setResultObj(flag);
//		String realPath ="D:/software/agent/tingyun/logs";//项目根目录
//		String localFilePath="D:/software/agent/tingyun/logs/tingyun_agent.log";		  
	    try {
//			LogFileUtils.realtimeShowLog(localFilePath,"/set",vo);
//            ExecutorService exec = Executors.newCachedThreadPool();
//            ArrayList<Future<String>> results = new ArrayList<Future<String>>();
//            results.add(exec.submit(new MemcachedThread("/set")));
//          results.add(ts);
//          Future<String> fs=results.get(0);
//          vo.setStr(fs.get());
	    	if(flag){
				vo.setStr(CommonUtils.successFlag);
		    }else{
			    vo.setStr(CommonUtils.failedFlag);
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	   
		return vo;
    }

    public ResultVO set(String key, int obj, int value){
    	if(memCachedClient==null){
    		return null;
    	}
    	ResultVO vo=new ResultVO();
    	long startTime=System.currentTimeMillis();
    	boolean flag=memCachedClient.set(key, obj, value);
    	long endTime=System.currentTimeMillis();
    	long duration=endTime-startTime;
    	vo.setDuration(duration);
		vo.setResultObj(flag);
    	if(flag){
			vo.setStr(CommonUtils.successFlag);
	    }else{
		    vo.setStr(CommonUtils.failedFlag);
	    }
		return vo;
    }
    public ResultVO get(String key){
    	if(memCachedClient==null){
    		return null;
    	}
    	ResultVO vo=new ResultVO();
    	long startTime=System.currentTimeMillis();
    	Object obj= memCachedClient.get(key);
    	long endTime=System.currentTimeMillis();
		long duration=endTime-startTime;
		vo.setDuration(duration);
		vo.setResultObj(obj);
    	if(obj!=null){
    		vo.setStr(CommonUtils.successFlag);
    	}else{
    	    vo.setStr(CommonUtils.failedFlag);
    	}
    	return vo;
    }
    public ResultVO gets(String key){
    	if(memCachedClient==null){
    		return null;
    	}
    	ResultVO vo=new ResultVO();
    	long startTime=System.currentTimeMillis();
    	MemcachedItem obj= memCachedClient.gets(key);
    	long endTime=System.currentTimeMillis();
		long duration=endTime-startTime;
		vo.setDuration(duration);
		vo.setResultObj(obj);
		if(obj!=null){
    		vo.setStr(CommonUtils.successFlag);
    	}else{
    	    vo.setStr(CommonUtils.failedFlag);
    	}
    	return vo;
    }
    public ResultVO cas(String key, MemcachedItem item){
    	if(memCachedClient==null){
    		return null;
    	}
    	ResultVO vo=new ResultVO();
    	long startTime=System.currentTimeMillis();
    	boolean flag=memCachedClient.cas(key, item.getValue(), item.getCasUnique());
    	long endTime=System.currentTimeMillis();
		long duration=endTime-startTime;
		vo.setDuration(duration);
		vo.setResultObj(flag);
		if(flag){
			vo.setStr(CommonUtils.successFlag);
		}else{
		    vo.setStr(CommonUtils.failedFlag);
		}
    	return vo;
    }
    /**
	 * 向缓存添加键值对并为该键值对设定逾期时间（即多长时间后该键值对从Memcached内存缓存中删除，比如： new Date(1000*10)，则表示十秒之后从Memcached内存缓存中删除）。
	 * 
	 * @author GaoHuanjie
	 */
	public ResultVO add(String key, String value, Date expire) {
		if(memCachedClient==null){
    		return null;
    	}
		ResultVO vo=new ResultVO();
		long startTime=System.currentTimeMillis();
	    boolean flag=memCachedClient.add(key, value, expire);
	    long endTime=System.currentTimeMillis();
		long duration=endTime-startTime;
		vo.setDuration(duration);
		vo.setResultObj(flag);
	    if(flag){
	    	vo.setStr(CommonUtils.successFlag);
		}else{
			vo.setStr(CommonUtils.failedFlag);
		}
		return vo;
	}
	public ResultVO delete(String key) {
		if(memCachedClient==null){
    		return null;
    	}
		ResultVO vo=new ResultVO();
		long startTime=System.currentTimeMillis();
		boolean flag=memCachedClient.delete(key);
		long endTime=System.currentTimeMillis();
		long duration=endTime-startTime;
		vo.setDuration(duration);
		vo.setResultObj(flag);
		if(flag){
		   vo.setStr(CommonUtils.successFlag);
		}else{
		   vo.setStr(CommonUtils.failedFlag);
	    }
	    return vo;
	}
	public ResultVO replace(String key, String target) {
		if(memCachedClient==null){
    		return null;
    	}
		ResultVO vo=new ResultVO();
		long startTime=System.currentTimeMillis();
		boolean flag=memCachedClient.replace(key, target);
		long endTime=System.currentTimeMillis();
		long duration=endTime-startTime;
		vo.setDuration(duration);
		vo.setResultObj(flag);
		if(flag){
			 vo.setStr(CommonUtils.successFlag);
		}else{
			 vo.setStr(CommonUtils.failedFlag);
		}
	    return vo;
	}
	public ResultVO append(String key, String target) {
		if(memCachedClient==null){
    		return null;
    	}
		ResultVO vo=new ResultVO();
		long startTime=System.currentTimeMillis();
		boolean flag=memCachedClient.append(key, target);
		long endTime=System.currentTimeMillis();
		long duration=endTime-startTime;
		vo.setDuration(duration);
		vo.setResultObj(flag);
	    if(flag){
	    	 vo.setStr(CommonUtils.successFlag);
		}else{
			 vo.setStr(CommonUtils.failedFlag);
		}
	    return vo;
	}
	public ResultVO incr(String key) {
		if(memCachedClient==null){
    		return null;
    	}
		ResultVO vo=new ResultVO();
		long startTime=System.currentTimeMillis();
//		System.out.println("================="+memCachedClient.get(key));
		long result=memCachedClient.incr(key);
		long endTime=System.currentTimeMillis();
		long duration=endTime-startTime;
		vo.setDuration(duration);
		vo.setResultObj(result);
		if(result==-1){
			vo.setStr(CommonUtils.failedFlag);
		}else{
			vo.setStr(CommonUtils.successFlag);
		}
		return vo;
	}
	public ResultVO decr(String key) {
		if(memCachedClient==null){
    		return null;
    	}
		ResultVO vo=new ResultVO();
		long startTime=System.currentTimeMillis();
		long result=memCachedClient.decr(key);
		long endTime=System.currentTimeMillis();
		long duration=endTime-startTime;
		vo.setDuration(duration);
		vo.setResultObj(result);
		if(result==-1){
		    vo.setStr(CommonUtils.failedFlag);
		}else{
			vo.setStr(CommonUtils.successFlag);
		}
		return vo;
	}
	public ResultVO flushAll() {
		if(memCachedClient==null){
    		return null;
    	}
		ResultVO vo=new ResultVO();
		long startTime=System.currentTimeMillis();
		boolean flag=memCachedClient.flushAll();
		long endTime=System.currentTimeMillis();
		long duration=endTime-startTime;		
		vo.setDuration(duration);
		vo.setResultObj(flag);
		if(flag){
		   vo.setStr(CommonUtils.successFlag);
		}else{
		   vo.setStr(CommonUtils.failedFlag);
		}
		return vo;
	}
//	 public static void main(String[] args) {
//	        /*初始化SockIOPool，管理memcached的连接池*/
//	        String[] servers = {"192.168.2.130:11211"};
//	        SockIOPool pool = SockIOPool.getInstance();
//	        pool.setServers(servers);
//	        pool.setFailover(true);
//	        pool.setInitConn(10);
//	        pool.setMinConn(5);
//	        pool.setMaxConn(250);
//	        pool.setMaintSleep(30);
//	        pool.setNagle(false);
//	        pool.setSocketTO(3000);
//	        pool.setAliveCheck(true);
//	        pool.initialize();
//	        /*建立MemcachedClient实例*/
//	        MemCachedClient memCachedClient = new MemCachedClient();
////	        for (int i = 0; i < 5; i++) {
////	            /*将对象加入到memcached缓存*/
////	            boolean success = memCachedClient.set("" + i, "Hello!");
////	            /*从memcached缓存中按key值取对象*/
////	            String result = (String) memCachedClient.get("" + i);
////	            System.out.println(String.format("set( %d ): %s", i, success));
////                System.out.println(String.format("get( %d ): %s", i, result));
////	        }
//	       memCachedClient = new MemCachedClient();
////	      Date expire=new Date(1000);
////	      memCachedClient.set("foo",new Date());
////	      Date value= (Date)memCachedClient.get("foo");
//	      boolean f=memCachedClient.set("foo",2);
//          long value=memCachedClient.incr("foo");
//	      System.out.println(f);
//	      System.out.println(value);
//	    }


}
