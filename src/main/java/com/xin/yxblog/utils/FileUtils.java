package com.xin.yxblog.utils;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Path;

public class FileUtils {

    public static void saveFile(byte[] bytes, String fileName, String savePath) throws IOException {
        File file = new File(savePath);
        if(!file.exists()){
            file.mkdirs();
        }
        FileOutputStream out = null;
        out = new FileOutputStream(savePath+fileName);
        out.write(bytes);
        out.flush();
        out.close();
    }

    /**
     * 获得本地项目classes路径
     * @return
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static String getLoaclFilePath() throws FileNotFoundException, UnsupportedEncodingException {
        URL url = ResourceUtils.getURL("classpath:");
        String path = URLDecoder.decode(url.toString(), "utf-8");
        path = path.substring(path.indexOf("/")+1);
        return path;
    }

    public static String getLoaclFilePath(String path) throws FileNotFoundException, UnsupportedEncodingException {
        path = getLoaclFilePath()+path;
        return path;
    }

    public static String getResourcePath(String path) throws UnsupportedEncodingException {
        String url = FileUtils.class.getClassLoader().getResource(path).getPath();
        return URLDecoder.decode(url, "utf-8");
    }
}