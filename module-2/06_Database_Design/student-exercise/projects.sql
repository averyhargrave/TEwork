drop table if exists employee cascade;
drop table if exists department cascade;
drop table if exists project cascade;
drop table if exists employee_project_mapping cascade;

create table employee
(
  employee_number   serial not null
, job_title         character varying (25) not null
, last_name         character varying (20) not null
, first_name        character varying (20) not null
, gender            character varying (10) not null
, date_of_birth     date not null
, date_of_hire      date not null
, department_number integer not null
, constraint pk_employee_number_employee_number PRIMARY KEY (employee_number)
)
;

create table department
(
 department_number serial not null
, name character varying (50) not null
, constraint pk_department_number_department_number PRIMARY KEY (department_number)
)
;

create table project
(
  project_number serial not null
, name           character varying (50)
, start_date     date not null
, constraint pk_project_number_project_number PRIMARY KEY (project_number)
)
;

create table employee_project_mapping
(
  employee_number int not null
, project_number  int not null
)
;

ALTER TABLE employee
ADD FOREIGN KEY(department_number)
REFERENCES department(department_number)
;

ALTER TABLE employee_project_mapping
ADD FOREIGN KEY(employee_number)
REFERENCES employee (employee_number)
;

ALTER TABLE employee_project_mapping
ADD FOREIGN KEY(project_number)
REFERENCES project (project_number)
;

insert into department
(name)
values('Dev Team')
;

insert into department
(name)
values('Quality')
;

insert into department
(name)
values('Management')
;

insert into employee
(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_number)
values('Instructor', 'Zappa', 'Frank', 'Male', '02/14/2021', '02/15/2021', 1)
;
insert into employee
(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_number)
values('Meatball-Man', 'Hoagie', 'Jason', 'Male', '04/20/1969', '01/01/2000', 1)
;

insert into employee
(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_number)
values('Queen', 'Cleopatra', 'N/A', 'Female', '01/02/0003', '12/30/1999', 2)
;

insert into employee
(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_number)
values('Mood Booster', 'Babbage', 'Malorie', 'Female', '12/12/1912', '11/11/2011', 2)
;

insert into employee
(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_number)
values('Complainer', 'Sargon', 'Akkad', 'Fale', '01/01/2015', '02/15/2021', 2)
;

insert into employee
(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_number)
values('Big Knuckles', 'Stephen', 'Steven', 'Male', '04/13/1995', '06/06/2006', 3)
;

insert into employee
(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_number)
values('Overlord', 'Kwondo', 'Rex', 'Male', '12/13/1960', '08/14/2002', 3)
;

insert into employee
(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_number)
values('Supreme Ruler', 'Dynamite', 'Napoleon', 'Female', '10/13/1995', '01/11/2021', 3)
;

insert into project
(name, start_date)
values('Capstone 1', '09/09/2020')
;

insert into project
(name, start_date)
values('Capstone 40', '11/11/2020')
;

insert into project
(name, start_date)
values('Capstone 69', '06/09/2021')
;

insert into project
(name, start_date)
values('Capstone 420', '04/20/2021')
;

insert into employee_project_mapping
(employee_number, project_number)
values((select employee_number from employee where first_name = 'Frank'), (select project_number from project where name = 'Capstone 1'))
;

insert into employee_project_mapping
(employee_number, project_number)
values((select employee_number from employee where first_name = 'Jason'), (select project_number from project where name = 'Capstone 40'))
;

insert into employee_project_mapping
(employee_number, project_number)
values((select employee_number from employee where first_name = 'Frank'), (select project_number from project where name = 'Capstone 69'))
;

insert into employee_project_mapping
(employee_number, project_number)
values((select employee_number from employee where first_name = 'Jason'), (select project_number from project where name = 'Capstone 420'))
;

select * from employee;
select * from project;
select * from department;
select * from employee_project_mapping;