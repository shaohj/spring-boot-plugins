package com.sprboot.plugin.aop.service;

import com.sprboot.plugin.aop.bean.*;
import com.sprboot.plugin.aop.bean.CustInfo;
import com.sprboot.plugin.aop.bean.Demand;
import com.sprboot.plugin.aop.bean.User;

/**
 * 编  号：
 * 名  称：ILogService
 * 描  述：
 * 完成日期：2018/8/4 13:25
 * @author：felix.shao
 */
public interface ILogService {

	/**
	 * set操作测试
	 * @return
	 * @author felix.shao
	 * @since 2018/8/4 13:10
	 */
	public void set();

	/**
	 * save操作测试
	 * @return
	 * @author felix.shao
	 * @since 2018/8/4 13:10
	 */
	public void save();

	/**
	 * save操作测试
	 * @param u :
	 * @return
	 * @author felix.shao
	 * @since 2018/8/4 13:10
	 */
	public User save(User u);

	/**
	 * save操作测试
	 * @param c :
	 * @return
	 * @author felix.shao
	 * @since 2018/8/4 13:10
	 */
	public CustInfo save(CustInfo c);

	/**
	 * save操作测试
	 * @param d :
	 * @return
	 * @author felix.shao
	 * @since 2018/8/4 13:11
	 */
	public Demand save(Demand d);
	
}
