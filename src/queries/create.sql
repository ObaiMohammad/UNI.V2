
create database padova;


CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table users
(
    id         serial primary key,
    first_name varchar   not null,
    last_name  varchar   not null,
    email      varchar   not null,
    guid       uuid DEFAULT uuid_generate_v4 (),
    role       varchar   not null,
    created_at  TIMESTAMP  default now(),
    updated_at TIMESTAMP default now()
);

alter table users
    alter column guid drop default,
    alter guid TYPE UUID USING  uuid_generate_v4(),
    alter guid SET DEFAULT uuid_generate_v4();

alter table users
    alter guid SET DEFAULT uuid_generate_v4();

drop table users;

drop database unipd;

insert into users (first_name, last_name, email,role)
values ('Obai', 'Mohammad', 'Obai@gmail.com','STUDENT');