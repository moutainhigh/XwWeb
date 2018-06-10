package accounting.plat.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import accounting.plat.core.AccountingException;

public class StringUtil {

	/**
	 * 本方法的主要功能是在一个字符串的每个大写字母前面加一个_ ，字符串的首字母除外 然后把整个串以小写输出 例如：cusId-->cus_id
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 处理后的字符串
	 */
	public static String AddUnderlineByUppercase(String str) {
		String result = "";
		StringBuffer stringBuffer = new StringBuffer();
		if (str == null || str.length() == 0) {
			return str;
		}
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (Character.isUpperCase(c)) {
				stringBuffer.append("_").append(Character.toLowerCase(c));
			} else {
				stringBuffer.append(c);
			}
		}
		result = stringBuffer.toString();
		if (result.startsWith("_")) {
			result = result.substring(1);
		}
		return result;
	}

	public static String AddUnderlineByUppercase2(String str) {

		// 创建一个数组列表，用来存储大写字母的位置

		// ArrayList<Integer> al = new ArrayList<Integer>();
		// int count = 0;
		// for(int j=1; j<str.length(); j++){
		// if(Character.isUpperCase(str.charAt(j))){
		// al.add(j);
		// count ++;
		// }
		ArrayList<Integer> al = new ArrayList<Integer>();
		int count = 0;
		for (int j = 0; j < str.length(); j++) {
			if (Character.isUpperCase(str.charAt(j))) {
				al.add(j);
				count++;
			}
			// if(NewStringUtils.isNumeric((Character.toString(str.charAt(j))))){
			// al.add(j);
			// count ++;
			// }
		}
		if (count == 0) {
			return str;
		}
		StringBuffer tmpStr = new StringBuffer("");

		// 根据大小字母的位置在其前面加上 _

		for (int x = 0; x < al.size(); x++) {
			if ((x == 0) && (x == al.size() - 1)) {
				if (str.length() == 1) {
					tmpStr.append(str);
				} else {
					tmpStr.append(str.substring(0, al.get(x))).append("_").append(
							str.substring(al.get(x), str.length()));
				}

			} else if ((x == 0) && (x != al.size() - 1)) {
				tmpStr.append(str.substring(0, al.get(x))).append("_");
			} else if ((x != 0) && (x == al.size() - 1)) {
				if (al.get(x) == str.length()) {
					tmpStr.append(str.substring(al.get(x - 1), al.get(x)));
				} else {
					tmpStr.append(str.substring(al.get(x - 1), al.get(x))).append("_").append(
							str.substring(al.get(x), str.length()));
				}
			} else {
				tmpStr.append(str.substring(al.get(x - 1), al.get(x))).append("_");
			}
		}
		// 转换成小写返回
		String rv = tmpStr.toString().toLowerCase();
		if (rv.substring(0, 1).equals("_")) {
			rv = rv.substring(1);
		}
		return rv;
	}

	/**
	 * 改变字符串中的某一位的字符值
	 * 
	 * @param source
	 *            需要改变的字符串
	 * @param position
	 *            位置
	 * @param changeChar
	 *            改变后的字符
	 * @return 输入 ("abc",2,'e') 输出"abe"
	 */
	public static String changeCharInString(String source, int position, char changeChar) throws Exception {
		if (source == null) {
			return null;
		}
		char[] sourceList = source.toCharArray();

		if (source.length() <= position) {
			throw new Exception("输入参数不合法");
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < sourceList.length; i++) {
			if (i == position) {
				sb.append(changeChar);
			} else {
				sb.append(sourceList[i]);
			}
		}

		return sb.toString();
	}

	/**
	 * 加密 *
	 * 
	 * @param source
	 *            byte[]
	 * @return byte[]
	 */
	public static byte[] addKey(byte[] source) {
		byte[] reByte = new byte[source.length];
		String key = "dlvanda";
		byte[] keyByte = key.getBytes();
		for (int i = 0; i < source.length; i++) {
			int j = i % keyByte.length;
			reByte[i] = (byte) (source[i] + keyByte[j]);
		}
		return reByte;
	}

	/**
	 * 解密 *
	 * 
	 * @param source
	 *            byte[]
	 * @return byte[]
	 */
	public static byte[] desKey(byte[] source) {
		byte[] reByte = new byte[source.length];
		String key = "dlvanda";
		byte[] keyByte = key.getBytes();
		for (int i = 0; i < source.length; i++) {
			int j = i % keyByte.length;
			reByte[i] = (byte) (source[i] - keyByte[j]);
		}
		return reByte;
	}

	/**
	 * 加密 *
	 * 
	 * @param source
	 *            String
	 * @return String
	 */
	// public String addKey(String source) {
	// Descrypt des = new Descrypt();
	// return des.descrypt(source,"dlvanda");
	// }

	/**
	 * 返回两位数据字串 *
	 * 
	 * @param sz
	 *            int
	 * @return String
	 */
	public static String bZero(int sz) {
		return (sz < 10 ? ("0" + String.valueOf(sz)) : String.valueOf(sz));
	}

	/**
	 * 字符串("|")到数组 *
	 * 
	 * @param string
	 *            String
	 * @return String[]
	 */
	public static String[] strToArray(String string) {
		String tmpString;
		String[] returnArray = null;
		if (string == null || string.equalsIgnoreCase("")) {
			returnArray = new String[1];
			returnArray[0] = "";
		} else {
			int j = 0, jsInt = 0;
			for (int i = 0; i < string.length(); i++) {
				if (string.charAt(i) == '|') {
					j++;
				}
			}
			if (j == 0) {
				returnArray = new String[1];
				returnArray[0] = string;
			} else {
				returnArray = new String[j];
				j = 0;
				for (int i = 0; i < string.length(); i++) {
					if (string.charAt(i) == '|') {
						if (j == i) {
							tmpString = " ";
						} else {
							tmpString = string.substring(j, i).trim();
						}
						j = i + 1;
						returnArray[jsInt] = tmpString;
						jsInt += 1;
					}
				}
			}
		}
		return returnArray;
	}

	/**
	 * 字符串到数组 *
	 * 
	 * @param string
	 *            String
	 * @param separator
	 *            char
	 * @return String[]
	 */
	public static String[] strToArray(String string, char separator) {
		String[] returnArray;
		if (string == null || string.equalsIgnoreCase("")) {
			returnArray = new String[1];
			returnArray[0] = "";
		} else {
			int j = 0, lastpos = 0;
			ArrayList pos = new ArrayList();
			pos.add(String.valueOf(-1));
			for (int i = 0; i < string.length(); i++) {
				if (string.charAt(i) == separator) {
					j++;
					lastpos = i;
					pos.add(String.valueOf(i));
				}
			}
			if (lastpos != string.length()) {
				j++;
				pos.add(String.valueOf(string.length()));
			}

			int[] ps = new int[pos.size()];
			for (int i = 0; i < ps.length; i++) {
				ps[i] = Integer.parseInt(String.valueOf(pos.get(i)));
			}
			returnArray = new String[ps.length - 1];
			for (int i = 0; i < returnArray.length; i++) {
				returnArray[i] = string.substring(ps[i] + 1, ps[i + 1]).trim();
			}

		}
		return returnArray;
	}

	/**
	 * 数组到字串,用"|"隔开 *
	 * 
	 * @param strArray
	 *            String[]
	 * @return String
	 */
	public static String arrayToStr(String strArray[]) {
		String reStr = "";
		if (strArray != null) {
			for (int i = 0; i < strArray.length; i++) {
				if (strArray[i] != null) {
					reStr = reStr + strArray[i].trim() + "|";
				} else {
					reStr = reStr + "|";
				}
			}
		}
		return reStr;
	}

	/**
	 * 得到两个字符间的字符串 *
	 * 
	 * @param string
	 *            String
	 * @param leftFlag
	 *            char
	 * @param rightFlag
	 *            char
	 * @return String
	 */
	public static String getParamString(String string, char leftFlag, char rightFlag) {
		String param = "";

		while (string.indexOf(rightFlag) < string.indexOf(leftFlag))
			string = string.substring(string.indexOf(rightFlag) + 1);

		if (string.indexOf(leftFlag) >= 0 && string.indexOf(rightFlag) >= 0) {
			int pos1 = string.indexOf(leftFlag);
			int pos2 = string.indexOf(rightFlag);
			param = string.substring(pos1 + 1, pos2);
		}

		return param;
	}

	/**
	 * 得到两个相同字符间的字符串 *
	 * 
	 * @param string
	 *            String
	 * @param leftFlag
	 *            char
	 * @param rightFlag
	 *            char
	 * @return String
	 */
	public static String getParam(String string, char flag) {
		if (string == null || string.equalsIgnoreCase(""))
			return "";

		String param = "";
		int pos1 = string.indexOf(flag);
		int pos2 = 0;
		if (pos1 >= 0) {
			pos2 = string.indexOf(flag, pos1 + 1);

			if (pos2 > 0) {
				param = string.substring(pos1 + 1, pos2);
			}
		}
		return param;
	}

	/**
	 * 得到一个字符串中所有两个字符间的字符串 *
	 * 
	 * @param string
	 *            String
	 * @param leftFlag
	 *            char
	 * @param rightFlag
	 *            char
	 * @return List
	 */
	public static List getParamList(String string, char leftFlag, char rightFlag) {
		String param = "";
		List<String> lt = new ArrayList<String>();
		while (string.indexOf(leftFlag) != -1) {
			while (string.indexOf(rightFlag) < string.indexOf(leftFlag))
				string = string.substring(string.indexOf(rightFlag) + 1);

			if (string.indexOf(leftFlag) >= 0 && string.indexOf(rightFlag) >= 0) {
				int pos1 = string.indexOf(leftFlag);
				int pos2 = string.indexOf(rightFlag);
				param = string.substring(pos1 + 1, pos2);
				string = string.substring(pos2 + 1);
				lt.add(param);
			}

		}
		return lt;
	}

	/**
	 * 用str2 替换 str与str1 间字串 *
	 * 
	 * @param str
	 *            String
	 * @param str1
	 *            String
	 * @param str2
	 *            String
	 * @return String
	 */
	public static String replace(String str, String str1, String str2) {
		for (int pos = str.indexOf(str1); pos >= 0; pos = str.indexOf(str1))
			str = new String(new StringBuffer(str.substring(0, pos)).append(str2).append(
					str.substring(str1.length() + pos)));

		return str;
	}

	/**
	 * 得到10位随机码
	 * 
	 * @return
	 */
	public static String getRandomId() {
		String str = "";
		int[] t = new int[10];
		Random rand = new Random();
		for (int i = 0; i < t.length; i++) {
			t[i] = rand.nextInt(10);
		}
		for (int i = 0; i < t.length; i++) {
			str += t[i];
		}
		return str;
	}

	/**
	 * 
	 * @param stringList
	 *            字符串集合
	 * @return 返回SQL语句中in的部分 如: '0001','0002','0003'
	 */
	public static String listToString(List<String> stringList) {
		if (stringList == null || stringList.isEmpty()) {
			return null;
		} else {
			StringBuffer sb = new StringBuffer();
			for (Iterator<String> iterator = stringList.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				sb.append(",'").append(string).append("'");
			}
			return sb.toString().substring(1);
		}
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	public static String transConditionStr(String conditionStr, String field) {

		String str = " " + field + " ";
		String str2 = "(" + field + " ";
		int j = conditionStr.indexOf(str);
		if (j != -1) {
			conditionStr = conditionStr.replace(str + "= '", str + "like '%");
			str = str + "like '%";
			int otherStart = conditionStr.indexOf(str) + str.length();
			int oIndex = conditionStr.indexOf("'", otherStart);
			conditionStr = conditionStr.substring(0, oIndex) + "%'" + conditionStr.substring(oIndex + 1);
			// conditionStr = conditionStr.replace(conditionStr.substring(j,
			// oIndex+1),conditionStr.substring(j,oIndex)+"%'");
		}
		if (conditionStr.indexOf(str2) != -1) {
			conditionStr = conditionStr.replace(str2 + "= '", str2 + "like '%");
			str2 = str2 + "like '%";
			int otherStart = conditionStr.indexOf(str2) + str2.length();
			int oIndex = conditionStr.indexOf("'", otherStart);
			conditionStr = conditionStr.substring(0, oIndex) + "%'" + conditionStr.substring(oIndex + 1);
			// conditionStr = conditionStr.replace(conditionStr.substring(j,
			// oIndex+1),conditionStr.substring(j,oIndex)+"%'");
		}

		return conditionStr;
	}

	public static boolean isNumeric(String str, Class clazz) {
		try {
			if (clazz.equals(Byte.class)) {
				Byte.parseByte(str);
			} else if (clazz.equals(Double.class)) {
				Double.parseDouble(str);
			} else if (clazz.equals(Float.class)) {
				Float.parseFloat(str);
			} else if (clazz.equals(Integer.class)) {
				Integer.parseInt(str);
			} else if (clazz.equals(Long.class)) {
				Long.parseLong(str);
			} else if (clazz.equals(Short.class)) {
				Short.parseShort(str);
			}
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	private static boolean changeFlag = false;

	/**
	 * 给指定集合中的对象按多个属性名升序降序排列
	 * 
	 * @param targetList
	 *            目标对象集合
	 * @param parameterList
	 *            属性名升降序集合
	 */
	public static void sortList(List<Object> targetList, List<String> parameterList) {

		try {
			for (int i = 0; i <= targetList.size() - 2; i++) {
				for (int j = targetList.size() - 1; j > i; j--) {
					changePosition(targetList, j - 1, j, parameterList);
					changeFlag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param targetObject
	 * @param name
	 * @return
	 */
	private static Object getValue(Object targetObject, String name) {
		Object value = null;
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(targetObject.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				if (name.equals(propertyDescriptor.getName())) {
					value = (Object) propertyDescriptor.getReadMethod().invoke(targetObject);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * @param targetObject
	 * @param name
	 * @return
	 */
	private static String getValueType(Object targetObject, String name) {
		String valueType = null;
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(targetObject.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				if (name.equals(propertyDescriptor.getName())) {
					Class typeClass = propertyDescriptor.getPropertyType();
					valueType = typeClass.getSimpleName();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valueType;
	}

	/**
	 * @param targetList
	 * @param index1
	 * @param index2
	 * @param parameterList
	 * @throws AccountingException
	 */
	private static void changePosition(List<Object> targetList, int index1, int index2, List<String> parameterList)
			throws AccountingException {

		Object index1Object = targetList.get(index1);
		Object index2Object = targetList.get(index2);
		for (int i = 0; i < parameterList.size(); i += 2) {
			if (changeFlag) {
				return;
			}
			String name = parameterList.get(i);
			String order = parameterList.get(i + 1);
			String valueType = getValueType(index1Object, name);
			// System.out.println(valueType);

			if (order.equals("desc")) {
				if (valueType.equals("String")) {
					String index1Value = String.valueOf(getValue(index1Object, name)).trim();
					String index2Value = String.valueOf(getValue(index2Object, name)).trim();
					if (index2Value.compareTo(index1Value) > 0) {
						Object tempObject = null;
						tempObject = targetList.get(index1);
						targetList.set(index1, targetList.get(index2));
						targetList.set(index2, tempObject);
						changeFlag = true;
						break;

					} else if (index2Value.compareTo(index1Value) == 0) {
						if (parameterList.size() == 2) {
							changeFlag = true;
							break;
						}
						List<String> parameterListNext = parameterList.subList(2, parameterList.size());
						changePosition(targetList, index1, index2, parameterListNext);
					}

				} else {
					double index1Value = Double.parseDouble(String.valueOf(getValue(index1Object, name)));
					double index2Value = Double.parseDouble(String.valueOf(getValue(index2Object, name)));
					if (index2Value > index1Value) {
						Object tempObject = null;
						tempObject = targetList.get(index1);
						targetList.set(index1, targetList.get(index2));
						targetList.set(index2, tempObject);
						changeFlag = true;
						break;

					} else if (index2Value == index1Value) {
						if (parameterList.size() == 2) {
							changeFlag = true;
							break;
						}
						List<String> parameterListNext = parameterList.subList(2, parameterList.size());
						changePosition(targetList, index1, index2, parameterListNext);
					}
				}
			} else if (order.equals("esc")) {
				if (valueType.equals("String")) {
					String index1Value = String.valueOf(getValue(index1Object, name));
					String index2Value = String.valueOf(getValue(index2Object, name));
					if (index2Value.compareTo(index1Value) < 0) {
						Object tempObject = null;
						tempObject = targetList.get(index1);
						targetList.set(index1, targetList.get(index2));
						targetList.set(index2, tempObject);
						changeFlag = true;
						break;

					} else if (index2Value.compareTo(index1Value) == 0) {
						if (parameterList.size() == 2) {
							changeFlag = true;
							break;
						}
						List<String> parameterListNext = parameterList.subList(2, parameterList.size());
						changePosition(targetList, index1, index2, parameterListNext);
					}

				} else {
					double index1Value = Double.parseDouble(String.valueOf(getValue(index1Object, name)));
					double index2Value = Double.parseDouble(String.valueOf(getValue(index2Object, name)));
					if (index2Value < index1Value) {
						Object tempObject = null;
						tempObject = targetList.get(index1);
						targetList.set(index1, targetList.get(index2));
						targetList.set(index2, tempObject);
						changeFlag = true;
						break;

					} else if (index2Value == index1Value) {
						if (parameterList.size() == 2) {
							changeFlag = true;
							break;
						}
						List<String> parameterListNext = parameterList.subList(2, parameterList.size());
						changePosition(targetList, index1, index2, parameterListNext);
					}
				}
			} else {
				throw new AccountingException("排序名称只可为esc或desc");
			}
		}

	}

	public static String getCopySql(Object sourceObject, String targetTableName, String[] nameStrs, Object[] values,
			String condition) throws AccountingException {
		if ((nameStrs == null && values != null) || (nameStrs != null && values == null)
				|| (nameStrs != null && values != null && nameStrs.length != values.length)) {
			throw new AccountingException("字段名参数和值参数必须保持一致");
		}
		StringBuffer insertSqlSb = null;
		StringBuffer selectSqlSb = null;
		String sourceTableName = StringUtil.AddUnderlineByUppercase(sourceObject.getClass().getSimpleName());

		try {
			insertSqlSb = new StringBuffer("insert into ").append(targetTableName).append(" (");
			selectSqlSb = new StringBuffer(" select ");
			Field[] fields = sourceObject.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String fieldName = field.getName();
				if (i == fields.length - 1) {
					if (nameStrs == null || nameStrs.length == 0) {
						insertSqlSb.append(StringUtil.AddUnderlineByUppercase(fieldName)).append(") ");
						selectSqlSb.append(StringUtil.AddUnderlineByUppercase(fieldName)).append(" from ").append(
								sourceTableName).append(" where ").append(condition);
					} else {
						insertSqlSb.append(StringUtil.AddUnderlineByUppercase(fieldName)).append(",");
						selectSqlSb.append(StringUtil.AddUnderlineByUppercase(fieldName)).append(",");
						for (int j = 0; j < nameStrs.length; j++) {
							if (j == nameStrs.length - 1) {
								insertSqlSb.append(nameStrs[j]).append(")");
								selectSqlSb.append("'" + String.valueOf(values[j]) + "' from ").append(sourceTableName)
										.append(" where ").append(condition);

							} else {
								insertSqlSb.append(nameStrs[j]).append(",");
								selectSqlSb.append("'" + String.valueOf(values[j]) + "',");
							}
						}
					}
				} else {
					insertSqlSb.append(StringUtil.AddUnderlineByUppercase(fieldName)).append(",");
					selectSqlSb.append(StringUtil.AddUnderlineByUppercase(fieldName)).append(",");

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccountingException(e);
		}
		return insertSqlSb.append(selectSqlSb).toString();
	}

	public static Map changeListToMap(String keyName, List list) throws AccountingException {
		Map map = new HashMap<String, Object>();
		String key = "";
		for (int i = 0; i < list.size(); i++) {
			Object object = list.get(i);
			if(object==null){
				continue;
			}
			Class clazz = object.getClass();
			try {
				Field field = clazz.getDeclaredField(keyName);
				field.setAccessible(true);
				key = String.valueOf(field.get(object));
				map.put(key, object);
			} catch (Exception e) {
				e.printStackTrace();
				throw new AccountingException(clazz.toString() + "中没有名字为:" + keyName + "的变量!");
			}
		}

		return map;
	}

	/**
	 *将传进来的list,以list中对象的keyName值为主键,list中的对象为value存放到map中.
	 * keyNames为数组,如果不带'_'则必须与domain类中的变量名一致,若带有'_',则不区分大小写.
	 * 
	 * @param keyNames
	 * @param list
	 * @return
	 * @throws AccountingException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> changeListToMap(String[] keyNames, List list) throws AccountingException {
		Map map = new HashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			StringBuffer key = new StringBuffer();
			Object object = list.get(i);
			Class clazz = object.getClass();
			for (String keyName : keyNames) {
				try {
					if (keyName.contains("_")) {
						StringBuffer buffer = new StringBuffer();
						String[] strs = keyName.split("_");
						buffer.append(strs[0]);
						for (int j = 1; j < strs.length; j++) {
							String temp = strs[j];
							temp = StringToTitleCase(temp);
							buffer.append(temp);
						}
						keyName = buffer.toString();
					}
						Field field = clazz.getDeclaredField(keyName);
						field.setAccessible(true);
						key.append("," + String.valueOf(field.get(object)));
				} catch (Exception e) {
					e.printStackTrace();
					throw new AccountingException(clazz.toString() + "中没有名字为:" + keyName + "的变量!");
				}
			}
			String str = key.toString();
			if (str.startsWith(",")) {
				str = str.substring(1, key.length());
			}
			map.put(str, object);
		}

		return map;
	}

	/**
	 * 首字母变大写
	 * 
	 * @param string
	 * @return
	 */
	public static String StringToTitleCase(String string) {
		string = string.toLowerCase();
		char first = string.charAt(0);
		first = (char) (first - 32);
		string = string.replace(string.charAt(0), first);

		return string;
	}
	/**
	 * 根据替换字符串中指定字符
	 * @param str1 被替换的字符串
	 * @param str2 替换字符串
	 * @param str3 需要被替换的字符
	 * @return 替换之后的字符串
	 */
	public static String stringReplace(String str1,String str2,String str3){
		return str1 = str1.replace(str2, str3);
	}
	/**
	 * 判断字符串中是否存在指定字符
	 * @param str原始字符串
	 * @param str1判断指定字符
	 * @return
	 */
	public static boolean YXString(String str,String str1){
		int i = str.indexOf(str1);
		if (i > 0)
			return true;
		else
			return false;
	}
	public static void main(String[] args) throws Exception {
		// System.out.println(StringUtil.AddUnderlineByUppercase("ABCdE"));
		// String sql = getCopySql(new AcLnMst(), "ac_ln_mst_hst", new String[]
		// { "trace_no" }, new Object[] { "aaa" },
		// "ac_id='123'");
		// System.out.println(sql);

//		Connection conn = CreatePrimaryKey.getConnection();
//		List<AcLnLo> acLnLos = (ArrayList) JdbcDao.queryList(new AcLnLo(), "loan_no='c2011001'", "ac_ln_lo", conn);
//		System.out.println(acLnLos.size());
//		String str[] = { "perd_no", "acId","loan_no" };
//		Map<String, Object> map = changeListToMap(str, acLnLos);
//		System.out.println(map.size());
//		for (String s : map.keySet()) {
//			System.out.println(s + "@@@" + ((AcLnLo) map.get(s)).getPerdNo());
//		}
		String content = "尊敬的C客户，您的测试项目发发发-B应付息缴纳时间即将到期，本期应还本金E元，应付息G元，应还总额100元。请于本月day日00点之前存入足额资金，以便系统扣划。如未及时还款，您的逾期信息将被报送至人民银行个人征信数据库。如已存入请忽略此提醒，感谢您的配合与支持！";
		if (StringUtil.YXString(content, "A"))
		{content = StringUtil.stringReplace(content, "A", "aa");}
	 if (StringUtil.YXString(content, "B"))
		{content = StringUtil.stringReplace(content, "B", "bb");}
	 if (StringUtil.YXString(content, "C"))
		{content = StringUtil.stringReplace(content, "C", "cc");}
	 if (StringUtil.YXString(content, "D"))
		{content = StringUtil.stringReplace(content, "D", "dd");}
	 if (StringUtil.YXString(content, "E"))
		{content = StringUtil.stringReplace(content, "E", "ee");}
	 if (StringUtil.YXString(content, "F"))
		{content = StringUtil.stringReplace(content, "F", "ff");}
	 if (StringUtil.YXString(content, "G"))
		{content = StringUtil.stringReplace(content, "G", "gg");}
		System.out.println(content);
	}
}
