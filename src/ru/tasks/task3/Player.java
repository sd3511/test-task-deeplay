package ru.tasks.task3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Player {
    private String name;
    private List<Integer> selectedSequence;
    private Integer numberOfPoints;

    public Player(String name, int first, int second, int third) {
        this.name = name;
        selectedSequence = Arrays.asList(first, second, third);
        numberOfPoints = 0;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Integer numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public List<Integer> getSelectedSequence() {
        return Collections.unmodifiableList(selectedSequence);
    }
}
