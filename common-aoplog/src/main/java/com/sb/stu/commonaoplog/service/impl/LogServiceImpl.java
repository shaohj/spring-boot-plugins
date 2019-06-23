package com.sb.stu.commonaoplog.service.impl;

import com.sb.stu.commonaoplog.service.ILogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sb.stu.commonaoplog.model.*;

/**
 * 编  号：
 * 名  称：LogServiceImpl
 * 描  述：
 * 完成日期：2019/6/23 12:26
 * @author：felix.shao
 */
@Service
public class LogServiceImpl implements ILogService {

	private Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

	@Override
	public void set() {
		logger.info("***** set ***********");
	}

	@Override
	public void save() {
		logger.info("***** save ***********");
	}

	@Override
	public User save(User u) {
		logger.info("***** save user ***********");
		return u;
	}

	@Override
	public CustInfo save(CustInfo c) {
		logger.info("***** save custinfo ***********");
		return c;
	}

	@Override
	public Demand save(Demand d) {
		logger.info("***** save demand ***********,did={}", d.getDid());
		return d;
	}
	
}
