package com.xin.yxblog.service.impl;

import com.xin.yxblog.mapper.GeneratorMapper;
import com.xin.yxblog.model.Generator;
import com.xin.yxblog.model.Table;
import com.xin.yxblog.service.GeneratorService;
import com.xin.yxblog.utils.GeneratorUtils;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;
@Service
public class GeneratorServiceImpl implements GeneratorService {
    @Autowired
    private GeneratorMapper generatorMapper;

    @Override
    public byte[] generatorCode(String tableName) throws IOException, TemplateException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(out);
        generatorCommonCode(zip);
        generator(tableName,zip);
        IOUtils.closeQuietly(zip);
        return out.toByteArray();
    }

    @Override
    public byte[] generatorAllCode() throws IOException, TemplateException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(out);
        generatorCommonCode(zip);

        List<Map<String, String>> maps = generatorMapper.listTables(new HashMap());
        for (Map<String, String> map : maps) {
            generator(map.get("tableName"), zip);
        }
        IOUtils.closeQuietly(zip);
        return out.toByteArray();
    }

    @Override
    public List<Map<String,String>> list(Map map) {
        return generatorMapper.listTables(map);
    }

    @Override
    public int count() {
        return generatorMapper.count();
    }

    @Override
    public byte[] batchGeneratorCode(String tableNames) throws IOException, TemplateException {
        String[] tables = tableNames.split(",");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(out);
        for (String table : tables) {
            generator(table,zip);
        }
        IOUtils.closeQuietly(zip);
        return out.toByteArray();
    }


    public ZipOutputStream generator(String tableName,ZipOutputStream zip) throws IOException, TemplateException {
        Map<String,String> tableInfo = generatorMapper.get(tableName);
        List<Map<String,String>> listColumn = generatorMapper.listColumn(tableName);
        GeneratorUtils generatorUtils = new GeneratorUtils();
        generatorUtils.generatorCode(tableInfo,listColumn,zip);
        return zip;
    }

    public ZipOutputStream generatorCommonCode(ZipOutputStream zip) throws IOException, TemplateException {
        GeneratorUtils generatorUtils = new GeneratorUtils();
        generatorUtils.generatorCommonCode(zip);
        return zip;
    }
}
