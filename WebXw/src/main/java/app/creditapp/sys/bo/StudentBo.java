package app.creditapp.sys.bo;

import app.base.DAOException;
import app.creditapp.sys.entity.Student;

public interface StudentBo {
	public void insert(Student student) throws DAOException;

}
