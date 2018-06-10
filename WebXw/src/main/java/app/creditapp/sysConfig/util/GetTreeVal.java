package app.creditapp.sysConfig.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.creditapp.sysConfig.entity.TableInfo;
import app.creditapp.sysConfig.entity.TreeNode;

import com.alibaba.fastjson.JSON;



public class GetTreeVal {
	public static Map<String,StringBuffer> getTableVal(List<TableInfo> tablelist) throws SQLException{
		Map<String,StringBuffer> tableMap = new HashMap<String,StringBuffer>();
		StringBuffer initialLetters = new StringBuffer();
		StringBuffer tablesName = new StringBuffer();
		tableMap.put("initialLetter", initialLetters);
		for(int i=0;i<tablelist.size();i++){
			String tableName = tablelist.get(i).getTable_name().trim();
			String tableComment = tablelist.get(i).getComments()==null?"Ã»ÓÐ×¢ÊÍ":tablelist.get(i).getComments().trim();
			
			if(tableMap.get("initialLetter").indexOf(tableName.substring(0, 1).toUpperCase())==-1){
				tableMap.put("initialLetter", initialLetters.append(tableName.substring(0, 1).toUpperCase()+","));
				if(tableName.substring(0, 1).toUpperCase().equals("A")){
					tablesName.append(tableName+"-"+tableComment+",");
				}else{
					tablesName.append("@"+tableName+"-"+tableComment+",");
				}
				
			}else{
				tablesName.append(tableName+"-"+tableComment+",");
			}
		}
		tableMap.put("tableInfo", tablesName);
		return tableMap;
	}
	
	public static List<TreeNode> getTreeVal(Map<String,StringBuffer> tableMap){
		List<TreeNode> resultList = new ArrayList<TreeNode>();
		String[] iniLetArr = tableMap.get("initialLetter").toString().split(",");
		String[] tableArr = tableMap.get("tableInfo").toString().split("@");
		int i = 1;
		for(String inilet:iniLetArr){
			TreeNode treeNode = new TreeNode();
			treeNode.setId(String.valueOf(i));
			treeNode.setpId(String.valueOf(0));
			treeNode.setName(inilet);
			resultList.add(treeNode);
			
			int j = 1;
//			for(String tables:tableArr){
				String[] table =tableArr[i-1].split(",");
				if(table[0].trim().indexOf(inilet)!=-1){
					for(String displayName:table){
						treeNode = new TreeNode();
						treeNode.setId(String.valueOf(i*10+(j++)));
						treeNode.setpId(String.valueOf(i));
						treeNode.setName(displayName);
						resultList.add(treeNode);
					}
				}
				i++;
//				break;
//			}
		}
		return resultList;
	}
	public static List<TreeNode> getZTreeVal(Map<String,StringBuffer> tableMap){
		List<TreeNode> resultList = new ArrayList<TreeNode>();
		String[] iniLetArr = tableMap.get("initialLetter").toString().split(",");
		String[] tableArr = tableMap.get("tableInfo").toString().split("@");
		int i = 1;
		for(String inilet:iniLetArr){
			TreeNode treeNode = new TreeNode();
			treeNode.setId(String.valueOf(i));
			treeNode.setpId(String.valueOf(0));
			treeNode.setName(inilet);
			treeNode.setOpen("true");
			resultList.add(treeNode);
			
			int j = 1;
//			for(String tables:tableArr){
				String[] table =tableArr[i-1].split(",");
				if(table[0].trim().indexOf(inilet)!=-1){
					for(String displayName:table){
						treeNode = new TreeNode();
						treeNode.setId(String.valueOf(i*10+(j++)));
						treeNode.setpId(String.valueOf(i));
						treeNode.setName(displayName);
						treeNode.setOpen("false");
						resultList.add(treeNode);
					}
				}
				i++;
//				break;
//			}
		}
		return resultList;
	}
	
	public static String toJsp2(List<TableInfo> tablelist) throws SQLException{
		List<TreeNode> list =GetTreeVal.getZTreeVal(GetTreeVal.getTableVal(tablelist));
//		String jsonString = JsonUtil.getJsonString4List(list);
		String jsonString = JSON.toJSONString(list);
		return jsonString;
	}
	
	public static String toJsp(List<TableInfo> tablelist) throws SQLException{
		List<TreeNode> list =GetTreeVal.getTreeVal(GetTreeVal.getTableVal(tablelist));
//		String jsonString = JsonUtil.getJsonString4List(list);
		String jsonString = JSON.toJSONString(list);
		return jsonString;
	}
}
