create table barcoderecord
(
    id              integer not null
        constraint barcoderecord_pkey
            primary key,
    attendance      timestamp,
    classsession_id integer
        constraint fkfsh7oqpb2b5raicj12uwelci0
            references classsession,
    studentid       integer
        constraint fkl9ojhraicaufkowkan1anjsdi
            references student
);

alter table barcoderecord
    owner to zendo;

