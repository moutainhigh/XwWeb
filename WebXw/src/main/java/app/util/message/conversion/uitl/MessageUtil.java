package app.util.message.conversion.uitl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class MessageUtil {
	
	private static BigDecimal bigDecimal;
	
	public static <T> List<T> deepCopy(List<T> src) throws IOException,
			ClassNotFoundException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);
		ByteArrayInputStream byteIn = new ByteArrayInputStream(
				byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		@SuppressWarnings("unchecked")
		List<T> dest = (List<T>) in.readObject();
		return dest;
	}
	
	/**
	 * 当map中存在src时（忽略大小写），就认为存在该属性
	 * @param rulesMap
	 * @param src
	 * @return
	 */
	public static boolean containsIgnoreCase(Map<String,?> rulesMap,String src){
		for(String dist:rulesMap.keySet()){
			if(dist.equalsIgnoreCase(src))return true;
		}
		return false;
	}
	
	public static <T> T getIgnoreCase(Map<String,T> rulesMap,String key){
		for(String dist:rulesMap.keySet()){
			if(dist.equalsIgnoreCase(key))return rulesMap.get(dist);
		}
		return null;
	}
	
	public static String transNumberCount(String value){
		bigDecimal = new BigDecimal(value); 
		return bigDecimal.toPlainString();
	}
}
