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
 * @date 2023/6/9 19:04
 */
public class FurnTest {
    private FurnService service;
    @Before
    public void ioc(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = context.getBean(FurnService.class);
    }
    @Test
    public void list(){
        System.out.println("service = " + service);
        List<Furn> furnList = service.getFurnList();
        System.out.println(furnList);
    }
    @Test
    public void update(){
        Furn furn = new Furn();
        furn.setId(10);
        furn.setMaker("hello");
        service.update(furn);
    }
    @Test
    public void getFurnById(){
        Furn furnById = service.getFurnById(1);
        System.out.println(furnById);
    }
    @Test
    public void delFurnById(){
        int i = service.delFurnById(10);
    }
    @Test
    public void save(){
        int save = service.save(null);
    }
}
