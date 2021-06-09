create table course
(
    id           integer not null
        constraint course_pkey
            primary key,
    abbreviation varchar(5),
    name         varchar(255)
);

alter table course
    owner to zendo;

