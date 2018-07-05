package com.xin.yxblog.service;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface GeneratorService {

    byte[] generatorCode(String tableName) throws IOException, TemplateException;

    byte[] generatorAllCode() throws IOException, TemplateException;

    List<Map<String,String>> list(Map map);

    int count();

    byte[] batchGeneratorCode(String tableNames) throws IOException, TemplateException;
}
