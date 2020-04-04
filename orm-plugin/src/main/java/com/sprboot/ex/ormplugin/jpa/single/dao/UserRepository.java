package com.sprboot.ex.ormplugin.jpa.single.dao;

import com.sprboot.ex.ormplugin.jpa.single.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 编  号：
 * 名  称：UserRepository
 * 描  述：
 * 完成日期：2020/4/4 18:00
 * @author：felix.shao
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
