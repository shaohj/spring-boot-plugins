package com.sprboot.ex.ormplugin.jpa.muldb.dao.db1;

import com.sprboot.ex.ormplugin.jpa.muldb.model.db1.User1;
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
 * 名  称：User1Repository
 * 描  述：
 * 完成日期：2020/4/4 18:22
 * @author：felix.shao
 */
@Repository
public interface User1Repository extends JpaRepository<User1, Integer> {

	List<User1> findByCode(String code);
	
	@Query(value="select db1.* from user db1 where db1.code=?1",
			nativeQuery = true)
	List<User1> getByCode(String code);
	
	User1 findFirstByCode(String code);

	@Query(value = "select db1.* from user db1 where db1.code=?1 \n#pageable\n ",
			countQuery = "select count(0) from user db1 where db1.code=?1",
			nativeQuery = true)
	Page<User1> findPageByCode(String number, Pageable pageable);

	@Modifying
    @Query("delete from User1 db1 where db1.id = :id")
    void deleteUser(@Param("id") int id);
    
}
