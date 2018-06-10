package app.util.message.conversion.entity;

public class TransferResult {
	private String transferId;
	private boolean isWorkSuccess;
	private String getRebackColumn;
	private String getRebackValue;
	private int errorDataNumber;
	
	
	public String getGetRebackValue() {
		return getRebackValue;
	}
	public void setGetRebackValue(String getRebackValue) {
		this.getRebackValue = getRebackValue;
	}
	public String getTransferId() {
		return transferId;
	}
	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}
	public int getErrorDataNumber() {
		return errorDataNumber;
	}
	public void setErrorDataNumber(int errorDataNumber) {
		this.errorDataNumber = errorDataNumber;
	}
	public boolean isWorkSuccess() {
		return isWorkSuccess;
	}
	public void setWorkSuccess(boolean isWorkSuccess) {
		this.isWorkSuccess = isWorkSuccess;
	}
	public String getGetRebackColumn() {
		return getRebackColumn;
	}
	public void setGetRebackColumn(String getRebackColumn) {
		this.getRebackColumn = getRebackColumn;
	}
	
}
