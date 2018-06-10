package app.util.ruleFiter.clientPort;

import app.util.ruleFiter.entity.SendData;

/**
 * 消息发送方
 * <br>
 * 规则校验过滤器从此接口获得需要校验的数据文件
 * <br>
 * 数据文件可能存在多种文件格式：txt,excel,json，xml等等
 * <br>
 * 
 *
 */
public interface SenderInterface {
	/**
	 * 需要实现本接口进行报文数据的获取。
	 * <br>
	 * 报文数据可能存在多种数据格式，比如txt,json字符串，xml格式等，建议固定一种方式获取。也可以在同样的方法中通过策略模式获取不同类型的数据
	 * <br>
	 * 
	 * @return 封装后的数据格式为SendData，需要包含申请校验的规则ID，报文文件类型，以及报文相关资料数据（字符串或者文件）
	 */
	public SendData sendDataByFile();
	
}
