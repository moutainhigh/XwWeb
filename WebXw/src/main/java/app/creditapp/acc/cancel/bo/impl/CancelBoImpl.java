package app.creditapp.acc.cancel.bo.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import accounting.domain.sys.AfferentDomain;
import accounting.plat.TransCode;
import accounting.plat.core.AccountingException;
import accounting.plat.core.Operation;
import accounting.plat.core.OperationFactory;
import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.acc.cancel.bo.CancelBo;

public class CancelBoImpl extends BaseService implements CancelBo {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private Connection getConnection() {

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return conn;
	}


	/**
	 * ³åÕý½»Ò×
	 */
	public String reverseTrace(AfferentDomain afferentDomain)
			throws ServiceException {
		Connection conn = this.getConnection();
		try {
			Operation op = (Operation) OperationFactory.getFactoryInstance()
					.getOperation(TransCode.LNRV, conn);
			op.execute(afferentDomain);
		} catch (AccountingException e) {
			if (e.getLevel() == 1) {
				return e.getMessage();
			} else {
				e.printStackTrace();
				throw new ServiceException(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ServiceException(e);
			}
		}
		return null;
	}

}
