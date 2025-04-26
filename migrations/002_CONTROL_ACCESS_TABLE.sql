--liquibase formatted sql

--changeset v.trofimchenko:add-control_access-table
create table if not exists control_access
(
    id   serial primary key,
    name varchar(40) not null
);