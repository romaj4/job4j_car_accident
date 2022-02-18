package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements Repo {
    private final AtomicInteger id = new AtomicInteger(2);

    private final Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        accidents.put(0, new Accident("Accident1", "description of the accident_1", "Street1"));
        accidents.put(1, new Accident("Accident2", "description of the accident_1", "Street2"));
        accidents.put(2, new Accident("Accident3", "description of the accident_1", "Street3"));
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        return this.accidents.values();
    }

    @Override
    public void create(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(id.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    @Override
    public Accident findById(int id) {
        return accidents.get(id);
    }
}
