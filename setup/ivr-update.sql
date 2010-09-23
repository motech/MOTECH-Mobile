use motechmobiledb;

drop table if exists ivr_menu;
drop table if exists ivr_call;
drop table if exists ivr_call_session_requests;
drop table if exists ivr_call_session;

create table ivr_call_session (
	call_session_id bigint(20) not null auto_increment, 
	version int(11) not null, 
	user_id varchar(255) not null, 
	phone varchar(255), 
	language varchar(255), 
	call_direction varchar(255) not null, 
	attempts int(11) not null, 
	days int(11) not null, 
	state int(11) not null, 
	created datetime, 
	next_attempt datetime, 
	primary key (call_session_id)
);

create table ivr_call (
	ivr_call_id bigint(20) not null auto_increment, 
	version int(11) not null, 
	created datetime, 
	connected datetime, 
	disconnected datetime, 
	duration int(11), 
	external_id varchar(255) not null, 
	status varchar(255), 
	status_reason varchar(255), 
	ivr_call_session bigint(20) not null, 
	constraint FKC56C6178DC5C6D13 foreign key (ivr_call_session) references ivr_call_session (call_session_id),
	primary key (ivr_call_id)
);

create table ivr_call_session_requests (
	ivr_call_session_id bigint(20) not null, 
	message_request_id bigint(20) not null, 
	constraint FK9A4BB734893C52A foreign key (message_request_id) references message_request (id),
	constraint FK9A4BB7343FDB92CF foreign key (ivr_call_session_id) references ivr_call_session (call_session_id),
	primary key (ivr_call_session_id, message_request_id)
);

create table ivr_menu (
	ivr_menu_id bigint(20) not null auto_increment, 
	version int(11) not null, 
	name varchar(255), 
	entryTime datetime, 
	duration int(11), 
	key_pressed varchar(255), 
	recording varchar(255), 
      	ivr_call_id bigint(20),	
	constraint FKC570FC79FC424934 foreign key (ivr_call_id) references ivr_call (ivr_call_id),
	primary key (ivr_menu_id)
);

