package com.sprboot.ex.ormplugin.jpa.muldb.rest.db2;

import com.alibaba.fastjson.JSONObject;
import com.sprboot.ex.ormplugin.jpa.muldb.model.db2.User2;
import com.sprboot.ex.ormplugin.jpa.muldb.service.db2.User2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * 
 * 编  号：<br/>
 * 名  称：User2Controller<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月24日 下午1:45:50<br/>
 * 编码作者：ShaoHj<br/>
 */
@Slf4j
@Controller
@RequestMapping("/mulDb2")
public class User2Controller {

    @Autowired
    private User2Service user2Service;

    //http://localhost:8080/jpaMuldb/mulDb2/get/id/1
    /**
     * 返回text格式数据
     * @param id 主键id
     * @return 用户json字符串
     */
    @RequestMapping("/get/id/{id}")
    @ResponseBody
    public String getUserById(@PathVariable("id")String id){
        log.info("getUserById, id = {}", id);
        Optional<User2> order = user2Service.findById(Integer.parseInt(id));
        if(order.isPresent()){
            return JSONObject.toJSONString(order.get());
        }
        return "unknown";
    }

}
