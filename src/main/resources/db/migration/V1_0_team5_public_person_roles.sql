create table person_roles
(
    person_id integer not null,
    roles     integer
);

alter table person_roles
    owner to zendo;

INSERT INTO public.person_roles (person_id, roles) VALUES (1, 0);