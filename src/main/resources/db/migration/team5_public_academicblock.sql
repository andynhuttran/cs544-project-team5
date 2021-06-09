create table academicblock
(
    id        integer      not null
        constraint academicblock_pkey
            primary key,
    begindate date         not null,
    enddate   date         not null,
    name      varchar(255) not null,
    semester  varchar(255) not null
);

alter table academicblock
    owner to zendo;

