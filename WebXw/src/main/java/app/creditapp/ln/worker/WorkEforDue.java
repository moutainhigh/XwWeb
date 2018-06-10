package app.creditapp.ln.worker;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import app.base.SourceTemplate;
import app.creditapp.ln.bo.LnDueBo;
import app.creditapp.ln.entity.LnDue;
import app.creditapp.ln.entity.Message;
import app.creditapp.pact.entity.LnPact;
import app.creditapp.redis.util.RedisUtil;
import app.util.syslog.bo.SysExceptionBo;

import com.alibaba.fastjson.JSON;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 6, 2016
 * @描述 业务处理E节点 生成借据信息
 */
public class WorkEforDue implements Runnable {
	Logger logger = Logger.getLogger(WorkEforDue.class);
	
	private LnPact lnPact;
	public WorkEforDue(LnPact lnPact) {
		this.lnPact = lnPact;
	}

	public void run() {
		try {
		    if( lnPact==null ){
		    	logger.error("E任务处理失败,接收到数据为空！");
		    }else{
		    	// 调用存储过程生成借据
//		    	try{
//		    		System.out.println("************等50ms");
//					Thread.sleep(50);
//				}
//					catch(InterruptedException e){
//				}
				logger.info("APPID:"+lnPact.getAppId()+" WORK E 处理开始");
				String _todue_sts = WorkUtils.getInstance().proc_pact_todue(lnPact.getPactId()); //获得输出参数
				if( "1".equals( _todue_sts ) ){  // 生成借据成功的才push入6号消息队列
					LnDue lnDue = this.getLnDueByPactId(lnPact.getPactId());  // 查询出借据信息
					// push入6号消息队列
			    	Jedis jedis = RedisUtil.getJedis();
			  		jedis.lpush(RedisUtil.QUEUE6, JSON.toJSONString(lnDue));
			  		jedis.close();
			  		logger.info("E任务处理成功-[合同编号为PactId=" + lnPact.getPactId()+",合同号为"+lnPact.getPactNo()+"]！");
				}else{
					logger.info("E任务处理-未生成贷款借据-PKG_LN_DUE.PROC_PACT_TODUE[合同编号为PactId=" + lnPact.getPactId()+",合同号为"+lnPact.getPactNo()+"]");
				}
		    }
		} catch (Exception e) {
			SysExceptionBo sysExceptionBo = (SysExceptionBo) SourceTemplate.getSpringContextInstance().getBean("sysExceptionBo");
			sysExceptionBo.insertNewWorkExceptionLog(e, lnPact.getAppId());
			e.printStackTrace();
		}
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jul 1, 2016
	 * @方法说明：根据业务合同编号查询借据信息
	 * @返回参数 LnDue
	 */
	public LnDue getLnDueByPactId(String pactId){
		LnDueBo lnDueBo = (LnDueBo) SourceTemplate.getSpringContextInstance().getBean("lnDueBo");
		LnDue lnDue = lnDueBo.getByPactId(pactId);
		return lnDue;
	}
	
	// 复写父类的方法
	public String toString(){
		String ret = "";
		if( lnPact != null ){
			ret = "业务ID："+ lnPact.getAppId()+",合同编号："+lnPact.getPactNo();
		}
		return ret;
	}
	
	public static void main(String[] args) throws Exception {
		Jedis jedis = RedisUtil.getJedis();
		//Jedis jedis = new Jedis("10.7.101.38",6379,0);
		Message m = null;
		try {
			while(true){
				System.out.println("len-->"+jedis.llen("wangtao5"));
				System.out.println("message-->" + jedis.brpop(0, RedisUtil.QUEUE5).get(1));
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
