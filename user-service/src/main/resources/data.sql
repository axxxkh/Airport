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