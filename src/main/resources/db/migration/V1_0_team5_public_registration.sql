create table registration
(
    id          integer not null
        constraint registration_pkey
            primary key,
    date        date,
    offering_id integer
        constraint fks9nefgh6tqtqkdyxffah7g19f
            references courseoffering,
    student_id  integer
        constraint fkeb2bnppytukjyrylpvnfxto1r
            references student
);

alter table registration
    owner to zendo;

