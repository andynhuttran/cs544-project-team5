create table timeslot
(
    id         integer not null
        constraint timeslot_pkey
            primary key,
    endtime    time,
    starttime  time,
    timeslotid varchar(255)
        constraint uk_grxebwnu2nhefxsbdb2pr4a2w
            unique,
    title      varchar(255)
);

alter table timeslot
    owner to zendo;

