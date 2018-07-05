package com.xin.yxblog.controller;

import com.xin.yxblog.dto.Page;
import com.xin.yxblog.service.GeneratorService;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequestMapping("/generator")
@Controller
public class GeneratorController {
    @Autowired
    private GeneratorService generatorService;

    @GetMapping("/")
    public String generator() {
        return "generator/list";
    }

    @ResponseBody
    @GetMapping("/list")
    public Page list(@RequestParam Map map) {
        List<Map<String, String>> list = generatorService.list(map);
        int total = generatorService.count();
        Page<Map<String, String>> page = new Page<>(total, list);
        return page;
    }

    @GetMapping("/code")
    public void generatorCode(String tableName,HttpServletResponse response) throws IOException, TemplateException {
        byte[] data = generatorService.generatorCode(tableName);
        returnZipResponse(data,response);
    }

    @GetMapping("/code/batch")
    public void batchGeneratorCode(String tableNames,HttpServletResponse response) throws IOException, TemplateException {
        byte[] data = generatorService.batchGeneratorCode(tableNames);
        returnZipResponse(data,response);
    }

    @GetMapping("/code/list")
    public void generatorAllCode(HttpServletResponse response) throws IOException, TemplateException {
        byte[] data = generatorService.generatorAllCode();
        returnZipResponse(data,response);
    }

    /**
     * 设置返回zip文件
     */
    public void returnZipResponse(byte[] data,HttpServletResponse response) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"yxblog.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data,response.getOutputStream());
    }

}
