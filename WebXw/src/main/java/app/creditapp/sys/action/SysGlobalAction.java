package app.creditapp.sys.action;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import app.base.BaseAction;
import app.creditapp.entity.SysGlobal;
import app.creditapp.sys.bo.SysGlobalBo;
import app.util.LoginSessionListener;

public class SysGlobalAction extends BaseAction {
	  
	//页面传值
	private SysGlobal sysGlobal;
	
	//注入SysAreaBO
	private SysGlobalBo sysGlobalBo;
	
	private String sysSts;
	
	
	
	public String getSysGlobalPage() throws Exception{
		sysGlobal = sysGlobalBo.getSysGlobal();
		return "list";	
	}
	
	
	public String updateSts() throws Exception{
		sysGlobal = sysGlobalBo.getSysGlobal();

		sysGlobal = new SysGlobal();
		sysGlobal.setSys_sts(sysSts);
		try {
			sysGlobalBo.updateSts(sysGlobal);
			if ("1".equals(sysSts)) {
				kickAllUserDown();//将所有用户踢下线
			}
			if("1".equals(sysSts)){
				this.addActionMessage("系统启用成功,可以做正常业务操作!");
			}else if("2".equals(sysSts)){
				this.addActionMessage("系统停用成功,可以开始日终处理!");
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
		}
		return getSysGlobalPage();	
	}
	/**
	 * 将所有用户踢下线
	 */
	private void kickAllUserDown()
	{
		Map<String, HttpSession> sessions=LoginSessionListener.htsession;
		if(null!=sessions&&sessions.size()>0)
		{
			for (Entry<String, HttpSession> entry : sessions.entrySet()) 
			{
				entry.getValue().invalidate();
			}
			sessions.clear();
			LoginSessionListener.hUserName.clear();
			LoginSessionListener.mpOper.clear();
		}
	}
	public String update() throws Exception{
		sysGlobal = new SysGlobal();
		sysGlobalBo.updateDocSize(sysGlobal);
		this.addActionMessage("保存成功!");
		return getSysGlobalPage();	
	}


	public SysGlobalBo getSysGlobalBo() {
		return sysGlobalBo;
	}


	public void setSysGlobalBo(SysGlobalBo sysGlobalBo) {
		this.sysGlobalBo = sysGlobalBo;
	}




	public void setSysGlobal(SysGlobal sysGlobal) {
		this.sysGlobal = sysGlobal;
	}


	public SysGlobal getSysGlobal() {
		return sysGlobal;
	}


	public String getSysSts() {
		return sysSts;
	}


	public void setSysSts(String sysSts) {
		this.sysSts = sysSts;
	}

	
}