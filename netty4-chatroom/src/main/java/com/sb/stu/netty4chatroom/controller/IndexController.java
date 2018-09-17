package com.sb.stu.netty4chatroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 编  号：
 * 名  称：IndexController
 * 描  述：
 * 完成日期：2018/09/17 20:10
 *
 * @author：felix.shao
 */
@Controller
public class IndexController {

    /**
     * 返回至WebSocket首页视图
     * @return
     * @author felix.shao
     * @since 2018/9/17 20:10
     */
    @RequestMapping("/")
    public String WebsocketChatClient(){
        return "/WebsocketChatClient";
    }

}
