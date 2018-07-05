package com.xin.yxblog;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xin.yxblog.utils.GeneratorUtils;
import freemarker.template.Configuration;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

public class Demo {
    private Date time;
    private Boolean status;

    @Test
    public void test() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("generator.properties"));
        String tinyint = properties.getProperty("tinyint");
        System.out.println(tinyint);
    }

    @Test
    public void test2() throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("");
        String str = this.getClass().getClassLoader().getResource("generator.properties").getPath();
        System.out.println(url.toString());
        System.out.println(str);
    }

    @Test
    public void test3() throws IOException {
        String d_name = GeneratorUtils.columnToProperty("d_name_age");
        System.out.println(d_name);
        char s = 's';
        s-=32;
        System.out.println(s);
    }


}
