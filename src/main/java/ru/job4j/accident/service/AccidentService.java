package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccidentService {
    private AccidentHibernate accidents;

    public AccidentService(AccidentHibernate accidentMem) {
        this.accidents = accidentMem;
    }

    public List<Accident> listAccident() {
        return new ArrayList<>(this.accidents.findAllAccidents());
    }

    public AccidentType findAccidentTypeById(int id) {
        return this.accidents.findAccidentTypeById(id);
    }

    public Collection<Rule> findAllRule() {
        return this.accidents.findAllRule();
    }

    public void create(Accident accident, String[] rIds) {
        for (int i = 0; i < rIds.length; i++) {
            accident.addRule(this.accidents.findRuleById(Integer.parseInt(rIds[i])));
        }
        this.accidents.create(accident);
    }

    public Collection<AccidentType> findAllAccidentType() {
        return this.accidents.findAllAccidentType();
    }

    public Accident findAccidentById(int id) {
        return this.accidents.findAccidentById(id);
    }
}
