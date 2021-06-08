create table courseoffering
(
    id        integer not null
        constraint courseoffering_pkey
            primary key,
    capacity  integer not null,
    period    varchar(255),
    startdate date,
    blockid   integer
        constraint fklc3oag8u4jg3fl6uohk23c0s6
            references academicblock,
    courseid  integer
        constraint fk6nautvk22m3x6kndotbuf0rnr
            references course,
    facultyid integer
        constraint fk9imblephkln0vb028t82m79nh
            references faculty
);

alter table courseoffering
    owner to zendo;

