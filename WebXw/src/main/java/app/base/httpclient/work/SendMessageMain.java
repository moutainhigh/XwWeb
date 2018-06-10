package app.base.httpclient.work;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.dhcc.workflow.api.WorkflowException;

import app.base.ServiceException;
import app.base.httpclient.entity.SendMessageEntity;
import app.base.httpclient.entity.SendMessageType;
import app.creditapp.aft.entity.aftMessage.PasSubTypeEntity;
import app.oscache.CachecodeUtil;
import app.oscache.MBaseCache;

public class SendMessageMain {
	//private static String httpUrl = getWsHttpUrl();
//	private static String httpUrl = "http://10.7.101.38:8088/websocket//websocket/pushMessage.ws";
	private static String httpUrl = null;
	private static String getWsHttpUrl(){
		if(httpUrl == null){
			String pushMessageServerPath = (String) MBaseCache.getCache().getBeanCache("pushMessageServerPath", CachecodeUtil.SECURITY);
			String pushMessageRequestPath = (String) MBaseCache.getCache().getBeanCache("pushMessageRequestPath", CachecodeUtil.SECURITY);
			httpUrl =  "http://"+pushMessageServerPath+pushMessageRequestPath;
		}
		return httpUrl;
	}
	
	public static void sendMessage(String title,String content)throws ClientProtocolException, IOException{
		sendMessage(new SendMessageEntity(SendMessageType.OTHER,title,content,PasSubTypeEntity.OtherWork));
	}
	
	public static void sendMessage(String title,String content,PasSubTypeEntity typeEntity)throws ClientProtocolException, IOException{
		sendMessage(new SendMessageEntity(SendMessageType.MESSAGE,title,content,typeEntity));
	}
	
	public static void sendMessage(SendMessageEntity sendMessageEntity) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(getWsHttpUrl());
	    List<NameValuePair> nvps = createNameValuePairList(sendMessageEntity);
	    CloseableHttpResponse response2 = null;
	    try {
	    	 UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, "GBK");
	 	    httpPost.setEntity(formEntity);
	 	    response2 = httpclient.execute(httpPost);
	        //System.out.println(response2.getStatusLine());
	        HttpEntity entity2 = response2.getEntity();
	        EntityUtils.consume(entity2);
	    } catch(WorkflowException e){

		}catch(Exception e2){
			System.out.println("连接推送服务器超时");
		}finally {
			if(response2!=null){
				response2.close();
			}
	    }
	}
	
	private static List<NameValuePair> createNameValuePairList(SendMessageEntity sendMessageEntity){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	    nvps.add(new BasicNameValuePair("sendType", sendMessageEntity.getSendType().getValue()));
	    nvps.add(new BasicNameValuePair("title", sendMessageEntity.getTitle()));
	    nvps.add(new BasicNameValuePair("content", sendMessageEntity.getContet()));
	    nvps.add(new BasicNameValuePair("messageType", sendMessageEntity.getPasSubTypeEntity().getBigTypeNo()));
	    nvps.add(new BasicNameValuePair("groupNo", sendMessageEntity.getGroupNo()));
	    if(sendMessageEntity.getReciveUserNos()!=null && sendMessageEntity.getReciveUserNos().length >0){
	    	String reciveUserNoStr = "";
	    	for(String userNo:sendMessageEntity.getReciveUserNos()){
	    		reciveUserNoStr = reciveUserNoStr + userNo + "#";
	    	}
		    nvps.add(new BasicNameValuePair("reciveUserNos", reciveUserNoStr));
	    }
	    return nvps;
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		SendMessageMain.sendMessage(new SendMessageEntity(SendMessageType.MESSAGE,"推送消息","成功推送了一条消息","8888",new String[]{"0000","2002"},PasSubTypeEntity.RewFundMessage));
	}
}
