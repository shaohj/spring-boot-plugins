package com.sb.stu.commonjpahb.dao;

import java.util.List;

import com.sb.stu.commonjpahb.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * 编  号：<br/>
 * 名  称：UserRepository<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月24日 下午1:45:16<br/>
 * 编码作者：ShaoHj<br/>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByCode(String code);
	
	@Query(value="select u.* from user u where u.code=?1", nativeQuery = true)
	List<User> getByCode(String code);
	
	User findFirstByCode(String code);

	@Query(value = "select u.* from user u where u.number=?1 \n#pageable\n ",
			countQuery = "select count(0) from user u where u.number=?1",
			nativeQuery = true)
	Page<User> findPageByCode(String code, Pageable pageable);

	@Modifying
    @Query("delete from User u where u.id = :id")
    void deleteById(@Param("id") int id);
    
}
