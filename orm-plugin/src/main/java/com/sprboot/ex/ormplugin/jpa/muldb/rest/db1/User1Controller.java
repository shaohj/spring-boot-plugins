package com.sprboot.ex.ormplugin.jpa.muldb.rest.db1;

import com.alibaba.fastjson.JSONObject;
import com.sprboot.ex.ormplugin.jpa.muldb.model.db1.User1;
import com.sprboot.ex.ormplugin.jpa.muldb.service.db1.User1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * 编  号：
 * 名  称：User1Controller
 * 描  述：
 * 完成日期：2020/4/4 18:31
 * @author：felix.shao
 */
@Slf4j
@Controller
@RequestMapping("/mulDb1")
public class User1Controller {

    @Autowired
    private User1Service user1Service;

    // http://localhost:8080/jpaMuldb/mulDb1/get/id/1
    /**
     * 返回text格式数据
     * @param id 主键id
     * @return 用户json字符串
     */
    @RequestMapping("/get/id/{id}")
    @ResponseBody
    public String getUserById(@PathVariable String id){
        log.info("getUserById, id = {}", id);
        Optional<User1> user = user1Service.findById(Integer.parseInt(id));
        if(user.isPresent()){
            return JSONObject.toJSONString(user.get());
        }
        return "unknown";
    }

    /**
     * 返回json格式数据
     * @param code 编号
     * @return 用户
     */
    @RequestMapping("/get/code/{code}")
    @ResponseBody
    public User1 getUserByNumber(@PathVariable String code){
        User1 user = user1Service.findByCode(code);
        return user;
    }

    @RequestMapping("/get/all/{page}/{size}")
    @ResponseBody
    public List<User1> getAllUserByPage(@PathVariable("page") int page, @PathVariable("size") int size){
        return user1Service.findAllUserByPage(page,size);
    }

    @RequestMapping("/update/{id}/{code}/{name}")
    @ResponseBody
    public User1 addUser(@PathVariable("id") int id,
                         @PathVariable("code") String code, @PathVariable("name") String name, boolean throwEx){
        User1 user = new User1();
        user.setId(id);
        user.setCode(code);
        user.setName(name);
        User1 userNew = null;
        try{
            user1Service.updateUser(user,throwEx);
        }catch (RuntimeException ex){
            log.error("", ex);
        }
        return userNew;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public void getUserById(@PathVariable("id")int id){
        user1Service.deleteUser(id);
    }

}
