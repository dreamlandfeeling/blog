package com.xin.yxblog.service.impl;

import com.xin.yxblog.mapper.CommentMapper;
import com.xin.yxblog.model.Comment;
import com.xin.yxblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentMapper commentMapper;


    @Override
    public Comment get(int id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Comment add(Comment comment) {
        comment.setCreatetime(new Date());
        commentMapper.insert(comment);
        return comment;
    }

    @Override
    public List<Comment> list(Map map) {
        return commentMapper.list(map);
    }

    @Override
    public int count(Map map) {
        return commentMapper.count(map);
    }

    @Override
    public int delete(int id) {
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Comment comment) {
        return commentMapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    public int batchDelete(int[] ids) {
        return commentMapper.batchRemove(ids);
    }
}