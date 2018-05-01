package com.sb.stu.commonmuldbhb.service.moredb.db2;

import com.sb.stu.commonmuldbhb.model.db2.MoreDb2Table;

import java.util.Optional;

/**
 * 
 * 编  号：<br/>
 * 名  称：OrderService<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月24日 下午1:46:11<br/>
 * 编码作者：ShaoHj<br/>
 */
public interface IMoreDb2TableService {

    Optional<MoreDb2Table> findById(int id);

    MoreDb2Table updateOrder(MoreDb2Table order, boolean throwEx);

}
