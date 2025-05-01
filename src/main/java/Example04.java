public class Example04 {

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++)
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                System.out.println("Hello from " + Thread.currentThread().getName());
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        MyRunnable r = new MyRunnable();
        Thread t = new Thread(r);
        t.start();
        try {
          Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.join();
        System.out.println("Main thread finished");
    }
}
