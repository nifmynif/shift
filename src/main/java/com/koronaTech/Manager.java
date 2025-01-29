package com.koronaTech;

import lombok.Getter;

@Getter
public class Manager extends Worker {

    public Manager(int id, String name, double salary, String department) {
        super(id, name, salary, department);
    }

    @Override
    public String toString() {
        return "Manager," +
                super.toString() +
                "\n";
    }
}
