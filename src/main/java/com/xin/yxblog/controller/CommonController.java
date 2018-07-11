package com.xin.yxblog.controller;

import com.alibaba.fastjson.JSON;
import com.xin.yxblog.dto.Page;
import com.xin.yxblog.dto.Query;
import com.xin.yxblog.dto.Result;
import com.xin.yxblog.model.Article;
import com.xin.yxblog.model.Comment;
import com.xin.yxblog.service.BlogService;
import com.xin.yxblog.service.CommentService;
import com.xin.yxblog.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class CommonController extends BaseController{
    @Value("${img.location}")
    private String path;
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String get(Model model){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("status",2);
        int count = commentService.count(map);
        model.addAttribute("checkCommentCount",count);
        return "index";
    }

    @ResponseBody
    @PostMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf('.'));
        FileUtils.saveFile(file.getBytes(),"/"+fileName,path);
        Result result = Result.ok("/"+fileName);
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @PostMapping("/common/newComment")
    public Result newComment(Comment comment){
        Integer cid = comment.getCid();
        comment = encodeComment(comment);
        if(cid!=null&&cid>0){
            Comment parent = commentService.get(cid);
            comment.setContent(comment.getContent()+"//@<span style='color:blue'>"+parent.getAuthor()+"</span>:"+parent.getContent());
        }
        comment = commentService.add(comment);
        if(comment !=null){
            return Result.ok(comment);
        }
        return Result.error("保存失败");
    }

    @GetMapping("/common/{id}")
    public String article(@PathVariable("id") int id, Model model) {
        Article article = blogService.get(id);
        model.addAttribute("article",article);
        return "forward:/common/comment/listAll?aid="+id;
    }

    @GetMapping("/common/comment/listAll")
    public String listAll(@RequestParam Map<String,Object> map, Model model){
        Query query = new Query(map);
        query.setLimit(999);
        query.put("sort","createtime");
        query.put("order","desc");
        List<Comment> list = commentService.list(query);
        int total = commentService.count(query);
        Page<Comment> page = new Page<>(total, list);
        model.addAttribute("page",page);
        return "blog/artile";
    }


}
