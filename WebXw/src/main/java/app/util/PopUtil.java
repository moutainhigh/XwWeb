package app.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class PopUtil extends Object {

	
	public static void main(String[] args ){
		String aa = getSql("select cif_no,cif_name from cif_base@1121  ",
				"cif_no-cif_no =#cif_no#;cifName-cif_name =#cifName@#;","cif_no=222,cifName=阿萨德","AND");
		System.out.println(aa);
	}
	
	
	public static String getSql(String sql,String whereCaulse,String parms,String flag ) {
		if( parms==null || "".equals(parms) ){
			return sql;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		String[] arr_val = null;
		String[] arr = null ;
		if( parms.indexOf(",") > -1 ){
			arr = parms.split(",");
			for( int i=0;i<arr.length;i++ ){
				arr_val = arr[i].split("=");
				map.put(arr_val[0], arr_val[1]);
			}
		}else{
			arr_val = parms.split("=");
			map.put(arr_val[0], arr_val[1]);
		}
		String where_sql = "";
		Pattern pattern = null ;
		if( sql.indexOf("#") > -1 ){
			int len = sql.indexOf("#");
			if( len > 2 ){
				if( parms.indexOf(".") > -1 ){
					pattern = Pattern.compile("#[\\w\\d]+.+#");
				}else{
					pattern = Pattern.compile("#[\\w\\d]+#");
				}
			}else{
				pattern = Pattern.compile("#[\\w\\d]+.+#"); 
			}
			Matcher matcher=pattern.matcher(sql); 
			while(matcher.find()){  
		        String paraStr=matcher.group();  //#cif_no#
		        String paramName=paraStr.replaceAll("#", "");  //cif_no
		        Object value=map.get(paramName);  //cif_no对应的值
		        if( value!=null && !"".equals(value.toString()) ){
		        	sql = sql.replace(paraStr, "'"+value.toString()+"'");
		        }
		     }
		}
		if( whereCaulse.indexOf(";") > -1 ){
			arr = whereCaulse.split(";");
			for( int i=0;i<arr.length;i++ ){
				arr_val = arr[i].split("-");
				if( arr_val[1]!=null && !"".equals(arr_val[1]) ){
					if( arr_val[1].indexOf("@") > -1 ){
						pattern = Pattern.compile("#[\\w\\d]+.+#"); 
					}else{
						pattern = Pattern.compile("#[\\w\\d]+.+#"); 
					}
					where_sql += replaceMatch(pattern,map,arr_val[1],flag);
				}
			}
		}else{
			arr_val = whereCaulse.split("-");
			if( arr_val[1].indexOf("@") > -1 ){
				pattern = Pattern.compile("#[\\w\\d]+.+#"); 
			}else{
				pattern = Pattern.compile("#[\\w\\d]+.+#"); 
			}
			where_sql = replaceMatch(pattern,map,arr_val[1],flag);
		}
		where_sql = where_sql.replaceFirst(flag, "");
		sql = sql.toUpperCase();
		if( !"".equals(where_sql)){
			if( sql.indexOf("WHERE") > -1  ){
				sql = sql+" "+flag+" "+"("+where_sql+")" ;
			}else{
				sql = sql+" WHERE "+"("+where_sql+")";
			}
		}
		
		return sql;
	}
	
	public static String replaceMatch(Pattern pattern ,Map<String,Object> map,String str,String flag){
		String where_sql = "";
		Matcher matcher=pattern.matcher(str); 
		while(matcher.find()){  
	        String paraStr=matcher.group();  //#cif_no#
	        String paramName=paraStr.replaceAll("#", "").replaceAll("@", "");  //cif_no
	        Object value=map.get(paramName);  //cif_no对应的值
	        if( value!=null && !"".equals(value.toString()) ){
	        	if( paraStr.indexOf("@") > -1 ){
	        		str=str.replace(paraStr, "'%"+value.toString()+"%'");
	        	}else{
	        		if( str.contains(">") || str.contains("<") ){
	        			str=str.replace(paraStr, value.toString() );
	        		}else{
	        			str=str.replace(paraStr, "'"+value.toString()+"'");
	        		}
	        	}
	        	where_sql += " "+ flag+ " "+ str ;
	        }
	     }
		return where_sql;
	}
	
}
