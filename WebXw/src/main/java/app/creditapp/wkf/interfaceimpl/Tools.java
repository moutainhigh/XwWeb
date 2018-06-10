package app.creditapp.wkf.interfaceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.dhcc.workflow.WFUtil;
import com.dhcc.workflow.api.ProcessDefinition;
import com.dhcc.workflow.api.ProcessDefinitionQuery;

public class Tools {
	
	/**
	 * @param oldProcessKey  原来的流程Key,  
	 * @param oldParam       原来的：用户,机构号，机构名称
	 * @param newParam       新的: 用户，机构号，机构名称, 
	 * @param templateFileName  所使用的流程模板文件绝对全路径
	 */
	public static void doCopyProcess(String oldProcessKey, String oldParam, String newParam, String templateProcessKey) {
		//String oldParam = "400001,40000,原机构名称";//模板中的操作员管理员操作员号,机构号，机构名称
		//String newParams = "400001,40000,新的机构名称";//新的管理员操作员号,机构号，机构名称,最好不要超过10组
		
		String[] oldParams = oldParam.split(",");
		String[] newParams = newParam.split(",");
		String operatorKey = "author=\"" + oldParams[0] + "\"";
		String newOperator = "author=\"" + newParams[0] + "\"";
		String branchKey = "branch=\"" + oldParams[1] + "\"";
		String newBranch = "branch=\"" + newParams[1] + "\"";
		String branchNameKey = "branchName=\"" + oldParams[2] + "\"";
		String newBranchName = "branchName=\"" + newParams[2] + "\"";
		String candidateBranchKey = "candidate-branch=\"" + oldParams[1] + "\"";
		String newCandidateBranch = "candidate-branch=\"" + newParams[1] + "\"";
		
		String newProcessKey = getProcessKey(null);
		String processKey = "key=\"" + oldProcessKey + "\"";
		String newProcessKeyValue = "key=\"" + newProcessKey + "\"";
		
		String processNameKey = "name=\"" + oldProcessKey + "\"";
		String newProcessName = "name=\"" + newProcessKey + "\"";
		
		String strProcessValue = null;
		try {
			List<ProcessDefinition> pdlist = WFUtil.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(templateProcessKey).orderDesc(ProcessDefinitionQuery.PROPERTY_VERSION).list();
			if( pdlist.size() > 0 ) {
				strProcessValue = new String(pdlist.get(0).getValue(), "UTF-8");
			}
			
			strProcessValue = strProcessValue.replaceAll(processKey, newProcessKeyValue);
			
			strProcessValue = strProcessValue.replaceAll(processNameKey, newProcessName);
				
			strProcessValue = strProcessValue.replaceAll(operatorKey, newOperator);

			strProcessValue = strProcessValue.replaceAll(branchKey, newBranch);				

			strProcessValue = strProcessValue.replaceAll(branchNameKey, newBranchName);
					
			strProcessValue = strProcessValue.replaceAll(candidateBranchKey, newCandidateBranch);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(strProcessValue);
		//保存并更新流程
		//WFUtil.getRepositoryService().create().addResourceFromString(newProcessKey + WfdlDeployer.wfdlExtension, strProcessValue).deploy();
	}
	
	
	public static String getProcessKey(String name) {
		if( name == null || "".equals(name) ||"null".equals(name) ) {
			return "process_" + getID();
		} else {
			return (name + "_" + getID());
		}
	}
	
	public static String getID()  {
		DateFormat df = new SimpleDateFormat("yyyyMM-ddHHmmss");
		return df.format(new Date());
	}
	
}
