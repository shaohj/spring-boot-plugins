package com.sprboot.ex.ormplugin.jpa.single.dao;

import com.alibaba.fastjson.JSON;
import com.sprboot.ex.ormplugin.jpa.single.common.BaseApplicationTest;
import com.sprboot.ex.ormplugin.jpa.single.entity.UserEntity;
import com.sprboot.ex.ormplugin.jpa.single.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

@Slf4j
public class UserRepositoryImplTest extends BaseApplicationTest {

	@Autowired
	private UserRepositoryImpl userRepoImpl;
	
	@Test
	public void getPageAllTest(){
		Sort sort = new Sort(Sort.Direction.ASC, "id");
		Pageable pageAble = new PageRequest(0, 10, sort);
		PageImpl<List<Map<String , Object>>> page = userRepoImpl.getPageAll(pageAble);

		log.info("\n-->page={}", JSON.toJSON(page));
	}
	
	@Test
	public void getAllTest(){
		List<Map<String , Object>> rows = userRepoImpl.getAll();
		log.info("\n-->list.size={}", rows.size());
		log.info("\n-->list={}", JSON.toJSON(rows));
	}
	
	//测试失败,可能这种方式只支持返回配置了@Entity的实体类
	@Test
	public void getAllBy1Test(){
		List<Map<String , Object>> rows = userRepoImpl.getAllBy1();
		log.info("\n-->list.size={}", rows.size());
		log.info("\n-->list={}", JSON.toJSON(rows));
	}
	
	//测试失败,可能这种方式只支持返回配置了@Entity的实体类
	@Test
	public void getAllBy2Test(){
		List<UserEntity> rows = userRepoImpl.getAllBy2();
		log.info("\n-->list.size={}", rows.size());
		log.info("\n-->list={}", JSON.toJSON(rows));
	}
	
	@Test
	public void getAllBy3Test(){
		List<User> rows = userRepoImpl.getAllBy3();

		log.info("\n-->list={}", JSON.toJSON(rows));
	}
	
}
