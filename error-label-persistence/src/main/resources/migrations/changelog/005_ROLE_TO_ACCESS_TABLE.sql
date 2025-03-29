--liquibase formatted sql

--changeset v.trofimchenko:add-role_to_access-table
create table if not exists role_to_access
(
    role_id bigint not null,
    access_id   bigint not null,

    primary key (role_id, access_id),
    foreign key (role_id) references user_role (id) on delete cascade,
    foreign key (access_id) references control_access (id) on delete cascade
);