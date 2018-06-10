package app.creditapp.sys.entity;

import java.util.ArrayList;
import java.util.List;

import app.base.SourceTemplate;
import app.creditapp.sys.bo.ParmAreaBo;

public class ParmAreaTree {

	public ParmArea getTreeTop(){
		ParmArea parmArea = new ParmArea();
		ParmAreaBo parmAreaBo = (ParmAreaBo) SourceTemplate.getSpringContextInstance().getBean("parmAreaBo");
		parmArea = parmAreaBo.getTreeTop();
		return parmArea;
	}
	
	public List getChildren(String deptid){
		List<ParmArea> parmAreaList = new ArrayList<ParmArea>();
		ParmAreaBo tblOrgDepartmentsBo = (ParmAreaBo) SourceTemplate.getSpringContextInstance().getBean("parmAreaBo");
		parmAreaList = tblOrgDepartmentsBo.getChildren(deptid);
		return parmAreaList;
		
	}
}
