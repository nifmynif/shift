package com.koronaTech.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class WorkerHandler<C extends Worker> {
    private List<C> workers = new ArrayList<>();

    public WorkerHandler(List<C> workers) {
        addWorkers(workers);
    }

    public void addWorker(C worker) {
        for (C w : workers)
            if (w.getId() == worker.getId())
                throw new IllegalArgumentException("Сотрудник с таким ID уже есть");
        workers.add(worker);
    }

    public void addWorkers(List<C> workers) {
        workers.forEach(this::addWorker);
    }

    public void addWorkers(WorkerHandler<C> workers) {
        workers.getWorkers().forEach(this::addWorker);
    }

    public C getWorker(int i) {
        return workers.get(i);
    }

    public int size() {
        return workers.size();
    }

    public void sortByName(boolean order) {
        workers = workers.stream()
                .sorted(order ? Comparator.comparing(Worker::getName)
                        : Comparator.comparing(Worker::getName).reversed())
                .collect(Collectors.toList());
    }

    public void sortBySalary(boolean order) {
        workers = workers.stream()
                .sorted(order ? Comparator.comparing(Worker::getSalary)
                        : Comparator.comparing(Worker::getSalary).reversed())
                .collect(Collectors.toList());
    }

    public String getReport() {
        List<C> workersTemp = new ArrayList<>(workers);
        List<C> errorWorkers = workersTemp.stream()
                .filter(worker -> worker.getSalary() < 0)
                .toList();
        workersTemp = workersTemp.stream()
                .filter(worker -> worker.getSalary() >= 0)
                .collect(Collectors.toList());
        Map<String, WorkerHandler<C>> departmentMap = new HashMap<>();
        List<String> departments = workersTemp.stream()
                .filter(worker -> worker instanceof Manager)
                .map(Worker::getDepartment)
                .distinct().sorted().toList();
        for (String department : departments)
            departmentMap.put(department, getWorkersOfDepartment(workersTemp, department));
        StringBuilder sb = new StringBuilder();
        departmentMap.forEach((k, v) -> sb.append(k).append("\n").append(v).append(v.getDepartmentStatistic()).append("\n"));
        sb.append("Некорректные данные:").append("\n");
        workersTemp.forEach(sb::append);
        errorWorkers.forEach(sb::append);
        return sb.toString();
    }

    private String getDepartmentStatistic() {
        return size() + ", " + getAverageSalary();
    }

    private String getAverageSalary() {
        double average = workers.stream()
                .map(Worker::getSalary)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        return BigDecimal.valueOf(average)
                .setScale(2, RoundingMode.CEILING).toString();
    }

    private WorkerHandler<C> getWorkersOfDepartment(List<C> workersTemp, String departmentName) {
        WorkerHandler<C> department = new WorkerHandler<>();
        List<C> managers = workersTemp.stream()
                .filter(w -> w.getDepartment().equals(departmentName))
                .toList();
        List<C> employers = workersTemp.stream()
                .filter(w -> managers.stream().anyMatch(m -> w.getDepartment().equals(String.valueOf(m.getId()))))
                .toList();
        workersTemp.removeAll(managers);
        workersTemp.removeAll(employers);
        department.addWorkers(managers);
        department.addWorkers(employers);
        return department;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        workers.forEach(sb::append);
        return sb.toString();
    }
}
