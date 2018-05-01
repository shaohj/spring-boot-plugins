package com.sb.stu.commonmuldbhb.rest.moredb.db2;

import com.alibaba.fastjson.JSONObject;
import com.sb.stu.commonmuldbhb.model.db2.MoreDb2Table;
import com.sb.stu.commonmuldbhb.service.moredb.db2.IMoreDb2TableService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * 
 * 编  号：<br/>
 * 名  称：MoreDb2TableController<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月24日 下午1:45:50<br/>
 * 编码作者：ShaoHj<br/>
 */
@Controller
@RequestMapping("/moreDsDb2")
public class MoreDb2TableController {

	private static final Logger logger = LoggerFactory.getLogger(MoreDb2TableController.class);

    @Autowired
    private IMoreDb2TableService mDb2TableServ;

    //http://localhost:8080/muldb_hb/rest/moreDsDb2/get/id/1
    /**
     * 返回text格式数据
     * @param id 主键id
     * @return 用户json字符串
     */
    @RequestMapping("/get/id/{id}")
    @ResponseBody
    public String getOrderById(@PathVariable("id")String id){
        logger.info("request /moreDsDb2/get/id/{id}, parameter is "+id);
        Optional<MoreDb2Table> order = mDb2TableServ.findById(Integer.parseInt(id));
        if(order.isPresent()){
            return JSONObject.toJSONString(order.get());
        }
        return "unknown";
    }


    @RequestMapping("/update/{id}/{number}/{price}")
    @ResponseBody
    public MoreDb2Table addUser(@PathVariable("id")int id, @PathVariable("number")String number, @PathVariable("price")double price,boolean throwEx){
        MoreDb2Table order = new MoreDb2Table();
        order.setId(id);
        order.setNumber(number);
        order.setPrice(price);
        MoreDb2Table orderNew = null;
        try{
            this.mDb2TableServ.updateOrder(order, throwEx);
        }catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }
        return orderNew;
    }
}
