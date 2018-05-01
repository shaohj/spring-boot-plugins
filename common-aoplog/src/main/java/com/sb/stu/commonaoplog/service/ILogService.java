package com.sb.stu.commonaoplog.service;

import com.sb.stu.commonaoplog.model.*;

public interface ILogService {

	public void set();
	
	public void save();
	
	public User save(User u);
	
	public CustInfo save(CustInfo c);
	
	public Demand save(Demand d);
	
}
