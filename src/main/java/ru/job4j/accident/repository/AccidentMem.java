package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements Repo {
    private final AtomicInteger id = new AtomicInteger(2);

    private final Map<Integer, Accident> accidents = new HashMap<>();

    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();

    private final Map<Integer, Rule> rules = new HashMap<>();

    public AccidentMem() {
        accidentTypes.put(1, AccidentType.of(1, "Две машины"));
        accidentTypes.put(2, AccidentType.of(2, "Машина и человек"));
        accidentTypes.put(3, AccidentType.of(3, "Машина и велосипед"));
        rules.put(1, Rule.of(1, "Статья 1"));
        rules.put(2, Rule.of(2, "Статья 2"));
        rules.put(3, Rule.of(3, "Статья 3"));
        Accident accident1 = new Accident("Accident1", "description of the accident_1", "Street1", this.findAccidentTypeById(1));
        Accident accident2 = new Accident("Accident2", "description of the accident_2", "Street2", this.findAccidentTypeById(2));
        Accident accident3 = new Accident("Accident3", "description of the accident_3", "Street3", this.findAccidentTypeById(3));
        this.addRules(accident1, new String[]{"1", "2"});
        this.addRules(accident2, new String[]{"2", "3"});
        this.addRules(accident3, new String[]{"1", "2", "3"});
        accidents.put(0, accident1);
        accidents.put(1, accident2);
        accidents.put(2, accident3);
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

    @Override
    public Collection<Rule> findAllRule() {
        return this.rules.values();
    }

    @Override
    public Rule findRuleById(int id) {
        return this.rules.get(id);
    }

    public void addRules(Accident accident, String[] rIds) {
        for (int i = 0; i < rIds.length; i++) {
            accident.addRule(this.findRuleById(Integer.parseInt(rIds[i])));
        }
    }
}
