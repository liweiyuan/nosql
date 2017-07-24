package com.test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.test.common.CommonUtils;
import com.test.detail.controller.nosql.DBMongoDBController;
import com.test.detail.vo.ResultVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by tingyun on 2017/7/19.
 * 外部访问MongoDB类
 */
@WebServlet(value = "/mongodb")
public class MongoDBServlet extends HttpServlet {

    //初始化MongoDB连接
    DBMongoDBController dbMongoDBController=new DBMongoDBController();
    DBCollection collection=null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String outStr="";
        ResultVO resultVO=null;
        collection=getCollection();
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<html><head><title></title></head><body><h2>Mongodb driver methods:</h2>");
        resultVO=save();
        if(resultVO!=null){
            outStr="save:"+"<br>"+"duration:"+resultVO.getDuration()+"; "+"result str:"+resultVO.getStr();
            stringBuffer.append(outStr + "<br>");
        }
        resultVO=insert();
        if(resultVO!=null){
            outStr="insert:"+"<br>"+" duration:"+resultVO.getDuration()+"; "+"result str:"+resultVO.getStr();
            stringBuffer.append(outStr + "<br>");
        }
        resultVO=update();
        if(resultVO!=null){
            outStr="update:"+"<br>"+" duration:"+resultVO.getDuration()+"; "+"result str:"+resultVO.getStr();
            stringBuffer.append(outStr + "<br>");
        }
        resultVO=findOne();
        if(resultVO!=null){
            outStr="findOne:"+"<br>"+" duration:"+resultVO.getDuration()+"; "+"result str:"+resultVO.getStr()+"; "+"result obj:"+resultVO.getResultObj();
            stringBuffer.append(outStr + "<br>");
        }
        resultVO=findAndModify();
        if(resultVO!=null){
            outStr="findAndModify:"+"<br>"+" duration:"+resultVO.getDuration()+"; "+"result str:"+resultVO.getStr();
            stringBuffer.append(outStr + "<br>");
        }
        resultVO=distinct();
        if(resultVO!=null){
            outStr="distinct:"+"<br>"+" duration:"+resultVO.getDuration()+"; "+"result str:"+resultVO.getStr();
            stringBuffer.append(outStr + "<br>");
        }
        resultVO=group();
        if(resultVO!=null){
            outStr="group:"+"<br>"+" duration:"+resultVO.getDuration()+"; "+"result str:"+resultVO.getStr()+"; "+"result obj:"+resultVO.getResultObj();
            stringBuffer.append(outStr + "<br>");
        }
        resultVO=remove();
        if(resultVO!=null){
            outStr="remove:"+"<br>"+" duration:"+resultVO.getDuration()+"; "+"result str:"+resultVO.getStr();
            stringBuffer.append(outStr + "<br>");
        }
        resultVO=drop();
        if(resultVO!=null){
            outStr="drop:"+"<br>"+" duration:"+resultVO.getDuration()+"; "+"result str:"+resultVO.getStr();
            stringBuffer.append(outStr + "<br>");
        }
        resultVO=createIndex();
        if(resultVO!=null){
            outStr="createIndex:"+"<br>"+" duration:"+resultVO.getDuration()+"; "+"result str:"+resultVO.getStr();
            stringBuffer.append(outStr + "<br>");
        }
        resultVO=dropIndex();
        if(resultVO!=null){
            outStr="dropIndex:"+"<br>"+" duration:"+resultVO.getDuration()+"; "+"result str:"+resultVO.getStr();
            stringBuffer.append(outStr + "<br>");
        }
        stringBuffer.append(" </body></html>");
        CommonUtils.printToPage(response, stringBuffer.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    private DBCollection getCollection() {
        if(collection!=null){
            return collection;
        }
        Properties p= CommonUtils.getPropertyFile("/mongodb.properties", this.getServletContext());//默认取webapp下根目录
        if(p==null){
            return null;
        }
        String ip=p.getProperty("ip");
        String database=p.getProperty("database");
        String table=p.getProperty("table");
        collection=dbMongoDBController.getCollectionByMongo(ip,database,table);
        return collection;
    }




    private ResultVO save(){
        if(collection==null){
            collection=getCollection();
        }
        DBObject obj=null;
        ResultVO vo=null;
        BasicDBObject db_obj=new BasicDBObject();
        db_obj.put("id", 1001);
        ResultVO vo1=dbMongoDBController.findOne(collection, db_obj);
        if(vo1!=null){
            if(vo1.getResultObj()!=null){
                obj=(DBObject)vo1.getResultObj();
            }else{
                obj=new BasicDBObject();
                obj.put("id", 1001);
                obj.put("name", "yhb");
                obj.put("age", "30");
            }
            vo=dbMongoDBController.save(collection,obj);//这里必须用查出来的对象进行save,如果查询对象存在，执行update,不存在就执行insert,如果重新new实例就会执行insert
        }
        return vo;
    }
    private ResultVO insert(){
        if(collection==null){
            collection=getCollection();
        }
        BasicDBObject db_obj=new BasicDBObject();
        db_obj.put("id", 1001);
        db_obj.put("name", "yhb");
        db_obj.put("age", 30);
        ResultVO vo=dbMongoDBController.insert(collection,db_obj);
        return vo;
    }
    private ResultVO remove(){
        if(collection==null){
            collection=getCollection();
        }
        long duration=-1;
        BasicDBObject db_obj=new BasicDBObject();
        db_obj.put("id", 1001);
        db_obj.put("name", "yhb");
        db_obj.put("age", 30);
        ResultVO vo=dbMongoDBController.remove(collection,db_obj);
        return vo;
    }
    private ResultVO update(){
        if(collection==null){
            collection=getCollection();
        }
        BasicDBObject queryObj=new BasicDBObject();
        queryObj.put("id", 1001);
        BasicDBObject targetObj=new BasicDBObject();
        targetObj.put("id", 1001);
        targetObj.put("name", "yhb");
        targetObj.put("age", 26);
        ResultVO vo=dbMongoDBController.update(collection,queryObj,targetObj);
        return vo;
    }
    private ResultVO findOne(){
        if(collection==null){
            collection=getCollection();
        }
        BasicDBObject queryObj=new BasicDBObject();
        queryObj.put("id", 1001);
        ResultVO vo=dbMongoDBController.findOne(collection,queryObj);
        return vo;
    }
    private ResultVO findAndModify(){
        if(collection==null){
            collection=getCollection();
        }
        BasicDBObject queryObj=new BasicDBObject("id", 1001);
        BasicDBObject targetObj=new BasicDBObject();
        targetObj.put("id", 1001);
        targetObj.put("name", "yhb");
        targetObj.put("age", 26);
        ResultVO vo=dbMongoDBController.findAndModify(collection,queryObj,targetObj);
        return vo;
    }
    private ResultVO distinct(){
        if(collection==null){
            collection=getCollection();
        }
        BasicDBObject queryObj=new BasicDBObject("name", "yhb");
        ResultVO vo=dbMongoDBController.distinct(collection,"age",queryObj);
        return vo;
    }
    private ResultVO drop(){
        if(collection==null){
            collection=getCollection();
        }
        ResultVO vo=dbMongoDBController.drop(collection);
        return vo;
    }

    private ResultVO createIndex(){
        if(collection==null){
            collection=getCollection();
        }
        DBObject obj = new BasicDBObject();
        obj.put("intData",1);
        obj.put("unique",true);
        ResultVO vo=dbMongoDBController.createIndex(collection,obj);
        return vo;
    }
    private ResultVO dropIndex(){
        if(collection==null){
            collection=getCollection();
        }

        //删除的索引必须跟创建的索引名称\排序\是否唯一都相同才能删除
        BasicDBObject obj = new BasicDBObject();
        obj.put("intData",1);
        obj.put("unique",true);
        ResultVO vo=dbMongoDBController.dropIndex(collection,obj);
        return vo;
    }
    /**
     * 测试DBCollection的group管道聚合分组方法
     */
    private ResultVO group(){
        if(collection==null){
            collection=getCollection();
        }
        BasicDBObject key = new BasicDBObject("age",true);
        BasicDBObject cond = new BasicDBObject("num",new BasicDBObject("$gt","10").append("$lt", "20"));
        BasicDBObject initial = new BasicDBObject("num",0);
        String reduce = "function (doc,prev){"
                +"if(doc.num>prev.num){"
                +"prev.num=doc.num;"
                +"prev.name=doc.name"
                +"prev.age=num.age;"
                +"}"
                +"}";
        ResultVO vo=dbMongoDBController.group(collection,key,cond,initial,reduce);
        return vo;
    }
}
