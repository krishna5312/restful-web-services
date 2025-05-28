drop table post;
drop table user_details;


CREATE SEQUENCE seq_user_details 
START WITH 10001
INCREMENT BY 1;

CREATE SEQUENCE seq_post 
START WITH 5001
INCREMENT BY 1;

create table user_details (
userid int not null primary key,
name varchar(255) not null,
dateOfBirth date not null
);

create table post(
postid int not null primary key,
description varchar(255),
userid int not null
)