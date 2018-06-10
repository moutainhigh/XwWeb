package app.creditapp.sys.entity;

import java.util.ArrayList;

import app.base.BaseDomain;
      
public class TableDoc extends BaseDomain implements java.io.Serializable {
	/*
	 * 此实体类用于行政区划维护管理
	 */
//变量定义
private String doc_no;              //行政区划编号
private String doc_name;            //行政区划名称
private String lev;                  //行政级别
private String uplev;                //上级编号


private ArrayList<TableDoc> children; //子菜单

public TableDoc() {
	this.doc_no = "";
	this.doc_name = "";
	this.lev = "";
	this.uplev = "";
	this.children = new ArrayList<TableDoc>();
}


/**
 * @param docNo
 * @param docName
 * @param lev
 * @param uplev
 * @param docSeq
 * @param children
 */
public TableDoc(String docNo, String docName, String lev, String uplev, String docSeq, ArrayList<TableDoc> children) {
	super();
	doc_no = docNo;
	doc_name = docName;
	this.lev = lev;
	this.uplev = uplev;
	if(children!=null){
		this.children = children;
	}else{
		this.children = new ArrayList<TableDoc>();
	}
}

public void reInit(TableDoc node){
	this.doc_no = node.getDoc_no();
	this.doc_name = node.getDoc_name();
	this.lev = node.getLev();
	this.uplev = node.getUplev();
	this.children = node.getChildren();
}

public void addChildren(TableDoc child){
	this.children.add(child);
}

public String toJson(){
	StringBuffer str = new StringBuffer();
	if(this.children!=null && this.children.size()>=1){
		for(int i=0;i<this.children.size();i++){
			str.append(this.children.get(i).toJson());
			if(i+1<this.children.size()){
				str.append(",");
			}
		}
	}
	return "{'doc_no':'"+doc_no+"','text':'"+doc_name+"','lev':'"+lev+"','uplev':'"+uplev+"','children':["+str.toString()+"]}";
}

//set方法

public void setLev(String lev){ this.lev=lev;} 
public void setUplev(String uplev){ this.uplev=uplev;} 

//get方法

public String getLev(){return lev;} 
public String getUplev(){return uplev;} 

public ArrayList<TableDoc> getChildren() {
	return children;
}
public void setChildren(ArrayList<TableDoc> children) {
	this.children = children;
}


public String getDoc_no() {
	return doc_no;
}


public void setDoc_no(String docNo) {
	doc_no = docNo;
}





public String getDoc_name() {
	return doc_name;
}


public void setDoc_name(String docName) {
	doc_name = docName;
} 
}