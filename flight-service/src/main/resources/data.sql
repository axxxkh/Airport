INSERT INTO roles
(id, role)
values
(1, "ADMIN"),
(2, "STAFF"),
(3, "PASSENGER");

INSERT INTO user
(id, email, password, secret_question, secret_answer)
values
(1,"bohdan@gmail.com", "$2a$10$fJYHWJD6SK0eDbludARIJ.V3x7LUWCftYcCFX2tzFibd0a8.6Oqxu","name","bohdan"),
(2,"alxxxkh@gmail.com", "$2a$10$KG7sM3zgF4FzNN7ElSsh4eSPod8TrNMZfaoEF9YRLXIfEFUirMgpO", "name", "alex"); -- 123;

INSERT INTO passport
(id, serial_number, birthdate, issue_date)
values
(1,"SO1234",'1987-12-28', '2000-10-23'),
(2,"SO1211",'1988-11-11', '2010-05-22');

INSERT INTO passenger
(id, user_id, passport_id, name, surname)
values
(1, 1,1, "Bohdan", "Shchehliuk"),
(2, 2,2, "Oleksii", "Khomyshen");

INSERT INTO terminal
(id, name, capacity)
values
(1, "West",100),
(2, "East",150);

INSERT INTO gate
(id, terminal_id, capacity)
values
(1, 1, 60),
(2, 1, 40),
(3, 2, 80),
(4, 2,70);

INSERT INTO route
(id, name)
values
(1,"Vinnytsya-Jashkiv"),
(2,"Kiev-Rahnu"),
(3,"Zhmerynka-Tjapche");

INSERT INTO aircraft_type
(id, producer, type, capacity)
values
(1, "Boeing", "737",350),
(2, "Airbus", "A310",320);

INSERT INTO airline
(id, name, rate, active)
values
(1, "Qatar Airways", 10, true),
(2, "Qantas", 9, true),
(3, "IAU", 5, true);

INSERT INTO aircraft
(id, airline_id,aircraft_type_id, serial_number, active)
values
(1, 1,1, 12212, true),
(2, 1,2,903473, true),
(3,2,2, 909084, true),
(4,2,1, 234349, true),
(5,3,2, 234004, true);

INSERT INTO flight
(id, flight_number, time, flight_status, airline_id,  craft_id, gate_id, active, route_id)
values
(1, 11, '2021-12-31', 1,1,  1, 1, true, 1),
(2, 12, '2022-12-31', 0,1, 2,1, true, 1),
(3, 13, '2021-11-30', 2,2, 3, 1, true,2),
(4, 14, '2021-12-31', 1, 2, 4, 2, true,2),
(5, 15, '2023-12-31', 0, 1, 2, 2, true,3),
(6, 16, '2023-12-31', 1,3, 5, 1, true,3);

INSERT INTO ticket
(id, number, flight_id , seat, passenger_id, ticket_status, buy_date, active)
values
(1, 11111, 1, 34, 1, 1,'2021-11-21', true),
(2, 22222, 1, 4, 2,0, '2021-05-04',true),
(3, 33333, 1, 2, 1,1,'2021-11-02', true),
(4, 44444, 2, 3, 2,0,'2021-07-11', true),
(5, 55555, 2, 34, 1,0,'2021-02-20', true),
(6, 66666, 2, 24, 2,0,'2021-01-03', true),
(7, 77777, 3, 14, 1,2,'2021-06-04', true),
(8, 88888, 3, 1, 2,2, '2021-03-11',true),
(9, 88888, 6, 1, 2,2, '2021-03-11',true),
(10, 99999, 3, 31, 2,2,'2021-12-31', true);
