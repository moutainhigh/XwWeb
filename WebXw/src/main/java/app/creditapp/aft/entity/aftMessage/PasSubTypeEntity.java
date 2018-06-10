package app.creditapp.aft.entity.aftMessage;

import com.opensymphony.xwork2.conversion.TypeConversionException;

public enum PasSubTypeEntity {
	RewPactMessage(1,"1","101","业务消息提醒"),
	RewPactAlert(2,"2","201","业务风险预警"),
	RewProjMessage(3,"1","102","项目消息提醒"),
	RewProjAlert(4,"2","202","项目风险预警"),
	RewFundMessage(5,"1","103","资金消息提醒"),
	RewFundAlert(6,"2","203","资金消息预警"),
	ToApproveWork(7,"3","301","待审批业务"),
	CashNoEffectWork(8,"3","302","资金未生效处理"),
	RewRealMessage(9,"1","104","实时消息提醒"),
	RewRealAlert(10,"2","204","实时消息预警"),
	OtherWork(0,"0","000","其他事务");
	
	private int seqNo;
	private String subTypeNo;
	private String showName;
	private String bigTypeNo;
	
	private PasSubTypeEntity(int seqNo,String bigTypeNo,String subTypeNo,String showName){
		this.seqNo = seqNo;
		this.showName = showName;
		this.subTypeNo = subTypeNo;
	}
	
	public static PasSubTypeEntity getTypeNameByNo(String subTypeNo){
		for(PasSubTypeEntity type:values()){
			if(type.getSubTypeNo().equals(subTypeNo))return type;
		}
		throw new TypeConversionException("映射数据类型匹配不正确");
	}
	
	public static PasSubTypeEntity getTypeBySeqNo(int typeSeqNo){
		for(PasSubTypeEntity type:values()){
			if(type.getSeqNo() == typeSeqNo)return type;
		}
		throw new TypeConversionException("无法根据序号找到对应的类型");
	}
	
	public int getSeqNo() {
		return seqNo;
	}

	public String getSubTypeNo() {
		return subTypeNo;
	}

	public String getShowName() {
		return showName;
	}

	public String getBigTypeNo() {
		return bigTypeNo;
	}
}
