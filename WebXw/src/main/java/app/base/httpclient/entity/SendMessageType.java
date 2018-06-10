package app.base.httpclient.entity;

public enum SendMessageType {
	MESSAGE("message"),OTHER("other");
	
	private String value;

	private SendMessageType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
