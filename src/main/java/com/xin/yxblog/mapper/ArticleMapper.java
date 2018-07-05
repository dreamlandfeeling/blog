package com.xin.yxblog.mapper;

import com.xin.yxblog.model.Article;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ArticleMapper {
    List<Article> list(Map map);

    int count(Map map);

    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    int batchRemove(int[] ids);
}