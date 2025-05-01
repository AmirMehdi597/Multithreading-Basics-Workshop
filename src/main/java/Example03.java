public class Example03 {

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int j = 1; j <= 10; j++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " was interrupted, exiting loop.");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + ": greetings");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " caught InterruptedException, exiting.");
                    break;
                }
            }

            System.out.println(Thread.currentThread().getName() + " has finished running.");
        }
    }

    public static void main(String[] args) {

        MyRunnable runnable = new MyRunnable();
        Thread t1 = new Thread(runnable, "Thread-1");
        Thread t2 = new Thread(runnable, "Thread-2");

        t1.start();
        t2.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final State of t1: " + t1.getState());
        System.out.println("Final State of t2: " + t2.getState());
        System.out.println("Main thread finished.");
    }
}
