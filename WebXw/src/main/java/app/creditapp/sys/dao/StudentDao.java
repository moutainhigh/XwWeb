package  app.creditapp.sys.dao;

import app.base.DAOException;
import app.creditapp.sys.entity.Student;
/**
* Title: SysUserDao.java
* Description:
**/
public interface StudentDao {

	public void insert(Student student) throws DAOException;

	
}