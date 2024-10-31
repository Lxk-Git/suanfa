package dcgpt.class04;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class TestCountDownLatch2 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        LatchDemo2 ld = new LatchDemo2(latch);
        Long start = System.currentTimeMillis();
/*        for (int i = 0; i < 10; i++) {
            new Thread(ld).start();
        }*/
        new Thread(ld).start();
        try {
            long count = latch.getCount();
            System.out.println("线程数目"+count);
            boolean await = latch.await(1, TimeUnit.SECONDS);//这10个线程执行完之前先等待
            System.out.println("boolean:"+await);
            long count2 = latch.getCount();
            System.out.println("线程数目2:"+count2);
        } catch (InterruptedException e) {
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));
    }
}
class LatchDemo2 implements Runnable {
    private CountDownLatch countDownLatch;
    public LatchDemo2(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        synchronized (this) {
            try {
                for (long i = 0; i < 5000; i++) {
                    if (i % 2 == 0) {
                        System.out.println(i);
                    }
                }
            } finally {
                //每执行完一个就递减一个
                countDownLatch.countDown();
            }

        }
    }
}
