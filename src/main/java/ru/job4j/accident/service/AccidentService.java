package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {
    private AccidentHibernate accidentMem;

    public AccidentService(AccidentHibernate accidentMem) {
        this.accidentMem = accidentMem;
    }

    public List<Accident> listAccident() {
        return new ArrayList<>(accidentMem.findAllAccidents());
    }
}
