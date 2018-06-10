package  app.creditapp.sys.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.sys.bo.StudentBo;
import app.creditapp.sys.dao.StudentDao;
import app.creditapp.sys.entity.Student;
/**
* Title: SysUserBoImplImpl.java
* Description:
**/
public class StudentBoImpl extends BaseService implements StudentBo {

	private StudentDao studentDao;


	

	public StudentDao getStudentDao() {
		return studentDao;
	}




	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}




	public void insert(Student student) throws ServiceException {
		try {
			studentDao.insert(student);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
}