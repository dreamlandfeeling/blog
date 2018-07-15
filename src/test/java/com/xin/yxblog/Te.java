package com.xin.yxblog;

public class Te implements Runnable{

    private String name;

    public Te() {
    }

    public Te(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (Te.class) {
            System.out.println("第一次调用");
            synchronized (Te.class) {
                System.out.println("第二次调用");
            }

        }
    }

}
