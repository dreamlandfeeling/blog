package com.xin.yxblog.utils;

import cn.hutool.http.HtmlUtil;

public class HtmlUtils {


    /**
     * 转义文本中的HTML字符为安全的字符
     */
    public static String encode(String text){
        return HtmlUtil.encode(text);
    }
}
