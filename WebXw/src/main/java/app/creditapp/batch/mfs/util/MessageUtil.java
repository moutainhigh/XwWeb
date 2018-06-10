package app.creditapp.batch.mfs.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.text.SimpleDateFormat;

import app.util.DBUtils;

public class MessageUtil {

	public void sendMail(String email, String title, String msg) {
		Connection conn = null;
		CallableStatement proc = null;
		//long time = new java.util.Date().getTime();
		System.out.println("MAIL=="+msg);
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date endDate = new java.util.Date(date.getTime()+1000*60);
		try {
			conn = DBUtils.getMessageConnection();
			proc = conn.prepareCall("{ call PORTAL.SEND_INFORMATION(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?) }"); // 设置存储过程
			proc.setString(1, title);// 提醒标题
			proc.setString(2, msg);// 文本内容
			proc.setString(3, "");// html提醒内容
			proc.setString(4, "");// 附加信息路径
			proc.setDate(5, new java.sql.Date(date.getTime()));// 消息申请日期
			proc.setDate(6, java.sql.Date.valueOf(sdf.format(date)));// 提醒有效开始日期
			proc.setDate(7, java.sql.Date.valueOf(sdf.format(endDate)));// 提醒有效结束日期
			proc.setString(8, "");// 汉字描述
			proc.setString(9, "");// 业务类型
			proc.setString(10, "3");// 系统ID
			proc.setString(11, "");// 安全码
			proc.setString(12, "BITIC");// 提醒发起人ID
			proc.setString(13, "system");// 发起人名称
			proc.setString(14, "");// URL
			proc.setInt(15, 2);// 消息来源项目
			proc.setString(16, "1");// 提醒级别
			proc.setString(17, "2");// 发送渠道 2邮件 3短信
			proc.setString(18, "5");// 发送范围 4内部人员、5外部人员
			proc.setString(19, "");// 发送范围值
			proc.setString(20, email);// 关联角色
			proc.registerOutParameter(21, Types.INTEGER);// 第二个参数输出参数,是VARCHAR类型的
			proc.execute();// 执行
			int rv = proc.getInt(21);
			System.out.println("rv=="+rv);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	}

	public void sendMsg(String telNo, String title, String msg) {
		Connection conn = null;
		CallableStatement proc = null;
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date endDate = new java.util.Date(date.getTime()+1000*60);
		try {
			conn = DBUtils.getMessageConnection();
			proc = conn.prepareCall("{ call PORTAL.SEND_INFORMATION(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?) }"); // 设置存储过程
			proc.setString(1, title);// 提醒标题
			proc.setString(2, msg);// 文本内容
			proc.setString(3, "");// html提醒内容
			proc.setString(4, "");// 附加信息路径
			proc.setDate(5, new java.sql.Date(date.getTime()));// 消息申请日期
			proc.setDate(6, java.sql.Date.valueOf(sdf.format(date)));// 提醒有效开始日期
			proc.setDate(7, java.sql.Date.valueOf(sdf.format(endDate)));// 提醒有效结束日期
			proc.setString(8, "");// 汉字描述
			proc.setString(9, "");// 业务类型
			proc.setString(10, "3");// 系统ID
			proc.setString(11, "");// 安全码
			proc.setString(12, "BITIC");// 提醒发起人ID
			proc.setString(13, "system");// 发起人名称
			proc.setString(14, "");// URL
			proc.setInt(15, 2);// 消息来源项目
			proc.setString(16, "1");// 提醒级别
			proc.setString(17, "3");// 发送渠道 3短信
			proc.setString(18, "5");// 发送范围 4内部人员、5外部人员
			proc.setString(19, "");// 发送范围值
			proc.setString(20, telNo);// 关联角色
			proc.registerOutParameter(21, Types.INTEGER);// 第二个参数输出参数,是VARCHAR类型的
			proc.execute();// 执行
			int rv = proc.getInt(21);
			System.out.println("rv=="+rv);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	}
	
	public static void main(String[] args) {
		//new MessageUtil().sendMsg("王善芳#13615380539", "小微系统批量执行成功", "小微系统批量执行成功，时间：2016.12.14.15.54");
		new MessageUtil().sendMail("小微系统批量#wsftc001@163.com", "小微系统批量执行成功", "小微系统批量执行成功，时间：2016.12.14 15.54");
	}
}
