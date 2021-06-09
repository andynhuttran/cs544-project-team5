create table classsession
(
    id                integer not null
        constraint classsession_pkey
            primary key,
    date              date,
    courseoffering_id integer
        constraint fkqg2xjbk9w6484850sek13dcii
            references courseoffering,
    location_id       integer
        constraint fkoi7goddnvw6hucq3n2jmjnuvu
            references location,
    timeslot_id       integer
        constraint fk8wfxx9itvenk6ch75equp443c
            references timeslot
);

alter table classsession
    owner to zendo;

