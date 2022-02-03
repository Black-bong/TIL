public class Ex13_10 {
    public static void main(String[] args) {
        MyThread th1 = new MyThread("1번");
        MyThread th2 = new MyThread("2번");
        MyThread th3 = new MyThread("3번");

        th1.start();
        th2.start();
        th3.start();

        try {
            Thread.sleep(2000);
            th1.suspend();
            Thread.sleep(2000);
            th2.suspend();
            Thread.sleep(3000);
            th1.resume();
            Thread.sleep(3000);
            th1.stop();
            th2.stop();
            Thread.sleep(2000);
            th3.stop();
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }
}

class MyThread implements Runnable {
    volatile boolean suspended = false;
    volatile boolean stopped = false;

    Thread th;

    MyThread(String name) {
        th = new Thread(this, name); // Thread(Runnable r, String name)
    }

    void start() {
        th.start();
    }

    void stop() {
        stopped = true;
    }

    void suspend() {
        suspended = true;
    }

    void resume() {
        suspended = false;
    }

    @Override
    public void run() {
        while (!stopped) {
            if (!suspended) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                }
            }
        }
    }
}