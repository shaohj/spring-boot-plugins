package com.sb.stu.commonutil.util.hbvalid;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 名  称：<br/>
 * 描  述：<br/>
 * 完成日期： <br/>
 * 编码作者：<br/>
 */
public class HbValidatorUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginBaseVo.class);

    @Test
    public void isValidApp(){
        LoginBaseVo vo = new LoginBaseVo();
        vo.setSource("1"); //app用户
        vo.setUserId(100); //用户id
        try{
            vo.isSuccessLoginCheck();
            logger.info(">>>>>>>>验证成功");
        } catch (IllegalArgumentException e){
            logger.error(">>>>>>>>验证失败，msg={}", e.getMessage());
        }
    }

    @Test
    public void isValidWx(){
        LoginBaseVo vo = new LoginBaseVo();
        vo.setSource("2"); //wx用户
        try{
            vo.isSuccessLoginCheck();
            logger.info(">>>>>>>>验证成功");
        } catch (IllegalArgumentException e){
            logger.error(">>>>>>>>验证失败，msg={}", e.getMessage());
        }
    }

    @Test
    public void isValidVisitor(){
        LoginBaseVo vo = new LoginBaseVo();
        vo.setSource("3"); //Visitor用户
        try{
            vo.isSuccessLoginCheck();
            logger.info(">>>>>>>>验证成功");
        } catch (IllegalArgumentException e){
            logger.error(">>>>>>>>验证失败，msg={}", e.getMessage());
        }
    }

}
