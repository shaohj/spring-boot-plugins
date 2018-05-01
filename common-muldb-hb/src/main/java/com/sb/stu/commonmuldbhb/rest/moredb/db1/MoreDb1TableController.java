package com.sb.stu.commonmuldbhb.rest.moredb.db1;

import com.alibaba.fastjson.JSONObject;
import com.sb.stu.commonmuldbhb.model.db1.MoreDb1Table;
import com.sb.stu.commonmuldbhb.service.moredb.db1.IMoreDb1TableService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/moreDsDb1")
public class MoreDb1TableController {

	private static final Logger logger = LoggerFactory.getLogger(MoreDb1TableController.class);

    @Autowired
    private IMoreDb1TableService userService;

    // http://localhost:8080/muldb_hb/rest/moreDsDb1/get/id/1
    /**
     * 返回text格式数据
     * @param id 主键id
     * @return 用户json字符串
     */
    @RequestMapping("/get/id/{id}")
    @ResponseBody
    public String getUserById(@PathVariable("id")String id){
        logger.info("request /moreDsDb1/get/id/{id}, parameter is "+id);
        Optional<MoreDb1Table> user = userService.findById(Integer.parseInt(id));
        if(user.isPresent()){
            return JSONObject.toJSONString(user.get());
        }
        return "unknown";
    }

    /**
     * 返回json格式数据
     * @param number 编号
     * @return 用户
     */
    @RequestMapping("/get/number/{number}")
    @ResponseBody
    public MoreDb1Table getUserByNumber(@PathVariable("number")String number){
        MoreDb1Table user = userService.findByNumber(number);
        return user;
    }

    @RequestMapping("/get/all/{page}/{size}")
    @ResponseBody
    public List<MoreDb1Table> getAllUserByPage(@PathVariable("page")int page,@PathVariable("size")int size){
        return this.userService.findAllUserByPage(page,size);
    }

    @RequestMapping("/update/{id}/{number}/{name}")
    @ResponseBody
    public MoreDb1Table addUser(@PathVariable("id")int id, @PathVariable("number")String number, @PathVariable("name")String name,boolean throwEx){
        MoreDb1Table user = new MoreDb1Table();
        user.setId(id);
        user.setNumber(number);
        user.setName(name);
        MoreDb1Table userNew = null;
        try{
            userService.updateUser(user,throwEx);
        }catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }
        return userNew;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public void getUserById(@PathVariable("id")int id){
        this.userService.deleteUser(id);
    }

}
