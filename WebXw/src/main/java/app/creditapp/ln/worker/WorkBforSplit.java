package app.creditapp.ln.worker;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import app.base.SourceTemplate;
import app.creditapp.ln.entity.LnApplyMid;
import app.creditapp.redis.util.RedisUtil;
import app.util.syslog.bo.SysExceptionBo;

import com.alibaba.fastjson.JSON;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 6, 2016
 * @描述 业务处理B节点 生成正式申请表，并拆分成客户信息表、账户信息、押品信息表
 */
public class WorkBforSplit implements Runnable {
	Logger logger = Logger.getLogger(WorkBforSplit.class);
	private LnApplyMid lnApplyMid;
	public WorkBforSplit(LnApplyMid lnApplyMid) {
		this.lnApplyMid = lnApplyMid;
	}
	
	public void run() {
		String _mid_toreg = "0"; //生成正式进件申请表成功标志，默认失败
		String _split_result="0"; // 拆分成功标志，默认失败
		try {
		    if(lnApplyMid==null){
		    	logger.error("B任务处理失败,接收到数据为空！");
		    }else{
		    	logger.info("APPID:"+lnApplyMid.getAppId()+" WORK B 处理开始");
				  // 调用存储过程生成正式进件申请表
			      _mid_toreg = WorkUtils.getInstance().proc_mid_toreg(lnApplyMid.getAppId());//获得输出参数
			      if("1".equals( _mid_toreg )){  // 生成正式进件申请表成功
			    	  // 调用存储过程进行正式进件信息拆分
				      _split_result = WorkUtils.getInstance().proc_apply_split(lnApplyMid.getAppId()); // 获得输出参数
				      if("1".equals( _split_result )){  // 正式进件表拆分成功
				    	  //  push入3号消息队列
				    	  Jedis jedis = RedisUtil.getJedis();
				  		  jedis.lpush(RedisUtil.QUEUE3, JSON.toJSONString(lnApplyMid));
				  		  jedis.close();
				  		  logger.info("B任务处理成功-[申请编号AppId=" + lnApplyMid.getAppId()+",合同号PactNo="+lnApplyMid.getPactNo()+"]");
				      }else{
				    	  logger.info("B任务处理失败-正式进件表拆分失败-PKG_LN_APPLY.PROC_APPLY_SPLIT-[申请编号AppId=" + lnApplyMid.getAppId()+",合同号PactNo="+lnApplyMid.getPactNo()+"]");
				      }
			      }else{			    	  
			    	  logger.info("B任务处理失败-生成正式进件申请表失败-PKG_LN_APPLY.PROC_MID_TOREG-[申请编号AppId=" + lnApplyMid.getAppId()+",合同号PactNo="+lnApplyMid.getPactNo()+"]");
			      }
		    }
		} catch (Exception e) {
			SysExceptionBo sysExceptionBo = (SysExceptionBo) SourceTemplate.getSpringContextInstance().getBean("sysExceptionBo");
			sysExceptionBo.insertNewWorkExceptionLog(e, lnApplyMid.getAppId());
			e.printStackTrace();
		}
	}
	// 复写父类的方法
	public String toString(){
		String ret = "";
		if( lnApplyMid!=null ){
			ret = "业务ID："+ lnApplyMid.getAppId()+",合同编号："+lnApplyMid.getPactNo();
		}
		return ret;
	}
}
