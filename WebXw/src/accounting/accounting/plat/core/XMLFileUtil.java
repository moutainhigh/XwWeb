package accounting.plat.core;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *@Classname	XMLFileUtil.java
 *@Version 1.0	
 *@Copyright 	yuchengtech
 *@Description：此类主要负责对XML文件的读写
 */
public class XMLFileUtil{
	/*
	 * 存储所有需要解析的文件列表
	 */
	private ArrayList<String> al = new ArrayList<String>();
	
	private String m_fileType = ".xml";		  //文件类型	
	
	/*
	 * 组件配置文件的xml标签
	 */
	public final String  	BUILDINGELEMENT ="buildingElement";
	public final String  	DAO = "DAO";
	public final String  	OPERATION = "OPERATION";
	public final String 	ID = "id";
	public final String 	DESCRIBE = "describe";
	public final String 	COMPROPERTY = "comproperty";
	public final String 	CLASSNAME = "classname";
	
	
	/**
	 * 从组件配置文件中取出所有的代理信息，存放到一个MAP中,
	 * 能解析的文件结构
	 * <cmis>
	 * 		<dddd>
	 * 			<xxx></xxx>
	 * 			<ddd></ddd>
	 * 		<\dddd>
	 * 		<dddd>...</ddd>
	 * </cmis>
	 * @param path 文件路径
	 * @return 组件信息 结构（）
	 * @throws Exception
	 */
	public Map readDaoFromXMLFile(String dir) throws Exception{
		
		this.searchFiles(dir);
		
		HashMap<String, HashMap<String, String>> infoMap = new HashMap<String, HashMap<String, String>>();
		
		//构件解析器
		DocumentBuilderFactory doBuilderFactory= DocumentBuilderFactory.newInstance();
		DocumentBuilder doBuilder = doBuilderFactory.newDocumentBuilder();
		
		for(int k=0; k<al.size(); k++){
			String path = al.get(k);
			
			//EMPLog.log(this.getClass().getName(), EMPLog.DEBUG, 0, "start parser dao file :  path");
			
//			File file= new File(path);
//			Document doc =  doBuilder.parse(file);
			
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream(path);
			Document doc =  doBuilder.parse(is);
			
			//取得根目录
			doc.getDocumentElement().normalize();
			
			//取得所有构件列表
			NodeList buildingList = doc.getElementsByTagName(BUILDINGELEMENT);
			
			for(int s=0; s<buildingList.getLength(); s++){
				//得到单个构件节点
				Node buildNode = buildingList.item(s);
				Element buildElement = (Element)buildNode;
				
				//得到这个构件下所有的agent
				NodeList agentList = buildElement.getElementsByTagName(DAO);
				/*
				 * 取出每个agent节点信息，存入一个map
				 */
				for(int i=0; i<agentList.getLength(); i++){
					//创建单个agent信息存储容器
					HashMap<String, String> agentMap = new HashMap<String, String>();
					
					Node agentNode = agentList.item(i);
					Element agentElement  = (Element)agentNode;
					String agentId = agentElement.getAttribute(ID);
					String agentDescribe = agentElement.getAttribute(DESCRIBE);
					String agentComproperty = agentElement.getAttribute(COMPROPERTY);
					
					agentMap.put(DESCRIBE, agentDescribe);
					agentMap.put(COMPROPERTY, agentComproperty);
					
					NodeList classnameList_agent = agentElement.getChildNodes();
					String classname_agent = ((Node)classnameList_agent.item(0)).getNodeValue().trim();
					agentMap.put(CLASSNAME, classname_agent);
					
					infoMap.put(agentId, agentMap);
				}
	
			}
		}
		
		return infoMap;
	}	
	
	/**
	 * 解析某个目录下所有文件得到需要的文件的绝对路径
	 * @param dir 文件路径  如： c:\work
	 * @return 文件路径
	 * @throws Exception
	 */
    private ArrayList searchFiles(String dir) throws Exception {
        //清空数据
        al.clear();
        	
//		File root = new File(dir);
//		//得到该文件夹下的所有类型文件名称
//		File[] filesOrDirs = root.listFiles();
//   
//		for (int i = 0; i < filesOrDirs.length; i++){
//			/*
//			* 判断该文件是否是文件夹，是则继续读取，不是则读取该目录
//			*/
//			 if (filesOrDirs[i].isDirectory()){
//			    	
//			     searchFiles(filesOrDirs[i].getAbsolutePath());
//			        
//			  } else {
//				  
//			    //得到文件名
//			    String fileName = filesOrDirs[i].getName();
//			    
//			    /*
//			    * 匹配文件名，看看该文件是否是需要找的文件
//			    */
//			    
//				boolean IsValidfileEnd   = m_fileType.equals(fileName.substring(fileName.length()-4, fileName.length()));
//				    	
//				//完成匹配的把该文件路径存起来
//				if(IsValidfileEnd){      
//					
//				    al.add(filesOrDirs[i].getAbsolutePath()) ;  
//				    
//				}	
//			    
//			 }
//		}
        al.add(dir+File.separator+"LoanInterfConfig.xml");
	
        return al;
        
    }

	public Map readOperationFromXMLFile(String dir) throws Exception{
		
		this.searchFiles(dir);
		
		HashMap<String, HashMap<String, String>> infoMap = new HashMap<String, HashMap<String, String>>();
		
		//构件解析器
		DocumentBuilderFactory doBuilderFactory= DocumentBuilderFactory.newInstance();
		DocumentBuilder doBuilder = doBuilderFactory.newDocumentBuilder();
		
		for(int k=0; k<al.size(); k++){
			String path = al.get(k);
//          EMPLog.log(this.getClass().getName(), EMPLog.DEBUG, 0, "start parser interface file :  path");
//			File file= new File(path);
//			Document doc =  doBuilder.parse(file);
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream(path);
			Document doc =  doBuilder.parse(is);
			//取得根目录
			doc.getDocumentElement().normalize();
			//取得所有构件列表
			NodeList buildingList = doc.getElementsByTagName(BUILDINGELEMENT);
		
			for(int s=0; s<buildingList.getLength(); s++){
				//得到单个构件节点
				Node buildNode = buildingList.item(s);
				Element buildElement = (Element)buildNode;
				
				//得到这个构建下所有的interface
				NodeList interfaceList = buildElement.getElementsByTagName(OPERATION);
				
				/*
				 * 取出每个interface节点信息，存入一个map
				 */
				for(int i=0; i<interfaceList.getLength(); i++){
					//创建单个interface信息存储容器
					HashMap<String, String> interfaceMap = new HashMap<String, String>();
					
					Node interfaceNode = interfaceList.item(i);
					Element interfaceElement  = (Element)interfaceNode;
					String interfaceId = interfaceElement.getAttribute(ID);
					String interfaceDescribe = interfaceElement.getAttribute(DESCRIBE);
					String interfaceComproperty = interfaceElement.getAttribute(COMPROPERTY);
					
					interfaceMap.put(DESCRIBE, interfaceDescribe);
					interfaceMap.put(COMPROPERTY, interfaceComproperty);
					
					NodeList classnameList = interfaceElement.getChildNodes();
					String classname = ((Node)classnameList.item(0)).getNodeValue().trim();
					interfaceMap.put(CLASSNAME, classname);
					
					infoMap.put(interfaceId, interfaceMap);
					
				}
	
			}
		}
		
		return infoMap;
	}    
    
}