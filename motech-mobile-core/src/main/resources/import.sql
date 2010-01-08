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

/*Data for the table `incoming_message_form_definition` */

insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`date_created`) values (254657657567688,0,'RegChild','2010-01-08 14:40:15');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`date_created`) values (597658468478768,0,'PregnancyStop','2010-01-08 14:19:05');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`date_created`) values (678565673457657,0,'EditPatient','2010-01-08 14:41:23');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`date_created`) values (785688106549491,0,'GeneralOPD','2009-12-18 11:04:20');

/*Data for the table `incoming_message_form_parameter_definition` */

insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (834459078775,0,678565673457657,'secondaryPhoneType','ALPHA',10,false,'2010-01-08 15:02:43');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (3245464634645,0,254657657567688,'chpsId','ALPHANUM',20,true,'2010-01-08 14:54:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (4564564645577,0,597658468478768,'chpsId','ALPHANUM',20,true,'2010-01-08 14:46:33');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (6378978445645,0,678565673457657,'secondaryPhone','NUMERIC',15,false,'2010-01-08 15:02:43');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (7645645756876,0,254657657567688,'childRegNum','ALPHANUM',20,true,'2010-01-08 14:54:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (7658545887996,0,254657657567688,'regDate','DATE',10,true,'2010-01-08 14:54:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (7765435645455,0,678565673457657,'chpsId','ALPHANUM',20,true,'2010-01-08 15:02:43');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (12353635757884,0,254657657567688,'childGender','GENDER',20,true,'2010-01-08 14:54:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (15674564545534,0,678565673457657,'patientRegNum','ALPHANUM',20,false,'2010-01-08 15:02:43');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (20564578745345,0,678565673457657,'nhis','ALPHANUM',20,false,'2010-01-08 15:02:43');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (24575686567567,0,254657657567688,'nhisExp','DATE',10,true,'2010-01-08 14:54:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (42783631278378,0,254657657567688,'nhis','ALPHANUM',20,true,'2010-01-08 14:54:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (44780981663901,0,785688106549491,'DoB','DATE',10,true,'2009-12-18 11:04:20');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (53475686867887,0,254657657567688,'motherRegNum','ALPHANUM',20,true,'2010-01-08 14:54:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (64348767787689,0,254657657567688,'dob','DATE',20,true,'2010-01-08 14:54:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (72345769456456,0,678565673457657,'nhisExp','DATE',10,false,'2010-01-08 15:02:43');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (92366865767568,0,254657657567688,'childFirstName','ALPHA',20,true,'2010-01-08 14:54:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (93786634412380,0,678565673457657,'primaryPhone','NUMERIC',15,false,'2010-01-08 15:02:43');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (125568987021450,0,785688106549491,'Referral','BOOLEAN',1,true,'2009-12-18 11:04:20');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (359809010998245,0,785688106549491,'FacilityId','NUMERIC',10,true,'2009-12-18 11:04:20');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (409624894059308,0,785688106549491,'Date','DATE',10,true,'2009-12-18 11:04:20');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (447236646138029,0,785688106549491,'SerialNo','ALPHANUM',20,true,'2009-12-18 11:04:20');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (447617060511586,0,785688106549491,'Diagnosis','NUMERIC',3,true,'2009-12-18 11:04:20');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (535767567677889,0,678565673457657,'primaryPhoneType','ALPHA',10,false,'2010-01-08 15:02:43');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (876875676546456,0,597658468478768,'patientRegNum','ALPHANUM',20,true,'2010-01-08 14:46:33');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`) values (964559790068935,0,785688106549491,'Sex','GENDER',1,true,'2009-12-18 11:04:20');