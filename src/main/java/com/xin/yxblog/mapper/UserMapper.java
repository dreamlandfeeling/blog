package com.xin.yxblog.mapper;

import com.xin.yxblog.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserMapper {
    User selectByUsernameAndPassword(Map map);

    List<User> list(Map map);

    int count(Map map);

    int deleteByPrimaryKey(Integer id);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKeyWithBLOBs(User user);

    int updateByPrimaryKey(User user);

    int batchRemove(int[] ids);
}