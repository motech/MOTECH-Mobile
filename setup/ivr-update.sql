--
-- MOTECH PLATFORM OPENSOURCE LICENSE AGREEMENT
--
-- Copyright (c) 2010-11 The Trustees of Columbia University in the City of
-- New York and Grameen Foundation USA.  All rights reserved.
--
-- Redistribution and use in source and binary forms, with or without
-- modification, are permitted provided that the following conditions are met:
--
-- 1. Redistributions of source code must retain the above copyright notice,
-- this list of conditions and the following disclaimer.
--
-- 2. Redistributions in binary form must reproduce the above copyright notice,
-- this list of conditions and the following disclaimer in the documentation
-- and/or other materials provided with the distribution.
--
-- 3. Neither the name of Grameen Foundation USA, Columbia University, or
-- their respective contributors may be used to endorse or promote products
-- derived from this software without specific prior written permission.
--
-- THIS SOFTWARE IS PROVIDED BY GRAMEEN FOUNDATION USA, COLUMBIA UNIVERSITY
-- AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
-- BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
-- FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL GRAMEEN FOUNDATION
-- USA, COLUMBIA UNIVERSITY OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
-- INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
-- LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
-- OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
-- LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
-- NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
-- EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
--

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

