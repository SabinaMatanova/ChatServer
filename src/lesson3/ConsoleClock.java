package lesson3;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

public class ConsoleClock {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            public void run() {
                Calendar time = new GregorianCalendar();
                while (!isInterrupted()) {
                    System.out.println(time.get(Calendar.HOUR) + ":" + time.get(Calendar.MINUTE) + ":" + time.get(Calendar.SECOND));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                System.out.println("The clock was stopped");
            }
        };

        t1.start();
        Thread.currentThread().sleep(3000);
        t1.interrupt();
        System.out.println("Thread finished");
    }

}
