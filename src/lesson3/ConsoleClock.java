package lesson3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.Thread.sleep;

public class ConsoleClock extends Thread {
    @Override
    public void run() {
        while (!isInterrupted()) {
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Calendar cali = Calendar.getInstance();
            cali.getTime();
            String time = timeFormat.format(cali.getTimeInMillis());
            System.out.println(time);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }

        }
        System.out.println("The clock was stopped");

    }

    public static void main(String[] args) throws InterruptedException {
        ConsoleClock cc = new ConsoleClock();
        cc.start();
        Thread.sleep(3000);
        cc.interrupt();
    }
}
