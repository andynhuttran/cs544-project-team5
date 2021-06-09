create table academicblockreaddto
(
    id        integer      not null
        constraint academicblockreaddto_pkey
            primary key,
    begindate date         not null,
    enddate   date         not null,
    name      varchar(255) not null,
    semester  varchar(255) not null
);

alter table academicblockreaddto
    owner to zendo;

