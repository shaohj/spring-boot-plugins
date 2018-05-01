package com.sb.stu.commonjpahb.rest;

import com.alibaba.fastjson.JSONObject;
import com.sb.stu.commonjpahb.model.User;
import com.sb.stu.commonjpahb.service.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * 
 * 编  号：<br/>
 * 名  称：MoreDb1TableController<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月24日 下午1:45:45<br/>
 * 编码作者：ShaoHj<br/>
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    /**
     * 返回text格式数据
     *   http://localhost:8080/jpademo_hb/rest/user/get/id/1
     * @param id 主键id
     * @return 用户json字符串
     */
    @RequestMapping("/get/id/{id}")
    @ResponseBody
    public String getUserById(@PathVariable("id")String id){
        logger.info("request /moreDsDb1/get/id/{id}, parameter is "+id);
        Optional<User> user = userService.findById(Integer.parseInt(id));
        if(user.isPresent()){
            return JSONObject.toJSONString(user.get());
        }
        return "unknown";
    }

    /**
     * 返回json格式数据
     *   http://localhost:8080/jpademo_hb/rest/user/get/code/10086
     * @param code 编号
     * @return 用户
     */
    @RequestMapping("/get/code/{code}")
    @ResponseBody
    public User getUserByNumber(@PathVariable String code){
        User user = userService.findByCode(code);
        return user;
    }

    /**
     *   http://localhost:8080/jpademo_hb/rest/user/get/all/0/10
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/get/all/{page}/{size}")
    @ResponseBody
    public Page<User> getAllUserByPage(@PathVariable("page")int page, @PathVariable("size")int size){
        return this.userService.findAllUserByPage(page,size);
    }

    /**
     * http://localhost:8080/jpademo_hb/rest/user/add/100/ls
     * @param code
     * @param name
     * @param throwEx
     * @return
     */
    @RequestMapping("/add/{code}/{name}")
    @ResponseBody
    public User addUser(@PathVariable String code, @PathVariable String name, boolean throwEx){
        User user = new User();
        user.setCode(code);
        user.setName(name);
        User userNew = null;
        try{
            userNew = userService.updateUser(user,throwEx);
        }catch (RuntimeException ex){
            logger.info("", ex);
        }
        return userNew;
    }

    /**
     * http://localhost:8080/jpademo_hb/rest/user/update/6/100/ww
     * @param id
     * @param code
     * @param name
     * @param throwEx
     * @return
     */
    @RequestMapping("/update/{id}/{code}/{name}")
    @ResponseBody
    public User updateUser(@PathVariable int id, @PathVariable String code, @PathVariable String name, boolean throwEx){
        User user = new User();
        user.setId(id);
        user.setCode(code);
        user.setName(name);
        User userNew = null;
        try{
            userNew = userService.updateUser(user,throwEx);
        }catch (RuntimeException ex){
            logger.info("", ex);
        }
        return userNew;
    }

    /**
     * http://localhost:8080/jpademo_hb/rest/user/deleteById/2
     * @param id
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public void deleteById(@PathVariable("id")int id){
        this.userService.deleteById(id);
    }

}
