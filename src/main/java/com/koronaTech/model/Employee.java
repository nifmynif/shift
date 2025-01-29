package com.koronaTech.model;

import lombok.Getter;

@Getter
public class Employee extends Worker {
    public Employee(int id, String name, double salary, int managerId) {
        super(id, name, salary, String.valueOf(managerId));
    }

    @Override
    public String toString() {
        return "Employee," +
                super.toString() +
                "\n";
    }
}
