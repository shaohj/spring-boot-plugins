package net.javamail;

/**
 * 
 * @param <T>
 */
public class ServiceResponse<T> {
	
	/** 默认成功code */
	public static final int SUCCESS = 0;
	
	/** 默认失败code */
	public static final int DEFAULT_FAIL = 100;
	
	/** 返回码 */
	private int errorNO;

	/** 返回信息 */
	private String errorMsg;

	/** 返回对象 */
	private T result;

	public ServiceResponse() {

	}
	
	public ServiceResponse(int errorNo) {
		this.errorNO = errorNo;
	}

	@SuppressWarnings("unchecked")
	public ServiceResponse(int errorNo, Object obj) {
		this.errorNO = errorNo;
		if(obj instanceof String){
			this.errorMsg = obj.toString();
		}else{
			this.result = (T)obj;
		}
	}

	public ServiceResponse(int errorNo, String errorMsg, T result) {
		this.errorNO = errorNo;
		this.errorMsg = errorMsg;
		this.result = result;
	}

	public int getErrorNO() {
		return errorNO;
	}

	public void setErrorNO(int errorNo) {
		this.errorNO = errorNo;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
