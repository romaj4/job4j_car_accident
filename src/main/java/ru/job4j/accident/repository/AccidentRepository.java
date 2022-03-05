package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query("select distinct a from Accident a join fetch a.rules r")
    List<Accident> findAll();

    @Query("select distinct a from Accident a join fetch a.rules r where a.id=?1")
    Accident findById(int id);
}