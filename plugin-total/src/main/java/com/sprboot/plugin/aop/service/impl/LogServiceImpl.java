package com.sprboot.plugin.aop.service.impl;

import com.sprboot.plugin.aop.bean.*;
import com.sprboot.plugin.aop.service.ILogService;
import com.sprboot.plugin.aop.bean.CustInfo;
import com.sprboot.plugin.aop.bean.Demand;
import com.sprboot.plugin.aop.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 编  号：
 * 名  称：LogServiceImpl
 * 描  述：
 * 完成日期：2019/6/23 12:26
 * @author：felix.shao
 */
@Slf4j
@Service
public class LogServiceImpl implements ILogService {

	@Override
	public void set() {
		log.info("***** set ***********");
	}

	@Override
	public void save() {
		log.info("***** save ***********");
	}

	@Override
	public User save(User u) {
		log.info("***** save user ***********");
		return u;
	}

	@Override
	public CustInfo save(CustInfo c) {
		log.info("***** save custinfo ***********");
		return c;
	}

	@Override
	public Demand save(Demand d) {
		log.info("***** save demand ***********,did={}", d.getDid());
		return d;
	}
	
}
