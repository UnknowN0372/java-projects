package com.lkpackage.furn.test;

import com.lkpackage.furn.bean.Furn;
import com.lkpackage.furn.service.FurnService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/6/9 14:25
 */
public class MysqlCon {
    private FurnService service;
    @Before
    public void con(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = context.getBean(FurnService.class);
    }
    @Test
    public void mysql(){
        System.out.println("service = " + service);
        List<Furn> furnList = service.getFurnList();
        System.out.println(furnList);
    }
}
