package com.example.hellospringboot;

import com.example.hellospringboot.Service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoServiceTest {

    @Autowired
    DemoService demoService;

    @TestConfiguration
    public static class configTest{
        @Bean
        DemoService demoService(){
            return new DemoService();
        }
    }
    @Test
    public void demoTest(){

        assertEquals(5, demoService.sum(2,3));
    }

    @Test
    public void oktest(){
        assertEquals("ok", demoService.okela());
    }


}
