package com.yuer.king;

import android.graphics.Paint;

import com.yuer.king.model.RObject;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
      static RObject.MyInputType[] sStyleArray = {
            RObject.MyInputType.Type1, RObject.MyInputType.Type2, RObject.MyInputType.Type3, RObject.MyInputType.Type4
    };
    @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
        // 2. 遍历对象
        TestMainThread sb1 = new  TestMainThread();
        TestMainThread sb2 = new  TestMainThread();
        sb1.flag = true;
        sb2.flag = false;
        System.out.print("================================================================");
        new Thread(sb1).start();
//        new Thread(sb2).start();
    }
    private String sb1 = new String();
    private String sb2 = new String();

    private class TestMainThread implements Runnable{
        boolean flag = false;
        String threadName = Thread.currentThread().getName();

        @Override
        public void run() {
            if(flag){
                synchronized(sb1){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(threadName+"已经进入到线程A，马上就要进入到线程B了");
                    synchronized (sb2){
                        System.out.print(threadName+"进入到线程B了");
                    }
                }
            }else{
                synchronized (sb2){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(threadName+"已经进入到线程B，马上就要进入到线程A了");
                    synchronized (sb1){
                        System.out.print(threadName+"进入到线程A了");
                    }
                }
            }
        }
    }
}