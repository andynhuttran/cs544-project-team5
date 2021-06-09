create table faculty
(
    title varchar(255),
    id    integer not null
        constraint faculty_pkey
            primary key
        constraint fkmjipb8roamp147iwf5onc3fof
            references person
);

alter table faculty
    owner to zendo;

