create table room (id integer not null, name varchar(255), number integer, user_id integer, primary key (id));
create table user (id integer not null, login varchar(255), password varchar(255), primary key (id));
alter table room add constraint room_user foreign key (user_id) references user;

create sequence seq_user
minvalue 1
start with 5
increment by 1
cache 5;
