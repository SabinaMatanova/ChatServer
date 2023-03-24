//Рассмотрим программу, которая решает следующие задачи:
// ищет максимум в массиве чисел из 10 миллионов элементов,
// сортирует другой массив чисел, заполненный от 10 миллионов до 1,
// и убирает все элементы с последнего индекса из ArrayList, содержащего 10 миллионов чисел.
package lesson5;

import java.util.*;

public class MainOneThread2 {

    public static void main(String[] args) throws InterruptedException {

        Thread maxFromList = new Thread() {
            @Override
            public void run() {
                List<Integer> numbers = new ArrayList<>();
                for (int i = 1; i <= 10000000; i++) {
                    numbers.add(i);
                }
                int max = Collections.max(numbers);
                System.out.println("Max found: " + max);
            }
        };
        // 2 часть
        Thread sortedList = new Thread() {

            @Override
            public void run() {
                List<Integer> unsorted = new ArrayList<>();
                for (int i = 10000000; i >= 1; i--) {
                    unsorted.add(i);
                }
                Collections.sort(unsorted);
                System.out.println("List is sorted now");
            }
        };
        // 3 часть
        Thread removeList = new Thread() {
            @Override
            public void run() {
                List<Integer> list = new ArrayList<>();
                for( int i = 1; i <=10000000;i++) {
                    list.add(i);
                }
                while(list.size()!=0) {
                    list.remove(list.size() - 1);
                }
                System.out.println("List cleared");
            }
        };
        // Итог
        long start = System.currentTimeMillis();
        maxFromList.start();
        sortedList.start();
        removeList.start();

        maxFromList.join();
        sortedList.join();
        removeList.join();
        long end = System.currentTimeMillis();

//        Простейший способ замерить время — вызвать System.currentTimeMillis в начале и конце операций и вычислить их разницу.
        System.out.println("Total time: " + (end - start) + " ms");
    }

}