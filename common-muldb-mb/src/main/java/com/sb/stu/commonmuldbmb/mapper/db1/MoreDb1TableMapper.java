package com.sb.stu.commonmuldbmb.mapper.db1;

import com.sb.stu.commonmuldbmb.model.db1.MoreDb1Table;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MoreDb1TableMapper {
	
    List<MoreDb1Table> selectByNumber(String number);
    
    List<MoreDb1Table> selectByNumberAndName(@Param("number") String number, @Param("name") String name);
    
}