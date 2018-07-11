package com.xin.yxblog.service;

import com.xin.yxblog.model.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {

    Comment get(int id);

    Comment add(Comment comment);

    List<Comment> list(Map map);

    int count(Map map);

    int delete(int id);

    int update(Comment comment);

    int batchDelete(int[] ids);
}