package com.xin.yxblog;

import com.xin.yxblog.utils.StringUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TT {
    @Test
    public void Test() throws IOException {
        myCopy("C:\\Users\\Administrator\\Desktop\\草稿.txt","C:\\Users\\Administrator\\Desktop\\草稿2.txt");
    }

    @Test
    public void Test2() throws IOException {
        FileInputStream in = new FileInputStream("C:\\Users\\Administrator\\Desktop\\新建文本文档 (2).txt");
        FileOutputStream out = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\新建文本文档(3).txt");
        InputStreamReader reader = new InputStreamReader(in,"gbk");
        OutputStreamWriter write = new OutputStreamWriter(out,"gbk");
        BufferedWriter bwrite = new BufferedWriter(write);
        TextWrite textWrite = new TextWrite(reader);
        //BufferedReader textWrite = new BufferedReader(reader);
        String str = "";
        while ((str = textWrite.readLine())!=null&& str.trim()!=""){
            bwrite.write(str+"\r\n");
            //bwrite.newLine();
        }
        bwrite.close();
        write.close();
        textWrite.close();
        reader.close();
        out.close();
        in.close();
    }

    @Test
    public void Test3() throws IOException {
        String str = "61.54.231.9 61.54.231.247";
        String regex = "(\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3} )";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(str);
        while (matcher.find()){
            String group = matcher.group();
            str = str.replaceAll(group, group.substring(0, group.indexOf(" ")) + ",");
        }

    }
    @Test
    public void Test4(){
        String a = null;
        a.toLowerCase();
    }

    public void myCopy(String sourceFile,String destFile) throws IOException {
        File source = new File(sourceFile);
        File dest = new File(destFile);
        FileInputStream in = new FileInputStream(source);
        FileOutputStream out = new FileOutputStream(dest);
        byte[] bytes = new byte[1024];
        int len =0;
        while ((len = in.read(bytes)) >=0){
            out.write(bytes,0,len);
        }
        in.close();
        out.close();
    }


    @Test
    public void Test5() throws IOException {
        Properties properties = new Properties();
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("generator.properties");
        properties.load(resourceAsStream);
        String property = properties.getProperty("BIT");
        System.out.println(property);
        int[] ints1 = new int[10];
        List<int[]> ints2 = Arrays.asList(ints1);

        ArrayList<int[]> ints = new ArrayList<int[]>(ints2);
    }
}
