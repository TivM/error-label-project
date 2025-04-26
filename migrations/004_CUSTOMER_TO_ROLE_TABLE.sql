--liquibase formatted sql

--changeset v.trofimchenko:add-customer_to_role-table
create table if not exists customer_to_role
(
    customer_id  bigint  not null,
    role_id     bigint  not null,

    primary key (customer_id, role_id),
    foreign key (customer_id) references customer (id) on delete cascade,
    foreign key (role_id) references user_role (id) on delete cascade
);

