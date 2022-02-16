package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem implements Repo {

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
}
