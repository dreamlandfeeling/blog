package com.xin.yxblog.service;

import com.xin.yxblog.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User getByUsernameAndPassWord(Map map);

    User get(int id);

    int add(User user);

    List<User> list(Map map);

    int count(Map map);

    int delete(int id);

    int update(User user);

    int batchDelete(int[] ids);
}