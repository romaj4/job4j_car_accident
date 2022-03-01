package ru.job4j.accident.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.job4j.accident.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccidentTypeMapper implements RowMapper<AccidentType> {
    @Override
    public AccidentType mapRow(ResultSet resultSet, int i) throws SQLException {
        AccidentType accidentType = new AccidentType();
        accidentType.setId(resultSet.getInt("id"));
        accidentType.setName(resultSet.getString("name"));
        return accidentType;
    }
}
