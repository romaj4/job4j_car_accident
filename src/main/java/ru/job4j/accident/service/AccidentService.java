package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {
    private AccidentMem accidentMem = new AccidentMem();

    public List<Accident> listAccident() {
        return new ArrayList<>(accidentMem.findAllAccidents());
    }
}
