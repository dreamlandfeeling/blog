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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/blog")
@Controller
public class BlogController extends BaseController {
    @Autowired
    private BlogService blogService;


    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "0") int currentPage,Model model) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid",getUserId());
        map.put("offset",currentPage*10);
        map.put("limit",10);
        List<Article> list = blogService.list(map);
        int total = blogService.count(map);
        Page<Article> page = new Page<>(total, list);
        model.addAttribute("page",page);
        model.addAttribute("currentPage",currentPage);
        return "blog/list";
    }

    @GetMapping("/article/add")
    public String add() {
        return "article/add";
    }

    @GetMapping("/article")
    public String list() {
        return "article/list";
    }
    @GetMapping("/comment/2")
    public String commentCheck() {
        return "comment/checkList";
    }
    @GetMapping("/comment/1")
    public String commentPass() {
        return "comment/passList";
    }



    @GetMapping("/article/{id}")
    public String getArticleToEdit(@PathVariable int id, Model model) {
        Article article = blogService.get(id);
        model.addAttribute("article", article);
        return "article/edit";
    }

    @ResponseBody
    @GetMapping("/article/list")
    public Page list(@RequestParam Map<String, Object> map) {
        Query query = new Query(map);
        query.put("uid", getUserId());
        List<Article> list = blogService.list(query);
        int total = blogService.count(query);
        Page<Article> page = new Page<>(total, list);
        return page;
    }

    @ResponseBody
    @PostMapping("/article")
    public Result save(Article article) {
        if (StringUtils.isBlank(article.getContent()) || StringUtils.isBlank(article.getTitle())) {
            return Result.error("标题或者内容不能为空");
        }
        article.setUid(getUserId());
        article = encodeComment(article);
        int result = blogService.add(article);
        if (result > 0) {
            return Result.ok();
        }
        return Result.error("未知失败");
    }

    @ResponseBody
    @PutMapping("/article/{id}")
    public Result update(Article article) {
        if (StringUtils.isBlank(article.getContent()) || StringUtils.isBlank(article.getTitle())) {
            return Result.error("标题或者内容不能为空");
        }
        int result = blogService.update(article);
        if (result > 0) {
            return Result.ok();
        }
        return Result.error("未知失败");
    }

    @ResponseBody
    @DeleteMapping("/article/{id}")
    public Result delete(@PathVariable int id) {
        if (blogService.delete(id) > 0) {
            return Result.ok();
        }
        return Result.error("未知失败");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @DeleteMapping("/article/batchRemove")
    public Result batchDelete(@RequestParam(value = "ids[]") int[] ids) {
        if (blogService.batchDelete(ids) == ids.length) {
            return Result.ok();
        }
        return Result.error("未知失败");
    }


}
