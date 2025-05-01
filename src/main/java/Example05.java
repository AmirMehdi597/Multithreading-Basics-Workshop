public class Example05 {

    static class LoadingRunnable implements Runnable {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.print("\rloading   "); Thread.sleep(300);
                    System.out.print("\rloading.  "); Thread.sleep(300);
                    System.out.print("\rloading.. "); Thread.sleep(300);
                    System.out.print("\rloading..."); Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                System.out.println("\rLoading stopped.");
            }
        }
    }
    static class TaskRunnable implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(600);
                }
            } catch (InterruptedException e) {
                System.out.println("Task interrupted.");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread taskThread = new Thread(new TaskRunnable());
        Thread loadingThread = new Thread(new LoadingRunnable());

        loadingThread.start();
        taskThread.start();
        taskThread.join();
        loadingThread.interrupt();
        System.out.println();
        System.out.println("Task thread done.");
    }
}

