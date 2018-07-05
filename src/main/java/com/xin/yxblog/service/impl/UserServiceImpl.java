package com.xin.yxblog.service.impl;

import com.xin.yxblog.mapper.UserMapper;
import com.xin.yxblog.model.User;
import com.xin.yxblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;


    @Override
    public User get(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User getByUsernameAndPassWord(Map map) {
        return userMapper.selectByUsernameAndPassword(map);
    }

    @Override
    public int add(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> list(Map map) {
        return userMapper.list(map);
    }

    @Override
    public int count(Map map) {
        return userMapper.count(map);
    }

    @Override
    public int delete(int id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int batchDelete(int[] ids) {
        return userMapper.batchRemove(ids);
    }
}