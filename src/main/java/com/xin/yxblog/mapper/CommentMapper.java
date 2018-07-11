package com.xin.yxblog.mapper;

import com.xin.yxblog.model.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CommentMapper {
    List<Comment> list(Map map);

    int count(Map map);

    int deleteByPrimaryKey(Integer id);

    int insert(Comment comment);

    int insertSelective(Comment comment);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment comment);

    int updateByPrimaryKeyWithBLOBs(Comment comment);

    int updateByPrimaryKey(Comment comment);

    int batchRemove(int[] ids);
}