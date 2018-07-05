package com.xin.yxblog.controller;

import com.alibaba.fastjson.JSON;
import com.xin.yxblog.dto.Result;
import com.xin.yxblog.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Controller
public class CommonController {
    @Value("${img.location}")
    private String path;

    @ResponseBody
    @PostMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf('.'));
        FileUtils.saveFile(file.getBytes(),fileName,path);
        Result result = Result.ok(fileName);
        return JSON.toJSONString(result);
    }
}
