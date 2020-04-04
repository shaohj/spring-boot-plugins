package com.sprboot.ex.ormplugin.jpa.muldb.dao.db2;

import com.sprboot.ex.ormplugin.jpa.muldb.model.db2.User2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 编  号：
 * 名  称：User2Repository
 * 描  述：
 * 完成日期：2020/4/4 18:22
 * @author：felix.shao
 */
@Repository
public interface User2Repository extends JpaRepository<User2, Integer> {


}
