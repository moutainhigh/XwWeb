package app.creditapp.sys.dao.impl;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.TableDocDAO;
import app.creditapp.sys.entity.TableDoc;
import app.creditapp.sysConfig.entity.TreeNode;
import app.oscache.CachecodeUtil;
import app.oscache.MBaseCache;
import app.util.DBUtils;

public class TableDocDAOImpl   extends BaseIbatisDao implements TableDocDAO {
public void del(String id) throws DAOException {
    try {
      getSqlMapClientTemplate().delete("TableDoc.del", id);
    }catch (Exception e) {
      log.error(e);
      throw new DAOException("删除失败");
    }
  }
  public List<TableDoc> findByPage(TableDoc tabledoc) throws DAOException {
    List tabledoclist = null;
    try {
      tabledoclist =  getSqlMapClientTemplate().queryForList("tabledoclist.findByPage",tabledoc);
    } catch (Exception e) {
      log.error(e);
      throw new DAOException("分页查询失败");
    }
    return tabledoclist;
  }
  public List<TableDoc> getMkTblList() throws DAOException {
	   List tabledoclist = null;
	    try {
	      tabledoclist =  getSqlMapClientTemplate().queryForList("tabledoclist.getMkTblList",null);
	    } catch (Exception e) {
	      log.error(e);
	      throw new DAOException("分页查询失败");
	    }
	    return tabledoclist;
  }
  public TableDoc getById(String id) throws DAOException {
    TableDoc tabledoc = null;
    try {
      tabledoc = (TableDoc)getSqlMapClientTemplate().queryForObject("TableDoc.getById", id);
    }catch (Exception e) {
      log.error(e);
      throw new DAOException("查询失败");
    }
    return tabledoc;
  }
  public int getCount(TableDoc tabledoc) throws DAOException {
    
    int count = 0;
    try {
      count = (Integer)getSqlMapClientTemplate().queryForObject("TableDoc.findPage.count",tabledoc);
    } catch (Exception e) {
      log.error(e);
      throw new DAOException("查询统计失败");
    }
    return count;
  }
  public void insert(TableDoc tabledoc) throws DAOException {
    try {
      getSqlMapClientTemplate().insert("TableDoc.insert", tabledoc);
    }catch (Exception e) {
      log.error(e);
      throw new DAOException("新增失败");
    }
    
  }
	/**
	 * 批量插入模块因素
	 */
	public void insertParmBatch(List<TreeNode> treeNodes) throws DAOException {
		PreparedStatement ps = null;
		Connection conn = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			this.delBeforeInsert(conn);
			sql.append("INSERT INTO TABLE_DOC (DOC_NO,DOC_NAME,UPLEV,LEV,SEQ) VALUES (?,?,?,?,?)");
//			sql.append("INSERT INTO FIN_MK_ITEM (MK_NO,ITEM_NO,UP_ITEM,ITEM_LEV,SEQN) VALUES (?,?,?,?,?)");

			ps = conn.prepareStatement(sql.toString());
			for (int i = 0; i < treeNodes.size(); i++) {
				ps.setString(1, treeNodes.get(i).getId());
				String name = treeNodes.get(i).getName();
				ps.setString(2, name.split("-")[0]);
				ps.setString(3, treeNodes.get(i).getpId()==null ? "0" : treeNodes.get(i).getpId());
				ps.setString(4, treeNodes.get(i).getLev());
				ps.setInt(5, i+1);
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(ps);
			DBUtils.closeConnection(conn);
		}
	}

	public void delBeforeInsert(Connection conn) throws DAOException {
		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM TABLE_DOC ";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(ps);
		}
	}
  public void update(TableDoc tabledoc) throws DAOException {
    try {
      getSqlMapClientTemplate().insert("TableDoc.update", tabledoc);
    } catch (Exception e) {
      log.error(e);
      throw new DAOException("更新失败");
    }    
  }
/* (non-Javadoc)
 * 返回所有菜单组成的字符串
 * @see app.creditapp.dao.TableDocDAO#findAll(java.lang.String)
 */
public List<TableDoc> findAll(String sts) throws DAOException {
	
	List<TableDoc> lst = null;
	try {
		lst = getSqlMapClientTemplate().queryForList("TableDoc.findAll",sts);
	} catch (Exception e) {
		log.error(e);
		throw new DAOException("查询所有菜单失败");
	}
	return lst;
}



public void createdoc(Connection conn) throws Exception {
	String downloadFile = null;
	 String sql0="  select doc_no,doc_name,comments,lev,uplev from table_doc " +
	 		" left join user_tab_comments on doc_name =table_name order by SEQ ";
		 PreparedStatement ps = null;
		ResultSet rsXd = null;
		ps = conn.prepareStatement(sql0);
		rsXd = ps.executeQuery();
		
		Statement psXd = null;
		 ResultSet rs = null;

     XWPFDocument doc = new XWPFDocument();
     
		while (rsXd.next()) {
			String area_no=rsXd.getString(1);
			String area_name = rsXd.getString(2).toUpperCase();
			String comments=rsXd.getString(3);
			String lev=rsXd.getString(4);
			
			if("0".equals(lev)){
				  XWPFParagraph para = doc.createParagraph();
			      XWPFRun run = para.createRun();
			      run.setFontSize(22);
			      run.setFontFamily("新宋体");
			      run.setBold(true); //加粗
			      run.setText(area_name);
			      run.addCarriageReturn();
				//写大标题
			}else if("1".equals(lev)){
				   XWPFParagraph para = doc.createParagraph();
				      XWPFRun run = para.createRun();
				      run.setFontSize(18);
				      run.setFontFamily("新宋体");
				      run.setBold(true); //加粗
				      run.setText(area_name);
				      run.addCarriageReturn();
			}else if("2".equals(lev)){
				   XWPFParagraph para = doc.createParagraph();
				      XWPFRun run = para.createRun();
				      run.setFontSize(16);
				      run.setFontFamily("新宋体");
				      run.setBold(true); //加粗
				      run.setText(area_name);
				      run.addCarriageReturn();
			}else if("3".equals(lev)){
				System.out.println("===="+area_no+"  "+comments+": "+area_name);
				 XWPFParagraph para = doc.createParagraph();
			      XWPFRun run = para.createRun();
			      run.setFontSize(14);
			      run.setFontFamily("新宋体");
			      run.setBold(true); //加粗
			      run.setText(area_name+": "+comments);
			      run.setTextPosition(13);
			      run.addCarriageReturn();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			      
				//写表结构
				   String sql1=
					   "   SELECT '主键' AS NAME_1,CU.CONSTRAINT_NAME AS KEY_1, '列' AS NAME_2, CU.COLUMN_NAME AS KEY_2    "+ 
					   "     FROM USER_CONS_COLUMNS CU, USER_CONSTRAINTS AU                                                "+
					   "    WHERE CU.CONSTRAINT_NAME = AU.CONSTRAINT_NAME AND AU.CONSTRAINT_TYPE = 'P'                     "+
					   "      AND AU.TABLE_NAME = '"+area_name+"' UNION ALL                                                       "+
					   "   SELECT '索引' || ROWNUM AS NAME_1,KEY_1,'列' AS NAME_2,KEY_2                                    "+
					   "   FROM (SELECT  T.INDEX_NAME AS KEY_1,T.COLUMN_NAME AS KEY_2                                      "+
					   "     FROM USER_IND_COLUMNS T, USER_INDEXES I                                                       "+
					   "    WHERE T.INDEX_NAME = I.INDEX_NAME AND T.TABLE_NAME = I.TABLE_NAME                              "+
					   "      AND T.TABLE_NAME = '"+area_name+"' ORDER BY T.INDEX_NAME)                                         ";
				  
				   psXd = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);  
				   rs = psXd.executeQuery(sql1); 
				   rs.last(); //结果集指针知道最后一行数据  
					  int Row1 = rs.getRow()+1;  
					  rs.beforeFirst();//将结果集指针指回到开始位置，这样才能通过while获取rs中的数据 
					  String a[][]; a = new String[Row1][4] ; 
					  a[0][0] = "表名";
					  a[0][1] = area_name;
					  a[0][2] = "中文说明";
					  a[0][3] = comments;
					  int x=1;
					  while(rs.next()){  
						  a[x][0] = rs.getString(1);
						  a[x][1] = rs.getString(2);
						  a[x][2] = rs.getString(3);
						  a[x][3] = rs.getString(4);
						  x++;
						  } 
					  if(rs!=null) rs.close();
					  if(psXd!=null)psXd.close();
					  
				      //创建一个Row1行4列的表格
				      XWPFTable table = doc.createTable(Row1, 4);
				      List<XWPFTableRow> rows = table.getRows();
				      int rowSize = rows.size();
				      XWPFTableRow row;
				      List<XWPFTableCell> cells;
				      int cellSize;
				      XWPFTableCell cell;
				      for (int i=0; i<rowSize; i++) {
				    	  row = rows.get(i);
				    	  row.setHeight(250);
				    	  cells = row.getTableCells();
				          cellSize = cells.size();
				          for (int j=0; j<cellSize; j++) {
				        	  cell = cells.get(j);
				        	  CTTcPr cellPr=cell.getCTTc().addNewTcPr();
				        	  if(j==0||j==2){cell.setColor("F3F3F3");}
				        	  cell.setText(a[i][j]);
				        	  if(j==1&j==3){
				        		  }
				        	  else{
				        	  }					        	
				          }					          
				      }
				      
				      //一个XWPFRun代表具有相同属性的一个区域。					      
				      para = doc.createParagraph();
				      XWPFRun run0 = para.createRun();
				      run0.setText("");
				     

					  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////								      
					   String sql2=
						   "   select t.column_name,                                                                                                          "+ 
						   "          c.comments,                                                                                                            "+
						   "          case when t.data_type='NUMBER' then  t.data_type || '(' || t.data_precision ||','||t.data_scale ||')'                  "+
						   "          else t.data_type || '(' || t.data_length || ')' end as data_type,                                                      "+
						   "          t.default_length,                                                                                                      "+
						   "          t.nullable,                                                                                                            "+
						   "          (select wmsys.wm_concat(substr(opt_code ||'.'||opt_name,800))  from parm_dic where t.column_name=key_name ) as parm        "+
						   "     from user_tab_columns t, user_col_comments c                                                                                "+
						   "    where t.table_name = c.table_name                                                                                            "+
						   "      and t.column_name = c.column_name                                                                                          "+
						   "      and t.table_name = '"+area_name+"'  order by column_id                                                                                        ";
					  
					   psXd = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);  
					   rs = psXd.executeQuery(sql2); 
					   rs.last(); //结果集指针知道最后一行数据  
						  int Row2 = rs.getRow()+1;  
						  rs.beforeFirst();//将结果集指针指回到开始位置，这样才能通过while获取rs中的数据 
						  String b[][]; 
						  b = new String[Row2][6] ; 
						  b[0][0] = "字段名称";
						  b[0][1] = "中文名称";
						  b[0][2] = "数据类型";
						  b[0][3] = "默认值";
						  b[0][4] = "可空";
						  b[0][5] = "备注";
		
						  int y=1;
						  while(rs.next()){  
							  b[y][0] = rs.getString(1);
							  b[y][1] = rs.getString(2);
							  b[y][2] = rs.getString(3);
							  b[y][3] = rs.getString(4);
							  b[y][4] = rs.getString(5);
							  b[y][5] = rs.getString(6); 
							  y++;
							  } 
						  if(rs!=null) rs.close();
						  if(psXd!=null)psXd.close();
				      //创建一个Row2行7列的表格
				      XWPFTable table2 = doc.createTable(Row2, 6);
				       rows = table2.getRows();
				      rowSize = rows.size();

				      for ( int i=0; i<rowSize; i++) {
				    	  row = rows.get(i);
				    	  row.setHeight(250);
				    	  cells = row.getTableCells();
				          cellSize = cells.size();
				          for (int j=0; j<cellSize; j++) {
				        	  cell = cells.get(j);
			        	      //if(i==0){ cell.setColor("F3F3F3"); }
			        	      cell.setText(b[i][j]);
				        	  CTTcPr cellPr=cell.getCTTc().addNewTcPr();
				        	  if(j==0 || j==1 || j==2){
				        	  }else if(j==3){
				        	  }else if(j==4){
				        	  }else if(j==5){
				        	  }
				          }
				      }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			      
			}
			
		}
  /*****************************************************************
   * DROP sequence TABLE_DOC_SEQ;
		create sequence TABLE_DOC_SEQ
		minvalue 1
		maxvalue 9999999999
		start with 1
		increment by 1
		cache 10;
   * UPDATE table_doc SET doc_no = UpLEV ||'.'|| table_doc_seq.nextval WHERE UpLEV = '14';
   ***************/
     //文件不存在时会自动创建
		Map<String,String> sysPathMap =(Map<String,String>)MBaseCache.getCache().getBeanCache(CachecodeUtil.SYS_PATH_STR, CachecodeUtil.SYS_PATH);
        downloadFile = sysPathMap.get("303");
     OutputStream os = new FileOutputStream(downloadFile);
     //写入文件
     doc.write(os);
     os.flush();
     os.close();
  	}
  }