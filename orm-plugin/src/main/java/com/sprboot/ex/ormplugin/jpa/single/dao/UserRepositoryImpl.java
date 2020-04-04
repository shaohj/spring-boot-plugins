package com.sprboot.ex.ormplugin.jpa.single.dao;

import com.sprboot.ex.ormplugin.jpa.single.entity.UserEntity;
import com.sprboot.ex.ormplugin.jpa.single.model.User;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * 编  号：
 * 名  称：UserRepositoryImpl
 * 描  述：返回map测试
 * 完成日期：2020/4/4 18:02
 * @author：felix.shao
 */
@Transactional  
@Repository  
public class UserRepositoryImpl {
	
	@PersistenceContext  
    EntityManager entityManager;  
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageImpl<List<Map<String, Object>>> getPageAll(Pageable pageable){  
		Query query = entityManager.createNativeQuery("select u.* from user u");
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
			.setMaxResults(pageable.getPageSize())
			.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		
		List rows = query.getResultList();  
		
		query = entityManager.createNativeQuery("select count(0) from user u");
		int total = Integer.parseInt(query.getSingleResult().toString());

		PageImpl pageImpl = new PageImpl<>(rows, pageable, total);
		
		return pageImpl;
	}  
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String, Object>> getAll(){  
	    Query query = entityManager.createNativeQuery("select id, code, name from user");
	    query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
	    List rows = query.getResultList();  
	    return rows;
	}  
	
	/**
	 * 资料说2.*版本才支持,没试2.*版本
	 * @Title: getAllBy1 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String, Object>> getAllBy1(){  
		Query query = entityManager.createNativeQuery("select id, code, name from user", java.util.Map.class);
		List rows = query.getResultList();  
		return rows;
	}  
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<UserEntity> getAllBy2(){
		Query query = entityManager.createNativeQuery("select id, code, name from user", UserEntity.class);
		List rows = query.getResultList();  
		return rows;
	}  
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<User> getAllBy3(){
		Query query = entityManager.createNativeQuery("select u.* from user u", User.class);
		List rows = query.getResultList();  
		return rows;
	}

}
