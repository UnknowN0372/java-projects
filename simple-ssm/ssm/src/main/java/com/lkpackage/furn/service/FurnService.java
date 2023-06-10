package com.lkpackage.furn.service;

import com.lkpackage.furn.bean.Furn;

import java.util.List;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/6/8 19:33
 */
public interface FurnService {
    // 获取所有的furn
    public List<Furn> getFurnList();
    // 获取单个对象
    public Furn getFurnById(Integer id);
    // 修改对象
    public int update(Furn furn);
    // 保存对象
    public int save(Furn furn);
    // 删除对象
    public int delFurnById(Integer id);
    // 带条件的查询语句
    public List<Furn> queryFurnByConditional(String key);
}
