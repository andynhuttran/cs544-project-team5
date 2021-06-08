create table student
(
    barcode   varchar(50) not null
        constraint uk_6xmhr0731ty108b4ouhfdisyh
            unique,
    entrydate date,
    studentid varchar(11) not null
        constraint uk_g3dyr509chkgf9iag58iae9q2
            unique,
    id        integer     not null
        constraint student_pkey
            primary key
        constraint fk3bkmp4kohbl54m14tb2fcqya3
            references person
);

alter table student
    owner to zendo;

INSERT INTO public.student (barcode, entrydate, studentid, id) VALUES ('b1', '2021-06-07', '001', 1);
INSERT INTO public.student (barcode, entrydate, studentid, id) VALUES ('b2', '2021-05-01', '002', 2);