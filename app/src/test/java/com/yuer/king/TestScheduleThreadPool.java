package com.yuer.king;

import com.yuer.king.utils.LogUtils;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 类功能描述：</br>
 *
 * @author 于亚豪
 * @version 1.0 </p> 修改时间：2018/8/3</br> 修改备注：</br>
 */
public class TestScheduleThreadPool {
    private  static final long initialDelay = 0;
    private static final long period = 1;// 500 millseconds
    @Test
    public void test0(){
        System.out.print(">> 11111111111");
        //ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("scheduledPool-%d").build();
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(1);
        scheduledPool.scheduleAtFixedRate(
                new Account(),
                initialDelay,
                period,
                TimeUnit.SECONDS
        );

        Thread t=Thread.currentThread();
        t.suspend();
    }

    class Account implements Runnable{

        @Override
        public void run() {
            System.out.print(">> 11111111111");
            LogUtils.e("yyh",">> 11111111111");
        }

    }
}
