package ru.tasks.task4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        splitArray(new int[]{17, 12, 10, 4, 9, 8}, 2);
        splitArray(new int[]{10, 11, 9, 3, 3, 3}, 3);
    }

    static void splitArray(int[] array, int n) throws Exception {
        List<Integer> list = new ArrayList<>();
        for (int elem : array) {
            list.add(elem);
        }
        System.out.println("Исходный массив: " + list + " поделить на " + n + " групп(ы)");
        int sum = list.stream().mapToInt(x -> x).sum();
        if (sum % n != 0) {
            System.out.println("Невозможно");
            return;
        }
        List<List<Integer>> groups = new ArrayList<>();
        List<Integer> group = new ArrayList<>();
        int needSum = sum / n;

        if (!searchEqualGroups(list, 0, needSum, groups, group, needSum, n)) {
            System.out.println("Невозможно");
            return;
        }
        System.out.println("Результат:");
        groups.forEach(System.out::println);

    }

    static boolean searchEqualGroups(List<Integer> list, int i, int limit, List<List<Integer>> groups, List<Integer> group, int needSum, int numberOfParts) {
        if (limit == 0) {
            groups.add(group);
            if (groups.size() == numberOfParts) {
                return true;
            } else {
                List<Integer> a = new ArrayList<>();
                for (Integer elem : group) {
                    for (int k = 0; k < list.size(); k++) {
                        if (list.get(k).compareTo(elem) == 0) {
                            list.remove(k);
                        }
                    }
                }
                List<Integer> newGroup = new ArrayList<>();
                if (searchEqualGroups(list, 0, needSum, groups, newGroup, needSum, numberOfParts)) {
                    return true;
                }
            }
        }
        if (limit < 0 || i == list.size()) {
            return false;
        }
        if (i > list.size()) {
            return false;
        }
        group.add(list.get(i));

        if (searchEqualGroups(list, i + 1, limit - list.get(i), groups, group, needSum, numberOfParts)) return true;
        group.remove(group.size() - 1);


        return searchEqualGroups(list, i + 1, limit, groups, group, needSum, numberOfParts);
    }
}


