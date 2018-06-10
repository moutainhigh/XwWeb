package app.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import freemarker.core.Environment;

/**批量查征 20160525 孙明义
 * 1.输入参数批次号，查出记录数
 * 2.并发查征
 * 3.把信用报告FTP服务器，并存url和客户号关联至表里
 * 4.解析报文至数据表中
 * 5.生成规则模板
 * 6.上传规则模板至FTP服务器
 */

public class FtpClient {
	private FTPClient ftp;
	public boolean connect(String path, String addr, int port, String username, String password) {
		try {
			ftp = new FTPClient();
			ftp.setControlEncoding("GBK"); 
			int reply;
			ftp.connect(addr);
			System.out.println("连接到：" + addr + ":" + port);
			reply = ftp.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				System.err.println("FTP目标服务器积极拒绝.");
				System.exit(1);
				return false;
			}else{
				ftp.login(username, password);
				ftp.enterLocalPassiveMode();
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftp.changeWorkingDirectory(path);
				System.out.println("已连接：" + addr + ":" + port);
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return false;
		}
	}
//然后再利用ftpclient的makeDirectory方法创建文件夹
	public void createDir(String username, String password,String dirname,String filename, String pc){
		try{
			boolean lool = ftp.makeDirectory(dirname);
			if(lool){
				System.out.println("在目标服务器上成功建立了文件夹: " + dirname);//   进到创建好的目录中创建文件，写文件
				ftp.changeWorkingDirectory(dirname);
				writeFile(filename,pc);
			}else{
				ftp.changeWorkingDirectory(dirname);
				writeFile(filename,pc);
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
//断开host连接
	public void disconnect(){
		try {
			ftp.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//创建文件
	public static boolean createFile() throws IOException {
		boolean bl = false;
		File file = new File("test.txt");//创建一个文件
		if(!file.exists())
			bl = file.createNewFile();//返回true或者false判断该文件是否已经创建好
		return bl;
	}
	
	//写入文件
	public void writeFile(String filename, String pc) {
		InputStream is = null;
//		String utf8 = new String(pc.getBytes(), "utf-8"); 
		
//		try {
//			is = new ByteArrayInputStream(pc.toString().getBytes("utf-8"));
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
        try {
        	is = new ByteArrayInputStream(pc.getBytes("GBK"));  
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);  
			ftp.storeFile(new String(filename.getBytes("GBK"),"iso-8859-1"), is);
//			ftp.storeFile(new String(filename.getBytes("GBK"),"utf-8"), is);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//最后是程序的调用方法
	public boolean ftpFile(String fileName,String batchNo,String pcrpcontext) {
		/*********FTP开始*********/     
		String url = "10.7.53.30";
		int port = 21;
//		ftp = new FTPClient();
//		ftp.setControlEncoding("GBK"); 
		String username = "itp";
		String password = "su7U2yru";
		String filename = fileName;
		String pc = pcrpcontext;
		Properties pathProperties = new Properties();
		boolean bool = false;
		try { 
			pathProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("path.properties"));		 
			String path =pathProperties.getProperty("htmlPath")+File.separator+new SimpleDateFormat("yyyyMMdd").format(new Date())+File.separator+batchNo;
//		String path = File.separator+"PUB"+File.separator+batchNo+File.separator+ new SimpleDateFormat("yyyyMMdd").format(new Date());
			this.connect(path, url, port, username, password);
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.changeWorkingDirectory(path);
			this.createDir(username, password,path,filename, pc);
			bool = true;
		} catch (RuntimeException e) {
			bool = false;
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (ftp != null && !ftp.isConnected()) {
				try {
					ftp.logout(); 
					ftp.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
        return bool;
		/**************FTP结束***************/
	}
	
	public void createDir1(String username, String password,String dirname,String filename, String pc){
		try{
			boolean lool = ftp.makeDirectory(dirname);
			if(lool){
				System.out.println("在目标服务器上成功建立了文件夹: " + dirname);//   进到创建好的目录中创建文件，写文件
				ftp.changeWorkingDirectory(dirname);
				writeFile(filename,pc);
			}else{
				ftp.changeWorkingDirectory(dirname);
				writeFile(filename,pc);
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public boolean saveFile(String fileName,String batchNo,String pcrpcontext) throws IOException{
		Properties pathProperties = new Properties();
		pathProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("path.properties"));		 
		String path =File.separator+pathProperties.getProperty("htmlPath")+File.separator+new SimpleDateFormat("yyyyMMdd").format(new Date())+File.separator+batchNo;
		String crp_report_filepath = path+File.separator+fileName; 
		boolean bool = false;

		File file =new File(path);  
		File file1 = new File(crp_report_filepath);
		try {  
			//如果文件夹不存在则创建    
			if(!file.exists() && !file.isDirectory()){
			    file.mkdirs();    
			}
			if(!file1.exists()){
				file1.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file1),"GBK");
            BufferedWriter bufferWritter = new BufferedWriter(write);
            bufferWritter.write(pcrpcontext);
            if (bufferWritter != null) {
				bufferWritter.flush();
				write.close();
				bufferWritter.close();
			}
			bool = true;
		} catch (RuntimeException e) {
			bool = false;
			e.printStackTrace();
		} catch (IOException e) {
			bool = false;
			e.printStackTrace();
		}
        return bool;
	}
	
	public boolean saveFile2(String fileName,String batchNo,String pcrpcontext) throws IOException{
		Properties pathProperties = new Properties();
		pathProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("path.properties"));		 
		String path =pathProperties.getProperty("htmlPath")+File.separator+new SimpleDateFormat("yyyyMMdd").format(new Date())+File.separator+batchNo;
		String crp_report_filepath = path+File.separator+fileName; 
		boolean bool = false;

		System.out.println("pcrpcontext==>"+pcrpcontext);
		System.out.println("path:haha==>"+path);
		System.out.println("crp_report_filepath:haha==>"+crp_report_filepath);
		
		File file = new File(crp_report_filepath);
		if (!file.getParentFile().exists()) {
		    file.getParentFile().mkdirs();
		}
		try {
		    file.createNewFile();
		} catch (IOException e) {
		    e.printStackTrace();
		}

		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(pcrpcontext);
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
        return bool;
	}
	
	public boolean saveFile3(String fileName,String batchNo,String pcrpcontext) throws IOException{
		Properties pathProperties = new Properties();
		pathProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("path.properties"));		 
		String path =pathProperties.getProperty("htmlPath")+File.separator+new SimpleDateFormat("yyyyMMdd").format(new Date())+File.separator+batchNo;
		String crp_report_filepath = path+File.separator+fileName; 
		boolean bool = false;
        FileWriter fw = null;   
		
		File file = new File(crp_report_filepath);
		if (!file.getParentFile().exists()) {
		    file.getParentFile().mkdirs();
		    bool = true;
		}else{
			bool = false;
		}
		if(!file.exists()){
			fw = new FileWriter(crp_report_filepath); 
			bool = true;
		}else{
			bool = false;
		}
		try {
//			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),"GBK"));
//			writer.write(pcrpcontext);
			
			fw.write(pcrpcontext);
			
//			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(fileName),"GBK");
//			BufferedWriter bufferWritter = new BufferedWriter(write);
//            bufferWritter.write(pcrpcontext);
//			write.write(pcrpcontext);
			fw.close(); 
//			if (bufferWritter != null) {
//				bufferWritter.flush();
//				write.close();
//				bufferWritter.close();
//			}
		} catch (IOException e) {
		    e.printStackTrace();
		    bool = false;
		}
        return bool;
	}
}
