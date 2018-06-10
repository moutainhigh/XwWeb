package accounting.plat.core.init;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import accounting.domain.loan.CorpParm;
import accounting.domain.loan.PrdtBase;
import accounting.domain.sys.SCtrl;
import accounting.plat.PUBConstant;
import accounting.plat.core.AccountingException;
import accounting.plat.dao.JdbcDao;

public class SetSysInfo {

	public static void init(Connection conn) {
		SCtrl sc = querySysInfo(conn);

		PUBConstant.CUR_PRCS_DT = sc.getCurPrcsDt();
		PUBConstant.SYS_STS = sc.getSysSts();
		PUBConstant.LAST_PRCS_DT = sc.getLastPrcsDt();
		PUBConstant.NEW_ACC_IND = sc.getNewAccInd();

		try {

			// 初始化核算参数表Map
			initPubAccountParmMap(conn);
			
			//初始化合作机构配置信息map
			initPubCorpParmMap(conn);

		} catch (AccountingException e) {
			e.printStackTrace();
		}
	}

	private static SCtrl querySysInfo(Connection conn) {
		SCtrl sCtrl = new SCtrl();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select sys_sts,lst_dt,sys_dt,real_time_ind,sys_ind,new_acc_ind from AC_COM_SYS_PARM ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs != null && rs.next()) {
				sCtrl.setCurPrcsDt(rs.getString("sys_dt"));
				// sCtrl.setNextPrcsDt(rs.getString("NEXT_PRCS_DT"));
				sCtrl.setLastPrcsDt(rs.getString("lst_dt"));
				sCtrl.setSysSts(rs.getString("sys_sts"));
				sCtrl.setNewAccInd(rs.getString("new_acc_ind"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sCtrl;
	}



	/**
	 * @auth chengguangyuan 初始化核算参数表Map
	 * @param conn
	 * @throws AccountingException
	 */
	private static void initPubAccountParmMap(Connection conn) throws AccountingException {
		String condition = "1=1";
		List<PrdtBase> prdtBaseList = (ArrayList)JdbcDao.queryList(new PrdtBase(), condition, "prdt_base", conn);
		for (int i = 0; i < prdtBaseList.size(); i++) {
			PrdtBase prdtBase = (PrdtBase) prdtBaseList.get(i);
			String key = prdtBase.getPrdtNo(); // 产品编号
			if (PUBConstant.PUB_ACCOUNT_PRAM.get(key) == null) {
				PUBConstant.PUB_ACCOUNT_PRAM.put(key, prdtBase);
			}
		}
	}
	
	/**
	 * @auth LIUJ 初始化合作机构配置信息Map
	 * @param conn
	 * @throws AccountingException
	 */
	private static void initPubCorpParmMap(Connection conn) throws AccountingException {
		String condition = "1=1";
		List<CorpParm> corpParmList = (ArrayList)JdbcDao.queryList(new CorpParm(), condition, "corp_parm", conn);
		for (int i = 0; i < corpParmList.size(); i++) {
			CorpParm corpParm = (CorpParm) corpParmList.get(i);
			String key = corpParm.getBrNo(); // 产品编号
			if (PUBConstant.PUB_CORP_PRAM.get(key) == null) {
				PUBConstant.PUB_CORP_PRAM.put(key, corpParm);
			}
		}
	}

}
