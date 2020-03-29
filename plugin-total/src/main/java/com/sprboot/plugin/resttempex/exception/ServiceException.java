package com.sprboot.plugin.resttempex.exception;

/**
 * 编  号：
 * 名  称：ServiceException
 * 描  述：service层异常
 *  <li>继承RuntimeException,可实现抛出该异常，使得事务回滚
 * 完成日期：2020/3/29 20:45
 * @author：felix.shao
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public static final int SUCCESS_CODE = 0;
	
	public static final String SUCCESS_MSG = "ok";
	
	public static final int DEFAULT_FAIL_CODE = -1;
	
	public static final String DEFAULT_FAIL_MSG = "default fail";
	
	/** 返回码 */
	private int code;
	
	/** 返回错信息 */
	private String message;
	
	public ServiceException(){
		
	}
	
	public ServiceException(String message){
		super(message);
		this.message =  message;
	}
	
	public ServiceException(int code, String message) {
		this(message);
		this.code = code;
	}
	
	public ServiceException(Throwable t){
		super(t);
	}
	
	public ServiceException(String message, Throwable t){
		super(message, t);
		this.message =  message;
	}
	
	public ServiceException(int code, String message, Throwable t){
		this(message, t);
		this.code =  code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public static ServiceException getDefaultFailException(){
		return new ServiceException(DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG);
	}
	
}
