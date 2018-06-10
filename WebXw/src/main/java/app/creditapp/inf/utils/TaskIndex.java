package app.creditapp.inf.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import app.creditapp.batch.util.FTPBean;
import app.oscache.Datadict;

public class TaskIndex extends Thread {
	public static Logger logger = Logger.getLogger(TaskIndex.class.getClass());
	private static boolean isOpen = false;// 连接ftp服务器
	private static FTPClient ftp = null;

	@Override
	public void run() {
		logger.error("开启影像out目录中拿文件监听");
		// 获取信息
		Datadict data = new Datadict("IMG_RELATION");
		final FTPBean ftpBean = new FTPBean();
		ftpBean.setIp(data.getDatadictByCode("ADDRESS"));
		ftpBean.setPort(Integer.parseInt(data.getDatadictByCode("PORT")));
		ftpBean.setUserName(data.getDatadictByCode("USER"));
		ftpBean.setPasswd(data.getDatadictByCode("PWD"));
		final String sourceDir = data.getDatadictByCode("SOURCEDIR");
		String outDir=data.getDatadictByCode("OUTDIR");//签章out目录
		final String targetDir = data.getDatadictByCode("TARGETDIR");
		if (!isOpen) {
			TaskIndex.connect(ftpBean);
		}
		if (isOpen) {
			//取文件操作
			//1.获取出所有的文件list
			try {
				ftp.setControlEncoding("UTF-8");
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftp.enterLocalPassiveMode();
				FTPFile[] files=ftp.listFiles(outDir);//从out目录下获取
				if (files!=null && files.length!=0) {
					//查找文件进行移动，复制，重命名，放到合作机构exp目录下
					for (int i = 0; i < files.length; i++) {
						String fileName=files[i].getName();//获取文件名
						String[]imgInfo=fileName.split("\\_");
						String brNo=imgInfo[0];
						String transNo=imgInfo[1];
						String docType=imgInfo[2];
						long time=Long.parseLong(imgInfo[3].split("\\.")[0]);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						Date d = new Date(time);
						String date=sdf.format(d);
						//获取到要放回到哪个目录下
						String finalDir = targetDir + "/" + brNo
								+ "/" + date + "/"
								+ transNo;
						//移动文件
						String newFileName="sign_"+fileName;
						String suffix=newFileName.replace(newFileName.substring(0,newFileName.lastIndexOf(".")), "");//后缀名
						boolean flag=ftp.rename(outDir+"/"+fileName,finalDir+"/"+newFileName);
						if (flag) {//修改原来的路径签章状态，新增一条路径信息
//							ImgPath imgPath=new ImgPath();
//							imgPath.setDocName(fileName);
//							imgPath.setIfSign("1");
//							ImgPathDao imgPathDao=(ImgPathDao) SourceTemplate.getSpringContextInstance().getBean("imgPathDao");
//							imgPathDao.updateIfSign(imgPath);
//							//新增一条路径信息
//							ImgPath imgPath2=new ImgPath();
//							imgPath2.setBrNo(brNo);
//							imgPath2.setDocName(newFileName);
//							imgPath2.setDocPath(finalDir+"/"+newFileName);
//							imgPath2.setDocType(docType);
//							imgPath2.setFileType(suffix);
//							imgPath2.setIfSign("2");
//							imgPath2.setInTime(DateUtil.getDateTime());
//							imgPath2.setTransNo(transNo);
//							imgPathDao.insertPath(imgPath2);
							
							ByteArrayInputStream in = null;  
						    ByteArrayOutputStream fos = new ByteArrayOutputStream(); 
						    
							
							//复制文件到合作机构目录下
							ftp.setBufferSize(1024 * 2);  
					        // 变更工作路径  
							ftp.changeWorkingDirectory(finalDir);  
					        // 设置以二进制流的方式传输  
							ftp.setFileType(FTP.BINARY_FILE_TYPE);  
					        // 将文件读到内存中  
							ftp.retrieveFile(newFileName, fos);  
					        in = new ByteArrayInputStream(fos.toByteArray());  
					        if (in != null) {  
					        	ftp.changeWorkingDirectory(sourceDir+"/"+brNo+"/exp");  
					        	boolean a=ftp.storeFile(newFileName, in);
					        }  
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//取出文件名
		//按照文件名拆分，然后获取到基本信息，将签章完成的文件 做以下操作
		//1.重命名放回到目标文件夹
		//2.更新之前的文件路径表是否签章状态
		//3.新增一条签章完成的路径信息
		//4。放到合作机构目录下
	}
	/**
	 * 连接ftp服务器
	 */
	public static void  connect(FTPBean bean){
		logger.info("FTP 连接" + bean.getIp() + " " + bean.getPort() + " " + bean.getUserName());
		try {
			ftp = new FTPClient();
			ftp.connect(bean.getIp(), bean.getPort());
			ftp.login(bean.getUserName(), bean.getPasswd());
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				logger.error("FTP 登录拒绝");
				isOpen=false;
			} else {
				isOpen = true;
				//ftp.changeWorkingDirectory(bean.getReomtePath());
				ftp.setControlEncoding("GBK");
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				logger.info("FTP 登录成功");
				isOpen=true;
			}
		} catch (SocketException e) {
			logger.error("连接FTP失败.");
			isOpen = false;
			e.printStackTrace();
		}catch (IOException ioe) {
			ioe.printStackTrace();
			logger.error("FTP 连接失败" + ioe.getMessage());
			isOpen=false;
		}
	} 

}
