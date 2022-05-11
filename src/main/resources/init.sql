DROP DATABASE AIRPORT;
CREATE DATABASE AIRPORT;
USE AIRPORT;

drop table passenger;

create table Passenger
(id INT AUTO_INCREMENT NOT NULL,
name varchar (20),
surname varchar (50),
active boolean NOT NULL default true,
primary key (id)
);

insert into Passenger
(id, name, surname, active)
values
(1, "Bohdan", "Shchehliuk", true),
(2, "Liosha", "Khomyshen", true),
(3, "Ira", "Drezden", true),
(4, "Vasia", "Prystai", true),
(5, "Olia", "Halahan", true),
(6, "Nastai", "Halyha", true),
(7, "Liosha", "Tsar", true),
(8, "Petia", "Mavon", true),
(9, "Olia", "Shchehliuk", true),
(10, "Yulia", "Antonenko", true);

select * from Passenger;

drop table passport;

create table passport (
id int AUTO_INCREMENT NOT NULL,
serial_number varchar(30) UNIQUE,
birthdate DATE NOT NULL,
issue_date DATE NOT NULL,
passenger_id int NOT NULL,
passport_type varchar (20),
active boolean NOT NULL default true,
primary key (id),
foreign key (passenger_id) references Passenger(id)
);

insert into passport 
(id, serial_number, birthdate, issue_date, passenger_id, passport_type, active)
values 
(1,"SO1234",'1987-12-28', '2000-10-23',1, "UAinternational",true),
(2,"SO1211",'1988-11-11', '2010-05-22',2, "UAinternationalBIO",true),
(3,"GO1234",'1999-02-28', '2020-09-03',3, "UAinternational",true),
(4,"124566", '1997-12-01','2018-08-13',4, "UAinternational",true),
(5,"ddd124",'1985-12-21','2010-03-11',5,"UA_IDcard",true),
(6,"SO344d", '2007-12-18','2010-02-28',6, "UAinternationalBIO",true),
(7,"13322d",'1999-05-22', '2015-03-10',7, "PLinternationalBIO",true),
(8,"23fw2f",'1988-06-28', '1999-04-28',8,"UAIDcard",true),
(9,"1223dw", '1992-11-30', '2000-05-24',9,"UAIDcard",true),
(10,"11ddd2",'1996-12-28','2009-06-19',10,"UAinternational", false);

drop table Terminal;

create table Terminal
(id INT AUTO_iNCREMENT NOT NULL,
name varchar(20) NOT NULL,
capacity int NOT NULL,
active boolean default true,
primary key (id)
);

insert into  Terminal
(id, name, capacity)
values
(1, "West",100),
(2, "East",150);

select*from  Terminal;

drop table Gate;

create table Gate
(id INT AUTO_iNCREMENT NOT NULL,
terminal_id int NOT NULL,
capacity int NOT NULL,
active boolean default true,
primary key (id),
foreign key (terminal_id) references Terminal(id)
);

insert into  Gate
(id, terminal_id, capacity)
values
(1, 1, 60),
(2, 1, 40),
(3, 2, 80),
(4, 2,70);

drop table route;

create table Route 
(id INT AUTO_iNCREMENT NOT NULL PRIMARY KEY,
route VARCHAR(30),
active boolean default true
);

insert into route
(id, route)
values
(1,"Vinnytsya-Jashkiv"),
(2,"Kiev-Rahnu"),
(3,"Zhmerynka-Tjapche");

select * from routes;

drop table Aircraft_Type;

create table  Aircraft_Type
(id INT AUTO_iNCREMENT NOT NULL,
producer varchar(20) NOT NULL,
type varchar(20) NOT NULL,
capacity int NOT NULL,
active boolean default true,
primary key (id)
);

insert into  Aircraft_Type
(id, producer, type, capacity)
values
(1, "Boeing", "737",350),
(2, "Airbus", "A310",320);

select*from AircraftType;

drop table Airline;

create table Airline
(id INT AUTO_iNCREMENT NOT NULL,
name varchar(20),
rate int, -- від 1 до 10
active boolean NOT NULL default true,
primary key (id)
);

insert into Airline
(id, name, rate, active)
values
(1, "Qatar Airways", 10, true),
(2, "Qantas", 9, true),
(3, "IAU", 5, true);

select*from Airline;

drop table aircraft;

create table Aircraft
(id INT AUTO_iNCREMENT NOT NULL,
serial_number int NOT NULL UNIQUE,
airline_id int NOT NULL,
aircraft_type_id int NOT NULL,
active boolean NOT NULL default true,
primary key (id),
foreign key (airline_id) references Airline(id),
foreign key (aircraft_type_id) references Aircraft_type(id)
);

insert into Aircraft
(id, airline_id,aircraft_type_id, serial_number, active)
values
(1, 1,1, 12212, true),
(2, 1,2,903473, true),
(3,2,2, 909084, true),
(4,2,1, 234349, true),
(5,3,2, 234004, true);

select*from Aircraft;

drop table Flight;

create table Flight
(id INT AUTO_iNCREMENT NOT NULL,
flight_number int NOT NULL,
time date,
flight_status binary NOT NULL, -- 0 - створено, 1 - подано до завантаження, 2 - в польоті, 3 - завершено, 4-  скасовано
airline_id int,
craft_id int,
gate_id int,
route_id int,
active boolean NOT NULL default true,
primary key (id),
foreign key (airline_id) references Airline(id),
foreign key (craft_id) references Aircraft(id),
foreign key (gate_id) references Gate(id),
foreign key (route_id) references Route(id)
);

insert into Flight
(id, flight_number, time, flight_status, airline_id,  craft_id, gate_id, active, route_id)
values
(1, 11, '2021-12-31', 1,1,  1, 1, true, 1),
(2, 12, '2022-12-31', 0,1, 2,1, true, 1),
(3, 13, '2021-11-30', 2,2, 3, 1, true,2),
(4, 14, '2021-12-31', 1, 2, 4, 2, true,2),
(5, 15, '2023-12-31', 0, 1, 2, 2, true,3),
(6, 16, '2020-12-31', 1,3, 5, 1, true,3);

select * from Flight;

drop table Ticket;

create table Ticket
(id INT AUTO_iNCREMENT NOT NULL,
number int,
flight_id int NOT NULL,
seat int NOT NULL,
passenger_id int,
ticket_status binary NOT NULL, -- 0 not sold, 1 - sold, 2 - boarded, 3 - finished
active boolean default true,
CONSTRAINT UNIQUE_TICKET UNIQUE (flight_id, seat),
primary key (id),
foreign key (flight_id) references Flight(id),
foreign key (passenger_id) references Passenger(id)
);

insert into Ticket
(id, number, flight_id , seat, passenger_id, ticket_status, active)
values
(1, 11111, 1, 34, 1, 1, true),
(2, 22222, 1, 4, 2,0, true),
(3, 33333, 1, 2, 3,1, true),
(4, 44444, 2, 3, 1,0, true),
(5, 55555, 2, 34, 4,0, true),
(6, 66666, 2, 24, 2,0, true),
(7, 77777, 3, 14, 5,2, true),
(8, 88888, 3, 1, 6,2, true),
(9, 99999, 3, 31, 7,2, true),
(10, 10101, 4, 24, 8,1, true),
(11, 10111, 4,23, 9,1, true),
(12, 1212, 4, 4, 10,1, true),
(13, 1313, 5, 15, 5,0, true),
(14, 1414, 5, 4, 4,0, true),
(15, 1515, 5, 5, 3,0, true),
(16, 161616, 6, 7, 10,1, true),
(17, 1717, 6, 8, 2,1, true);

select * from Ticket;

drop table Personal;

create table Personal (
id INT AUTO_iNCREMENT NOT NULL,
name varchar(20) NOT NULL,
phone int NOT NULL,
gate_id int,  
active boolean default true,
primary key (id),
foreign key (gate_id) references Gate(id)
);

insert into  Personal
(id, name, phone, gate_id)
values
(1, "Bohdan", 0931111,3),
(2, "Liosha",0502222,1),
(3, "Olia", 0673333,2),
(4, "Ira", 0503333,4);

select * from Personal;

drop table Salary;

create table Salary (
id INT AUTO_iNCREMENT NOT NULL,
id_personal int,
position varchar (20),
salary int,
active boolean default true,
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

drop table personalInfo;

create table personalInfo (
id INT AUTO_iNCREMENT NOT NULL,
id_personal int,
married  BOOLEAN,
Birthday  date,
city  varchar (30),
active boolean default true,
foreign key (id_personal) references Personal(id),
primary key (id)
);

insert into personalInfo 
(id, id_personal, married, Birthday, city)
values
(1, 1,true, "1987-12-28", "Kyiv"),
(2, 2,true, "1993-11-18", "Lviv"),
(3, 3,true, "1957-12-05", "Odesa"),
(4, 3,false, "1988-12-05", "Kyiv");

select * from personalInfo;

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
