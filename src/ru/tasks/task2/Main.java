package ru.tasks.task2;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        System.out.println("Исходный массив:");
        System.out.println(Arrays.toString(arr));

        searchDuplicateValues(arr); //Через Map
        System.out.println(Arrays.toString(searchDuplicate(arr))); //Без коллекций
    }

    private static void searchDuplicateValues(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        //Используем ключ как элемент массива, значение как счетчик
        for (int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) == null) {
                map.put(arr[i], 1);
            } else {
                int value = map.get(arr[i]);
                map.put(arr[i], ++value);
            }
        }

        //Поиск максимального значения в счетчике
        Map.Entry<Integer, Integer> maxValue = Collections.max(map.entrySet(), ((o1, o2) -> o1.getValue().compareTo(o2.getValue())));
        //Поиск списка максимальных значений (для проверки, если их несколько)
        List<Map.Entry<Integer, Integer>> maxValues = map.entrySet()
                .stream()
                .filter(i -> i.getValue().compareTo(maxValue.getValue()) == 0)
                .collect(Collectors.toList());
        System.out.println("Наиболее встречающиеся числа:");
        for (Map.Entry<Integer, Integer> maxEntry : maxValues) {
            System.out.println(maxEntry.getKey() + " - " + maxEntry.getValue() + "раз(а)");
        }
    }


    public static int[] searchDuplicate(int[] arr) {
        int[] uniqueElements = new int[]{arr[0]};
        //Заполнение массива с уникальными элементами
        for (int i = 1; i < arr.length; i++) {
            int number = arr[i];
            if (indexOf(uniqueElements, number) == -1) {
                uniqueElements = Arrays.copyOf(uniqueElements, uniqueElements.length + 1);
                uniqueElements[uniqueElements.length - 1] = number;
            }
        }
        int[] counterNumberDuplicate = new int[uniqueElements.length];
        int max = 0;
        //Подсчет количества входов в оригинальный массив для каждого уникального элемента
        for (int number : arr) {
            int index = indexOf(uniqueElements, number);
            counterNumberDuplicate[index]++;
            if (counterNumberDuplicate[index] > max) {
                max = counterNumberDuplicate[index];
            }
        }
        int[] result = new int[0];
        //Поиск в массиве уникальных элементов таких элементов, вхождение которых в оригинальный максимально
        for (int i = 0; i < counterNumberDuplicate.length; i++) {
            if (counterNumberDuplicate[i] == max) {
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = uniqueElements[i];
            }
        }
        return result;
    }

    public static int indexOf(int[] array, int element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }
}
