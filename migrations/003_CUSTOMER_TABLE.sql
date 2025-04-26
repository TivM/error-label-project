--liquibase formatted sql

--changeset v.trofimchenko:add-customer-table
create table if not exists customer
(
    id       serial primary key,
    name     varchar(40) not null,
    surname  varchar(40),
    email    varchar(50) not null,
    password varchar(255) not null
);