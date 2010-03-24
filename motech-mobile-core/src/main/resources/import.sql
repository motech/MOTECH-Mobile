--inserting into the language table

insert into language (id, code,name,description) values (1,'en','English', 'english language is the official spoken language in Ghana');
insert into language (id, code,name,description) values (2,'nan','Nankam', 'Nankam is the most spoken language in North Ghana');
insert into language (id, code,name,description) values (3,'kas','Kassim', 'Kassim the most spoken language');


insert into notification_type(id, name, description) values (2, 'Min-by-min test ','First informational message sent at min 1');
insert into notification_type(id, name, description) values (3, 'Min-by-min test ','Second informational message sent at min 2');
insert into notification_type(id, name, description) values (4, 'Min-by-min test ','Prompt for tetanus dose 1  sent at min 3');
insert into notification_type(id, name, description) values (5, 'Min-by-min test ','Third informational message sent at min 5');
insert into notification_type(id, name, description) values (6, 'Min-by-min test ','Fourth informational message sent at min 6');
insert into notification_type(id, name, description) values (7, 'Min-by-min test ','Reminder for missed tetanus 1 sent at min 6 (if missed)');
insert into notification_type(id, name, description) values (8, 'Min-by-min test ','Second reminder for missed tetanus 1 sent at min 8 (if missed)');
insert into notification_type(id, name, description) values (9, 'Min-by-min test ','Prompt for tetanus 2');
insert into notification_type(id, name, description) values (10, 'Min-by-min test ','Reminder for missed tetanus dose 2');
insert into notification_type(id, name, description) values (11, 'Min-by-min test ','Second reminder for missed tetanus dose 2');


insert into notification_type(id, name, description) values (14, 'Day-by-day test ','Day 1 message');
insert into notification_type(id, name, description) values (15, 'Day-by-day test ','Day 2 message');
insert into notification_type(id, name, description) values (16, 'Day-by-day test ','Day 3 message');
insert into notification_type(id, name, description) values (17, 'Day-by-day test ','Day 4 message');
insert into notification_type(id, name, description) values (18, 'Day-by-day test ','Day 5 message');
insert into notification_type(id, name, description) values (19, 'Day-by-day test ','Day 6 message');
insert into notification_type(id, name, description) values (20, 'Day-by-day test ','Day 7 message');
insert into notification_type(id, name, description) values (21, 'Day-by-day test ','Day 8 message');
insert into notification_type(id, name, description) values (22, 'Day-by-day test ','Day 9 message');
insert into notification_type(id, name, description) values (23, 'Day-by-day test ','Day 10 message');
insert into notification_type(id, name, description) values (24, 'Day-by-day test ','Day 11 message');
insert into notification_type(id, name, description) values (25, 'Day-by-day test ','Day 12 message');
insert into notification_type(id, name, description) values (26, 'Day-by-day test ','Day 13 message');
insert into notification_type(id, name, description) values (27, 'Day-by-day test ','Day 14 message');
insert into notification_type(id, name, description) values (28, 'Day-by-day test ','Day 15 message');
insert into notification_type(id, name, description) values (29, 'Day-by-day test ','Day 16 message');
insert into notification_type(id, name, description) values (30, 'Day-by-day test ','Day 17 message');
insert into notification_type(id, name, description) values (31, 'Day-by-day test ','Day 18 message');
insert into notification_type(id, name, description) values (32, 'Day-by-day test ','Day 19 message');
insert into notification_type(id, name, description) values (33, 'Day-by-day test ','Day 20 message');
insert into notification_type(id, name, description) values (34, 'Day-by-day test ','Day 21 message');
insert into notification_type(id, name, description) values (35, 'Day-by-day test ','Day 22 message');
insert into notification_type(id, name, description) values (36, 'Day-by-day test ','Day 23 message');
insert into notification_type(id, name, description) values (37, 'Day-by-day test ','Day 24 message');
insert into notification_type(id, name, description) values (38, 'Day-by-day test ','Day 25 message');
insert into notification_type(id, name, description) values (39, 'Day-by-day test ','Day 26 message');
insert into notification_type(id, name, description) values (40, 'Day-by-day test ','Day 27 message');
insert into notification_type(id, name, description) values (41, 'Day-by-day test ','Day 28 message');
insert into notification_type(id, name, description) values (42, 'Day-by-day test ','Day 29 message');
insert into notification_type(id, name, description) values (43, 'Day-by-day test ','Day 30 message');
insert into notification_type(id, name, description) values (44, 'Day-by-day test ','Day 31 message');
insert into notification_type(id, name, description) values (45, 'Day-by-day test ','Day 32 message');
insert into notification_type(id, name, description) values (46, 'Day-by-day test ','Day 33 message');
insert into notification_type(id, name, description) values (47, 'Day-by-day test ','Day 34 message');
insert into notification_type(id, name, description) values (48, 'Day-by-day test ','Day 35 message');
insert into notification_type(id, name, description) values (49, 'Day-by-day test ','Day 36 message');
insert into notification_type(id, name, description) values (50, 'Day-by-day test ','Day 37 message');
insert into notification_type(id, name, description) values (51, 'Day-by-day test ','Day 38 message');
insert into notification_type(id, name, description) values (52, 'Day-by-day test ','Day 39 message');
insert into notification_type(id, name, description) values (53, 'Day-by-day test ','Day 40 message');


insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (1,2,'TEXT',1,'Tetanus is a disorder that leads to severe and painful muscle spasms. It is caused by a bacterium that is commonly found in soil, dust and animal waste.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (100,2,'TEXT',2,'Tetanus is a disorder that leads to severe and painful muscle spasms. It is caused by a bacterium that is commonly found in soil, dust and animal waste.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (101,2,'TEXT',3,'Tetanus is a disorder that leads to severe and painful muscle spasms. It is caused by a bacterium that is commonly found in soil, dust and animal waste.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (2,3,'TEXT',1,'Immunization against tetanus is recommended especially in pregnant women. Vaccination of pregnant women is the best preventive measure against neonatal tetanus.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (102,3,'TEXT',2,'Immunization against tetanus is recommended especially in pregnant women. Vaccination of pregnant women is the best preventive measure against neonatal tetanus.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (103,3,'TEXT',3,'Immunization against tetanus is recommended especially in pregnant women. Vaccination of pregnant women is the best preventive measure against neonatal tetanus.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (3,4,'TEXT',1,'You are due for your first tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (104,4,'TEXT',2,'You are due for your first tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (105,4,'TEXT',3,'You are due for your first tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (4,5,'TEXT',1,'All wounds should be considered risky, so always be cleaned with soap and water.  Some wounds, however small, such as from thorns of roses or lemon, are highly risky and perhaps even more than other large open wounds.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (106,5,'TEXT',2,'All wounds should be considered risky, so always be cleaned with soap and water.  Some wounds, however small, such as from thorns of roses or lemon, are highly risky and perhaps even more than other large open wounds.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (107,5,'TEXT',3,'All wounds should be considered risky, so always be cleaned with soap and water.  Some wounds, however small, such as from thorns of roses or lemon, are highly risky and perhaps even more than other large open wounds.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (5,6,'TEXT',1,'Tetanus in newborns is severe, with high mortality rate. It usually occurs by contamination of the umbilical cord at delivery or in the first days of life. Furthermore, this disease often aggravated by complications, including infectious, which are the triggers of death.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (108,6,'TEXT',2,'Tetanus in newborns is severe, with high mortality rate. It usually occurs by contamination of the umbilical cord at delivery or in the first days of life. Furthermore, this disease often aggravated by complications, including infectious, which are the triggers of death.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (109,6,'TEXT',3,'Tetanus in newborns is severe, with high mortality rate. It usually occurs by contamination of the umbilical cord at delivery or in the first days of life. Furthermore, this disease often aggravated by complications, including infectious, which are the triggers of death.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (6,7,'TEXT',1,'You have missed your first tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (110,7,'TEXT',2,'You have missed your first tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (111,7,'TEXT',3,'You have missed your first tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (7,8,'TEXT',1,'This is your second reminder for missing your first tetanus vaccination.  Please go to your nearest clinic.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (112,8,'TEXT',2,'This is your second reminder for missing your first tetanus vaccination.  Please go to your nearest clinic.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (113,8,'TEXT',3,'This is your second reminder for missing your first tetanus vaccination.  Please go to your nearest clinic.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (8,9,'TEXT',1,'You are due for your second tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (114,9,'TEXT',2,'You are due for your second tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (115,9,'TEXT',3,'You are due for your second tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (9,10,'TEXT',1,'You have missed your second tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (116,10,'TEXT',2,'You have missed your second tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (117,10,'TEXT',3,'You have missed your second tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (10,11,'TEXT',1,'This is your second reminder for missing your second tetanus vaccination.  Please go to your nearest clinic.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (118,11,'TEXT',2,'This is your second reminder for missing your second tetanus vaccination.  Please go to your nearest clinic.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (119,11,'TEXT',3,'This is your second reminder for missing your second tetanus vaccination.  Please go to your nearest clinic.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (11,14,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 1.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (121,14,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 1.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (122,14,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 1.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (12,15,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 2.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (123,15,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 2.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (124,15,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 2.  Your estimated due date is <DueDate>.','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (13,16,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 3.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (125,16,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 3.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (126,16,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 3.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (14,17,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 4.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (127,17,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 4.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (128,17,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 4.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (15,18,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 5.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (129,18,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 5.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (130,18,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 5.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (16,19,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 6.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (131,19,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 6.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (132,19,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 6.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (17,20,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 7.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (133,20,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 7.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (134,20,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 7.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (18,21,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 8.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (135,21,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 8.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (136,21,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 8.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (19,22,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 9.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (137,22,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 9.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (138,22,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 9.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (20,23,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 10.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (139,23,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 10.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (140,23,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 10.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (21,24,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 11.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (141,24,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 11.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (142,24,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 11.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (22,25,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 12.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (143,25,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 12.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (144,25,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 12.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (23,26,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 13.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (145,26,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 13.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (146,26,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 13.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (24,27,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 14.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (147,27,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 14.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (148,27,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 14.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (25,28,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 15.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (149,28,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 15.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (150,28,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 15.  Your estimated due date is <DueDate>.','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (26,29,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 16.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (151,29,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 16.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (152,29,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 16.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (27,30,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 17.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (153,30,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 17.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (154,30,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 17.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (28,31,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 18.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (155,31,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 18.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (156,31,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 18.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (29,32,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 19.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (157,32,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 19.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (158,32,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 19.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (30,33,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 20.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (159,33,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 20.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (160,33,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 20.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (31,34,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 21.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (161,34,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 21.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (162,34,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 21.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (32,35,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 22.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (163,35,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 22.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (164,35,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 22.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (33,36,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 23.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (165,36,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 23.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (166,36,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 23.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (34,37,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 24.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (167,37,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 24.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (168,37,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 24.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (35,38,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 25.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (169,38,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 25.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (170,38,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 25.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (36,39,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 26.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (171,39,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 26.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (172,39,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 26.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (37,40,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 27.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (173,40,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 27.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (174,40,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 27.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (38,41,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 28.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (175,41,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 28.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (176,41,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 28.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (39,42,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 29.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (177,42,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 29.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (178,42,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 29.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (40,43,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 30.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (179,43,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 30.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (180,43,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 30.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (41,44,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 31.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (181,44,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 31.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (182,44,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 31.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (42,45,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 32.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (183,45,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 32.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (184,45,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 32.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (43,46,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 33.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (185,46,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 33.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (186,46,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 33.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (44,47,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 34.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (187,47,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 34.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (188,47,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 34.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (45,48,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 35.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (189,48,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 35.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (190,48,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 35.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (46,49,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 36.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (191,49,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 36.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (192,49,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 36.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (47,50,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 37.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (193,50,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 37.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (194,50,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 37.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (48,51,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 38.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (195,51,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 38.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (196,51,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 38.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (49,52,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 39.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (197,52,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 39.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (198,52,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 39.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (50,53,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 40.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (199,53,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 40.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (200,53,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 40.  Your estimated due date is <DueDate>.##','2009-10-06');


/*Data Entry Forms*/
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (254657657567688,0,'RegisterChild','ENCOUNTER','DISALLOWED','2010-01-08 14:40:15','2010-01-08 15:22:53');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (597658468478768,0,'PregnancyStop','ENCOUNTER','DISALLOWED','2010-01-08 14:19:05','2010-01-08 15:22:53');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (678565673457657,0,'EditPatient','ENCOUNTER','TIME_BOUND','2010-01-08 14:41:23','2010-01-08 15:22:53');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785688106549491,0,'GeneralOPD','ENCOUNTER','TIME_BOUND','2009-12-18 11:04:20','2010-01-08 15:22:53');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640001,0,'ANC','ENCOUNTER','TIME_BOUND','2010-02-03 09:02:20','2010-02-03 09:02:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640002,0,'Abortion','ENCOUNTER','TIME_BOUND','2010-02-03 09:04:02','2010-02-03 09:04:02');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640093,0,'Delivery','ENCOUNTER','TIME_BOUND','2010-02-03 09:04:02','2010-02-03 09:04:02');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640094,0,'PPC','ENCOUNTER','TIME_BOUND','2010-02-03 09:07:04','2010-02-03 09:07:04');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640095,0,'Death','ENCOUNTER','DISALLOWED','2010-02-03 09:07:04','2010-02-03 09:07:4');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640096,0,'Child','ENCOUNTER','TIME_BOUND','2010-02-03 09:10:23','2010-02-03 09:10:23');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640097,0,'ChildOPD','ENCOUNTER','TIME_BOUND','2010-02-03 09:10:25','2010-02-03 09:10:25');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640098,0,'MotherOPD','ENCOUNTER','TIME_BOUND','2010-02-03 09:10:20','2010-02-03 09:10:20');

/*Data Query Forms*/
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640100,0,'ANCDefault','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640101,0,'TTDefault','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640102,0,'PPCDefault','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640103,0,'PNCDefault','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640104,0,'CWCDefault','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640105,0,'UpcomingDeliveries','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640106,0,'RecentDeliveries','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640107,0,'OverdueDeliveries','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640108,0,'UpcomingCare','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640109,0,'ViewPatient','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (785739375640110,0,'FindMoTeCHID','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');

/*Data Query Forms Java Forms*/
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (095739375640100,0,'ANCDefault-jf','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (985739375640101,0,'TTDefault-jf','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (875739375640102,0,'PPCDefault-jf','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (765739375640103,0,'PNCDefault-jf','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (655739375640104,0,'CWCDefault-jf','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (545739375640105,0,'UpcomingDeliveries-jf','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (435739375640106,0,'RecentDeliveries-jf','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (325739375640107,0,'OverdueDeliveries-jf','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (215739375640108,0,'UpcomingCare-jf','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (105739375640109,0,'ViewPatient-jf','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');
insert into incoming_message_form_definition (id,obj_vesion,form_code,type,duplicatable,date_created,last_modified) values (005739375640110,0,'FindMoTeCHID-jf','QUERY','ALLOWED','2010-02-15 18:33:20','2010-02-15 18:33:20');


/*Data for the table incoming_message_form_parameter_definition */

/*Params for RegisterChild*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (3245464634645,0,254657657567688,'chpsId','ALPHANUM',20,true,'2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (53475686867887,0,254657657567688,'motherMotechId','ALPHANUM',20,true,'2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (64348767787689,0,254657657567688,'dob','DATE',20,true,'2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (7645645756876,0,254657657567688,'childMotechId','ALPHANUM',20,true,'2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (92366865767568,0,254657657567688,'firstName','ALPHA',20,false,'2010-01-08 14:54:14','2010-01-08 15:22:54');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (12353635757884,0,254657657567688,'sex','GENDER',20,true,'2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (24575686567567,0,254657657567688,'nhisExpiration','EXPIRYDATE',10,false,'2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (42783631278378,0,254657657567688,'nhisNo','ALPHANUM',20,false,'2010-01-08 14:54:14','2010-01-08 15:22:53');

/*Params for PregnancyStop*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (4564564645577,0,597658468478768,'chpsId','ALPHANUM',20,true,'2010-01-08 14:46:33','2010-01-08 15:22:53');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (876875676546456,0,597658468478768,'patientRegNum','ALPHANUM',20,true,'2010-01-08 14:46:33','2010-01-08 15:22:54');

/*Params for GeneralOPD*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (359809010998245,0,785688106549491,'chpsId','ALPHANUM',20,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (409624894059308,0,785688106549491,'Date','DATE',10,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (447236646138029,0,785688106549491,'SerialNo','ALPHANUM',20,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (964559790068935,0,785688106549491,'Sex','GENDER',1,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (44780981663901,0,785688106549491,'DoB','DATE',10,true,'2009-12-18 11:04:20','2010-01-08 15:22:53');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (447617060511587,0,785688106549491,'Insured','BOOLEAN',1,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (447617060511588,0,785688106549491,'NewCase','BOOLEAN',1,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (447617060511586,0,785688106549491,'Diagnosis','DIAGNOSIS',2,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (447617060511589,0,785688106549491,'secondaryDiagnosis','DIAGNOSIS',2,false,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (125568987021450,0,785688106549491,'Referral','BOOLEAN',1,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');

/*Params for EditPatient*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (7765435645455,0,678565673457657,'chpsId','ALPHANUM',20,true,'2010-01-08 15:02:43','2010-01-08 15:22:53');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (15674564545534,0,678565673457657,'patientRegNum','ALPHANUM',20,true,'2010-01-08 15:02:43','2010-01-08 15:22:53');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (93786634412380,0,678565673457657,'primaryPhone','NUMERIC',15,false,'2010-01-08 15:02:43','2010-01-08 15:22:54');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (535767567677889,0,678565673457657,'primaryPhoneType','PHONETYPE',10,false,'2010-01-08 15:02:43','2010-01-08 15:22:54');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (834459078775,0,678565673457657,'secondaryPhoneType','PHONETYPE',10,false,'2010-01-08 15:02:43','2010-01-08 15:22:53');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (6378978445645,0,678565673457657,'secondaryPhone','NUMERIC',15,false,'2010-01-08 15:02:43','2010-01-08 15:22:53');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (20564578745345,0,678565673457657,'nhisNo','ALPHANUM',20,false,'2010-01-08 15:02:43','2010-01-08 15:22:53');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (72345769456456,0,678565673457657,'nhisExpiration','EXPIRYDATE',10,false,'2010-01-08 15:02:43','2010-01-08 15:22:54');

/*Params for ANC*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (434546463984500,0,785739375640001,'chpsId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (434546463984501,0,785739375640001,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (434546463984502,0,785739375640001,'date','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (434546463984503,0,785739375640001,'visitNo','ANCVISIT',4,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (434546463984504,0,785739375640001,'TTDose','TTDOSE',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (434546463984505,0,785739375640001,'IPTDose','IPTDOSE',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (434546463984506,0,785739375640001,'ITN','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (434546463984507,0,785739375640001,'HIVResult','HIVSTATUS',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for Abortion*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (464546463987800,0,785739375640002,'chpsId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (464546463987801,0,785739375640002,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (464546463987802,0,785739375640002,'date','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (464546463987803,0,785739375640002,'abortionType','ABORTIONTYPE',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (464546463987804,0,785739375640002,'complications','ABORTIONCOMPLICATIONS',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for Delivery*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981200,0,785739375640093,'chpsId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981201,0,785739375640093,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981202,0,785739375640093,'DoD','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981203,0,785739375640093,'MoD','MOD',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981204,0,785739375640093,'OoD','OOD',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981205,0,785739375640093,'location','LOCATION',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981206,0,785739375640093,'deliveredBy','DELIVERER',4,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981207,0,785739375640093,'MaternalDeath','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981208,0,785739375640093,'cause','MATERNALDEATHCAUSE',1,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981209,0,785739375640093,'C1BirthOutcome','BIRTHOUTCOME',3,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981210,0,785739375640093,'C1MotechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981211,0,785739375640093,'C1Sex','GENDER',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981212,0,785739375640093,'C1Name','ALPHA',20,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981213,0,785739375640093,'C1OPV','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981214,0,785739375640093,'C1BCG','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981215,0,785739375640093,'C2BirthOutcome','BIRTHOUTCOME',3,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981216,0,785739375640093,'C2MotechId','ALPHANUM',20,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981217,0,785739375640093,'C2Sex','GENDER',1,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981218,0,785739375640093,'C2Name','ALPHANUM',10,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981219,0,785739375640093,'C2OPV','BOOLEAN',1,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (473346463981220,0,785739375640093,'C2BCG','BOOLEAN',3,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for PPC*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (254546463981100,0,785739375640094,'chpsId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (254546463981101,0,785739375640094,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (254546463981102,0,785739375640094,'date','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (254546463981103,0,785739375640094,'visitNo','PPCVISIT',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (254546463981104,0,785739375640094,'vitA','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (254546463981105,0,785739375640094,'TTDose','NUMERIC',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for Death*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (154546463981440,0,785739375640095,'chpsId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (154546463981441,0,785739375640095,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (154546463981442,0,785739375640095,'date','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (154546463981443,0,785739375640095,'cause','DEATHCAUSE',2,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for Child*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (064546463981700,0,785739375640096,'chpsId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (064546463981701,0,785739375640096,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (064546463981702,0,785739375640096,'date','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (064546463981703,0,785739375640096,'BCG','BOOLEAN',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (064546463981704,0,785739375640096,'OPVDOSE','OPVDOSE',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (064546463981705,0,785739375640096,'PentaDose','PENTADOSE',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (064546463981706,0,785739375640096,'YellowFever','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (064546463981707,0,785739375640096,'CSM','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (064546463981708,0,785739375640096,'IPTi','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (064546463981709,0,785739375640096,'VitA','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (064546463981765,0,785739375640096,'Measles','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for ChildOPD*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (879874663983210,0,785739375640097,'chpsId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (879874663983213,0,785739375640097,'serialNo','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (879874663983211,0,785739375640097,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (879874663983212,0,785739375640097,'date','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (879874663983214,0,785739375640097,'newCase','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (879874663983215,0,785739375640097,'Diagnosis','DIAGNOSIS',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (879874663983216,0,785739375640097,'secondaryDiagnosis','DIAGNOSIS',2,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (879874663983217,0,785739375640097,'referral','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for MotherOPD*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923330,0,785739375640098,'chpsId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923333,0,785739375640098,'serialNo','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923331,0,785739375640098,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923332,0,785739375640098,'date','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923334,0,785739375640098,'newCase','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923335,0,785739375640098,'Diagnosis','DIAGNOSIS',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923336,0,785739375640098,'secondaryDiagnosis','DIAGNOSIS',2,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923337,0,785739375640098,'referral','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for ANCDefault*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923338,0,785739375640100,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923339,0,785739375640100,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for ANCDefault Java Form*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (123474663923338,0,095739375640100,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (123574663923339,0,095739375640100,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (123674663923339,0,095739375640100,'sender','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for TTDefault*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923340,0,785739375640101,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923341,0,785739375640101,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for TTDefault Java Form*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (123774663923340,0,985739375640101,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (123874663923341,0,985739375640101,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (123974663923341,0,985739375640101,'sender','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for PPCDefault*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923342,0,785739375640102,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923343,0,785739375640102,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for PPCDefault Java Form*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (123074663923342,0,875739375640102,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (123174663923343,0,875739375640102,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (123274663923343,0,875739375640102,'sender','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for PNCDefault*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923344,0,785739375640103,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923345,0,785739375640103,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for PNCDefault Java Form */
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1233004663923344,0,765739375640103,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1231474663923345,0,765739375640103,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1231574663923345,0,765739375640103,'sender','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for CWCDefault*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923346,0,785739375640104,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923347,0,785739375640104,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for CWCDefault Java Form*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1231674663923346,0,655739375640104,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1231774663923347,0,655739375640104,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1231874663923347,0,655739375640104,'sender','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for UpcomingDeliveries*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923348,0,785739375640105,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923349,0,785739375640105,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for UpcomingDeliveries Java Form*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1231974663923348,0,545739375640105,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1232074663923349,0,545739375640105,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1232174663923349,0,545739375640105,'sender','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for RecentDeliveries*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923350,0,785739375640106,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923351,0,785739375640106,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for RecentDeliveries Java Form*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1232274663923350,0,435739375640106,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1232374663923351,0,435739375640106,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1232474663923351,0,435739375640106,'sender','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for OverdueDeliveries*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923352,0,785739375640107,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923353,0,785739375640107,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for OverdueDeliveries Java Form*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1232574663923352,0,325739375640107,'facilityId','NUMERIC',10,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1232674663923353,0,325739375640107,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1232774663923353,0,325739375640107,'sender','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for UpcomingCare*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923354,0,785739375640108,'motechId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923355,0,785739375640108,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for UpcomingCare Java Form*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1232874663923354,0,215739375640108,'motechId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1232974663923355,0,215739375640108,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1233074663923355,0,215739375640108,'sender','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for ViewPatients*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923356,0,785739375640109,'motechId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923357,0,785739375640109,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for ViewPatients Java Form*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1233174663923356,0,105739375640109,'motechId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1233274663923357,0,105739375640109,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1233374663923357,0,105739375640109,'sender','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for FindMoTeCHID*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923358,0,785739375640110,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923359,0,785739375640110,'firstName','ALPHA',20,false,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923360,0,785739375640110,'lastName','ALPHA',20,false,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923361,0,785739375640110,'preferredName','ALPHA',20,false,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923362,0,785739375640110,'dob','DATE',10,false,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923363,0,785739375640110,'nhisNo','ALPHANUM',20,false,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (695474663923364,0,785739375640110,'phone','NUMERIC',20,false,'2010-02-15 18:35:14','2010-02-15 18:35:14');

/*Params for FindMoTeCHID Java Form*/
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1233474663923358,0,005739375640110,'chpsId','ALPHANUM',20,true,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1233574663923359,0,005739375640110,'firstName','ALPHA',20,false,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1233674663923360,0,005739375640110,'lastName','ALPHA',20,false,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1233774663923361,0,005739375640110,'preferredName','ALPHA',20,false,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1233874663923362,0,005739375640110,'dob','DATE',10,false,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1233974663923363,0,005739375640110,'nhisNo','ALPHANUM',20,false,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1234074663923364,0,005739375640110,'phone','NUMERIC',20,false,'2010-02-15 18:35:14','2010-02-15 18:35:14');
insert into incoming_message_form_parameter_definition (id,obj_vesion,incoming_message_form_definition_id,name,parameter_type,length,required,date_created,last_modified) values (1234174663923364,0,005739375640110,'sender','NUMERIC',20,false,'2010-02-15 18:35:14','2010-02-15 18:35:14');
