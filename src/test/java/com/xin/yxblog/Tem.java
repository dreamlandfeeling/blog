package com.xin.yxblog;

public class Tem implements Runnable{

    private String name;


    public Tem(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            System.out.println(name+":"+i);
        }
    }
}
