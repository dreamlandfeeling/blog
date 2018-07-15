package com.xin.yxblog;

import com.xin.yxblog.mapper.GeneratorMapper;
import com.xin.yxblog.utils.GeneratorUtils;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YxblogApplicationTests {
    @Autowired
    GeneratorMapper generatorMapper;

    private static final String tableName = "student";

    @Test
    public void contextLoads() throws IOException, TemplateException {
        Map<String,String> tableInfo = generatorMapper.get(tableName);
        List<Map<String,String>> listColumn = generatorMapper.listColumn(tableName);
        GeneratorUtils generatorUtils = new GeneratorUtils();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(out);
        generatorUtils.generatorCode(tableInfo,listColumn,zip);
    }

    @Test
    public void test() throws IOException, TemplateException {
        Map tableInfo = generatorMapper.get("yx_article");
        List<Map<String,String>> listColumn = generatorMapper.listColumn("yx_article");
        for (Map map : listColumn) {
            for (Object o : map.keySet()) {
                System.err.print(o+" "+map.get(o)+"| ");
            }
        }
        //GeneratorUtils generatorUtils = new GeneratorUtils();
        //generatorUtils.generatorCode(tableInfo,listColumn,new File("C:\\Users\\Administrator\\Desktop\\model.java"));
    }
    //@Transactional
    //@Test
    //public void test2(){
    //    Student student = studentMapper.selectByPrimaryKey(1);
    //    Student student2 = studentMapper.selectByPrimaryKey(2);
    //    int count = studentMapper.count(new HashMap());
    //    //int delete = studentMapper.deleteByPrimaryKey(1);
    //    //int deletes = studentMapper.batchRemove(new int[]{1,2});
    //    Student new1 = new Student(3,"小花",24,new Date(),true,"","","");
    //    Student new2 = new Student(4,"小白",22,new Date(),false,null,null,"");
    //    int insert1 = studentMapper.insertSelective(new1);
    //    int insert2 = studentMapper.insertSelective(new2);
    //    student.setAge(null);
    //    student2.setContent("344444444");
    //    int update1 = studentMapper.updateByPrimaryKey(student);
    //    int update2 = studentMapper.updateByPrimaryKey(student2);
    //    System.err.println(student);
    //    System.err.println(student2);
    //    System.err.println(count);
    //    //System.err.println(delete);
    //    //System.err.println(deletes);
    //    System.err.println(insert1);
    //    System.err.println(insert2);
    //    System.err.println(update1);
    //    System.err.println(update2);
    //}
    //@Test
    //public void test3(){
    //    Student student = studentMapper.selectByPrimaryKey(1);
    //    Student student2 = studentMapper.selectByPrimaryKey(2);
    //    student.setAge(null);
    //    student.setContent("2233");
    //    student2.setContent("344444444");
    //    HashMap hashMap = new HashMap();
    //    hashMap.put("offset","0");
    //    hashMap.put("limit","10");
    //    List<Student> list = studentMapper.list(hashMap);
    //    for (Student student1 : list) {
    //        System.err.println(student1);
    //    }
    //}
}
