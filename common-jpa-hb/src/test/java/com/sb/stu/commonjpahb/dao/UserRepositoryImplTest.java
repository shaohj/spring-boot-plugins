package com.sb.stu.commonjpahb.dao;

import java.util.List;
import java.util.Map;

import com.sb.stu.common.BaseApplicationTest;
import com.sb.stu.commonjpahb.entity.UserEntity;
import com.sb.stu.commonjpahb.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.alibaba.fastjson.JSON;

public class UserRepositoryImplTest extends BaseApplicationTest {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);

	private @Autowired UserRepositoryImpl userRepoImpl;
	
	@Test
	public void getPageAllTest(){
		Sort sort = new Sort(Sort.Direction.ASC, "id");
		Pageable pageAble = new PageRequest(0, 10, sort);
		PageImpl<List<Map<String , Object>>> page = userRepoImpl.getPageAll(pageAble);

		logger.info("page={}", JSON.toJSON(page));
	}
	
	@Test
	public void getAllTest(){
		List<Map<String , Object>> rows = userRepoImpl.getAll();
		logger.info("list.size={}", rows.size());
		logger.info("list={}", JSON.toJSON(rows));
	}
	
	//测试失败,可能这种方式只支持返回配置了@Entity的实体类
	@Test
	public void getAllBy1Test(){
		List<Map<String , Object>> rows = userRepoImpl.getAllBy1();
		logger.info("list.size={}", rows.size());
		logger.info("list={}", JSON.toJSON(rows));
	}
	
	//测试失败,可能这种方式只支持返回配置了@Entity的实体类
	@Test
	public void getAllBy2Test(){
		List<UserEntity> rows = userRepoImpl.getAllBy2();
		logger.info("list.size={}", rows.size());
		logger.info("list={}", JSON.toJSON(rows));
	}
	
	@Test
	public void getAllBy3Test(){
		List<User> rows = userRepoImpl.getAllBy3();

		logger.info("list={}", JSON.toJSON(rows));
	}
	
}
