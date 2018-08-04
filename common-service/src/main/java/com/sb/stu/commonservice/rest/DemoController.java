package com.sb.stu.commonservice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 编  号：
 * 名  称：DemoController
 * 描  述：
 * 完成日期：2018/8/4 15:32
 * @author：felix.shao
 */
@RestController
public class DemoController {

    /**
     * http://localhost:8080/comserv/rest/hello
     * @return
     */
    @GetMapping(path="hello")
    public Object getUser(){
        return "Hello,World！";
    }

}
