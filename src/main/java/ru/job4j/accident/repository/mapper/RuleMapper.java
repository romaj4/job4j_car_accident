package ru.job4j.accident.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RuleMapper implements RowMapper<Rule> {
    @Override
    public Rule mapRow(ResultSet resultSet, int i) throws SQLException {
        Rule rule = new Rule();
        rule.setId(resultSet.getInt("id"));
        rule.setName(resultSet.getString("name"));
        return rule;
    }
}
