create table location
(
    id           integer not null
        constraint location_pkey
            primary key,
    description  varchar(255),
    locationname varchar(255)
);

alter table location
    owner to zendo;

