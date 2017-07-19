package com.test.detail.controller.nosql;

import com.mongodb.*;
import com.test.common.CommonUtils;
import com.test.detail.vo.ResultVO;

import java.util.List;

/**
 * Created by tingyun on 2017/7/19.
 * MongoDB操作类
 */
public class DBMongoDBController {

    public DBCollection getCollectionByMongo(String ip, String database, String table) {
        DBCollection collection;
        Mongo mongo = null;
        try {
            mongo = new Mongo(ip, CommonUtils.port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DB db = mongo.getDB(database);
        collection = db.getCollection(table);
        return collection;
    }


    /**
     * 插入
     *
     * @param collection
     * @param db_obj
     */
    public ResultVO insert(DBCollection collection, DBObject db_obj) {
        if (collection == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        WriteResult result = collection.insert(db_obj);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(result);
        if (result != null) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    /**
     * 保存save，调用insert或者update
     *
     * @param collection
     * @param db_obj
     */
    public ResultVO save(DBCollection collection, DBObject db_obj) {
        if (collection == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        WriteResult result = collection.save(db_obj);
        System.out.println("-------" + result);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(result);
        if (result != null) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    /**
     * 删除
     *
     * @param collection
     * @param db_obj
     */
    public ResultVO remove(DBCollection collection, DBObject db_obj) {
        if (collection == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        WriteResult result = collection.remove(db_obj);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(result);
        if (result != null) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    /**
     * 修改
     *
     * @param collection
     * @param queryObj
     * @param targetObj
     */
    public ResultVO update(DBCollection collection, DBObject queryObj, DBObject targetObj) {
        if (collection == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        WriteResult result = collection.update(queryObj, targetObj);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(result);
        if (result != null) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    /**
     * 查询唯一对象
     *
     * @param collection
     * @return
     */
    public ResultVO findOne(DBCollection collection, DBObject queryObj) {
        if (collection == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        DBObject result = collection.findOne(queryObj);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(result);
        if (result != null) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    /**
     * 根据id修改记录的age
     *
     * @param collection
     */
    public ResultVO findAndModify(DBCollection collection, DBObject queryObj, DBObject targetObj) {
        if (collection == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        DBObject result = collection.findAndModify(queryObj, targetObj);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(result);
        if (result != null) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    /**
     * distinct
     *
     * @param collection
     * @param fieldName
     * @param queryObj
     * @return
     */
    public ResultVO distinct(DBCollection collection, String fieldName, DBObject queryObj) {
        if (collection == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        List list = collection.distinct(fieldName, queryObj);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(list);
        if (list != null) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    /**
     * 删除表
     *
     * @param collection
     */
    public ResultVO drop(DBCollection collection) {
        if (collection == null) {
            return null;
        }
        long duration = -1;
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        collection.drop();
        long endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        vo.setDuration(duration);
        if (duration != -1) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    /**
     * 创建索引
     *
     * @param collection
     */
    public ResultVO createIndex(DBCollection collection, DBObject obj) {
        if (collection == null) {
            return null;
        }
        long duration = -1;
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        collection.createIndex(obj);
        long endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        vo.setDuration(duration);
        if (duration != -1) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }

    /**
     * 删除索引信息
     */
    public ResultVO dropIndex(DBCollection collection, DBObject obj) {
        if (collection == null) {
            return null;
        }
        long duration = -1;
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        collection.dropIndex(obj);
        long endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        vo.setDuration(duration);
        if (duration != -1) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }


    /**
     * DBCollection的group分组
     *
     * @param collection
     * @param key
     * @param cond
     * @param initial
     * @param reduce
     * @return
     */
    public ResultVO group(DBCollection collection, DBObject key, DBObject cond, DBObject initial, String reduce) {
        if (collection == null) {
            return null;
        }
        ResultVO vo = new ResultVO();
        long startTime = System.currentTimeMillis();
        BasicDBList group = (BasicDBList) collection.group(key, cond, initial, reduce);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        vo.setDuration(duration);
        vo.setResultObj(group);
        if (group != null) {
            vo.setStr(CommonUtils.successFlag);
        } else {
            vo.setStr(CommonUtils.failedFlag);
        }
        return vo;
    }
}
