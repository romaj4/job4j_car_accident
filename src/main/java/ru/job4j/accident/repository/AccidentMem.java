package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements Repo {
    private final AtomicInteger id = new AtomicInteger(2);

    private final Map<Integer, Accident> accidents = new HashMap<>();

    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();

    public AccidentMem() {
        accidentTypes.put(1, AccidentType.of(1, "Две машины"));
        accidentTypes.put(2, AccidentType.of(2, "Машина и человек"));
        accidentTypes.put(3, AccidentType.of(3, "Машина и велосипед"));
        accidents.put(0, new Accident("Accident1", "description of the accident_1", "Street1", this.findAccidentTypeById(1)));
        accidents.put(1, new Accident("Accident2", "description of the accident_1", "Street2", this.findAccidentTypeById(2)));
        accidents.put(2, new Accident("Accident3", "description of the accident_1", "Street3", this.findAccidentTypeById(3)));
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
        accident.setAccidentType(this.findAccidentTypeById(accident.getAccidentType().getId()));
        accidents.put(accident.getId(), accident);
    }

    @Override
    public Accident findAccidentById(int id) {
        return accidents.get(id);
    }

    @Override
    public Collection<AccidentType> findAllAccidentType() {
        return this.accidentTypes.values();
    }

    @Override
    public AccidentType findAccidentTypeById(int id) {
        return this.accidentTypes.get(id);
    }
}
