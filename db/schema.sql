CREATE TABLE if not exists accident_types(
    id   serial primary key,
    name varchar);

CREATE TABLE if not exists rules(
    id   serial primary key,
    name varchar);

CREATE TABLE if not exists accidents(
    id               serial primary key,
    name             varchar,
    text             varchar,
    address          varchar,
    type_id int references accident_types (id)    );


CREATE TABLE if not exists accident_rule(
    accident_id int references accidents (id),
    rule_id     int references rules (id));

INSERT INTO accident_types (name) values ('Две машины');
INSERT INTO accident_types (name) values ('Машина и человек');
INSERT INTO accident_types (name) values ('Машина и велосипед');
INSERT INTO rules(name) values ('Статья 1');
INSERT INTO rules(name) values ('Статья 2');
INSERT INTO rules(name) values ('Статья 3');