package com.sprboot.ex.ormplugin.jpa.single.dao;

import com.alibaba.fastjson.JSON;
import com.sprboot.ex.ormplugin.jpa.single.common.BaseApplicationTest;
import com.sprboot.ex.ormplugin.jpa.single.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class UserRepositoryTest extends BaseApplicationTest {
	
	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void findByCodeTest(){
		List<User> list = userRepo.findByCode("10000");

		//没有数据时,list非null,长度为0
		Assert.assertNotNull(list);
		log.info("\n-->list={}", JSON.toJSON(list));
	}

}
