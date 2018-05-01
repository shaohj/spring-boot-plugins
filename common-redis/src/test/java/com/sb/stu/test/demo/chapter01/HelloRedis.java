package com.sb.stu.test.demo.chapter01;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.sb.stu.common.BaseApplicationTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;

public class HelloRedis extends BaseApplicationTest {

    static final long ONE_WEEK_IN_SECONDS = 7 * 86400;

    static final long VOTE_SCORE = 432;
    
    static Logger logger = LoggerFactory.getLogger(HelloRedis.class);
    
    private @Autowired RedisTemplate<String, String> template;
    
    public static void main(String[] args) {
        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime preWeekDay = nowTime.minusDays(7);
        
        System.out.println(nowTime);
        System.out.println(preWeekDay);
        
        //获取秒数  
        System.out.println(nowTime.toEpochSecond(ZoneOffset.of("+8")));
        //获取毫秒数  
        System.out.println(nowTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

    /**
     * 文章投票
     * 测试时,手动新增一个文章和文章的发布时间
     * eq: hash art:1 {aid:1;votes:0;content:文章内容}
     *     zset arttime: art:1 1521449665
     * @Title: testArticleVote
     */
    @Test
    public void testArticleVote(){
        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime preWeekDay = nowTime.minusDays(7);
        long pubArtTime = preWeekDay.toEpochSecond(ZoneOffset.of("+8")); //发布文章时间,一周以内可以投票
        
        ZSetOperations<String, String> zSetOpt = template.opsForZSet();
        SetOperations<String, String> setOpt = template.opsForSet();
        HashOperations<String, Object, Object> hashOpt = template.opsForHash();
        
        Double artScore = zSetOpt.score("arttime:", "art:1");

        logger.info("artScore={}", artScore);
        
        if(null == artScore || artScore < pubArtTime){ //未发布的文章和一周以前的文章限制投票
            return ;
        }
        
        String artId = "1";
        Long addNum = setOpt.add("voted:" + artId, "user:0002"); //投票成功
        //投票成功
        if(addNum > 0){
            zSetOpt.incrementScore("artscore:", "art:1", VOTE_SCORE);
            hashOpt.increment("art:1", "votes", 1);
        }
    }
    
}
