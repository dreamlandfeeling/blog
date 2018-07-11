package com.xin.yxblog;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextWrite extends BufferedReader {

    public TextWrite(Reader in) {
        super(in);
    }

    @Override
    public String readLine() throws IOException {
        String str = super.readLine();
        String regex = "(\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}\\s+)";
        Pattern compile = Pattern.compile(regex);
        if (str == null || str.trim() == "") {
            return null;
        }
        Matcher matcher = compile.matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            String newGroup = "";
            newGroup = group.trim();
            String[] split = newGroup.split("\\.");
            for (int i = 0; i < split.length; i++) {
                while (split[i].length() - 3 < 0) {
                    split[i] = "0" + split[i];
                }
            }
            newGroup = "";
            for (int i = 0; i < split.length; i++) {
                if(split.length-1==i){
                    newGroup += split[i];
                }else{
                    newGroup += split[i];
                    newGroup += ".";
                }

            }

            str = str.replaceAll(group, newGroup+", ");

        }

        return str;
    }
}
