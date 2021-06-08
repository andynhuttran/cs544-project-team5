create table person_role
(
    person_id integer not null
        constraint fkrl42c2tjmq9jpuksmlcxj3fwi
            references person,
    roles_id  integer not null
        constraint fka71db3wm3ngwrubt886865q4g
            references role,
    constraint person_role_pkey
        primary key (person_id, roles_id)
);

alter table person_role
    owner to zendo;

