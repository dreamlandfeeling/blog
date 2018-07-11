package com.xin.yxblog.controller;

import cn.hutool.http.HtmlUtil;
import com.xin.yxblog.dto.Page;
import com.xin.yxblog.dto.Query;
import com.xin.yxblog.dto.Result;
import com.xin.yxblog.model.Comment;
import com.xin.yxblog.service.CommentService;
import com.xin.yxblog.utils.DateUtils;
import com.xin.yxblog.utils.HtmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

@RequestMapping("")
@Controller
public class CommentController extends BaseController{
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @GetMapping("/comment/{id}")
    public Result getcommentToEdit(@PathVariable int id,Model model){
        Comment comment = commentService.get(id);
        return Result.ok(comment);
    }

    /**
     * 根据状态码返回评论数据
     * @param map
     * @param status
     * @return
     */
    @ResponseBody
    @GetMapping("/comment/list/{status}")
    public Page list(@RequestParam Map<String,Object> map,@PathVariable Integer status){
        map.put("status",status);
        List<Comment> list = commentService.list(map);
        int total = commentService.count(map);
        Page<Comment> page = new Page<>(total, list);
        return page;
    }



    @ResponseBody
    @PostMapping("/comment")
    public Result save(Comment comment){
        Integer cid = comment.getCid();
        comment = encodeComment(comment);
        if(cid!=null&&cid>0){
            Comment parent = commentService.get(cid);
            comment.setContent(comment.getContent()+"//@<span style='color:blue'>"+parent.getAuthor()+"</span>:"+parent.getContent());
        }
        if(comment.getIsAdmin()!=null && comment.getIsAdmin()==1){
            comment.setAuthor(getUser().getName());
        }
        comment = commentService.add(comment);
        if(comment !=null){
            return Result.ok(comment);
        }
        return Result.error("保存失败");
    }

    @ResponseBody
    @PutMapping("/comment/{id}")
    public Result update(Comment comment){
        int result = commentService.update(comment);
        if(result >0){
            return Result.ok();
        }
        return Result.error("更新失败");
    }

    @ResponseBody
    @DeleteMapping("/comment/{id}")
    public Result delete(@PathVariable int id){
        if(commentService.delete(id)>0){
            return Result.ok();
        }
        return Result.error("删除失败");
    }

    @ResponseBody
    @DeleteMapping("/comment/batchRemove")
    public Result batchDelete(@RequestParam(value = "ids[]") int[] ids){
        if(commentService.batchDelete(ids)==ids.length){
            return Result.ok();
        }
        return Result.error("批量删除失败");
    }



}
