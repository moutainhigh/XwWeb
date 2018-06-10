package app.util.taglib;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import app.creditapp.entity.CacheBase;
import app.oscache.Datadict;

public class SelectTag extends TagSupport {

	private static final long serialVersionUID = 108756754423401185L;
	// 数据字典表中存放名称的字段
	private String ddname = null;
	// 选中值
	private String value = null;
	private String property = null;
	// 对应select控件的style属性
	private String style = null;
	// 对应select控件的class属性
	private String classname = null;
	// 对应的select控件的disabled属性
	private String disabled = null;
	// 对应select控件的onchange属性
	private String onchange = null;
	// 数据的scope属性
	private String scope = null;
	// 数据的name属性
	private String name = null;	
	// 对应是否增加"请选择"的选项
	private String allownull = null;
	//"请选择"的值
	private String all = null;
	//子级联的名称
	private String subname = null;
	//在级联中的级别
	private String level = null;
	//过滤条件
	private String filter=null;
	private String subfilter=null;
	private String alt=null;
	private String emptyok=null;
	private String readonly=null;
	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		Datadict data = new Datadict(ddname);
		List<CacheBase> list = data.getDatadictByLevel();
		StringBuffer buffer = new StringBuffer();
		buffer.append("<select");
		// name属性处理
		buffer.append(" name='" + property + "'");

		if (style != null) {
			buffer.append(" style='" + style + "'");
		}
		if (alt != null) {
			buffer.append(" alt='" + alt + "'");
		}
		if (readonly != null) {
			buffer.append(" readonly='" + readonly + "'");
		}
		if (emptyok != null) {
			buffer.append(" emptyok='" + emptyok + "'");
		}
		if (classname != null) {
			buffer.append(" class='" + classname + "'");
		}
		if ((disabled != null) && (disabled.equals("true"))) {
			buffer.append(" disabled");
		}
		if (onchange != null && subname == null) {
				buffer.append(" onchange='javascript:" + onchange + "'");
		}
		buffer.append(">");
		
		if (allownull != null && "true".equalsIgnoreCase(allownull)) {
			buffer.append("\n\t<option value=''>" + ""	+ "</option>");
		}
		if (all != null) {
			buffer.append("\n\t<option value='" + all + "'>" + "全部" + "</option>");
		}
		// 选项值处理
		int scope_type = PageContext.REQUEST_SCOPE;
		String _value="";
		if (name != null) {
			scope_type = parseScope(scope);
			Object obj = pageContext.getAttribute(name, scope_type);
			if(obj instanceof String){
				_value = (String)obj;
			}else{
				_value = getPropertyValue(obj, property);
			}
			if(_value==null){
				_value="";
			}
		}
		if (value == null || "".equals(value)) {
			value = _value;
		}
		for (int i = 0; i < list.size(); i++) {
			String code = list.get(i).getOpt_code();
				String _name = list.get(i).getOpt_name();
				if(filter(filter,code)){
					if (value.equals(code)) {
						buffer.append("\n\t<option selected value='" + code + "'>"
							+ _name + "</option>");
					} else {
						buffer.append("\n\t<option value='" + code + "'>" + _name
							+ "</option>");
					}
				}
			}
			buffer.append("\n</select>");
			printStr(buffer.toString());
//		}		
		return SKIP_BODY;
	}

	/**
	 * @param scope
	 * @return
	 */
	private int parseScope(String scope) {
		if (scope == null)
			scope = "request";
		if (scope.equalsIgnoreCase("requst")) {
			return PageContext.REQUEST_SCOPE;
		} else if (scope.equalsIgnoreCase("page")) {
			return PageContext.PAGE_SCOPE;
		} else if (scope.equalsIgnoreCase("session")) {
			return PageContext.SESSION_SCOPE;
		} else if (scope.equalsIgnoreCase("application")) {
			return PageContext.APPLICATION_SCOPE;
		}
		return PageContext.REQUEST_SCOPE;
	}

	@SuppressWarnings("unchecked")
	private String getPropertyValue(Object obj, String property) {
		if (obj == null)
			return null;
		Class type = obj.getClass();
		String result = null;
		String methodName = "get" + Character.toUpperCase(property.charAt(0))
				+ property.substring(1);
		try {
			Method method = type.getMethod(methodName, new Class[]{});
			result = (String) method.invoke(obj, new Object[]{});
		} catch (Exception e) {
			if( obj instanceof Map) {
				return (String)((Map)obj).get(property);
			}else{
				System.out.println("属性："+property+"在BEAN "+type+" 中不存在");
			}
		}
		return result;
	}

	/**
	 * 在页面上输入信息
	 * 
	 * @param label
	 * @throws JspException
	 */
	private void printStr(String label) throws JspException {
		StringBuffer buf = new StringBuffer();
		buf.append(label);
		try {
			pageContext.getOut().write(buf.toString());
		} catch (IOException ioe) {
			throw new JspTagException(ioe.getMessage());
		}
	}
	public int doEndTag() throws JspException {
		subname=null;
		level=null;
		value=null;
		subfilter = null;
		filter = null;
		return SKIP_BODY;
	}
	//过滤
	private boolean filter(String filter,String value){
		if(filter==null || "".equals(filter)){
			return true;
		}
		filter = filter.trim();
		String[] flt = new String[]{"&&","||"}; 

		int index = -1;
		for(int i=0;i<flt.length;i++){
			index = filter.indexOf(flt[i]);
			if(index!=-1){
				index = i;
				break;
			}
		}
		if(index==-1){
			return judge(filter,value);
		}
		switch (index){
			case 0:// &&
				return judge(filter.substring(0, filter.indexOf("&&")).trim(),value) && judge(filter.substring(filter.indexOf("&&")+2).trim(),value);
			case 1:// ||
				return judge(filter.substring(0, filter.indexOf("||")).trim(),value) || judge(filter.substring(filter.indexOf("||")+2).trim(),value);
		}
		return true;
	}
	//过滤判断
	private boolean judge(String filter,String value){
		String[] flt = new String[]{"*","_",">=","<=","=",">","<","["};
		int index=-1;
		for(int i=0;i<flt.length;i++){
			index = filter.indexOf(flt[i]);
			if(index!=-1){
				index = i;
				break;
			}
		}
		if(index==-1){
			return true;
		}
		boolean bool = true;
		Pattern pattern = null;
		String ptn="";
		switch (index){
			case 0://*匹配
				ptn = filter.replace("*", "[\\d\\w]*");
				pattern = Pattern.compile(ptn);
				bool = pattern.matcher(value).matches();
				break;
			case 1://_匹配
				ptn = filter.replace("_", "[\\d\\w]");
				pattern = Pattern.compile(ptn);
				bool = pattern.matcher(value).matches();
				break;
			case 5://>
				filter = filter.substring(1).trim();
				bool =  (value.compareTo(filter)>0 );
				break;
			case 6://<
				filter = filter.substring(1).trim();
				bool = (value.compareTo(filter)<0 );
				break;
			case 4://=
				filter = filter.substring(1).trim();
				bool = (value.compareTo(filter)==0 );
				break;
			case 2://>=
				filter = filter.substring(2).trim();
				bool = (value.compareTo(filter)>=0 );
				break;
			case 3://<=
				filter = filter.substring(2).trim();
				bool = (value.compareTo(filter)<=0 );
				break;
			case 7://[]
				filter = filter.substring(1,filter.length()-1).trim();
				bool = (filter.indexOf(value)!=-1 );
				break;
		}
		return bool;
	}
	public String getDdname() {
		return ddname;
	}

	public void setDdname(String ddname) {
		this.ddname = ddname;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}



	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAllownull() {
		return allownull;
	}

	public void setAllownull(String allownull) {
		this.allownull = allownull;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	public String getLevel() {
		return level;
	}

	public void setlevel(String level) {
		this.level = level;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSubfilter() {
		return subfilter;
	}

	public void setSubfilter(String subfilter) {
		this.subfilter = subfilter;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getEmptyok() {
		return emptyok;
	}

	public void setEmptyok(String emptyok) {
		this.emptyok = emptyok;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}


}
