create table coursedescription
(
    description varchar(4000),
    id          integer not null
        constraint coursedescription_pkey
            primary key
        constraint fkhgrhbrfyk1mhtkps2elu5hslr
            references course
);

alter table coursedescription
    owner to zendo;

