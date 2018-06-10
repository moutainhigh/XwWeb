package app.util.toolkit;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @鐎电钖勭拠瀛樻 閸掑棝銆夌猾锟�
 * @娣囶喗鏁肩拠瀛樻
 */
public class Ipage implements Serializable {

	private static final long serialVersionUID = -3937515284908171092L;
	
	private int pageNo = 1;// 瑜版挸澧犳い锟�
	private int pageSum = 1;// 閹銆夐弫锟�
	private int pageSize;// 濮ｅ繘銆夐弰鍓с仛鐠佹澘缍嶉弫锟�
	private int pageCounts = 0;// 閹槒顔囪ぐ鏇熸殶
	private int startRow;	//瀵拷婵顢�	add by hxf
	private int endNum;		//缂佹挻娼悰锟�	add by hxf
	private boolean hasNext = false;// 閺勵垰鎯侀張澶夌瑓娑擄拷妞わ拷
	private boolean hasPrevious = false;// 閺勵垰鎯侀張澶夌瑐娑擄拷妞わ拷
	private Object result;// 鐠佹澘缍嶉梿锟�
	private Map<String, Object> params = new HashMap<String, Object>();
	private String paramsStr="";
	/**
	 * 
	 * @閺傝纭剁拠瀛樻閿涙俺顔曠純顔煎棘閺侊拷
	 * @param name
	 * @param value
	 * @娣囶喗鏁肩拠瀛樻閿涳拷
	 */
	public void putParams(String name, Object value) {
		if(value!=null && !"".equals(value)){
			this.params.put(name, value);
			//閹风厧寮弫锟�
			if("".equals(paramsStr)){
				paramsStr+="?"+name+"="+value+"&";
			}else{
				paramsStr+=name+"="+value+"&";
			}
		}
	}
	/**
	 * 姒涙顓婚弸鍕拷锟�
	 */
	public Ipage(){
		this(15,1);
	}
	/**
	 * 閺嬪嫰锟斤拷  濮ｅ繘銆夐弰鍓с仛鐠佹澘缍嶉弫鐨僡geSize
	 */
	public Ipage(int pageSize) {
		this(pageSize, 1);
	}
	/**
	 * 閺嬪嫰锟斤拷 濮ｅ繘銆夐弰鍓с仛鐠佹澘缍嶉弫鐨僡geSize 瑜版挸澧犵粭鐞綼geNo妞わ拷
	 */
	public Ipage(int pageSize, int pageNo) {
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}
	/**
	 * 
	 * @閺傝纭剁拠瀛樻閿涙碍鐓＄拠銏犲毉閻ㄥ嫬鍨悰銊х枂缁岋拷
	 * @娣囶喗鏁肩拠瀛樻閿涳拷
	 */
	public void removeResult() {
		this.result = null;
	}
	/**
	 * 
	 * @閺傝纭剁拠瀛樻閿涙艾鍨垫慨瀣鐠佹澘缍嶉弫锟�
	 * @param obj
	 * @娣囶喗鏁肩拠瀛樻閿涳拷
	 */
	public void initPageCounts(Object obj) {
		this.initPageCounts(new Object[] { obj });
	}
	/**
	 * 
	 * @閺傝纭剁拠瀛樻閿涙艾鍨垫慨瀣鐠佹澘缍嶉弫锟�
	 * @param obj
	 * @娣囶喗鏁肩拠瀛樻閿涳拷
	 */
	public void initPageCounts(Integer[] obj) {
				
		if (obj == null || obj.length == 0) {
			return;
		}
		int counts = (Integer) obj[0];
//		System.out.println("uuu"+counts);
		this.setPageCounts(counts);
		if (counts <= 0) {
			this.setPageSum(1);
		} else {
			if (counts % this.pageSize != 0) {
				this.setPageSum((counts + this.pageSize) / this.pageSize);
			} else {
				this.setPageSum(counts / this.pageSize);
			}
		}
		if (this.pageNo < this.pageSum) {
			this.setHasNext(true);
		} else {
			this.setHasNext(false);
		}
		if (this.pageNo > 1 && this.pageSum > 1) {
			this.setHasPrevious(true);
		} else {
			this.setHasPrevious(false);
		}
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSum() {
		return pageSum;
	}

	public void setPageSum(int pageSum) {
		this.pageSum = pageSum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCounts() {
		return pageCounts;
	}

	public void setPageCounts(int pageCounts) {
		this.pageCounts = pageCounts;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public Object getResult() {
		return result;
	}

	@SuppressWarnings("unchecked")
	public void setResult(Object result) {
		this.result = result;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public String getParamsStr() {
		if(paramsStr!=null && paramsStr.endsWith("&")){
			return  paramsStr.substring(0,paramsStr.length()-1);
		}
		return paramsStr;
	}
	public void setParamsStr(String paramsStr) {
		this.paramsStr = paramsStr;
	}
	public int getEndNum() {
		return getStartRow() + this.pageSize ;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}	
	public int getStartRow() {
		if(this.getPageNo() == 0){
			return 1;
		}
		if(pageNo<0){
			pageNo = 0;
		}
		if(pageNo>pageSum){
			pageNo = pageSum;
		}
		return (this.getPageNo()-1) * this.getPageSize() + 1;
	}
}
