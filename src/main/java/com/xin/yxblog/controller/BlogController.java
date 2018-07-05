package com.xin.yxblog.controller;

import com.xin.yxblog.dto.Page;
import com.xin.yxblog.dto.Query;
import com.xin.yxblog.dto.Result;
import com.xin.yxblog.model.Article;
import com.xin.yxblog.service.BlogService;
import com.xin.yxblog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/blog")
@Controller
public class BlogController extends BaseController{
    @Autowired
    private BlogService blogService;

    @GetMapping("/add")
    public String add(){
        return "blog/add";
    }
    @GetMapping("/list")
    public String list(){
        return "blog/list";
    }

    @GetMapping("/article/{id}")
    public String getArticleToEdit(@PathVariable int id,Model model){
        Article article = blogService.get(id);
        model.addAttribute("article",article);
        return "blog/edit";
    }

    @ResponseBody
    @GetMapping("/article/list")
    public Page list(@RequestParam Map<String,Object> map){
        int a = 1/0;
        Query query = new Query(map);
        query.put("uid",getUserId());
        List<Article> list = blogService.list(query);
        int total = blogService.count(query);
        Page<Article> page = new Page<>(total, list);
        return page;
    }

    @ResponseBody
    @PostMapping("/article")
    public Result save(Article article){
        if(StringUtils.isBlank(article.getContent()) || StringUtils.isBlank(article.getTitle())){
            return Result.error("标题或者内容不能为空");
        }
        article.setUid(getUserId());
        int result = blogService.add(article);
        if(result >0){
            return Result.ok();
        }
        return Result.error("未知失败");
    }

    @ResponseBody
    @PutMapping("/article/{id}")
    public Result update(Article article){
        if(StringUtils.isBlank(article.getContent()) || StringUtils.isBlank(article.getTitle())){
            return Result.error("标题或者内容不能为空");
        }
        int result = blogService.update(article);
        if(result >0){
            return Result.ok();
        }
        return Result.error("未知失败");
    }

    @ResponseBody
    @DeleteMapping("/article/{id}")
    public Result delete(@PathVariable int id){
        if(blogService.delete(id)>0){
            return Result.ok();
        }
        return Result.error("未知失败");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @DeleteMapping("/article/batchRemove")
    public Result batchDelete(@RequestParam(value = "ids[]") int[] ids){
        if(blogService.batchDelete(ids)==ids.length){
            return Result.ok();
        }
        return Result.error("未知失败");
    }


}
