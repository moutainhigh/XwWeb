package accounting.batch.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 批处理与核心交互文件操作类
 */
public class FileUtil {
	
	/**
	 * 得到下传文件输入流
	 */
	public static BufferedReader getReader(String downFileName)throws Exception{
		File downFile = new File(downFileName);
		BufferedReader br = null;
		if(downFile.exists()){
			br = new BufferedReader(new FileReader(downFile));
		}else{
			throw new Exception("下传文件不存在");
		}
		return br;
	}
	
	/**
	 * 向文件中写入一行记录
	 * @param upFileName 上传文件名
	 * @param str[] 字符串数组
	 * @throws Exception
	 */
	public static void write(String upFileName, String str[]) throws Exception{
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new FileWriter(upFileName,true));
			for(int i=0;i<str.length;i++){
				bw.write(str[i]);
				if(i<str.length-1){
					bw.write("|");
				}
			}
			bw.newLine();
			bw.flush();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			bw.close();
			bw = null;
		}
	}
	
	/**
	 * 向文件中写入一行记录
	 * @param bw 文件输出流
	 * @param str[] 字符串数组
	 * @throws Exception
	 */
	public static void write1(BufferedWriter bw, String[] str) throws Exception{
		try{
			for(int i=0;i<str.length;i++){
				bw.write(str[i]);
				if(i<str.length-1){
					bw.write("|");
				}
			}
			bw.newLine();
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			bw.flush();
		}
	}
	
	/**
	 * 通过文件名,得到文件输出流
	 * @param uploadFileName 上传文件名
	 * @return bw 文件输出流
	 * @throws Exception
	 */
	public static BufferedWriter getWriter(String uploadFileName) throws Exception{
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new FileWriter(uploadFileName));
		}catch(Exception e){
			e.printStackTrace();
			if(bw != null){
				bw.close();
			}
			bw = null;
			throw e;
		}
		return bw;
	}
	
	public static void main(String[] args) throws Exception {
		FileUtil.write("c:\\1.txt",new String[]{"1","2","3"});
	}
}
