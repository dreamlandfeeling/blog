package com.xin.yxblog.service.impl;

import com.xin.yxblog.mapper.ArticleMapper;
import com.xin.yxblog.model.Article;
import com.xin.yxblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public Article get(int id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int add(Article article) {
        article.setCreatetime(new Date());
        article.setUpdatetime(new Date());
        return articleMapper.insert(article);
    }

    @Override
    public List<Article> list(Map map) {
        return articleMapper.list(map);
    }

    @Override
    public int count(Map map) {
        return articleMapper.count(map);
    }

    @Override
    public int delete(int id) {
        return articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Article article) {
        return articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public int batchDelete(int[] ids) {
        return articleMapper.batchRemove(ids);
    }
}
