package accounting.plat.core;

import java.net.URLDecoder;
import java.sql.Connection;
import java.util.HashMap;

import accounting.biz.pub.ParmBiz;
import accounting.plat.CreatePrimaryKey;
import accounting.plat.PUBConstant;

public class OperationFactory {

	/** 业务组件工厂实例 */
	private static OperationFactory instance = new OperationFactory();
	/** 业务组件例表 其KEY对应组件ID，其值为对应组件的配置信息（格式仍为HashMap） */
	private static HashMap interfaceTable = null;

	public static final String CONFIGKEY_MODULEID = "moduleid";
	public static final String CONFIGKEY_CLASSNAME = "classname";
	public static final String CONFIGKEY_DESCRIBE = "describe";
	
	
	/**
	 * <p>
	 * 取得业务组件工厂实例
	 * </p>
	 * 
	 * @return 业务组件工厂实例
	 */
	public static OperationFactory getFactoryInstance() {
		if (instance != null) {
			return instance;
		} else {
			return new OperationFactory();
		}
	}

	/**
	 * <p>
	 * 业务组件工厂初始化
	 * </p>
	 * <p>
	 * 从配置业务组件配置文件(componentcfg.xml)加载所有组件的配置信息
	 * </p>
	 * 
	 * @throws Exception
	 */
	public static void init() throws Exception {

		try {
			XMLFileUtil xmlFileUtil = new XMLFileUtil();
//			String CONFIG_FILM_DIR = PUBConstant.CONFIG_FILM_DIR;
//			String CONFIG_FILM_DIR = Thread.currentThread().getContextClassLoader().getResource(PUBConstant.CONFIG_FILM_DIR).getPath();
//			CONFIG_FILM_DIR = URLDecoder.decode(CONFIG_FILM_DIR, "UTF-8");
			interfaceTable = (HashMap) xmlFileUtil .readOperationFromXMLFile(PUBConstant.CONFIG_FILM_DIR);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("初始化组件配置失败" + ex.getMessage());
		}
	}
   
	/**
	 * <p>
	 * 由业务组件ID获得业务组件实例
	 * </p>
	 * <p>
	 * 依据配置业务组件的配置实例化组件，并将配置信息设置到实例出的组件中，该方法只允许在op中使用
	 * </p>
	 * 
	 * @param comId
	 *            业务组件ID
	 * @param context
	 *            EMP 结构
	 * @return 业务组件实例
	 */
	public Operation getOperation(String interfaceId,Connection connection) throws Exception {

		Operation op = null;
		op=this.getOperation(interfaceId,true,connection);
		return op;

	}
	/**
	 * <p>
	 * 由业务组件ID获得业务组件实例
	 * </p>
	 * <p>
	 * 依据配置业务组件的配置实例化组件，并将配置信息设置到实例出的组件中，该方法只允许在op中使用
	 * </p>
	 * 
	 * @param comId
	 *            业务组件ID
	 * @param context
	 *            EMP 结构
	 * @return 业务组件实例
	 */
	public Operation getOperation(String interfaceId,boolean setConnInd,Connection connection) throws Exception {

		if (interfaceTable == null || interfaceTable.size() <= 0) {
			throw new Exception("业务组件接口列表尚未初始化，请先调用初始化方法后再使用getComponentInterface方法");
		}

		if (interfaceId == null || interfaceId.trim().equals("")) {
			throw new Exception("业务组件接口编号为空， 无法实例化业务组件接口");
		}
		Operation instance = null;
		try {
			HashMap componentCfg = (HashMap) interfaceTable.get(interfaceId);
			if (componentCfg != null && componentCfg.size() > 0) {

				String st_classname = (String) componentCfg
						.get(CONFIGKEY_CLASSNAME);
				String st_describe = (String) componentCfg
						.get(CONFIGKEY_DESCRIBE);

				if (st_classname != null && !st_classname.trim().equals("")) {
					/** 实例化组件接口 */
					instance = (Operation) Class.forName(st_classname)
							.newInstance();
					/** 设置组件接口基本信息 */
					instance.setId(interfaceId);
					instance.setDescribe(st_describe);
				    instance.setOldAutoCommit(connection.getAutoCommit());
					if(setConnInd==true){
						instance.setConnection(connection);
					}
					// 获取交易流水号
					instance.setTraceNo(ParmBiz.getTraceNo(connection));
					
					System.err.println("核算业务组件返回信息-->>>st_describe: " + st_describe +",交易流水号:["+instance.getTraceNo()+"]");

				} else {
					throw new Exception("业务组件接口[" + interfaceId + "]没有配置实现类（"
									+ CONFIGKEY_CLASSNAME + "）， 无法实例化业务组件接口");
				}
			} else {
				throw new Exception("业务组件接口[" + interfaceId + "]尚未配置，无法实例化");
			}
		} catch (InstantiationException e) {
		
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (IllegalAccessException e) {
		
			e.printStackTrace();
			throw new Exception("初始化业务组件接口[" + interfaceId + "]失败，无权访问类" + e.getMessage());
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
			throw new Exception("初始化业务组件接口[" + interfaceId + "]失败，对应的实现类不存在"
							+ e.getMessage());
		}
		return instance;

	}

}
