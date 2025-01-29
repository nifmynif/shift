package com.koronaTech.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        workers.forEach(sb::append);
        return sb.toString();
    }
}
