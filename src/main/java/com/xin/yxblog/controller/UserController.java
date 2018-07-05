package com.xin.yxblog.controller;

import com.xin.yxblog.dto.Page;
import com.xin.yxblog.dto.Result;
import com.xin.yxblog.model.User;
import com.xin.yxblog.service.UserService;
import com.xin.yxblog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/user/{id}")
    public Result getuserToEdit(@PathVariable int id,Model model){
        User user = userService.get(id);
        return Result.ok(user);
    }

    //@ResponseBody
    //@GetMapping("/user/list")
    //public Page list(@RequestParam Map<String,Object> map){
    //    List<User> list = userService.list(map);
    //    int total = userService.count(map);
    //    Page<User> page = new Page<>(total, list);
    //    return page;
    //}

    @ResponseBody
    @PostMapping("/user")
    public Result save(User user){
        user.setPassword(MD5Utils.md5Two(user.getPassword()));
        int result = userService.add(user);
        if(result >0){
            return Result.ok();
        }
        return Result.error("保存失败");
    }

    @ResponseBody
    @PutMapping("/user/{id}")
    public Result update(User user){
        int result = userService.update(user);
        if(result >0){
            return Result.ok();
        }
        return Result.error("更新失败");
    }

    //@ResponseBody
    //@DeleteMapping("/user/{id}")
    //public Result delete(@PathVariable int id){
    //    if(userService.delete(id)>0){
    //        return Result.ok();
    //    }
    //    return Result.error("删除失败");
    //}
    //
    //@ResponseBody
    //@DeleteMapping("/user/batchRemove")
    //public Result batchDelete(@RequestParam(value = "ids[]") int[] ids){
    //    if(userService.batchDelete(ids)==ids.length){
    //        return Result.ok();
    //    }
    //        return Result.error("批量删除失败");
    //}

}
