package com.sb.stu.commonmuldbhb.dao.db1;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sb.stu.commonmuldbhb.model.db1.MoreDb1Table;

/**
 * 
 * 编  号：<br/>
 * 名  称：MoreDb1TableRepository<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月24日 下午1:45:16<br/>
 * 编码作者：ShaoHj<br/>
 */
@Repository
public interface MoreDb1TableRepository extends JpaRepository<MoreDb1Table, Integer> {

	List<MoreDb1Table> findByNumber(String number);
	
	@Query(value="select db1.* from more_db1_table db1 where db1.number=?1",
			nativeQuery = true)
	List<MoreDb1Table> getByNumber(String number);
	
	MoreDb1Table findFirstByNumber(String number);

	@Query(value = "select db1.* from more_db1_table db1 where db1.number=?1 \n#pageable\n ",
			countQuery = "select count(0) from more_db1_table db1 where db1.number=?1",
			nativeQuery = true)
	Page<MoreDb1Table> findPageByNumber(String number, Pageable pageable);

	@Modifying
    @Query("delete from MoreDb1Table db1 where db1.id = :id")
    void deleteUser(@Param("id") int id);
    
}
