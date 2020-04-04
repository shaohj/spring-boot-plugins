package com.sprboot.plugin.util.validator;

import com.sprboot.plugin.util.validator.bean.LoginBaseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 名  称：<br/>
 * 描  述：<br/>
 * 完成日期： <br/>
 * 编码作者：<br/>
 */
@Slf4j
public class HbValidatorUtilTest {

    @Test
    public void isValidApp(){
        LoginBaseVo vo = new LoginBaseVo();
        vo.setSource("1"); //app用户
        vo.setUserId(100); //用户id
        try{
            vo.isSuccessLoginCheck();
            log.info(">>>>>>>>验证成功");
        } catch (IllegalArgumentException e){
            log.error(">>>>>>>>验证失败，msg={}", e.getMessage());
        }
    }

    @Test
    public void isValidWx(){
        LoginBaseVo vo = new LoginBaseVo();
        vo.setSource("2"); //wx用户
        try{
            vo.isSuccessLoginCheck();
            log.info(">>>>>>>>验证成功");
        } catch (IllegalArgumentException e){
            log.error(">>>>>>>>验证失败，msg={}", e.getMessage());
        }
    }

    @Test
    public void isValidVisitor(){
        LoginBaseVo vo = new LoginBaseVo();
        vo.setSource("3"); //Visitor用户
        try{
            vo.isSuccessLoginCheck();
            log.info(">>>>>>>>验证成功");
        } catch (IllegalArgumentException e){
            log.error(">>>>>>>>验证失败，msg={}", e.getMessage());
        }
    }

}
