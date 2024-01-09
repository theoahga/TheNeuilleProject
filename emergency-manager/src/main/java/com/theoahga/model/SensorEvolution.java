package com.theoahga.model;

import java.util.List;

public class SensorEvolution {
    private List<Integer> values;

    public SensorEvolution(List<Integer> values){
        this.values = values;
    }

    public boolean off() {
        if (values.isEmpty()) {
            return true;
        }
        return values.get(values.size() - 1) == 0;
    }

    public boolean increase() {
        int size = values.size();
        if (size < 6) {
            for (int i = 1; i < size; i++) {
                if (values.get(i) <= values.get(i - 1)) {
                    return false;
                }
            }
            return true;
        }

        int lastIndex = size - 1;
        return values.get(lastIndex) > values.get(lastIndex - 1) &&
                values.get(lastIndex - 1) > values.get(lastIndex - 2) &&
                values.get(lastIndex - 2) > values.get(lastIndex - 3) &&
                values.get(lastIndex - 3) > values.get(lastIndex - 4) &&
                values.get(lastIndex - 4) > values.get(lastIndex - 5);
    }

    public boolean isStable() {
        int size = values.size();
        if (size < 10) {
            for (int i = 1; i < size; i++) {
                if (!values.get(i).equals(values.get(i - 1))) {
                    return false;
                }
            }
            return true;
        }

        int lastIndex = size - 1;
        return values.get(lastIndex) == values.get(lastIndex - 1) &&
                values.get(lastIndex - 1) == values.get(lastIndex - 2) &&
                values.get(lastIndex - 2) == values.get(lastIndex - 3) &&
                values.get(lastIndex - 3) == values.get(lastIndex - 4) &&
                values.get(lastIndex - 4) == values.get(lastIndex - 5) &&
                values.get(lastIndex - 5) == values.get(lastIndex - 6) &&
                values.get(lastIndex - 6) == values.get(lastIndex - 7) &&
                values.get(lastIndex - 7) == values.get(lastIndex - 8) &&
                values.get(lastIndex - 8) == values.get(lastIndex - 9);
    }

    public boolean decrease() {
        int size = values.size();
        if (size < 5) {
            for (int i = 1; i < size; i++) {
                if (values.get(i) >= values.get(i - 1)) {
                    return false;
                }
            }
            return true;
        }

        int lastIndex = size - 1;
        return values.get(lastIndex) < values.get(lastIndex - 1) &&
                values.get(lastIndex - 1) < values.get(lastIndex - 2) &&
                values.get(lastIndex - 2) < values.get(lastIndex - 3) &&
                values.get(lastIndex - 3) < values.get(lastIndex - 4);
    }
}
