public class Example01 {
    static class mythread extends Thread {
        public void run (){
            for (int i = 1; i <= 10; i++) {
                System.out.println("greetings");
            }
        }
    }
    public static void main(String[] args)
    {
        mythread my = new mythread();
        my.start();
    }
}
