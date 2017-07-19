package com.test.detail.vo;

import java.io.Serializable;
import java.util.List;

public class ResultVO implements Serializable{
  
   private long duration;
   private List resultList;
   private String str;
   private Object resultObj;
   
   public Object getResultObj() {
		return resultObj;
   }
   public void setResultObj(Object resultObj) {
	   this.resultObj = resultObj;
   }
 
  public String getStr() {
	return str;
  }
  public void setStr(String str) {
	this.str = str;
  }
  public List getResultList() {
	 return resultList;
  }
  public void setResultList(List resultList) {
	  this.resultList = resultList;
  }
  
  public long getDuration() {
	  return duration;
  }
  public void setDuration(long duration) {
	  this.duration = duration;
  }
 
}
