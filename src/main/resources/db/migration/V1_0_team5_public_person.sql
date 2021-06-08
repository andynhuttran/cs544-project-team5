create table person
(
    id        integer not null
        constraint person_pkey
        primary key,
    firstname varchar(255),
    lastname  varchar(255)
);

alter table person
    owner to zendo;

INSERT INTO public.person (id, firstname, lastname)
VALUES (1, 'ree', 'bk');
INSERT INTO public.person (id, firstname, lastname)
VALUES (2, 'abd', 'ali');
