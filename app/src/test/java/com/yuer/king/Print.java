package com.yuer.king;

import android.os.SystemClock;

import org.junit.Test;

/**
 * Created by yuer on 2018/6/25.
 */

public class Print{
    private static int flag = 1;//条件变量
    private static Object synObj = new Object();//对象锁
    private final int PRINT_NUM = 1;
    private int result = 10;
    public  void printA() throws InterruptedException{
        for(int i =0;i<PRINT_NUM;i++){
            synchronized(synObj){
                while(flag != 1){
                    System.out.print("printA\n");
                    synObj.wait();
                }
                /*SystemClock.sleep(2000);*/
//                new Thread(){
//                    @Override
//                    public void run() {
                        for (int j = 0; j < 10000000; j++) {
                            result ++;
                            System.out.print("result:  " + result);
                        }
//                    }
//                }.start();

                System.out.print('A');
                flag = 2;//改变条件并唤醒等待线程
                synObj.notifyAll();
            }
        }
    }
    public   void printB() throws InterruptedException{
        for(int i =0;i<PRINT_NUM;i++){
            synchronized(synObj){
                while(flag != 2){
                    System.out.print("printB\n");
                    synObj.wait();
                }
                System.out.print('B');
                flag = 3;
                synObj.notifyAll();
            }
        }
    }
    public   void printC() throws InterruptedException{
        for(int i =0;i<PRINT_NUM;i++){
            synchronized(synObj){
                while(flag != 3){
                    System.out.print("printC\n");
                    synObj.wait();
                }
                System.out.print('C');
                flag = 1;
                synObj.notifyAll();
            }
        }
    }

    class ThreadA implements Runnable {
        @Override
        public void run() {
            try {
                new Print().printA();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    class ThreadB implements Runnable {
        @Override
        public void run() {
            try {
                new Print().printB();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    class ThreadC implements Runnable {
        @Override
        public void run() {
            try {
                new Print().printC();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    @Test
    public   void main() throws InterruptedException {
        // TODO Auto-generated method stub
//        new Thread(new ThreadB()).start();
//        new Thread(new ThreadA()).start();
        new Thread(new ThreadC()).start();

        System.out.print("====================================================================");
        while(flag != 3){
            System.out.print("printC\n");
//            synObj.wait();
        }
    }
}