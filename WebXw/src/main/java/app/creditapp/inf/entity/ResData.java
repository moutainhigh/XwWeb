package app.creditapp.inf.entity;


/**
 * Title: AllocateReg.java Description:
 * 
 * @version：1.0
 **/
public class ResData{

	private String status;// 状态1:成功 -1:失败
	private String transID;// 生成的拨款主ID status为1时必填
	private String errorCopeType;// 错误类型0:系统错误1:主信息参数错误2:明细信息参数错误
	private String id;// 入参时候ID值，支持批量操作时候定位具体记录
	private String errorCode;// 错误码

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the transID
	 */
	public String getTransID() {
		return transID;
	}

	/**
	 * @param transID
	 *            the transID to set
	 */
	public void setTransID(String transID) {
		this.transID = transID;
	}

	/**
	 * @return the errorCopeType
	 */
	public String getErrorCopeType() {
		return errorCopeType;
	}

	/**
	 * @param errorCopeType
	 *            the errorCopeType to set
	 */
	public void setErrorCopeType(String errorCopeType) {
		this.errorCopeType = errorCopeType;
	}

	/**
	 * @return the errorMsgDTO
	 */
	// public ErrorMsgDTO getErrorMsgDTO() {
	// return errorMsgDTO;
	// }
	// /**
	// * @param errorMsgDTO the errorMsgDTO to set
	// */
	// public void setErrorMsgDTO(ErrorMsgDTO errorMsgDTO) {
	// this.errorMsgDTO = errorMsgDTO;
	// }
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}