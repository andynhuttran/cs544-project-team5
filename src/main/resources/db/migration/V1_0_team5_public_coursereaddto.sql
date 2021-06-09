create table coursereaddto
(
    id           integer not null
        constraint coursereaddto_pkey
            primary key,
    abbreviation varchar(5),
    name         varchar(255)
);

alter table coursereaddto
    owner to zendo;

