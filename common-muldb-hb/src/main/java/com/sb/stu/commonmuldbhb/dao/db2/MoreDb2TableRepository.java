package com.sb.stu.commonmuldbhb.dao.db2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sb.stu.commonmuldbhb.model.db2.MoreDb2Table;

/**
 * 
 * 编  号：<br/>
 * 名  称：MoreDb2TableRepository<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月24日 下午1:45:23<br/>
 * 编码作者：ShaoHj<br/>
 */
@Repository
public interface MoreDb2TableRepository extends JpaRepository<MoreDb2Table, Integer> {

	MoreDb2Table findByNumber(String number);

    @Modifying
    @Query("delete from MoreDb2Table db2 where db2.id = :id")
    void deleteOrder(@Param("id") int id);
    
}
