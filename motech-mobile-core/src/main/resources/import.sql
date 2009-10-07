--inserting into the language table

insert into "ROOT"."LANGUAGE" (id, code,name,description) values (1,'en','English', 'english language is the official spoken language in Ghana');
--insert into language (id, code,name,description) values ('-92233720368547758','gan','Gan', 'Gan is the most spoken language in Accra area');

--inserting into Nofitication type
insert into "ROOT"."NOTIFICATION_TYPE"(id, name, description) values (1, 'informational message','tetanus related information');
insert into "ROOT"."NOTIFICATION_TYPE"(id, name, description) values (2, 'informational message','tetanus related information');
insert into "ROOT"."NOTIFICATION_TYPE"(id, name, description) values (3, 'informational message','immuniazation against tetanus related advices');
insert into "ROOT"."NOTIFICATION_TYPE"(id, name, description) values (4, 'informational message','General related advices');
insert into "ROOT"."NOTIFICATION_TYPE"(id, name, description) values (5, 'invitation message','General related vaccination');
insert into "ROOT"."NOTIFICATION_TYPE"(id, name, description) values (6, 'invitation message','Tetanus related information');
insert into "ROOT"."NOTIFICATION_TYPE"(id, name, description) values (7, 'second reminder','tetanus related advices');


--inserting into "ROOT"."MESSAGE_TEMPLATE"
insert into "ROOT"."MESSAGE_TEMPLATE"(id, notification_type, message_type, language, template ,date_created ) values (1,1,'text',1,'Tetanus is a nervous system disorder that leads to severe and painful muscle spasms. Its caused by a bacterium that is commonly found in soil, dust and animal waste','2009-10-04');
insert into "ROOT"."MESSAGE_TEMPLATE"(id, notification_type, message_type, language, template ,date_created ) values (2,2,'text',1,'Tetanus can be prevented with a vaccine. The only safe and effective way to prevent it is to be vaccinated. The vaccine is  is highly effective and safe','2009-10-05');
insert into "ROOT"."MESSAGE_TEMPLATE"(id, notification_type, message_type, language, template ,date_created ) values (3,3,'text',1,'Immunization against tetanus is recommended especially in pregnant women. Vaccination of pregnant women is the best preventive measure against neonatal tetanus.','2009-10-06');
insert into "ROOT"."MESSAGE_TEMPLATE"(id, notification_type, message_type, language, template ,date_created ) values (4,4,'text',1,'All wounds should be considered risky, so always be cleaned with soap and water  Some wounds, however small, such as from thorns of roses or lemon, are highly risky and perhaps even more than other large open wounds.','2009-10-05');
insert into "ROOT"."MESSAGE_TEMPLATE"(id, notification_type, message_type, language, template ,date_created ) values (5,5,'text',1,'Please contact your nurse to determine if you need a Tetanus immunization.','2009-10-06');
insert into "ROOT"."MESSAGE_TEMPLATE"(id, notification_type, message_type, language, template ,date_created ) values (6,6,'text',1,'Tetanus is a serious disease with high mortality rate. One in three adults and all infants with the disease die.','2009-10-06');
insert into "ROOT"."MESSAGE_TEMPLATE"(id, notification_type, message_type, language, template ,date_created ) values (7,7,'text',1,'Tetanus in newborns is severe, with high mortality rate. It usually occurs by contamination of the umbilical cord at delivery or in the first days of life. Furthermore, this disease often aggravated by complications, including infectious, which are the triggers of death.','2009-10-06');





