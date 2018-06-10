package app.creditapp.entity;

import java.io.Serializable;

/**
 * 系统状态日期类
 * @see 
 * 修改记录:
 */
public class SysGlobal implements Serializable {
	
		
		private static final long serialVersionUID = -521474763013106572L;
		private String glo_no;               //系统编号
		private String glo_name;			 //系统名称(小微金融服务平台)
		private String glo_version;			 //系统版本号(V1.0)
		private String sys_date;             //营业日期
		private String glo_lic;              //系统授权
		private String lst_date;             //昨日日期
		private String bat_date; 			 //批量日期
		private String sys_sts;              //系统状态[1运行中02批量中03运维中04暂停服务05停止]
		
		public void setGlo_no(String glo_no){ this.glo_no=glo_no;}
		public void setGlo_name(String glo_name){ this.glo_name=glo_name;}
		public void setGlo_version(String glo_version){ this.glo_version=glo_version;}
		public void setSys_date(String sys_date){ this.sys_date=sys_date;}
		public void setGlo_lic(String glo_lic){ this.glo_lic=glo_lic;}
		public void setLst_date(String lst_date){ this.lst_date=lst_date;}
		public void setBat_date(String bat_date){ this.bat_date=bat_date;}
		public void setSys_sts(String sys_sts){ this.sys_sts=sys_sts;}
		
		public String getGlo_no(){return glo_no;}
		public String getGlo_name(){return glo_name;}
		public String getGlo_version(){return glo_version;}
		public String getSys_date(){return sys_date;}
		public String getGlo_lic(){return glo_lic;}
		public String getLst_date(){return lst_date;}
		public String getBat_date(){return bat_date;}
		public String getSys_sts(){return sys_sts;}
		
		public static long getSerialVersionUID() {
			return serialVersionUID;
		}
		
}
