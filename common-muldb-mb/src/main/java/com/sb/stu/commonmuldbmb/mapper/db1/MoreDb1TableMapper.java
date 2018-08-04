package com.sb.stu.commonmuldbmb.mapper.db1;

import com.sb.stu.commonmuldbmb.model.db1.MoreDb1Table;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 编  号：
 * 名  称：MoreDb1TableMapper
 * 描  述：
 * 完成日期：2018/8/4 15:40
 * @author：felix.shao
 */
public interface MoreDb1TableMapper {
	
    List<MoreDb1Table> selectByNumber(String number);
    
    List<MoreDb1Table> selectByNumberAndName(@Param("number") String number, @Param("name") String name);
    
}