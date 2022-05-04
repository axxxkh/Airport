
DROP DATABASE MyJoinsDB;
CREATE DATABASE AIRPORT;
USE AIRPORT;

drop table passenger;
create table Passenger
(id INT AUTO_iNCREMENT NOT NULL,
passport varchar (20) UNIQUE,
name varchar (20),
surname varchar (20),
birthdate DATE,
ticket_id int,
primary key (id)
);

insert into Passenger
(id, passport, name, surname, birthdate, ticket_id )
values
(1,"SO1234", "Bohdan", "Shchehliuk",'1987-12-28', 1),
(2,"SO1211", "Liosha", "Khomyshen",'1988-11-11', 2),
(3,"GO1234", "Ira", "Drexden",'1999-02-28', 3),
(4,"124566", "Vasia", "Prystai",'1997-12-01', 4),
(5,"ddd124", "Olia", "Halahan",'1985-12-21', 5),
(6,"SO344d", "Nastai", "Halyha",'2007-12-18', 6),
(7,"13322d", "Liosha", "Tsar",'1999-05-22', 7),
(8,"23fw2f", "Petia", "Mavon",'1988-06-28', 8),
(9,"1223dw", "Olia", "Shchehliuk",'1992-11-30', 9),
(10,"11ddd2", "Yulia", "Antonenko",'1996-12-28', 10);

select * from Passenger;

drop table Ticket;
create table Ticket
(id INT AUTO_iNCREMENT NOT NULL,
number int,
flight_id int,
seat int,
passanger_id int,
ticket_status int, -- 0 рейс очікується, -1 пасажир не зявився, -2 рейс не відбувся, 1 - пасажир прилетів у пункт призначення 
primary key (id)
);

insert into Ticket
(id, number, flight_id , seat, passanger_id, ticket_status)
values
(1, 11111, 1, 34, 1, 1),
(2, 22222, 1, 4, 2,-1),
(3, 33333, 1, 2, 3,1),
(4, 44444, 2, 3, 1,0),
(5, 55555, 2, 34, 4,0),
(6, 66666, 2, 24, 2,0),
(7, 77777, 3, 14, 5,-2),
(8, 88888, 3, 1, 6,-2),
(9, 99999, 3, 31, 7,-2),
(10, 10101, 4, 24, 8,1),
(11, 10111, 4,23, 9,-1),
(12, 1212, 4, 4, 10,1),
(13, 1313, 5, 15, 5,0),
(14, 1414, 5, 4, 4,0),
(15, 1515, 5, 5, 3,0),
(16, 161616, 6, 7, 10,1),
(17, 1717, 6, 8, 2,1);

select * from Ticket;

drop table Flight;

create table Flight
(id INT AUTO_iNCREMENT NOT NULL,
flight_number int, --  foreign key?
time date,
flight_status int, 
terminal_id int,
avialine_id int,
craft_id int,
gate_id int,
primary key (id)
);

insert into Flight
(id, flight_number, time, flight_status, avialine_id,  craft_id, terminal_id, gate_id)
values
(1, 11, '2021-12-31', 1,1,  1, 1, 1),
(2, 12, '2022-12-31', 0,1, 2, null,1),
(3, 13, '2021-11-30', -2,2, 3,1, 1),
(4, 14, '2021-12-31', 1, 2, 4,2, 2),
(5, 15, '2023-12-31', 0, 1, 2,null, 2),
(6, 16, '2020-12-31', 1,3, 5,2, 1);

select * from Flight;

create table Avialine
(
id INT AUTO_iNCREMENT NOT NULL,
name varchar(20),
rate int, -- від 1 до 10
primary key (id)
);

insert into Avialine
(id, name, rate)
values
(1, "Qatar Airways", 10),
(2, "Qantas", 9),
(3, "IAU", 5);

select*from Avialine;

create table Aircraft
(id INT AUTO_iNCREMENT NOT NULL,
serialNumber int,
avialine_id int,
type_id int,
primary key (id)
);

insert into Aircraft
(id, avialine_id,type_id)
values
(1, 1,1 ),
(2, 1,2),
(3,2,2),
(4,2,1),
(5,3,2);

select*from Aircraft;

drop table AircraftTypes;

create table  AircraftTypes
(id INT AUTO_iNCREMENT NOT NULL,
producer varchar(20),
type varchar(20),
capacity int,
primary key (id)
);

insert into  AircraftTypes
(id, producer, type, capacity)
values
(1, "Boeing", "737",350),
(2, "Airbus", "A310",320);

select*from AircraftTypes;

create table Terminal
(id INT AUTO_iNCREMENT NOT NULL,
name varchar(20),
capacity int,
primary key (id)
);

insert into  Terminal
(id, name, capacity)
values
(1, "West",100),
(2, "East",150);

select*from  Terminal;


create table Gate
(id INT AUTO_iNCREMENT NOT NULL,
numberOfgate int NOT NULL,
terminal_id int,
capacity int,
primary key (id)
);

insert into  Gate
(id, numberOfgate, terminal_id, capacity)
values
(1, 1,1, 60),
(2, 2,1, 40),
(3, 1,2, 80),
(4, 2,2,70);

select*from Gate;


create table routes 
(
id INT AUTO_iNCREMENT NOT NULL PRIMARY KEY,
route VARCHAR(30)
);

insert into routes
(id, route)
values
(1,"Vinnytsya-Jashkiv"),
(2,"Kiev-Rahnu"),
(3,"Zhmerynka-Tjapche");

select * from routes;

drop table Personal;

create table Personal (
id INT AUTO_iNCREMENT NOT NULL,
name varchar(20),
numb_phone int NOT NULL,
gate_id int,
primary key (id)
);

insert into  Personal
(id, name, numb_phone, gate_id)
values
(1, "Bohdan", 0931111,3),
(2, "Liosha",0502222,1),
(3, "Olia", 0673333,2),
(4, "Ira", 0503333,4);

select * from Personal;

create table Salary (
id INT AUTO_iNCREMENT NOT NULL,
id_personal int,
position varchar (20),
salary int,
foreign key (id_personal) references Personal(id),
primary key (id)
);

insert into Salary
(id, id_personal, position, salary)
values
(1, 1,"boss", 30000),
(2, 2, "manager", 20000),
(3, 3, "manager", 15000),
(4, 3, "manager", 15000);

select * from Salary;

create table personalInfo (
id INT AUTO_iNCREMENT NOT NULL,
id_personal int,
maryed  BOOLEAN,
Birhday  varchar (30),
city  varchar (30),
foreign key (id_personal) references Personal(id),
primary key (id)
);

insert into personalInfo 
(id, id_personal, maryed, Birhday, city)
values
(1, 1,true, "28/12/1987", "Kyiv"),
(2, 2,true, "18/11/1993", "Lviv"),
(3, 3,true, "05/12/1957", "Odesa"),
(4, 3,false, "05/12/1988", "Kyiv");

-- TRIGGERS and PROCEDURES

DELIMITER //
CREATE TRIGGER deletePersonal
BEFORE DELETE ON PERSONAL
FOR EACH ROW
BEGIN
DELETE FROM personalInfo WHERE id=old.id;
DELETE FROM Salary WHERE id=old.id;
END; //
DELIMITER ;

select * from passenger 
left outer join ticket
on passenger.id=ticket.id where flight_id=1; 

select * from passenger;