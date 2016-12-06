INSERT INTO rare_books VALUES (1, 'Jules Verne', true, 'GOOD', 'Journey to the Center of the Earth');
INSERT INTO rare_books VALUES (2, 'Isaac Asimov', true, 'FAIR', 'Foundation');
INSERT INTO rare_books VALUES (3, 'Stephen King', true, 'EXCELLENT', 'The Shining');
INSERT INTO rare_books VALUES (4, 'Michael Ende', false, 'EXCELLENT', 'The Neverending Story');
INSERT INTO rare_books VALUES (5, 'Henry David Thoreau', true, 'POOR', 'Walden');
SET FOREIGN_KEY_CHECKS=0;
BEGIN WORK;
INSERT INTO cars (id, make, model, year, driver_id) values (1, 'Chevy', 'Camaro', 1969, 1);
INSERT INTO drivers (id, first_name, last_name, car_id) values (1, 'Tom', 'Suliga', 1);
COMMIT;
SET FOREIGN_KEY_CHECKS=1;
INSERT INTO users (id, username, password, enabled) values (1, 'tom', '$2a$10$B2LrIkEXmztSlQu2OhVFNeP6EfbQNvrTNr7gBBkIPh9NlBxDN4nVe', true);
INSERT INTO authorities (id, username, authority) values (1, 'tom', 'ROLE_ADMIN');