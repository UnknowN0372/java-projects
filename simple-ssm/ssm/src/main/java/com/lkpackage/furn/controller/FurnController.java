package com.lkpackage.furn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lkpackage.furn.bean.Furn;
import com.lkpackage.furn.bean.Msg;
import com.lkpackage.furn.service.FurnService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/6/8 19:12
 */
@Controller
public class FurnController {
    // 装填service
    @Resource
    private FurnService furnService;

    // 返回所有的furn
    @GetMapping("/list")
    @ResponseBody
    public Msg list(){
        List<Furn> furnList = furnService.getFurnList();
        return Msg.success().add("furnList",furnList);
    }

    // 带条件的查询语句
    @GetMapping("/query")
    @ResponseBody
    public Msg queryByConditional(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "5") Integer pageSize,
                                  @RequestParam(defaultValue = "") String key){
        // 这里会物理分页
        PageHelper.startPage(pageNum,pageSize);
        List<Furn> furns = furnService.queryFurnByConditional(key);
        // 这个pageInfo 最后包含了查询的信息和分页的信息
        PageInfo<Furn> pageInfo = new PageInfo<>(furns, pageSize);
        return Msg.success().add("pageInfo",pageInfo);
    }

    @PutMapping("/update")
    @ResponseBody
    public Msg update(@RequestBody Furn furn){
        furnService.update(furn);
        System.out.println("furn =>" + furn.toString());
        Msg success = Msg.success();
        success.setMsg("修改成功");
        return success;
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public Msg findById(@PathVariable("id") Integer id){
        Furn furnById = furnService.getFurnById(id);
        if (furnById != null){
            Msg success = Msg.success();
            success.setMsg("查询成功");
            success.add("datapools",furnById);
            return success;
        }
        Msg msg = Msg.error();
        msg.setMsg("没有找到该家居");
        return msg;
    }

    @PostMapping("/save")
    @ResponseBody
    public Msg save(@Validated @RequestBody Furn furn, Errors errors){
        Map<String, Object> map = new HashMap<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for (FieldError error : fieldErrors) {
            map.put(error.getField(),error.getDefaultMessage());
        }
        if (!map.isEmpty()){
            return Msg.error().add("errors",map);
        }
        furnService.save(furn);
        Msg success = Msg.success();

        success.setMsg("添加成功");
        return success;
    }

    @DeleteMapping("/del")
    @ResponseBody
    public Msg delById(Integer id){
        Msg success = Msg.success();
        furnService.delFurnById(id);
        success.setMsg("删除成功");
        return success;
    }
}
