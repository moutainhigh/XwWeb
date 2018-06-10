package app.creditapp.redis.util;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;
/**
 * 连接和使用redis资源的工具类
 * @version 0.5
 */
public class RedisUtil {
	private static Logger logger = Logger.getLogger(RedisUtil.class);
    //Redis服务器IP
    private static String IP_ADDR;
    //Redis的端口号
    private static int PORT;
    //访问密码
    private static String PASSWORD;
    //可用连接实例的最大数目，默认值为8
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_TOTAL;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAITMILLIS;
    //超时时间
    private static int TIMEOUT;
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW;
    // 守护进程多长时间检查一次redis服务线程的状态
    public static long CHECK_TIME;
    // redis阻塞线程接收不到数据之后的超时时间，读取多长时间 默认配置5分钟
    public static int BRPOP_TIMEOUT;
    // 多长时间再次启动连接redis服务器获取数据，或者是轮询间隔 15秒
    public static long WHILE_WAIT;
    // 
    public static String QUEUE0;
    public static String QUEUE1;
    public static String QUEUE2;
    public static String QUEUE3;
    public static String QUEUE4;
    public static String QUEUE5;
    public static String QUEUE6;
    public static String QUEUE7;
    public static String QUEUE8;
    public static String QUEUE10;
    /**
     * redis过期时间,以秒为单位
     */
    public final static int EXRP_HOUR = 60*60;          //一小时
    public final static int EXRP_DAY = 60*60*24;        //一天
    public final static int EXRP_MONTH = 60*60*24*30;   //一个月
    private static JedisPool jedisPool = null;
    
    private static void initProperties(){
    	ResourceBundle prop = ResourceBundle.getBundle("redis");
	    IP_ADDR = prop.getString("redis.ip").trim();
		PORT = Integer.parseInt(prop.getString("redis.port").trim());
		PASSWORD = null;
		MAX_TOTAL = Integer.parseInt(prop.getString("redis.pool.maxTotal").trim());
		MAX_IDLE = Integer.parseInt(prop.getString("redis.pool.maxIdle").trim());
		MAX_WAITMILLIS = Integer.parseInt(prop.getString("redis.pool.maxWaitMillis").trim());
		TIMEOUT = Integer.parseInt(prop.getString("redis.pool.timeOut").trim());
		TEST_ON_BORROW = Boolean.parseBoolean(prop.getString("redis.pool.testOnBorrow").trim());
		CHECK_TIME =  Long.parseLong(prop.getString("redis.thread.checkTime").trim());
		BRPOP_TIMEOUT =  Integer.parseInt(prop.getString("redis.brpop.timeOut").trim());
		WHILE_WAIT =  Integer.parseInt(prop.getString("redis.while.wait").trim());
		QUEUE0 = prop.getString("redis.queue0").trim();
		QUEUE1 = prop.getString("redis.queue1").trim();
		QUEUE2 = prop.getString("redis.queue2").trim();
		QUEUE3 = prop.getString("redis.queue3").trim();
		QUEUE4 = prop.getString("redis.queue4").trim();
		QUEUE5 = prop.getString("redis.queue5").trim();
		QUEUE6 = prop.getString("redis.queue6").trim();
		QUEUE7 = prop.getString("redis.queue7").trim();
		QUEUE8 = prop.getString("redis.queue8").trim();
		QUEUE10 = prop.getString("redis.queue10").trim();
    }

    /**
     * 初始化Redis连接池
     */
    private static void initialPool(){
    	logger.info("Resis连接池初始化......");
        try {
        	initProperties();
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_TOTAL);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAITMILLIS);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, IP_ADDR, PORT, TIMEOUT);
        } catch (Exception e) {
        	logger.error("First create JedisPool error : "+e);
            try{
                //如果第一个IP异常，则访问第二个IP
                JedisPoolConfig config = new JedisPoolConfig();
                config.setMaxTotal(MAX_TOTAL);
                config.setMaxIdle(MAX_IDLE);
                config.setMaxWaitMillis(MAX_WAITMILLIS);
                config.setTestOnBorrow(TEST_ON_BORROW);
                jedisPool = new JedisPool(config, IP_ADDR, PORT, TIMEOUT);
            }catch(Exception e2){
            	logger.error("Second create JedisPool error : "+e2);
            	e2.printStackTrace();
            }
        }
    }
    
    // 内部类实现单利模式
//    private static class JedisPoolHolder {
//        private static final JedisPool jedisPool = initialPool() ;
//    }

//    public static JedisPool getJedisPool() { 
//        return JedisPoolHolder.jedisPool ;
//    }
    
//    /**
//     * 释放jedis资源
//     * @param jedis
//     */
//    public static void returnResource(final Jedis jedis) {
//        if (jedis != null ) {
//        	JedisPoolHolder.jedisPool.returnResource(jedis);
//        }
//    }
    
//    public synchronized static Jedis getJedis() {
//      Jedis jedis = null;
//      try {
//           jedis = JedisPoolHolder.jedisPool.getResource();
//      } catch (Exception e) {
//      	e.printStackTrace();
//          //logger.error("Get jedis error : "+e);
//      }finally{
//          returnResource(jedis);
//      }
//      return jedis;
//  }
    /**
     * 同步获取Jedis实例
     */
    public synchronized static Jedis getThreadJedis() {
    	Jedis jedis = new Jedis(IP_ADDR, PORT, TIMEOUT);
        return jedis;
    }
    
    
    
    /**
     * 在多线程环境同步初始化
     */
    public static synchronized void poolInit() {
        if (jedisPool == null) {
            initialPool();
        }
    }

    /**
     * 同步获取Jedis实例
     * @return Jedis
     */
    public synchronized static Jedis getJedis() {
//        if (jedisPool == null) {
//        	initialPool();
//        }
//        boolean broken = false;
//        Jedis jedis = null;
//        try {
//            if (jedisPool != null) {
//                jedis = jedisPool.getResource();
//                System.out.println("jedis链接创建成功.....");
//            }
//        } catch (JedisException e) {
//        	broken = handleJedisException(e);
//        	e.printStackTrace();
//            throw e;
//            //logger.error("Get jedis error : "+e);
//        }finally{
//        	 closeResource(jedis, broken);
//        }
//        return jedis;
    	
    	Jedis jedis = new Jedis(IP_ADDR, PORT, TIMEOUT);
    	return jedis;
    }
    
    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null && jedisPool !=null) {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 设置 String
     * @param key
     * @param value
     */
    public static void setString(String key ,String value){
        try {
            value = (value==null) ? "" : value;
            getJedis().set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置 过期时间
     * @param key
     * @param seconds 以秒为单位
     * @param value
     */
    public static void setString(String key ,int seconds,String value){
        try {
        	value = (value==null) ? "" : value;
            getJedis().setex(key, seconds, value);
        } catch (Exception e) {
        	 e.printStackTrace();
        }
    }

    /**
     * 获取String值
     * @param key
     * @return value
     */
    public static String getString(String key){
        if(getJedis() == null || !getJedis().exists(key)){
            return null;
        }
        return getJedis().get(key);
    }
    
    /**
     * Handle jedisException, write log and return whether the connection is broken.
     */
    public static boolean handleJedisException(JedisException jedisException) {
        if (jedisException instanceof JedisConnectionException) {
        	System.err.println("Redis connection lost.");
        } else if (jedisException instanceof JedisDataException) {
            if ((jedisException.getMessage() != null) && (jedisException.getMessage().indexOf("READONLY") != -1)) {
            	System.err.println("Redis connection are read-only slave.");
            } else {
                // dataException, isBroken=false
                return false;
            }
        } else {
        	System.err.println("Jedis exception happen.");
        }
        return true;
    }
    /**
     * Return jedis connection to the pool, call different return methods depends on the conectionBroken status.
     */
    public static void closeResource(Jedis jedis, boolean conectionBroken) {
        try {
            if (conectionBroken) {
                jedisPool.returnBrokenResource(jedis);
            } else {
                jedisPool.returnResource(jedis);
            }
        } catch (Exception e) {
        	System.err.println("return back jedis failed, will fore close the jedis.");
            jedis.disconnect();
        }
    }
}