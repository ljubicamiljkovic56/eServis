-- drop database tseo;
-- create database tseo;
-- koristi db tseo
use tseo;

set foreign_key_checks = 0;


-- delete all rows
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

truncate table title;
truncate table teacher_title;
truncate table document;
truncate table document_type;
truncate table admin;
truncate table payment;


set foreign_key_checks = 1;

insert into authority (name) values ('ROLE_ADMIN');
insert into authority (name) values ('ROLE_STUDENT');
insert into authority (name) values ('ROLE_TEACHER');

insert into user (username, password) values ('admin1', '123');
insert into user (username, password) values ('admin2', '123');

insert into user (username, password) values ('sf1-2017', '123');
insert into user (username, password) values ('sf2-2017', '123');
insert into user (username, password) values ('sf3-2017', '123');
insert into user (username, password) values ('sf4-2017', '123');
insert into user (username, password) values ('sf5-2017', '123');
insert into user (username, password) values ('sf6-2017', '123');
insert into user (username, password) values ('sf7-2017', '123');
insert into user (username, password) values ('sf8-2017', '123');
insert into user (username, password) values ('sf9-2017', '123');

insert into user (username, password) values ('teacher1', '123');
insert into user (username, password) values ('teacher2', '123');
insert into user (username, password) values ('teacher3', '123');



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



insert into student (card_number, first_name, last_name) values ('sf1-2014', 'Marko', 'Marković');
insert into student (card_number, first_name, last_name) values ('sf2-2014', 'Milan', 'Milanović');
insert into student (card_number, first_name, last_name) values ('sf3-2014', 'Ivana', 'Novaković');
insert into student (card_number, first_name, last_name) values ('sf4-2014', 'Bojan', 'Bojić');
insert into student (card_number, first_name, last_name) values ('sf5-2014', 'Jelena', 'Marković');
insert into student (card_number, first_name, last_name) values ('sf6-2014', 'Zoran', 'Zoranović');
insert into student (card_number, first_name, last_name) values ('sf7-2014', 'Milica', 'Petrović');
insert into student (card_number, first_name, last_name) values ('sf8-2014', 'Petar', 'Petrović');
insert into student (card_number, first_name, last_name) values ('sf9-2014', 'Igor', 'Jovin');

-- tbl za dokumente i tipove dokumenata
insert into document_type (name) values ('UPISNICA');
insert into document_type (name) values ('ISPISNICA');
insert into document_type (name) values ('DIPLOMA');
insert into document_type (name) values ('UVERENJE');

insert into document (document_types_id, student_id) values (1, 1);
insert into document (document_types_id, student_id) values (1, 4);
insert into document (document_types_id, student_id) values (2, 1);

insert into course (name) values ('Matematika');
insert into course (name) values ('Osnove programiranja');
insert into course (name) values ('Objektno programiranje');

insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 1, 1);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 2, 1);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 3, 1);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 4, 1);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 1, 2);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 2, 2);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 3, 2);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 5, 1);
insert into enrollment (start_date, end_date, student_id, course_id) values ('2015-01-01', '2015-06-01', 6, 2);

-- obrisala sam rank
insert into teacher (first_name, last_name) values ('Milan', 'Jovanovic');
insert into teacher (first_name, last_name) values ('Sanja', 'Stanic');
insert into teacher (first_name, last_name) values ('Nemanja', 'Jankovic');

-- ove dve tabele sam dodala
insert into title (name) values ('DOCENT');
insert into title (name) values ('ASISTENT');
insert into title (name) values ('DEMONSTRATOR');

insert into teacher_title (teacher_id, title_id) values (1, 1);
insert into teacher_title (teacher_id, title_id) values (2, 2);
insert into teacher_title (teacher_id, title_id) values (3, 3);

insert into teaching (course_id, teacher_id) values (1, 1);
insert into teaching (course_id, teacher_id) values (1, 2);
insert into teaching (course_id, teacher_id) values (2, 2);
insert into teaching (course_id, teacher_id) values (3, 3);

insert into exam_period (name, start_date, end_date) values ('Januarski 2020', '2020-01-27', '2020-02-25');
insert into exam_period (name, start_date, end_date) values ('Aprilski 2020', '2020-04-18', '2020-04-23');
insert into exam_period (name, start_date, end_date) values ('Junski 2020', '2020-06-15', '2020-07-14');
insert into exam_period (name, start_date, end_date) values ('Septembarski 2020', '2020-08-27', '2020-09-25');

insert into exam (student_id, course_id, exam_period_id, date, exam_points, lab_points) values (
	1, 1, 1, '2016-02-01', 20, 70);
insert into exam (student_id, course_id, exam_period_id, date, exam_points, lab_points) values (
	1, 2, 2, '2016-04-19', 15, 55);
insert into exam (student_id, course_id, exam_period_id, date, exam_points, lab_points) values (
	2, 1, 1, '2016-02-01', 18, 60);
insert into exam (student_id, course_id, exam_period_id, date, exam_points, lab_points) values (
	2, 2, 2, '2016-04-19', 17, 57);

insert into payment (student_id, reason_for_payment, receiver, amount, recievers_account, model, poziv_na_broj, payment_date) 
	values (1, 'Prijava ispita', 'FTN', 400.00, '840-1710666-12', 97, '33-99011-1-4532-178', '2020-08-25');
insert into payment (student_id, reason_for_payment, receiver, amount, recievers_account, model, poziv_na_broj, payment_date) 
	values (1, 'Overa semestra', 'FTN', 2000.00, '840-1710666-12', 97, '33-99011-1-4532-178', '2020-06-05');
insert into payment (student_id, reason_for_payment, receiver, amount, recievers_account, model, poziv_na_broj, payment_date) 
	values (2, 'Prijava ispita', 'FTN', 400.00, '840-1710666-12', 97, '33-99011-1-4532-123', '2020-08-20');
insert into payment (student_id, reason_for_payment, receiver, amount, recievers_account, model, poziv_na_broj, payment_date) 
	values (3, 'Prijava ispita', 'FTN', 200.00, '840-1710666-12', 97, '33-99011-1-4532-144', '2020-08-27');
insert into payment (student_id, reason_for_payment, receiver, amount, recievers_account, model, poziv_na_broj, payment_date) 
	values (3, 'Overa semestra', 'FTN', 2000.00, '840-1710666-12', 97, '33-99011-1-4532-168', '2020-06-17');
insert into payment (student_id, reason_for_payment, receiver, amount, recievers_account, model, poziv_na_broj, payment_date) 
	values (4, 'Prijava ispita', 'FTN', 400.00, '840-1710666-12', 97, '33-99011-1-4532-188', '2020-08-25');
insert into payment (student_id, reason_for_payment, receiver, amount, recievers_account, model, poziv_na_broj, payment_date) 
	values (4, 'Overa semestra', 'FTN', 2000.00, '840-1710666-12', 97, '33-99011-1-4532-188', '2020-06-05');
insert into payment (student_id, reason_for_payment, receiver, amount, recievers_account, model, poziv_na_broj, payment_date) 
	values (5, 'Prijava ispita', 'FTN', 400.00, '840-1710666-12', 97, '33-99011-1-4532-134', '2020-08-20');
insert into payment (student_id, reason_for_payment, receiver, amount, recievers_account, model, poziv_na_broj, payment_date) 
values (5, 'Prijava ispita', 'FTN', 800.00, '840-1710666-12', 97, '33-99011-1-4532-134', '2020-05-27');
insert into payment (student_id, reason_for_payment, receiver, amount, recievers_account, model, poziv_na_broj, payment_date) 
	values (6, 'Overa semestra', 'FTN', 2000.00, '840-1710666-12', 97, '33-99011-1-4532-160', '2020-06-17');
insert into payment (student_id, reason_for_payment, receiver, amount, recievers_account, model, poziv_na_broj, payment_date) 
	values (6, 'Prijava ispita', 'FTN', 400.00, '840-1710666-12', 97, '33-99011-1-4532-160', '2020-08-25');
insert into payment (student_id, reason_for_payment, receiver, amount, recievers_account, model, poziv_na_broj, payment_date) 
	values (7, 'Overa semestra', 'FTN', 2000.00, '840-1710666-12', 97, '33-99011-1-4532-170', '2020-06-05');
insert into payment (student_id, reason_for_payment, receiver, amount, recievers_account, model, poziv_na_broj, payment_date) 
	values (7, 'Prijava ispita', 'FTN', 400.00, '840-1710666-12', 97, '33-99011-1-4532-170', '2020-08-20');
insert into payment (student_id, reason_for_payment, receiver, amount, recievers_account, model, poziv_na_broj, payment_date) 
	values (8, 'Prijava ispita', 'FTN', 600.00, '840-1710666-12', 97, '33-99011-1-4532-100', '2020-08-27');
insert into payment (student_id, reason_for_payment, receiver, amount, recievers_account, model, poziv_na_broj, payment_date) 
	values (8, 'Overa semestra', 'FTN', 2000.00, '840-1710666-12', 97, '33-99011-1-4532-100', '2020-06-17');


