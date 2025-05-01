import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Example06 {
    static class LoadingRunnable implements Runnable {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.print("\rHashing   "); Thread.sleep(300);
                    System.out.print("\rHashing.  "); Thread.sleep(300);
                    System.out.print("\rHashing.. "); Thread.sleep(300);
                    System.out.print("\rHashing..."); Thread.sleep(300);
                }
            } catch (InterruptedException e) {
            }
        }
    }
    static class HashingTask implements Runnable {
        @Override
        public void run() {
            Path inputFile = Paths.get("F:\\Multithreading-Basics-Workshop\\src\\main\\resources\\plain-text-passwords.txt");
            Path outputFile = Paths.get("hashed_passwords.txt");

            try (BufferedReader reader = Files.newBufferedReader(inputFile);
                 BufferedWriter writer = Files.newBufferedWriter(outputFile)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String hashed = hashText(line);
                    writer.write(hashed);
                    writer.newLine();
                    Thread.sleep(500);
                }

            } catch (IOException | InterruptedException e) {
                System.err.println("Error in hashing task: " + e.getMessage());
            }
        }

        public String hashText(String input) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashedBytes = md.digest(input.getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : hashedBytes) {
                    sb.append(String.format("%02x", b));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("SHA-256 not supported", e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread hashingThread = new Thread(new HashingTask());
        Thread loadingThread = new Thread(new LoadingRunnable());

        loadingThread.start();
        hashingThread.start();

        hashingThread.join();
        loadingThread.interrupt();

        System.out.println();
        System.out.println("Hashing completed. Output saved in 'hashed_passwords.txt'.");
    }
}

