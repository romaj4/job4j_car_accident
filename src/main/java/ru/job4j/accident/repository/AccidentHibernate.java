package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

@Repository
public class AccidentHibernate implements Repo {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        try (Session session = sf.openSession()) {
            return session.createQuery("select distinct a from Accident a join fetch a.rules r", Accident.class)
                    .list();
        }
    }

    @Override
    public void create(Accident accident) {
        try (Session session = sf.openSession()) {
            session.saveOrUpdate(accident);
        }
    }

    @Override
    public Accident findAccidentById(int id) {
        try (Session session = sf.openSession()) {
            return (Accident) session.createQuery("select distinct  a from Accident a join fetch a.rules r where a.id=:id")
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    @Override
    public Collection<AccidentType> findAllAccidentType() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }

    @Override
    public AccidentType findAccidentTypeById(int id) {
        try (Session session = sf.openSession()) {
            return (AccidentType) session.createQuery("from AccidentType  where id=:id")
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    @Override
    public Collection<Rule> findAllRule() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule", Rule.class)
                    .list();
        }
    }

    @Override
    public Rule findRuleById(int id) {
        try (Session session = sf.openSession()) {
            return (Rule) session.createQuery("from Rule  where id=:id")
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    public void addRules(Accident accident, String[] rIds) {
        for (int i = 0; i < rIds.length; i++) {
            accident.addRule(this.findRuleById(Integer.parseInt(rIds[i])));
        }
    }
}