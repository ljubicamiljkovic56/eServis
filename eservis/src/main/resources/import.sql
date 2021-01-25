use tseo;

set foreign_key_checks = 0;

truncate table user_authority;
truncate table authority;
truncate table user;
truncate table enrollment;
truncate table teaching;
truncate table exam;
truncate table student;
truncate table course;
truncate table teacher;
truncate table exam_period;

truncate table document;
truncate table type;
truncate table admin;
truncate table payment;
truncate table lecture;

set foreign_key_checks = 1;

insert into authority (name) values ('ROLE_ADMIN');
insert into authority (name) values ('ROLE_STUDENT');
insert into authority (name) values ('ROLE_TEACHER');

insert into user (username, password, authority_id) values ('admin1', '123', 1);
insert into user (username, password, authority_id) values ('admin2', '123', 1);

insert into user (username, password, authority_id) values ('sf1-2017', '123', 2);
insert into user (username, password, authority_id) values ('sf2-2017', '123', 2);
insert into user (username, password, authority_id) values ('sf3-2017', '123', 2);
insert into user (username, password, authority_id) values ('sf4-2017', '123', 2);
insert into user (username, password, authority_id) values ('sf5-2017', '123', 2);
insert into user (username, password, authority_id) values ('sf6-2017', '123', 2);
insert into user (username, password, authority_id) values ('sf7-2017', '123', 2);
insert into user (username, password, authority_id) values ('sf8-2017', '123', 2);
insert into user (username, password, authority_id) values ('sf9-2017', '123', 2);

insert into user (username, password, authority_id) values ('teacher1', '123', 3);
insert into user (username, password, authority_id) values ('teacher2', '123', 3);
insert into user (username, password, authority_id) values ('teacher3', '123', 3);



insert into user_authority (user_id, authority_id) values (1, 1); 
insert into user_authority (user_id, authority_id) values (2, 1);

insert into user_authority (user_id, authority_id) values (3, 2);
insert into user_authority (user_id, authority_id) values (4, 2);
insert into user_authority (user_id, authority_id) values (5, 2);
insert into user_authority (user_id, authority_id) values (6, 2);
insert into user_authority (user_id, authority_id) values (7, 2);
insert into user_authority (user_id, authority_id) values (8, 2);
insert into user_authority (user_id, authority_id) values (9, 2);
insert into user_authority (user_id, authority_id) values (10, 2);
insert into user_authority (user_id, authority_id) values (11, 2);

insert into user_authority (user_id, authority_id) values (12, 3);
insert into user_authority (user_id, authority_id) values (13, 3);
insert into user_authority (user_id, authority_id) values (14, 3);

insert into admin(firstname, lastname, user_id) values ('Mile','Milić', 1);
insert into admin(firstname, lastname, user_id) values ('Petar','Petrovic', 2);

insert into student (card_number, first_name, last_name, user_id) values ('sf1-2014', 'Marko', 'Marković', 3);
insert into student (card_number, first_name, last_name, user_id) values ('sf2-2014', 'Milan', 'Milanović', 4);
insert into student (card_number, first_name, last_name, user_id) values ('sf3-2014', 'Ivana', 'Novaković', 5);
insert into student (card_number, first_name, last_name, user_id) values ('sf4-2014', 'Bojan', 'Bojić', 6);
insert into student (card_number, first_name, last_name, user_id) values ('sf5-2014', 'Jelena', 'Marković', 7);
insert into student (card_number, first_name, last_name, user_id) values ('sf6-2014', 'Zoran', 'Zoranović', 8);
insert into student (card_number, first_name, last_name, user_id) values ('sf7-2014', 'Milica', 'Petrović', 9);
insert into student (card_number, first_name, last_name, user_id) values ('sf8-2014', 'Petar', 'Petrović', 10);
insert into student (card_number, first_name, last_name, user_id) values ('sf9-2014', 'Igor', 'Jovin', 11);

insert into type(name) values ('UPISNICA');
insert into type(name) values ('ISPISNICA');
insert into type(name) values ('DIPLOMA');
insert into type(name) values ('UVERENJE');

insert into document (type_id, student_id) values (1, 1);
insert into document (type_id, student_id) values (1, 4);
insert into document (type_id, student_id) values (2, 1);




insert into teacher (first_name, last_name, title, user_id) values ('Milan', 'Jovanovic', 'nastavnik', 12);
insert into teacher (first_name, last_name, title, user_id) values ('Sanja', 'Stanic', 'asistent', 13);
insert into teacher (first_name, last_name, title, user_id) values ('Nemanja', 'Jankovic', 'demonstrator', 14);

insert into course (name, espb, semester, teacher_id) values ('Matematika', 8, 'zimski', 1);
insert into course (name, espb, semester, teacher_id) values ('Osnove programiranja', 8, 'zimski', 2);
insert into course (name, espb, semester, teacher_id) values ('Objektno programiranje', 6, 'letnji', 3);

insert into teaching (course_id, teacher_id) values (1, 1);
insert into teaching (course_id, teacher_id) values (2, 2);
insert into teaching (course_id, teacher_id) values (3, 3);

insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 1, 1);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 2, 1);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 3, 1);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 4, 1);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 1, 2);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 2, 2);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 3, 2);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 5, 1);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 6, 2);


insert into exam_period (name, start_date, end_date) values ('Januarski 2020', '2020-01-27', '2020-02-25');
insert into exam_period (name, start_date, end_date) values ('Aprilski 2020', '2020-04-18', '2020-04-23');
insert into exam_period (name, start_date, end_date) values ('Junski 2020', '2020-06-15', '2020-07-14');
insert into exam_period (name, start_date, end_date) values ('Septembarski 2020', '2020-08-27', '2020-09-25');

insert into exam (student_id, course_id, exam_period_id, datum, exam_points, lab_points) values (
	1, 1, 1, '2016-02-01', 20, 70);
insert into exam (student_id, course_id, exam_period_id, datum, exam_points, lab_points) values (
	1, 2, 2, '2016-04-19', 15, 55);
insert into exam (student_id, course_id, exam_period_id, datum, exam_points, lab_points) values (
	2, 1, 1, '2016-02-01', 18, 60);
insert into exam (student_id, course_id, exam_period_id, datum, exam_points, lab_points) values (
	2, 2, 2, '2016-04-19', 17, 57);


insert into payment(svrha, amount, datum, student_id) values ('Prijava ispita', 400.0, '2020-08-20', 2);
insert into payment(svrha, amount, datum, student_id) values ('Prijava ispita', 200.0, '2020-08-27', 3);
insert into payment(svrha, amount, datum, student_id) values ('Overa semestra', 2000.0,'2020-06-17', 3);
insert into payment(svrha, amount, datum, student_id) values ('Prijava ispita', 400.0, '2020-08-25', 4);
insert into payment(svrha, amount, datum, student_id) values ('Overa semestra', 2000.0,'2020-06-05', 4);
insert into payment(svrha, amount, datum, student_id) values ('Prijava ispita', 400.0, '2020-08-20', 5);
insert into payment(svrha, amount, datum, student_id) values ('Prijava ispita', 800.0, '2020-05-27', 5);
insert into payment(svrha, amount, datum, student_id) values ('Overa semestra', 2000.0, '2020-06-17', 6);
insert into payment(svrha, amount, datum, student_id) values ('Prijava ispita', 400.0, '2020-08-25', 6);
insert into payment(svrha, amount, datum, student_id) values ('Overa semestra', 2000.0, '2020-06-17', 7);
insert into payment(svrha, amount, datum, student_id) values ('Prijava ispita', 200.0, '2020-08-20', 7);
insert into payment(svrha, amount, datum, student_id) values ('Prijava ispita', 600.0, '2020-08-27', 8);
insert into payment(svrha, amount, datum, student_id) values ('Overa semestra', 2000.0, '2020-06-17', 8);
	

insert into lecture(course_id) values (1);
