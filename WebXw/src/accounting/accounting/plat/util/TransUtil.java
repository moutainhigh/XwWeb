package accounting.plat.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import accounting.plat.core.AccountingException;

public class TransUtil {

	/**
	 * 转换小数点保留模式
	 * 
	 * @param input
	 * @return
	 */
	public static RoundingMode transRounding(String intType) {
		RoundingMode mode = RoundingMode.HALF_UP ;
		switch(intType.charAt(0)){
		case 'H' :
			mode = RoundingMode.UP ;
			break ;
		case 'L' :
			mode = RoundingMode.DOWN ;
			break ;
		case 'R' :
			mode = RoundingMode.HALF_UP ;
			break ;
		}
		
		return mode ;
	}



	/**
	 *将对象src中的值赋到对象dest对应的属性
	 * 
	 * @param src		数据来源对象
	 * @param dest		数据赋值对象
	 * @throws AccountingException 
	 */
	@SuppressWarnings("unchecked")
	public static void copyProperties(Object src, Object dest) throws AccountingException {
		String key;
		try {
			Map<String, Object> map = BeanUtils.describe(src);
			for (Iterator it = map.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				if (!("id".equals(key) || "class".equals(key))) {
					BeanUtils.copyProperty(dest, key, map.get(key));
				}
			}
		} catch (IllegalAccessException e1) {
			throw new AccountingException("无效访问方法" + e1);
		} catch (InvocationTargetException e1) {
			throw new AccountingException("不能调用目标对象" + e1);
		} catch (NoSuchMethodException e1) {
			throw new AccountingException("无此方法" + e1);
		}
	}	
	
	public static Object kcolTOdomain(Object domain, ResultSet kCol)throws AccountingException {

		if ( null == kCol) {
			return null;
		}

		if (domain == null) {
			return null;
		}
		Class[] returnType = new Class[1];
		// 得到所有成员变量
		Field[] fields = domain.getClass().getDeclaredFields();
		/*
		 * 把domain中所有的值取出来，存入kCol
		 */
		for (int i = 0; i < fields.length; i++) {
			// 去成员变量名
			String str = fields[i].getName();
			// 在大写字母前加_ 首字母除外
			String formatName = StringUtil.AddUnderlineByUppercase(str);

			try {

				String sq1 = "get" + str.substring(0, 1).toUpperCase()
				+ str.substring(1, str.length());

				Method md1 = domain.getClass().getDeclaredMethod(sq1);

				if (md1 != null) {
					returnType[0] = md1.getReturnType();
					if (returnType[0].getName().endsWith("String")) {
						String sq = "set" + str.substring(0, 1).toUpperCase()
						+ str.substring(1, str.length());
						Method md = domain.getClass().getDeclaredMethod(sq,
								String.class);
						String kValue = (String) kCol.getString(formatName);
						System.out
						.println(kValue);
						md.invoke(domain, kValue);
					} else if (returnType[0].getName().endsWith("int")) {
						String sq = "set" + str.substring(0, 1).toUpperCase()
						+ str.substring(1, str.length());
						Method md = domain.getClass().getDeclaredMethod(sq,
								int.class);

						md.invoke(domain, kCol.getInt(formatName));

					} else if (returnType[0].getName().endsWith("double")) {
						String sq = "set" + str.substring(0, 1).toUpperCase()
						+ str.substring(1, str.length());
						Method md = domain.getClass().getDeclaredMethod(sq,
								double.class);

						md.invoke(domain, kCol.getDouble(formatName));
					} else if (returnType[0].getName().endsWith("BigDecimal")) {
						String sq = "set" + str.substring(0, 1).toUpperCase()
						+ str.substring(1, str.length());
						Method md = domain.getClass().getDeclaredMethod(sq,
								BigDecimal.class);
						BigDecimal bv = kCol.getBigDecimal(formatName);
						md.invoke(domain, bv);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return domain;
	}
}
