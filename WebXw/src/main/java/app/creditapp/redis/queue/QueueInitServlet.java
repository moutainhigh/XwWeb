package app.creditapp.redis.queue;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import app.base.SourceTemplate;
import app.creditapp.ln.bo.LnApplyMidBo;
import app.creditapp.ln.bo.LnDueBo;
import app.creditapp.ln.entity.LnApplyMid;
import app.creditapp.ln.entity.LnDue;
import app.creditapp.pact.bo.LnPactBo;
import app.creditapp.pact.entity.LnPact;
import app.creditapp.redis.daemon.ThreadDaemon;
import app.creditapp.redis.daemon.ThreadFactory;
import app.creditapp.redis.util.RedisUtil;

import com.alibaba.fastjson.JSON;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 23, 2016
 * @描述 启动web容器时，同时启动业务处理监听器
 */
public class QueueInitServlet extends HttpServlet {
	Logger logger = Logger.getLogger(QueueInitServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public QueueInitServlet() {
	}

	public void init(ServletConfig servletconfig) throws ServletException {
		RedisUtil.poolInit(); // 初始化jedis连接池
		threadCreat();
//		toWork();
	}

	public void threadCreat(){
		// 拉起五个业务处理节点服务
		logger.info("初始化jedis连接.....");
		try {
			/** ********启动P节点服务线程 开始**************** */
			Thread.sleep(500);
			Thread threadP = ThreadFactory.initcreate(new TaskServerP()).getThread(); // 创建A服务节点
			logger.info("P业务处理节点服务线程启动成功! [线程id=" + threadP.getId() + ",线程name=" + threadP.getName()+"]");
			/** ********启动P节点服务线程 结束**************** */
			
			/** ********启动A节点服务线程 开始**************** */
			Thread.sleep(500);
			Thread threadA = ThreadFactory.initcreate(new TaskServerA()).getThread(); // 创建A服务节点
			logger.info("A业务处理节点服务线程启动成功! [线程id=" + threadA.getId() + ",线程name=" + threadA.getName()+"]");
			/** ********启动A节点服务线程 结束**************** */
			
			/** ********启动B节点服务线程 开始**************** */
			Thread.sleep(500);
			Thread threadB = ThreadFactory.initcreate(new TaskServerB()).getThread(); // 创建B服务线程
			logger.info("B业务处理节点服务线程启动成功! [线程id=" + threadB.getId() + ",线程name=" + threadB.getName()+"]");
			/** ********启动B节点服务线程 结束**************** */
			
			/** ********启动C节点服务线程 开始**************** */
			Thread.sleep(500);
			Thread threadC = ThreadFactory.initcreate(new TaskServerC()).getThread();// 创建C服务线程
			logger.info("C业务处理节点服务线程启动成功! [线程id=" + threadC.getId() + ",线程name=" + threadC.getName()+"]");
			/** ********启动C节点服务线程 结束**************** */
			
			/** ********启动D节点服务线程 开始**************** */
			Thread.sleep(500);
			Thread threadD = ThreadFactory.initcreate(new TaskServerD()).getThread();  // 创建D服务节点
			logger.info("D业务处理节点服务线程启动成功! [线程id=" + threadD.getId() + ",线程name=" + threadD.getName()+"]");
			/** ********启动D节点服务线程 结束**************** */
			
			/** ********启动E节点服务线程 开始**************** */
			Thread.sleep(500);
			Thread threadE = ThreadFactory.initcreate(new TaskServerE()).getThread();// 创建E服务线程
			logger.info("E业务处理节点服务线程启动成功! [线程id=" + threadE.getId() + ",线程name=" + threadE.getName()+"]");
			/** ********启动E节点服务线程 结束**************** */
			
			/** ********启动F节点服务线程 开始**************** */
			Thread.sleep(500);
			Thread threadF = ThreadFactory.initcreate(new TaskServerF()).getThread(); // 创建F服务线程
			logger.info("F业务处理节点服务线程启动成功！[线程id=" + threadF.getId() + ",线程name=" + threadF.getName()+"]");
			/** ********启动F节点服务线程 结束**************** */
			
			/** ********启动发送报文服务线程 开始7**************** */
			Thread.sleep(500);
			Thread threadMsg = ThreadFactory.initcreate(new SendMsgServer()).getThread();
			logger.info("发送报文节点服务线程启动成功(扣款)！[线程id=" + threadMsg.getId() + ",线程name=" + threadMsg.getName()+"]");
			/** ********启动发送报文服务线程  结束7**************** */
			
			/** ********启动放款发送报文服务线程 开始8**************** */
			Thread.sleep(500);
			Thread threadMsgGrant = ThreadFactory.initcreate(new SendMsgServerGrant()).getThread(); // 创建F服务线程
			logger.info("发送报文节点服务线程启动成功(放款)！[线程id=" + threadMsgGrant.getId() + ",线程name=" + threadMsgGrant.getName()+"]");
			/** ********启动放款发送报文服务线程  结束8**************** */
			
			/** ********启动扣款处理服务线程 开始10**************** */
			Thread.sleep(500);
			Thread threadMes1 = ThreadFactory.initcreate(new RepyMesServer()).getThread();
			logger.info("扣款处理1节点服务线程启动成功！[线程id=" + threadMes1.getId() + ",线程name=" + threadMes1.getName()+"]");
			/** ********启动扣款处理服务线程 结束10**************** */
			
			
			/** ********启动守护线程 开始**************** */
			ThreadDaemon td = new ThreadDaemon();
			Thread thread = new Thread(td);
			thread.start();
			logger.info("守护线程启动成功！[线程id=" + thread.getId() + ",线程name=" + thread.getName()+"]");
			/** ********启动守护线程 结束**************** */
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void toWork(){
		logger.info("寻找未完成请求...");
		
		LnApplyMidBo lnApplyMidBo = (LnApplyMidBo) SourceTemplate.getSpringContextInstance().getBean("lnApplyMidBo");
		LnPactBo lnPactBo = (LnPactBo) SourceTemplate.getSpringContextInstance().getBean("lnPactBo");
		LnDueBo lnDueBo = (LnDueBo) SourceTemplate.getSpringContextInstance().getBean("lnDueBo");
		//查询未完成WorkA的请求
		List<LnApplyMid> lnApplyMidListA = lnApplyMidBo.findListToWorkA();
		//查询未完成WorkB的请求
		List<LnApplyMid> lnApplyMidListB = lnApplyMidBo.findListToWorkB();
		//查询未完成WorkC的请求
		List<LnApplyMid> lnApplyMidListC = lnApplyMidBo.findListToWorkC();
		//查询未完成WorkD的请求
		List<LnApplyMid> lnApplyMidListD = lnApplyMidBo.findListToWorkD();
		//查询未完成WorkE的请求
		List<LnPact> lnPactListE = lnPactBo.findListToWorkE();
		//查询未完成WorkF的请求
		List<LnDue> lnDueListF = lnDueBo.findListToWorkF();
		Jedis jedis = RedisUtil.getJedis();
		//将查询出来的未完成请求push入1号消息队列
		for(LnApplyMid lnApplyMid:lnApplyMidListA){
			logger.info("正在处理A队列请求...");
			jedis.lpush(RedisUtil.QUEUE1, JSON.toJSONString(lnApplyMid));
		}
		//将查询出来的未完成请求push入2号消息队列
		for(LnApplyMid lnApplyMid:lnApplyMidListB){
			logger.info("正在处理B队列请求...");
			jedis.lpush(RedisUtil.QUEUE2, JSON.toJSONString(lnApplyMid));
		}
		//将查询出来的未完成请求push入3号消息队列
		for(LnApplyMid lnApplyMid:lnApplyMidListC){
			logger.info("正在处理C队列请求...");
			jedis.lpush(RedisUtil.QUEUE3, JSON.toJSONString(lnApplyMid));
		}
		//将查询出来的未完成请求push入4号消息队列
		for(LnApplyMid lnApplyMid:lnApplyMidListD){
			logger.info("正在处理D队列请求...");
			jedis.lpush(RedisUtil.QUEUE4, JSON.toJSONString(lnApplyMid));
		}
		//将查询出来的未完成请求push入5号消息队列
		for(LnPact lnPact:lnPactListE){
			logger.info("正在处理E队列请求...");
			jedis.lpush(RedisUtil.QUEUE5, JSON.toJSONString(lnPact));
		}
		//将查询出来的未完成请求push入6号消息队列
		for(LnDue lnDue:lnDueListF){
			logger.info("正在处理F队列请求...");
			jedis.lpush(RedisUtil.QUEUE6, JSON.toJSONString(lnDue));
		}
		logger.info("未完成请求已进入工作流，正在处理！");
	}
	
	public static void main(String[] args) throws Exception {
//		Jedis jedis = RedisUtil.getJedis();
//		Properties p = new Properties();
//	    String fileName = "/redis.properties"; // redis 统一配置文件
//		try {
//			InputStream in = RedisUtil.class.getResourceAsStream(fileName);//这里有人用new FileInputStream(fileName),不过这种方式找不到配置文件。有人说是在classes下，我调过了，不行。
//	        p.load(in);
//	        in.close();
//	        String redisIp = p.getProperty("redis.ip");
//	        int redisProt =  Integer.parseInt(p.getProperty("redis.port"));
//	        Jedis jedis = new Jedis(redisIp,redisProt);
//			/** ********启动A节点服务线程 开始**************** */
//			JedisPubSub queue1Listener = new Queue1Listener(); // 创建1号队列监听器
//			System.out.println("业务处理A节点服务线程正在启动......");
//			Thread.sleep(500);
//			Thread threadA = ThreadFactory.initcreate(new TaskServerA()).getThread(); // 创建A服务节点
//			System.out.println("业务处理A节点服务线程启动ok [线程id=" + threadA.getId() + ",线程name=" + threadA.getName()+"]");
//			/** ********启动A节点服务线程 结束**************** */
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
		RedisUtil.poolInit(); // 初始化jedis连接池
		QueueInitServlet qi = new QueueInitServlet();
		qi.threadCreat();
		qi.toWork();
	}
}
