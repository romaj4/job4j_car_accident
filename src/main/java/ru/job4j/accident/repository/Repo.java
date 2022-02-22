package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

public interface Repo {
    Collection<Accident> findAllAccidents();

    void create(Accident accident);

    Accident findAccidentById(int id);

    Collection<AccidentType> findAllAccidentType();

    AccidentType findAccidentTypeById(int id);

    Collection<Rule> findAllRule();

    Rule findRuleById(int id);
}