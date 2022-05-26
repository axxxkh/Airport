CREATE DATABASE AIRPORT;
USE AIRPORT;

create table Passenger
(id INT AUTO_INCREMENT NOT NULL,
name varchar (20) NOT NULL,
surname varchar (50) NOT NULL,
active boolean NOT NULL default true,
primary key (id)
);

create table passport (
id int AUTO_INCREMENT NOT NULL,
serial_number varchar(30) UNIQUE,
birthdate DATE NOT NULL,
issue_date DATE NOT NULL,
passenger_id int NOT NULL,
passport_type varchar (20) NOT NULL,
active boolean NOT NULL default true,
primary key (id),
foreign key (passenger_id) references Passenger(id)
);

create table Terminal
(id INT AUTO_iNCREMENT NOT NULL,
name varchar(20) NOT NULL,
capacity int NOT NULL,
active boolean default true,
primary key (id)
);

create table Gate
(id INT AUTO_iNCREMENT NOT NULL,
terminal_id int NOT NULL,
capacity int NOT NULL,
active boolean default true,
primary key (id),
foreign key (terminal_id) references Terminal(id)
);

create table Route
(id INT AUTO_iNCREMENT NOT NULL PRIMARY KEY,
route VARCHAR(30),
active boolean default true
);

create table  Aircraft_Type
(id INT AUTO_iNCREMENT NOT NULL,
producer varchar(20) NOT NULL,
type varchar(20) NOT NULL,
capacity int NOT NULL,
active boolean default true,
primary key (id)
);

create table Airline
(id INT AUTO_iNCREMENT NOT NULL,
name varchar(20),
rate int, -- від 1 до 10
active boolean NOT NULL default true,
primary key (id)
);

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

create table Flight
(id INT AUTO_iNCREMENT NOT NULL,
flight_number int NOT NULL,
time date,
flight_status smallint NOT NULL, -- 0 - створено, 1 - подано до завантаження, 2 - в польоті, 3 - завершено, 4-  скасовано
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

create table Ticket
(id INT AUTO_iNCREMENT NOT NULL,
number int,
flight_id int NOT NULL,
seat int NOT NULL,
passenger_id int,
ticket_status smallint NOT NULL, -- 0 not sold, 1 - sold, 2 - boarded, 3 - finished
active boolean default true,
CONSTRAINT UNIQUE_TICKET UNIQUE (flight_id, seat),
primary key (id),
foreign key (flight_id) references Flight(id),
foreign key (passenger_id) references Passenger(id)
);

create table Personal (
id INT AUTO_iNCREMENT NOT NULL,
name varchar(20) NOT NULL,
phone int NOT NULL,
gate_id int,
active boolean default true,
primary key (id),
foreign key (gate_id) references Gate(id)
);

create table Salary (
id INT AUTO_iNCREMENT NOT NULL,
id_personal int,
position varchar (20),
salary int,
active boolean default true,
foreign key (id_personal) references Personal(id),
primary key (id)
);

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
