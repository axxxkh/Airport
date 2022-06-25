insert into Passenger
(id, name, surname, active, email, pwd)
values
(1, "Bohdan", "Shchehliuk",true, "bohdan@gmail.com", "123"),
(2, "Liosha", "Khomyshen", true, "alxxxkh@gmail.com", "$2a$10$KG7sM3zgF4FzNN7ElSsh4eSPod8TrNMZfaoEF9YRLXIfEFUirMgpO"), -- 123
(3, "Ira", "Drezden", true, "ira@gmail.com", "234"),
(4, "Vasia", "Prystai", true, "vasia@gmail.com", "345"),
(5, "Olia", "Halahan", true, "olia@gmail.com", "456"),
(6, "Nastai", "Halyha", true, "nastai@gmail.com", "567"),
(7, "Liosha", "Tsar", true, "liosha@gmail.com", "678"),
(8, "Petia", "Mavon", true, "petia@gmail.com", "789"),
(9, "Olia", "Shchehliuk", true, "shcehliuk@gmail.com", "890"),
(10, "Yulia", "Antonenko", true, "yulia@gmail.com", "901");

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

insert into roles
(id, role)
values
(1, "ADMIN"),
(2, "STAFF"),
(3, "PASSENGER");

insert into user_roles
(user_id, role_id)
values
(1,3),
(2,1),
(3,3),
(4,3),
(5,3),
(6,3),
(7,3),
(8,3),
(9,3),
(10,3);

insert into  Terminal
(id, name, capacity)
values
(1, "West",100),
(2, "East",150);

insert into  Gate
(id, terminal_id, capacity)
values
(1, 1, 60),
(2, 1, 40),
(3, 2, 80),
(4, 2,70);

insert into route
(id, name)
values
(1,"Vinnytsya-Jashkiv"),
(2,"Kiev-Rahnu"),
(3,"Zhmerynka-Tjapche");

insert into  Aircraft_Type
(id, producer, type, capacity)
values
(1, "Boeing", "737",350),
(2, "Airbus", "A310",320);

insert into Airline
(id, name, rate, active)
values
(1, "Qatar Airways", 10, true),
(2, "Qantas", 9, true),
(3, "IAU", 5, true);

insert into Aircraft
(id, airline_id,aircraft_type_id, serial_number, active)
values
(1, 1,1, 12212, true),
(2, 1,2,903473, true),
(3,2,2, 909084, true),
(4,2,1, 234349, true),
(5,3,2, 234004, true);

insert into Flight
(id, flight_number, time, flight_status, airline_id,  craft_id, gate_id, active, route_id)
values
(1, 11, '2021-12-31', 1,1,  1, 1, true, 1),
(2, 12, '2022-12-31', 0,1, 2,1, true, 1),
(3, 13, '2021-11-30', 2,2, 3, 1, true,2),
(4, 14, '2021-12-31', 1, 2, 4, 2, true,2),
(5, 15, '2023-12-31', 0, 1, 2, 2, true,3),
(6, 16, '2020-12-31', 1,3, 5, 1, true,3);

insert into Ticket
(id, number, flight_id , seat, passenger_id, ticket_status, buy_date, active)
values
(1, 11111, 1, 34, 1, 1,'2021-11-21', true),
(2, 22222, 1, 4, 2,0, '2021-05-04',true),
(3, 33333, 1, 2, 3,1,'2021-11-02', true),
(4, 44444, 2, 3, 1,0,'2021-07-11', true),
(5, 55555, 2, 34, 4,0,'2021-02-20', true),
(6, 66666, 2, 24, 2,0,'2021-01-03', true),
(7, 77777, 3, 14, 5,2,'2021-06-04', true),
(8, 88888, 3, 1, 6,2, '2021-03-11',true),
(9, 99999, 3, 31, 7,2,'2021-12-31', true),
(10, 10101, 4, 24, 8,1,'2021-12-01', true),
(11, 10111, 4,23, 9,1, '2021-04-15',true),
(12, 1212, 4, 4, 10,1, '2021-04-17',true),
(13, 1313, 5, 15, 5,0, '2021-11-07',true),
(14, 1414, 5, 4, 4,0, '2022-04-15',true),
(15, 1515, 5, 5, 3,0,'2021-03-09', true),
(16, 161616, 6, 7, 10,1,'2022-10-10', true),
(17, 1717, 6, 8, 2,1,'2021-11-14', true);

insert into  Personal
(id, name, phone, gate_id)
values
(1, "Bohdan", 0931111,3),
(2, "Liosha",0502222,1),
(3, "Olia", 0673333,2),
(4, "Ira", 0503333,4);

insert into Salary
(id, id_personal, position, salary)
values
(1, 1,"boss", 30000),
(2, 2, "manager", 20000),
(3, 3, "manager", 15000),
(4, 3, "manager", 15000);

insert into personal_info
(id, id_personal, married, Birthday, city)
values
(1, 1,true, "1987-12-28", "Kyiv"),
(2, 2,true, "1993-11-18", "Lviv"),
(3, 3,true, "1957-12-05", "Odesa"),
(4, 3,false, "1988-12-05", "Kyiv");