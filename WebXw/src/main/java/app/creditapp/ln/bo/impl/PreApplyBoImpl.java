package  app.creditapp.ln.bo.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsIn2001_1;
import app.creditapp.inf.entity.WsIn2002;
import app.creditapp.inf.entity.WsIn2004;
import app.creditapp.inf.entity.WsIn2004_1;
import app.creditapp.inf.entity.WsIn2006;
import app.creditapp.inf.entity.WsOut2002_1;
import app.creditapp.inf.entity.WsOut2006_1;
import app.creditapp.inf.entity.WsOut2006_1_1;
import app.creditapp.ln.bo.PreApplyBo;
import app.creditapp.ln.dao.PreApplyDao;
import app.creditapp.ln.dao.PreGageDao;
import app.creditapp.ln.entity.PreApply;
import app.creditapp.ln.entity.PreGage;
import app.util.DBUtils;
import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.toolkit.Ipage;
/**
* Title: PreApplyBoImplImpl.java
* Description:
**/
public class PreApplyBoImpl extends BaseService implements PreApplyBo {

	private PreApplyDao preApplyDao;
	private PreGageDao preGageDao;
	private FiterEngineInterface filterEngineInterface;

	public void del(PreApply preApply) throws ServiceException {
		try {
			preApplyDao.del(preApply);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, PreApply preApply)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) preApplyDao
					.getCount(preApply) });// 初始化分页类
			preApply.setStartNumAndEndNum (ipg);
			ipg.setResult(preApplyDao.findByPage(preApply));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public PreApply getById(PreApply preApply) throws ServiceException {
		try {
			preApply = preApplyDao.getById(preApply);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return preApply;
	}

	public void insert(PreApply preApply) throws ServiceException {
		try {
			/**
			 * 新增预进件申请信息的申请ID是序列
			 */
			preApply.setAppId(preApplyDao.getKey());
			preApplyDao.insert(preApply);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(PreApply preApply) throws ServiceException {
		try {
			preApplyDao.update(preApply);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public void updateZDSP(PreApply preApply) throws ServiceException {
		try {
			preApplyDao.updateZDSP(preApply);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	//ws2006 接口 wsout2006_1 list分页显示
	public Ipage findByPageforws2006(Ipage ipg, PreApply preApply)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) preApplyDao.getCountforws2006(preApply) });// 初始化分页类
			preApply.setStartNumAndEndNum(ipg);
			List<WsOut2006_1> wsOut2006_1list = preApplyDao.findByPageforws2006(preApply);
			List<WsOut2006_1_1> wsOut2006_1_1list = new ArrayList<WsOut2006_1_1>();
			PreGage preGage = new PreGage();
			for(int i = 0;i<wsOut2006_1list.size();i++){
				//根据app_id来获取该app_id所有的押品，以列表wsOut2006_1_1list的方式返回
				preGage.setAppId(wsOut2006_1list.get(i).getAppId());
				wsOut2006_1_1list = preGageDao.getws2006_1_1list(preGage);
				//为wsOut2006_1list列表中的wsOut2006_1_1list列表添加值
				wsOut2006_1list.get(i).setListGage(wsOut2006_1_1list);
			}
			
			
			//获取分页后的列表，完成分页操作
			ipg.setResult(wsOut2006_1list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	//接口ws2006 返回符合查询条件的数据数量count
	public int getcountforws2006 (PreApply preApply)throws ServiceException{
		int count = 0;
		count = preApplyDao.getCountforws2006(preApply);
		return count;
	}
	
	public void insertBatch(List<WsIn2004_1> wsIn2004_1) throws ServiceException {
		try {
			preApplyDao.insertBatch(wsIn2004_1);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	//接口ws2001列表插入数据库方法
	public void insertBatchfor2005(List<WsIn2001_1> wsIn2001_1) throws ServiceException {
		try {
			preApplyDao.insertBatchfor2001(wsIn2001_1);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public void insertforws(WsIn2004 wsIn2004) throws ServiceException {
		try {
			/**
			 * 新增预进件申请信息的申请ID是序列
			 */
			preApplyDao.insertforws(wsIn2004);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	//校验ws2002接口输入值 wsin2002
	public  ResponseParm validateforws2002(WsIn2002 wsIn2002) throws ServiceException{
		ResponseParm responseParm = new ResponseParm();
		//默认返回代码为0000，为通过状态
		responseParm.setRespCode("0000");
		responseParm.setRespDesc("输入数据校验通过！");
		PreApply wsIn2002forsearch = new PreApply();
		ValidateLog _vlwsin2002;
		try {
			_vlwsin2002 = filterEngineInterface.createValidateLog("wsIn2002Id",wsIn2002, true);
			if(!_vlwsin2002.isSuccess()){
				responseParm.setRespCode("9004");
				responseParm.setRespDesc(_vlwsin2002.getErrorMessage());
			}else{
				String batchNo = wsIn2002.getBatchNo();
				String prePactNo = wsIn2002.getPrePactNo();
				wsIn2002forsearch.setBatchNo(batchNo);
				int count = preApplyDao.getCount(wsIn2002forsearch);
				//判断是否存在该批次号
				if(count == 0){
					responseParm.setRespCode("2990");
					responseParm.setRespDesc("批次号为： "+batchNo+"  的记录不存在！");
				}else{
					wsIn2002forsearch.setPrePactNo(prePactNo);
					int countforPactNo = preApplyDao.getCount(wsIn2002forsearch);
					if(countforPactNo == 0){
						responseParm.setRespCode("1001");
						responseParm.setRespDesc("预审批ID为："+prePactNo+"  的记录不存在！");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  responseParm;
	}
	//校验ws2006接口输入值 wsin2006
	public  ResponseParm validateforws2006(WsIn2006 wsIn2006) throws ServiceException{
		ResponseParm responseParm = new ResponseParm();
		//默认返回代码为0000，为通过状态
		responseParm.setRespCode("0000");
		responseParm.setRespDesc("输入数据校验通过！");
		ValidateLog _vlwsin2002;
		try {
			_vlwsin2002 = filterEngineInterface.createValidateLog("wsIn2006Id",wsIn2006, true);
			if(!_vlwsin2002.isSuccess()){
				responseParm.setRespCode("9004");
				responseParm.setRespDesc(_vlwsin2002.getErrorMessage());
			}else{
				PreApply wsIn2006forsearch = new PreApply();
				String batchNo = wsIn2006.getBatNo();
				String prepactNo = wsIn2006.getPrePactNo();
				wsIn2006forsearch.setBatchNo(batchNo);
				int count = preApplyDao.getCount(wsIn2006forsearch);
				//判断是否存在该批次号
				if(count == 0){
					responseParm.setRespCode("2990");
					responseParm.setRespDesc("批次号为： "+batchNo+"  的记录不存在！");
				}else{
					wsIn2006forsearch.setPrePactNo(prepactNo);
					int countforPactNo = preApplyDao.getCount(wsIn2006forsearch);
					if(countforPactNo == 0){
						responseParm.setRespCode("1001");
						responseParm.setRespDesc("预审批ID为：  "+ prepactNo +"  的记录不存在！");
					}
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  responseParm;
		
	}
	
	/**
	 * ws2002接口查询结果返回方法
	 */
	public List<WsOut2002_1> findByBatchNo(PreApply preApply) throws ServiceException{
		List<WsOut2002_1> wsOut2002_1list = new ArrayList<WsOut2002_1>();
		wsOut2002_1list = preApplyDao.findByBatchNo(preApply);
		return wsOut2002_1list;
	}
	/*public List<WsOut2004> findByBatchNo(WsIn2004 wsIn2004) throws ServiceException{
		List<PreApply> preApplyList = new ArrayList<PreApply>();
		List<WsOut2004> wsOut2004List = new ArrayList<WsOut2004>();
		try {
			PreApply preApply = new PreApply();
			preApply.setBatchNo(wsIn2004.getBatchNo());
			preApply.setBrNo(wsIn2004.getBrNo());
			preApplyList = preApplyDao.findByBatchNo(preApply);
			//获取列表的大小
			int Num = preApplyList.size();
			for(int i = 0;i < Num;i++){
				//获取列表的实体类值
				preApply = preApplyList.get(i);
				//为wsPubBean赋值
				WsOut2004 wsOut2004 = new WsOut2004();
				wsOut2004.setAppSts(preApply.getAppSts());
				wsOut2004.setBrNo(preApply.getBrNo());
				wsOut2004.setBatchNo(preApply.getBatchNo());
				wsOut2004.setPactNo(preApply.getPactNo());
				wsOut2004.setBatchNo(preApply.getBatchNo());
				wsOut2004.setDealRes(preApply.getChkRes());
				wsOut2004.setDealDesc(preApply.getChkDesc());
				//生成wsPubBean实体类的 list
				wsOut2004List.add(wsOut2004);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
		return wsOut2004List;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 22, 2016
	 * @方法说明：预进件内部规则筛查处理
	 * @返回参数：返回有错误信息列表
	 */
	public void pre_screen(String batch_no) throws ServiceException {
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet cursor = null;
		try {
			  conn = DBUtils.getConn();
			  //conn = DBUtil.getConnection();
		      proc = conn.prepareCall("{ call PKG_PRE_APPLY.PROC_PRE_SCREEN(?,?) }"); //设置存储过程
		      proc.setString(1, batch_no);//设置第一个参数输入参数
		      proc.registerOutParameter(2, Types.VARCHAR);//第二个参数输出参数,是VARCHAR类型的
		     // proc.registerOutParameter(2, OracleTypes.CURSOR);//第二个参数输出参数,是游标类型的
		      proc.execute();// 执行
		     // cursor = proc.getString(2);//获得输出参数 
		      
		      /** 2016年6月28日与维信谈完，改为异步，不需要实时返回筛查结果
		       * 
		       * CREATE OR REPLACE PACKAGE BODY PKG_PRE_APPLY IS
				  ------------------------------------------------------------------
				  -- 过程描述：预审批黑名单和内部规则筛查处理
				  -- 处理周期：每次预审批进件处理
				  -- 编写人员：DHCC-SONG
					-- 日期：2016年6月21日
				  ------------------------------------------------------------------
				  PROCEDURE PROC_PRE_SCREEN(P_BATNO VARCHAR2, P_RESULT OUT sys_refcursor) IS
				    --APPLY_REC LN_APPLY_REG%ROWTYPE;
				    --V_CIFNO   VARCHAR2(12);
				    --V_EXISTS  BOOLEAN := TRUE;
				    --V_APPSTS  VARCHAR2(2);  
				  BEGIN
		      cursor = ((OracleCallableStatement)proc).getCursor(2);
		      ErrorMessage em  = null;
		      while(cursor.next()){
		    	  em = new ErrorMessage();
		    	  em.setBatchNo(cursor.getString(1));
		    	  em.setPactNo(cursor.getString(2));
		    	  em.setMessage(cursor.getString(3));
		    	  emList.add(em);
		    	  System.out.println("预进件业务合同号："+cursor.getString(2) + 
		    	  "筛查未通过！原因："+cursor.getString(3));
		      }**/
		      //System.out.println("=testPrint=is="+testPrint);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		} finally {
			DBUtils.closeConnection(conn);
			DBUtils.closeStatement(proc);
			DBUtils.closeResultset(cursor);
		}
	}
	
	 public static void main(String[] args) {
		 new PreApplyBoImpl().pre_screen("1111111");
	 }

	public PreApplyDao getPreApplyDao() {
		return preApplyDao;
	}

	public void setPreApplyDao(PreApplyDao preApplyDao) {
		this.preApplyDao = preApplyDao;
	}

	@Override
	public List<PreApply> getListByPreApply(PreApply preApply)
			throws ServiceException {
		List<PreApply> list = null;
		try {
			
			list = preApplyDao.getListByPreApply(preApply);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	public List<PreApply> findListByBatNo(PreApply preApply) throws ServiceException{
		List<PreApply> list = null;
		try {
			
			list = preApplyDao.findListByBatNo(preApply);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	//根据合同号更新AppId
	public void updateToAppId(PreApply preApply) throws ServiceException{
		try {
			preApplyDao.updateToAppId(preApply);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public PreGageDao getPreGageDao() {
		return preGageDao;
	}

	public void setPreGageDao(PreGageDao preGageDao) {
		this.preGageDao = preGageDao;
	}

	public FiterEngineInterface getFilterEngineInterface() {
		return filterEngineInterface;
	}

	public void setFilterEngineInterface(FiterEngineInterface filterEngineInterface) {
		this.filterEngineInterface = filterEngineInterface;
	}
	
}