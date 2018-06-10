package app.oscache;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import app.util.User;
/**
 * 功能描述：取权限按钮缓存类
 *
 */
public class DataButton {
	
	private boolean flag = true; // 默认值为有权限
	
	/**
	 * 功能描述：构造方法初始化 flag 值
	 * @param info 查询参数，格式 menu_no@button_no@role/user_id
	 * @param roleOrUser 系统权限模式 user = 角色 role = 权限
	 */
	public DataButton(String info,String roleOrUser){
		if(StringUtils.isNotBlank(info)){
			flag = MBaseCache.getCache().getBeanCacheForButton(info,roleOrUser);
			// 开发环境去掉权限设置，生产环境增加
//			flag = true;
		}
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	/**
	 * 功能描述：构造方法初始化 flag 值
	 * @param info 查询参数，格式 menu_no@button_no@role/user_id
	 * @param roleOrUser 系统权限模式 user = 角色 role = 权限
	 * @throws Exception 
	 */
	public DataButton(String fieldName) {
		flag = false;
		StringBuffer strBuf = new StringBuffer("");
		String stats = "@";
		String menuno = ""; 
		try{
		     menuno =  ServletActionContext.getRequest().getSession().getAttribute("menuno").toString().trim();
		}catch (Exception e) {
			//throw new Exception("menuno没有取到!，请检查");
			System.out.println("menuno没有取到!，请检查");
		}
		strBuf.append(menuno);
		strBuf.append(stats);
		strBuf.append(fieldName);
		strBuf.append(stats);
		try{
			if(menuno==null || menuno.equals("")){ // 增加如果取不到菜单编号的这种 就不需要控制按钮的权限：
				flag = true; 
			}else{
				String[] rolenos = User.getAllRoleno(ServletActionContext.getRequest()).replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\'", "").split(",");
				String roleNoBuf = "";
				for(String role_no:rolenos){
					roleNoBuf = strBuf.toString()+role_no;
					flag = MBaseCache.getCache().getBeanCacheForButton(roleNoBuf,"role");
					if(flag)break;
				}
			}
			
//		   strBuf.append("SH001");
//		   flag = MBaseCache.getCache().getBeanCacheForButton(strBuf.toString(),"role");
		}catch (Exception e) {
			
		}
		
		if(flag){
			if("query".equals(ServletActionContext.getRequest().getParameter("query"))){
				if("查询".equals(fieldName) || "返回列表".equals(fieldName) ||"返回".equals(fieldName)){
					flag = true;
				}else{
					flag = false;
				}
			}
		}
	}
}
