--liquibase formatted sql

--changeset v.trofimchenko:add-project-table
create table if not exists project (
    id serial primary key,
    project_name varchar(255) not null ,
    user_id bigint not null,
    created_at timestamp not null,

    foreign key (user_id) references customer (id) on delete cascade
);