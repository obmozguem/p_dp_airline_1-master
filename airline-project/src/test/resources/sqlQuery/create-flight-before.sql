INSERT INTO category (id, category_type) VALUES (1, 'FIRST'),
                                                (2, 'BUSINESS'),
                                                (3, 'PREMIUM_ECONOMY'),
                                                (4, 'ECONOMY');

INSERT INTO aircrafts (id, aircraft_number, model, model_year, flight_range)
VALUES (1, '17000012', 'Embraer E170STD', 2002, 3800);
INSERT INTO aircrafts (id, aircraft_number, model, model_year, flight_range)
VALUES (2, '5134', 'Airbus A320-200', 2011, 4300);
INSERT INTO aircrafts (id, aircraft_number, model, model_year, flight_range)
VALUES (3, '35283', 'Boeing 737-800', 2008, 5765);

INSERT INTO destination (id, airport_code, airport_name, city_name, country_name, timezone)
VALUES (1, 'VKO', 'Внуково', 'Москва', 'Россия', 'GMT +3');
INSERT INTO destination (id, airport_code, airport_name, city_name, country_name, timezone)
VALUES (2, 'VOG', 'Гумрак', 'Волгоград', 'Россия', 'GMT +3');
INSERT INTO destination (id, airport_code, airport_name, city_name, country_name, timezone)
VALUES (3, 'MQF', 'Магнитогорск', 'Магнитогорск', 'Россия', 'GMT +5');
INSERT INTO destination (id, airport_code, airport_name, city_name, country_name, timezone)
VALUES (4, 'OMS', 'Омск', 'Омск', 'Россия', 'GMT +6');

INSERT INTO flights (id, code, arrival_date, departure_date, flight_status, aircraft_id, from_id, to_id)
VALUES (1, 'VOGOMS', '2022-11-23 07:30:00', '2022-11-23 04:30:00', 'ON_TIME',
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 1),
        (SELECT destination.id FROM destination WHERE city_name = 'Волгоград'),
        (SELECT destination.id FROM destination WHERE city_name = 'Омск'));
INSERT INTO flights (id, code, arrival_date, departure_date, flight_status, aircraft_id, from_id, to_id)
VALUES (2, 'VKOVOG', '2023-10-23 10:50:00', '2023-10-23 08:15:00', 'DELAYED',
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 1),
        (SELECT destination.id FROM destination WHERE city_name = 'Москва'),
        (SELECT destination.id FROM destination WHERE city_name = 'Волгоград'));
INSERT INTO flights (id, code, arrival_date, departure_date, flight_status, aircraft_id, from_id, to_id)
VALUES (3, 'MQFVKO', '2023-02-14 09:11:00', '2023-02-14 04:20:00', 'ON_TIME',
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 2),
        (SELECT destination.id FROM destination WHERE city_name = 'Магнитогорск'),
        (SELECT destination.id FROM destination WHERE city_name = 'Москва'));
INSERT INTO flights (id, code, arrival_date, departure_date, flight_status, aircraft_id, from_id, to_id)
VALUES (4, 'OMSMQF','2023-04-01 23:50:00', '2023-04-01 20:30:00', 'ON_TIME',
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 3),
        (SELECT destination.id FROM destination WHERE city_name = 'Омск'),
        (SELECT destination.id FROM destination WHERE city_name = 'Магнитогорск'));

INSERT INTO seats (id, seat_number, is_near_emergency_exit, is_locked_back, category_id, aircraft_id)
VALUES (1, '1A', false, true,
        (SELECT category.id FROM category WHERE category.id = 1),
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 1));
INSERT INTO seats (id, seat_number, is_near_emergency_exit, is_locked_back, category_id, aircraft_id)
VALUES (2, '1B', false, true,
        (SELECT category.id FROM category WHERE category.id = 2),
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 1));
INSERT INTO seats (id, seat_number, is_near_emergency_exit, is_locked_back, category_id, aircraft_id)
VALUES (3, '1C', false, true,
        (SELECT category.id FROM category WHERE category.id = 3),
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 1));
INSERT INTO seats (id, seat_number, is_near_emergency_exit, is_locked_back, category_id, aircraft_id)
VALUES (4, '1D', false, true,
        (SELECT category.id FROM category WHERE category.id = 4),
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 1));
INSERT INTO seats (id, seat_number, is_near_emergency_exit, is_locked_back, category_id, aircraft_id)
VALUES (5, '1E', false, true,
        (SELECT category.id FROM category WHERE category.id = 1),
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 2));
INSERT INTO seats (id, seat_number, is_near_emergency_exit, is_locked_back, category_id, aircraft_id)
VALUES (6, '1F', false, true,
        (SELECT category.id FROM category WHERE category.id = 2),
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 2));
INSERT INTO seats (id, seat_number, is_near_emergency_exit, is_locked_back, category_id, aircraft_id)
VALUES (7, '2A', true, true,
        (SELECT category.id FROM category WHERE category.id = 2),
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 2));
INSERT INTO seats (id, seat_number, is_near_emergency_exit, is_locked_back, category_id, aircraft_id)
VALUES (8, '2B', true, true,
        (SELECT category.id FROM category WHERE category.id = 3),
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 2));
INSERT INTO seats (id, seat_number, is_near_emergency_exit, is_locked_back, category_id, aircraft_id)
VALUES (9, '2C', true, true,
        (SELECT category.id FROM category WHERE category.id = 3),
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 2));
INSERT INTO seats (id, seat_number, is_near_emergency_exit, is_locked_back, category_id, aircraft_id)
VALUES (10, '2D', true, true,
        (SELECT category.id FROM category WHERE category.id = 4),
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 3));
INSERT INTO seats (id, seat_number, is_near_emergency_exit, is_locked_back, category_id, aircraft_id)
VALUES (11, '2E', true, true,
        (SELECT category.id FROM category WHERE category.id = 4),
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 3));
INSERT INTO seats (id, seat_number, is_near_emergency_exit, is_locked_back, category_id, aircraft_id)
VALUES (12, '2F', true, true,
        (SELECT category.id FROM category WHERE category.id = 4),
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 3));
INSERT INTO seats (id, seat_number, is_near_emergency_exit, is_locked_back, category_id, aircraft_id)
VALUES (13, '11A', false, false,
        (SELECT category.id FROM category WHERE category.id = 1),
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 3));
INSERT INTO seats (id, seat_number, is_near_emergency_exit, is_locked_back, category_id, aircraft_id)
VALUES (14, '21F', false, false,
        (SELECT category.id FROM category WHERE category.id = 1),
        (SELECT aircrafts.id FROM aircrafts WHERE aircrafts.id = 3));

INSERT INTO passengers (first_name, last_name, middle_name, birth_date, gender, email, phone_number,
                        serial_number_passport, passport_issuing_date, passport_issuing_country, id)
VALUES ('John2', 'Simons2', 'J2', TO_DATE('2003/11/08', 'YYYY/MM/DD'), 'MALE', 'passenger2@mail.ru', '79111181111',
        '0010 001000', TO_DATE('2006/01/11', 'YYYY/MM/DD'), 'Россия', 4);

INSERT INTO passengers(first_name, last_name, middle_name, birth_date, gender, email, phone_number,
                       serial_number_passport, passport_issuing_date, passport_issuing_country, id)
VALUES ('Пётр2', 'Петров2', 'Петрович2', TO_DATE('1986/01/11', 'YYYY/MM/DD'), 'MALE', 'petrov2@mail.ru', '79111511111',
        '1121 111121', TO_DATE('2006/01/11', 'YYYY/MM/DD'), 'Россия', 5);

INSERT INTO passengers (first_name, last_name, middle_name, birth_date, gender, email, phone_number,
                        serial_number_passport, passport_issuing_date, passport_issuing_country, id)
VALUES ('John20', 'Simons20', 'J20', TO_DATE('2003/11/08', 'YYYY/MM/DD'), 'MALE', 'passenger20@mail.ru', '79111881111',
        '0011 001800', TO_DATE('2002/01/10', 'YYYY/MM/DD'), 'Россия', 6);

INSERT INTO flight_seats (id, fare, is_registered, is_sold, flight_id, seat_id)
VALUES (1, 500, true, false,
        (SELECT flights.id FROM flights WHERE flights.id = 1),
        (SELECT seats.id FROM seats WHERE seats.id = 1));
INSERT INTO flight_seats (id, fare, is_registered, is_sold, flight_id, seat_id)
VALUES (2, 600, true, true,
        (SELECT flights.id FROM flights WHERE flights.id = 1),
        (SELECT seats.id FROM seats WHERE seats.id = 2));
INSERT INTO flight_seats (id, fare, is_registered, is_sold, flight_id, seat_id)
VALUES (3, 650, true, true, (SELECT flights.id FROM flights WHERE flights.id = 1),
        (SELECT seats.id FROM seats WHERE seats.id = 3));


INSERT INTO tickets (id, ticket_number, flight_id, passenger_id, flight_seat_id)
VALUES (1, 'SD-2222',
        (SELECT flights.id FROM flights WHERE flights.id = 1),
        (SELECT passengers.id FROM passengers WHERE passengers.id = 4),
        (SELECT flight_seats.id FROM flight_seats WHERE flight_seats.id = 2));
INSERT INTO tickets (id, ticket_number, flight_id, passenger_id, flight_seat_id)
VALUES (2, 'ZX-3333', (SELECT flights.id FROM flights WHERE flights.id = 1),
        (SELECT passengers.id FROM passengers WHERE passengers.id = 5),
        (SELECT flight_seats.id FROM flight_seats WHERE flight_seats.id = 3));
