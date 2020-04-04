package com.sprboot.plugin.util.validator;

import com.sprboot.plugin.util.validator.bean.LoginBaseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 编  号：
 * 名  称：HbValidatorUtilTest
 * 描  述：
 * 完成日期：2020/4/4 19:34
 * @author：felix.shao
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
            log.info("\n--> 验证成功");
        } catch (IllegalArgumentException e){
            log.error("\n--> 验证失败，msg={}", e.getMessage());
        }
    }

    @Test
    public void isValidWx(){
        LoginBaseVo vo = new LoginBaseVo();
        vo.setSource("2"); //wx用户
        try{
            vo.isSuccessLoginCheck();
            log.info("\n--> 验证成功");
        } catch (IllegalArgumentException e){
            log.error("\n--> 验证失败，msg={}", e.getMessage());
        }
    }

    @Test
    public void isValidVisitor(){
        LoginBaseVo vo = new LoginBaseVo();
        vo.setSource("3"); //Visitor用户
        try{
            vo.isSuccessLoginCheck();
            log.info("\n--> 验证成功");
        } catch (IllegalArgumentException e){
            log.error("\n--> 验证失败，msg={}", e.getMessage());
        }
    }

}
