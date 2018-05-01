package com.sb.stu.commonjpahb.dao;

import java.util.List;

import com.sb.stu.common.BaseApplicationTest;
import com.sb.stu.commonjpahb.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.alibaba.fastjson.JSON;

public class UserRepositoryTest extends BaseApplicationTest {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);

	private @Autowired UserRepository userRepo;
	
	@Test
	public void findByCodeTest(){
		List<User> list = userRepo.findByCode("10086");
		
		Assert.assertNotNull(list); //没有数据时,list非null,长度为0
		logger.info("list={}", JSON.toJSON(list));
	}

}
