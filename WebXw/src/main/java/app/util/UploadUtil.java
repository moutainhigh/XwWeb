package app.util;

import java.io.File;
import java.io.FileInputStream;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import app.oscache.MBaseCache;

public class UploadUtil {
	/*
	 * 路径转化 
	 */
	public static String getPath(String path){
		String path1=path.replace("\\", "/");
		return path1;
	}
	/*
	 * @parm 文件路径
	 * 取得文件大小
	 */
	public static int getSize(String relpath){
		File file=new File(relpath);
         double l=file.length();
		int size=(int)(l/1024);
		return size;
	}
	/*
	 * 文件删除
	 */
	public static void delete(String relpath){
		File file=new File(relpath);
		Boolean boolean1=file.delete();
		if (boolean1) {
			System.out.println("=====文件删除成功");
		}
		
	}
	
	/**
	 * 功能描述：获取UUID唯一标识
	 * @return
	 */
	public static String getUUIDNo(){
		return java.util.UUID.randomUUID().toString();
	}
	
	/**
	 * 功能描述：汉字转换位汉语拼音，英文字符不变
	 * @param chines 汉字
	 * @return pinyinName 拼音
	 */
	public static String converterToSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0];
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}
	
	/**
	 * @方法说明: 文件创建
	 * @param path
	 *            路径
	 * @param fileName
	 *            文件名称
	 * @return File
	 * @修改说明:
	 */
	public static File createFile(String path, String fileName) {
		String[] pathArray = path.split("/");
		StringBuilder tempPath = new StringBuilder();
		if(path.startsWith("/")){
			tempPath.append("/");
		}
		for (int i = 0; i < pathArray.length; i++) {
			if(null==pathArray[i] || "".equals(pathArray[i])){
				continue;
			}
			tempPath.append(pathArray[i]+"/");
			File f = new File(tempPath.toString());
			if (!f.exists())
				f.mkdir();
		}
		File target = new File(path, fileName);
		return target;
	}
	

	/**
	 * @方法说明: 获取文件大小
	 * @param file
	 * @throws Exception
	 * @return long
	 * @修改说明:
	 */
	public static long getFileSizes(File file) throws Exception // 取得文件大小
	{
		long s = 0;
		if (file.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				s = fis.available();
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
		return s;
	}
	
	public static String getParm(String key) // 获取参数
	{
		return MBaseCache.getCache().getUploadParm().get(key).trim();
	}

}
