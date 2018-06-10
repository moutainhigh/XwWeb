package app.creditapp.batch.bo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.batch.bo.BatchExeBo;
import app.creditapp.batch.console.BatchRunner;
import app.creditapp.batch.dao.BatchExeDao;
import app.creditapp.batch.entity.BatchExe;
import app.creditapp.batch.util.DBUtil;
import app.util.DateUtil;
import app.util.toolkit.Ipage;

/**
 * Title: BatchExeBoImplImpl.java Description:
 * 
 **/
public class BatchExeBoImpl extends BaseService implements BatchExeBo {

	private BatchExeDao batchExeDao;

	public void del(BatchExe batchExe) throws ServiceException {
		try {
			batchExeDao.del(batchExe);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, BatchExe batchExe) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) batchExeDao.getCount(batchExe) });// 初始化分页类
			batchExe.setStartNumAndEndNum(ipg);
			ipg.setResult(batchExeDao.findByPage(batchExe));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public BatchExe getById(BatchExe batchExe) throws ServiceException {
		try {
			batchExe = batchExeDao.getById(batchExe);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return batchExe;
	}

	public void insert(BatchExe batchExe) throws ServiceException {
		try {
			batchExeDao.insert(batchExe);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(BatchExe batchExe) throws ServiceException {
		try {
			batchExeDao.update(batchExe);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public int getCountByBtchDate() throws ServiceException {
		int count = 0;
		try {
			count = batchExeDao.getCountByBtchDate();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}

	public int getFailBatchNodeCount(String nodeSts) throws ServiceException {
		int count = 0;
		try {
			count = batchExeDao.getFailBatchNodeCount(nodeSts);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}

	public BatchExeDao getBatchExeDao() {
		return batchExeDao;
	}

	public void setBatchExeDao(BatchExeDao batchExeDao) {
		this.batchExeDao = batchExeDao;
	}

	@Override
	public List<BatchExe> getAllBatchExeByDate(String batchDate) {
		return batchExeDao.getAllBatchExeByDate(batchDate);
	}

	@Override
	public List<BatchExe> getBatchExeList(String sysdate) {
		return batchExeDao.getBatchExeList(sysdate);
	}

	@Override
	public int runBatch(String date) throws ServiceException {
		int result = 0;
		BatchExe batchExe = new BatchExe();
		batchExe.setBatch_date(date);
		List<BatchExe> list = batchExeDao.getListByDate(batchExe);
		List<BatchExe> runList = new ArrayList<BatchExe>();
		for (BatchExe exe : list) {
			//未执行 或失败的
			if("1".equals(exe.getNode_status()) || "3".equals(exe.getNode_status())){
				//Arrive_step 为父节点的执行状态
				if(exe.getArrive_step() == 4){//如果父节点成功
					runList.add(exe);
				}
			}
		}
		BatchRunner batchRunner = new BatchRunner(date);
		for (BatchExe exe2 : runList) {
			batchRunner.startBatch(exe2.getNode_no());
			result++;
		}
		return result;
	}

	@Override
	public void runZfBatch() throws ServiceException {
		
		try {
//			ZFMain.acBatch();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public int checkBatch(String date) throws ServiceException {
		int count = 0;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			// log("初始化批量执行表");
			conn = DBUtil.getConnection();
			String sql = "";
			sql = "SELECT count(1) FROM BATCH_EXE WHERE BATCH_DATE=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, date);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			
			if (count == 0) {
				DBUtil.close(ps);
				sql = "INSERT INTO BATCH_EXE SELECT DISTINCT NODE_NO,NODE_NAME,'1'," + date + ",0 FROM BATCH_STEP";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate(sql);
				conn.commit();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return count;
	}
	
	public void runBatchExe(){
//		if(date == null || date.isEmpty())
		String date = DateUtil.getSysGlobal().getBat_date();
		checkBatch(date);
		int is = runBatch(date);
		if(is>=1){
			System.out.println("批量开始已执行");
		}else{
			System.out.println("批量已全部执行成功");
		}
	}
}