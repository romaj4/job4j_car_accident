package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccidentService {

    private AccidentRepository accidentRepository;

    private AccidentTypeRepository accidentTypeRepository;

    private RuleRepository ruleRepository;

    public AccidentService(AccidentRepository accidentRepository, AccidentTypeRepository accidentTypeRepository, RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    public List<Accident> listAccident() {
        List<Accident> listAccidents = new ArrayList<>();
        this.accidentRepository.findAll().forEach(listAccidents::add);
        return listAccidents;
    }

    public AccidentType findAccidentTypeById(int id) {
        return this.accidentTypeRepository.findById(id).get();
    }

    public Collection<Rule> findAllRule() {
        List<Rule> listRules = new ArrayList<>();
        this.ruleRepository.findAll().forEach(listRules::add);
        return listRules;
    }

    public void create(Accident accident, String[] rIds) {
        for (int i = 0; i < rIds.length; i++) {
            accident.addRule(this.ruleRepository.findById(Integer.parseInt(rIds[i])).get());
        }
        this.accidentRepository.save(accident);
    }

    public Collection<AccidentType> findAllAccidentType() {
        List<AccidentType> listTypes = new ArrayList<>();
        this.accidentTypeRepository.findAll().forEach(listTypes::add);
        return listTypes;
    }

    public Accident findAccidentById(int id) {
        return this.accidentRepository.findById(id);
    }
}
