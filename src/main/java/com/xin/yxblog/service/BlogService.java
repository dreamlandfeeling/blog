package com.xin.yxblog.service;

import com.xin.yxblog.model.Article;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Article get(int id);

    int add(Article article);

    List<Article> list(Map map);

    int count(Map map);

    int delete(int id);

    int update(Article article);

    int batchDelete(int[] ids);

}
