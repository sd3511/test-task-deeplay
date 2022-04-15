package ru.tasks.task1;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = new int[101];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println();

        int countOdd = 0;
        //Нечетные влево, четные вправо
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] % 2 != 0) {
                        countOdd++;
                        swap(arr, i, j);
                        break;
                    }
                }
            } else countOdd++;
        }
        //Сортировка нечетных по неубыванию
        Arrays.sort(arr, 0, countOdd);
        //Сортировка четных по неубванию (для отсечение нулей)
        Arrays.sort(arr, countOdd, arr.length);
        //Разворот отсортированной четной части = Сортировка по невозрастанию
        reverseSortEven(arr, countOdd);
        System.out.println(Arrays.toString(arr));

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void reverseSortEven(int[] arr, int lastOddIndex) {


        int lastZeroIndex = 0;
        //Поиск индекса последнего вхождения нуля в массив
        for (int i = lastOddIndex; i < arr.length; i++) {
            if (arr[i] == 0) {
                lastZeroIndex = i;
            }
        }
        if (lastZeroIndex != 0) {
            lastOddIndex = lastZeroIndex;
        }
        int j = 0;
        //Меняем местами
        for (int i = lastOddIndex; i < lastOddIndex + (arr.length - lastOddIndex) / 2; i++) {
            swap(arr, i, arr.length - 1 - j);
            j++;


        }
    }
}
