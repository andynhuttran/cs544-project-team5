create table person
(
    id        integer not null
        constraint person_pkey
            primary key,
    firstname varchar(255),
    lastname  varchar(255),
    password  varchar(255),
    username  varchar(255)
);

alter table person
    owner to zendo;

