package com.xin.yxblog.test;

public class LongVolatile {
    private static volatile int index=0;
    private  static  int aa=0;
    volatile boolean flag=false;
    public   class thread1 implements Runnable{
        String name;

        public thread1(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            int var = 0;
            while (index<10000000){
                index++;
                var++;
            }
            System.out.println(name+":"+var);
        }
    }

    public   class thread2 implements Runnable{
        String name;

        public thread2(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (!flag){
                aa++;
            }
        }
    }

    public void test(){
        thread1 t1 = new thread1("小白");
        thread1 t2 = new thread1("小苏");
        Thread tt1 = new Thread(t1);
        Thread tt2 = new Thread(t2);
        tt1.start();
        tt2.start();

        while (tt1.isAlive()||tt2.isAlive()){}

        System.out.println(index);
    }

    public void test2() throws InterruptedException {
        thread2 t1 = new thread2("小扇");
        Thread tt1 = new Thread(t1);
        tt1.start();
        Thread.sleep(2000);
        flag = true;
        while (tt1.isAlive()){}

        System.out.println(aa);
    }

    public static void main(String[] args) throws InterruptedException {
        LongVolatile longVolatile = new LongVolatile();
        longVolatile.test2();
        System.out.println(Integer.MAX_VALUE);
    }
}
