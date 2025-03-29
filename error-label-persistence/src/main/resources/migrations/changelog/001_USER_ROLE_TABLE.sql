--liquibase formatted sql

--changeset v.trofimchenko:add-user_role-table
create table if not exists user_role
(
    id   serial primary key,
    name varchar(40) not null
);