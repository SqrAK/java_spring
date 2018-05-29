create table book (isbn varchar(255) not null, author varchar(255), name varchar(255), user_id integer, primary key (isbn));
create table room (id integer not null, name varchar(255), number integer, user_id integer, primary key (id));
create table user (id integer not null, login varchar(255), password varchar(255), primary key (id));
alter table book add constraint book_user foreign key (user_id) references user;
alter table room add constraint room_user foreign key (user_id) references user;

create sequence seq_user
minvalue 1
start with 5
increment by 1
cache 5;

create sequence seq_room
minvalue 1
start with 6
increment by 1
cache 5;