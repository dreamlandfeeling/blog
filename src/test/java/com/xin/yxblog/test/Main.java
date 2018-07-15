package com.xin.yxblog.test;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        for (String str : list) {
            System.out.println(str);
            if ("b".equals(str)) {
                list.remove(str);
            }
        }
        System.out.println(list);
    }

    @Test
    public void test(){
        ResourceFactory resource = ResourceFactory.getResource();
        ResourceFactory resource2 = ResourceFactory.getResource();
        System.out.println(resource==resource2);
    }
}
