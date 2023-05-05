
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

---------------------
drop table users;

alter table users
    alter column id drop default,

    alter id TYPE UUID USING  uuid_generate_v4(),
    alter id SET DEFAULT uuid_generate_v4();

alter table users

    alter id TYPE varchar;


alter table users
    alter guid SET DEFAULT uuid_generate_v4();

drop table users;


insert into users (first_name, last_name, email,role)
values ('Obai', 'Mohammad', 'Obai@gmail.com','STUDENT');

--------------

CREATE TABLE courses
(
    id         serial PRIMARY KEY,
    title      VARCHAR(50) NOT NULL,
    credits    int         NOT NULL,
    created_at  TIMESTAMP  default now(),
    updated_at TIMESTAMP default now()
);

------------

CREATE TABLE course_student
(
    student_id INT,
    course_id  INT,
    CONSTRAINT fk_student
        FOREIGN KEY (student_id)
            REFERENCES users (id) on delete cascade,
    CONSTRAINT fk_course
        FOREIGN KEY (course_id)
            REFERENCES courses (id) on delete cascade,
    constraint fk_unique unique (student_id,course_id)
);
-----------------