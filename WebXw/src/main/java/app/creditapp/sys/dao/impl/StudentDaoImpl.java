package app.creditapp.sys.dao.impl;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.StudentDao;
import app.creditapp.sys.entity.Student;

public class StudentDaoImpl extends BaseIbatisDao implements StudentDao{

	public void insert(Student student) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("Student.insert",
					student);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}
}
