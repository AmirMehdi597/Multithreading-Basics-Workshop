public class Example02 {
    static class myrunnable implements Runnable{
        @Override
        public void run() {
            for (int i = 1; i <= 20; i++) {
                System.out.println("greetings");
            }
        }
    }
    public static void main(String[] args)
    {
       myrunnable n = new myrunnable();
       Thread t1 = new Thread(n);
       t1.start();
    }
}
