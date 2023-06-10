package com.lkpackage.furn.service.impl;

import com.lkpackage.furn.bean.Furn;
import com.lkpackage.furn.bean.FurnExample;
import com.lkpackage.furn.dao.FurnMapper;
import com.lkpackage.furn.service.FurnService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/6/8 19:33
 */
@Service
public class FurnServiceImpl implements FurnService {
    // 装填Mapper对象
    @Resource
    private FurnMapper furnMapper;

    @Override
    public List<Furn> getFurnList() {
        List<Furn> furns = furnMapper.selectByExample(null);
        return furns;
    }

    @Override
    public Furn getFurnById(Integer id) {
        Furn furn = furnMapper.selectByPrimaryKey(id);
        return furn;
    }

    @Override
    public int update(Furn furn) {
        int i = furnMapper.updateByPrimaryKeySelective(furn);
        return i;
    }

    @Override
    public int save(Furn furn) {
        int insert = furnMapper.insert(furn);
        return insert;
    }

    @Override
    public int delFurnById(Integer id) {
        int i = furnMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public List<Furn> queryFurnByConditional(String key) {
        FurnExample example = new FurnExample();
        FurnExample.Criteria criteria = example.createCriteria();
        criteria.andFurnNameLike("%"+key+"%");
        List<Furn> furns = furnMapper.selectByExample(example);
        return furns;
    }
}
