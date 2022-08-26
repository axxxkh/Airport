CREATE TABLE roles
(id bigint not null AUTO_INCREMENT,
role varchar(20) NOT NULL default "PASSENGER" unique,
active boolean NOT NULL default true,
primary key (id));

CREATE TABLE user
(id bigint NOT NULL AUTO_INCREMENT,
email varchar(50) NOT NULL UNIQUE,
password VARCHAR (60) NOT NULL,
secret_question VARCHAR (50) NOT NULL,
secret_answer VARCHAR (50) NOT NULL,
role_id bigint  default 3,
active boolean NOT NULL default true,
foreign key (role_id) references roles(id),
primary key(id));

CREATE TABLE passport
(id bigint AUTO_INCREMENT NOT NULL,
serial_number varchar(30) UNIQUE,
birthdate DATE NOT NULL,
issue_date DATE NOT NULL,
active boolean NOT NULL default true,
primary key (id)
);

CREATE TABLE passenger
(id bigint AUTO_INCREMENT NOT NULL,
user_id bigint NOT NULL,
name varchar (20) NOT NULL,
surname varchar (50) NOT NULL,
passport_id bigint NOT NULL,
active boolean NOT NULL default true,
primary key (id),
foreign key(user_id) references User(id),
foreign key(passport_id) references Passport(id)
);

CREATE TABLE terminal
(id bigint AUTO_INCREMENT NOT NULL,
name varchar(20) NOT NULL,
capacity int NOT NULL,
active boolean default true,
primary key (id)
);

CREATE TABLE gate
(id bigint AUTO_INCREMENT NOT NULL,
terminal_id bigint NOT NULL,
capacity int NOT NULL,
active boolean default true,
primary key (id),
foreign key (terminal_id) references Terminal(id)
);

CREATE TABLE route
(id bigint AUTO_INCREMENT NOT NULL PRIMARY KEY,
name VARCHAR(30),
active boolean default true
);

CREATE TABLE  aircraft_Type
(id bigint AUTO_INCREMENT NOT NULL,
producer varchar(20) NOT NULL,
type varchar(20) NOT NULL,
capacity int NOT NULL,
active boolean default true,
primary key (id)
);

CREATE TABLE airline
(id bigint AUTO_INCREMENT NOT NULL,
name varchar(20),
rate int, -- від 1 до 10
active boolean NOT NULL default true,
primary key (id)
);

CREATE TABLE aircraft
(id bigint AUTO_INCREMENT NOT NULL,
serial_number int NOT NULL UNIQUE,
airline_id bigint NOT NULL,
aircraft_type_id bigint NOT NULL,
active boolean NOT NULL default true,
primary key (id),
foreign key (airline_id) references Airline(id),
foreign key (aircraft_type_id) references Aircraft_type(id)
);

CREATE TABLE flight
(id bigint AUTO_INCREMENT NOT NULL,
flight_number varchar(20) NOT NULL UNIQUE,
time date,
flight_status smallint NOT NULL, -- 0 - створено, 1 - подано до завантаження, 2 - в польоті, 3 - завершено, 4-  скасовано
airline_id bigint,
craft_id bigint,
gate_id bigint,
route_id bigint,
active boolean NOT NULL default true,
primary key (id),
foreign key (airline_id) references Airline(id),
foreign key (craft_id) references Aircraft(id),
foreign key (gate_id) references Gate(id),
foreign key (route_id) references Route(id)
);

CREATE TABLE ticket
(id bigint AUTO_INCREMENT NOT NULL,
number int,
flight_id bigint NOT NULL,
seat int NOT NULL,
passenger_id bigint,
buy_date date,
ticket_status smallint NOT NULL, -- 0 not sold, 1 - sold, 2 - boarded, 3 - finished
active boolean default true,
CONSTRAINT UNIQUE_TICKET UNIQUE (flight_id, seat),
primary key (id),
foreign key (flight_id) references Flight(id),
foreign key (passenger_id) references Passenger(id)
);