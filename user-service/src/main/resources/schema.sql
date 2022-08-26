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
