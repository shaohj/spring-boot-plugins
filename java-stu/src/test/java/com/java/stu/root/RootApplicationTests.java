package com.java.stu.root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RootApplicationTests {

    public static void main(String[] args) {
        String key = "zs";
        System.out.println(key.hashCode());
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        System.out.println("" + hash);
        HashMap<String, String> hMap = new HashMap<>();
        hMap.put("zs", "123456");
        hMap.put("ls", "234567");

    }

    @Test
    public void contextLoads() {
    }

}
