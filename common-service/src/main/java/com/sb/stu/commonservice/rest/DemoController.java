package com.sb.stu.commonservice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
