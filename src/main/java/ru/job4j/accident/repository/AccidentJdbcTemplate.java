package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.mapper.AccidentTypeMapper;
import ru.job4j.accident.repository.mapper.RuleMapper;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class AccidentJdbcTemplate implements Repo {
    private final JdbcTemplate jdbc;

    private final RuleMapper ruleMapper;

    private final AccidentTypeMapper accidentTypeMapper;

    public AccidentJdbcTemplate(JdbcTemplate jdbc, RuleMapper ruleMapper, AccidentTypeMapper accidentTypeMapper) {
        this.jdbc = jdbc;
        this.ruleMapper = ruleMapper;
        this.accidentTypeMapper = accidentTypeMapper;
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        return jdbc.query("select id, name, text, address, accident_type_id from accidents order by id", (rs, row) -> {
            Accident accident = new Accident(rs.getString("name"), rs.getString("text"), rs.getString("address"),
                    this.findAccidentTypeById(rs.getInt("accident_type_id")));
            accident.setId(rs.getInt("id"));
            accident.setRules(this.findAccidentRulesById(accident.getId()));
            return accident;
        });
    }

    @Override
    public void create(Accident accident) {
        if (accident.getId() == 0) {
            save(accident);
        } else {
            update(accident);
        }
    }

    private void save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into accidents (name,text,address,accident_type_id) values (?,?,?,?)",
                    new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getAccidentType().getId());
            return ps;
        }, keyHolder);
        updateAccidentRule(accident, keyHolder.getKey().intValue());
    }

    private void update(Accident accident) {
        jdbc.update("update accidents set name=?,text=?, address=?, accident_type_id=? where id=?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getAccidentType().getId(),
                accident.getId());
        jdbc.update("delete from accidents_rules where accidents_id=?",
                accident.getId());
        updateAccidentRule(accident, accident.getId());
    }

    public void addRules(Accident accident, String[] rIds) {
        for (int i = 0; i < rIds.length; i++) {
            accident.addRule(this.findRuleById(Integer.parseInt(rIds[i])));
        }
    }

    public void updateAccidentRule(Accident accident, int id) {
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accidents_rules (accidents_id, rules_id) values (?, ?)",
                    id,
                    rule.getId());
        }
    }

    private Set<Rule> findAccidentRulesById(int id) {
        List<Integer> rules = jdbc.query(
                "select rules_id from accidents_rules where accidents_id = ?",
                (rs, row) -> rs.getInt("rules_id"), id);
        return rules.stream().map(this::findRuleById).collect(Collectors.toSet());
    }

    @Override
    public Accident findAccidentById(int id) {
        return jdbc.queryForObject("select id , name, text, address, accident_type_id from accidents where id=?",
                (rs, row) -> {
                    Accident accident = new Accident(rs.getString("name"), rs.getString("text"),
                            rs.getString("address"), this.findAccidentTypeById(rs.getInt("accident_type_id")));
                    accident.setId(rs.getInt("id"));
                    return accident;
                }, id);
    }

    @Override
    public Collection<AccidentType> findAllAccidentType() {
        return jdbc.query("select id, name from accident_types", accidentTypeMapper);
    }

    @Override
    public AccidentType findAccidentTypeById(int id) {
        return jdbc.queryForObject("select id, name from accident_types where id=?", accidentTypeMapper, id);
    }

    @Override
    public Collection<Rule> findAllRule() {
        return jdbc.query("select id, name from rules", ruleMapper);
    }

    @Override
    public Rule findRuleById(int id) {
        return jdbc.queryForObject("select id, name from rules where id=?", ruleMapper, id);
    }
}