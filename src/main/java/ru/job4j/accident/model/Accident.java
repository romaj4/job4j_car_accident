package ru.job4j.accident.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Accident {
    private int id;

    private String name;

    private String text;

    private String address;

    private AccidentType accidentType;

    private Set<Rule> rules = new HashSet<>();

    public Accident(String name, String text, String address, AccidentType accidentType) {
        this.name = name;
        this.text = text;
        this.address = address;
        this.accidentType = accidentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(AccidentType accidentType) {
        this.accidentType = accidentType;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Accident: id=%s, name=%s, text=%s, address=%s, accidentType=%s, rules=%s", id, name, text, address, accidentType, rules);
    }
}
