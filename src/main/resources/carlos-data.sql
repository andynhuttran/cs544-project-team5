use team5;


/* populate course*/
insert into dbo.Course( name, abbreviation) values('Enterprise Architecture', 'cs544')
insert into dbo.Course(name, abbreviation) values('Web Application Programming', 'cs472')
insert into dbo.Course( name, abbreviation) values('Algorithms', 'cs435')

/* populate courseDescription*/
insert into dbo.courseDescription(id, description) values(1, 'EA course description')
insert into dbo.courseDescription(id, description) values(2, 'Web Application Programming description')
insert into dbo.courseDescription(id, description) values(3, 'Algorithms description')

select * from dbo.Course c join dbo.courseDescription cd on c.id = cd.id

/* populate faculty*/
insert into dbo.Person(firstName, lastName, emailAddress) values('John', 'Doe', 'john@mail.com')

insert into dbo.Faculty(id, title) values(1, 'Professor')

select * from dbo.Person p join dbo.Faculty f on p.id = f.id

/* populate academicBlock*/
insert into dbo.AcademicBlock(id, beginDate, endDate, name, semester) 
	values(1, '2020-02-01', '2020-06-25', 'frist block', 'first semester')


/* populate courseOffering*/
insert into dbo.CourseOffering(capacity, period, startDate, blockId, courseId, facultyId) 
values(45, 'period 1', '2020-02-02', 1, 1, 1)


select * from dbo.CourseOffering co join dbo.Course c on co.courseId = c.id join dbo.Faculty f on 
f.id = co.facultyId join dbo.AcademicBlock ab on ab.id = co.blockId