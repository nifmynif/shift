package com.koronaTech;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class Worker {
    private int id;
    private String name;
    private double salary;
    private String department;

    @Override
    public String toString() {
        return id + "," +
                name + "," +
                salary + "," +
                department;
    }
}
