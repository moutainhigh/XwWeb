package app.util.taglib;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import app.oscache.Datadict;

public class TranslateTag extends TagSupport {

	private static final long serialVersionUID = -5700014812703400673L;
	//数据字典表中存放名称的字段
	private String ddname = null;
	//选中值
	private String value = null;
	private String name = null;
	private String scope = null;
	private String property = null;
	private String level = null;


	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		
		//选项值处理
		String value_1 = getOptionValue();
		if (value_1!=null)
			value = value_1;
		
		if (value==null||value.equals("")){
			return TranslateTag.SKIP_BODY;
		}
		
		String _data="";
		Datadict data = new Datadict(ddname);
		_data = data.getDatadictByCode(value);
		printStr(_data);
		return TranslateTag.SKIP_BODY;
	}
	private String getOptionValue() {
		//		选项值处理
		String value = "";
		int scope_type = PageContext.REQUEST_SCOPE;
		if (name != null) {
			scope_type = parseScope(scope);
			if (property == null) {
				value = String.valueOf(pageContext.getAttribute(name, scope_type));
			} else {
				Object obj = pageContext.getAttribute(name,scope_type);
				value = getPropertyValue(obj, property);
			}
		}
		if(null!=value){
			value = value.trim();
		}
		return value;
	}
	/**
	 * 在页面上输入信息
	 * @param label
	 * @throws JspException
	 */
	public void printStr(String label) throws JspException {
		StringBuffer buf = new StringBuffer();
		buf.append(label);
		try {
			pageContext.getOut().write(buf.toString());
		} catch (IOException ioe) {
			throw new JspTagException(ioe.getMessage());
		}
	}
	private int parseScope(String scope) {
		if (scope==null)
			scope = "request";
		if (scope.equalsIgnoreCase("requst")){
			return PageContext.REQUEST_SCOPE;
		} else if (scope.equalsIgnoreCase("page")){
			return PageContext.PAGE_SCOPE;
		} else if (scope.equalsIgnoreCase("session")){
			return PageContext.SESSION_SCOPE;
		} else if (scope.equalsIgnoreCase("application")){
			return PageContext.APPLICATION_SCOPE;
		}
		return PageContext.REQUEST_SCOPE;
	}
	@SuppressWarnings("unchecked")
	private String getPropertyValue(Object obj, String property) {
	if(obj==null) return "";
		Class type = obj.getClass();
		String result = null;
		String methodName =	"get" + Character.toUpperCase(property.charAt(0))
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
	public int doEndTag() throws JspException {
		level=null;
		value=null;
		return SKIP_BODY;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
