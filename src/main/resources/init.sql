
create table users (
id int primary key not null ,
first_name varchar2(30),
last_name varchar2(30),
login varchar2(40),
password varchar2(30)
);

create table children (
id int primary key not null,
first_name varchar2(30),
last_name varchar2(30),
login varchar2(30),
password varchar2(30),
id_schedule int
);

create table monitors (
id int primary key not null,
id_user int,
id_child int
);

create table schedules(
id int primary key not null,
name varchar2 (30),
id_user int
);

create table schedule_entries(
id int primary key not null,
name varchar2 (30),
time_start varchar2 (30),
time_stop varchar2(30),
day varchar2(30),
id_schedule int,
id_area int
);

create table locations (
id int primary key not null,
longitude number(10,7),
latitude number(10,7),
day varchar2(30),
time varchar(30),
id_child int
)

CREATE SEQUENCE schedule_entries_id_seq;

CREATE OR REPLACE TRIGGER schedule_entries_id_trig 
BEFORE INSERT ON schedule_entries 
FOR EACH ROW
BEGIN
  SELECT schedule_entries_id_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

CREATE SEQUENCE locations_id_seq;

CREATE OR REPLACE TRIGGER locations_id_trig 
BEFORE INSERT ON locations
FOR EACH ROW
BEGIN
  SELECT locations_id_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

CREATE SEQUENCE users_id_seq;

CREATE OR REPLACE TRIGGER users_id_trig 
BEFORE INSERT ON users 
FOR EACH ROW
BEGIN
  SELECT users_id_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

CREATE SEQUENCE children_id_seq;

CREATE OR REPLACE TRIGGER children_id_seq 
BEFORE INSERT ON children 
FOR EACH ROW
BEGIN
  SELECT children_id_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

CREATE SEQUENCE monitors_id_seq;

drop trigger monitors_id_seq;

CREATE OR REPLACE TRIGGER monitors_id_seq 
BEFORE INSERT ON monitors 
FOR EACH ROW
BEGIN
  SELECT monitors_id_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

CREATE SEQUENCE schedules_id_seq;

CREATE OR REPLACE TRIGGER schedules_id_trig 
BEFORE INSERT ON schedules 
FOR EACH ROW
BEGIN
  SELECT schedules_id_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
