DROP DATABASE IF EXISTS finance_database;
CREATE DATABASE finance_database;
USE finance_database;

CREATE TABLE init (
 login VARCHAR(255)  PRIMARY KEY NOT NULL,
 password VARCHAR(255) NOT NULL
);
INSERT INTO init VALUES ('aliaksei', '1234');
INSERT INTO init VALUES ('aliaksandr', 'qwerty');

CREATE TABLE accounts (
login VARCHAR(255) NOT NULL,
amount DECIMAL NOT NULL,
cart_name VARCHAR(255) NOT NULL,
PRIMARY KEY (login, cart_name),
CONSTRAINT init_account_fk 
FOREIGN KEY (login) REFERENCES init(login) 
	ON DELETE CASCADE
    ON UPDATE CASCADE
);
INSERT INTO accounts VALUES('aliaksei', 2500, 'first_cart');
INSERT INTO accounts VALUES('aliaksandr', 2500, 'first_cart');

CREATE TABLE categories (
login VARCHAR(255) NOT NULL,
category_name VARCHAR(255) NOT NULL,
category_type VARCHAR(255) NOT NULL,
PRIMARY KEY (login, category_name),
CONSTRAINT init_categories_fk FOREIGN KEY (login) REFERENCES init (login) 
	ON DELETE CASCADE
    ON UPDATE CASCADE
);
INSERT INTO categories VALUES ('aliaksei', 'food', 'SPENDING');
INSERT INTO categories VALUES ('aliaksei', 'salary', 'RESEIPT');
INSERT INTO categories VALUES ('aliaksandr', 'things', 'SPENDING');
INSERT INTO categories VALUES ('aliaksandr', 'robbing', 'RECEIPT');

CREATE TABLE counter_book (
login VARCHAR(255) NOT NULL,
date DATE NOT NULL,
amount DECIMAL NOT NULL,
cart_name VARCHAR(255) NOT NULL,
CONSTRAINT accounts_counter_book_fk
FOREIGN KEY (login, cart_name) REFERENCES accounts (login, cart_name) 
	ON DELETE CASCADE
	ON UPDATE CASCADE,
CONSTRAINT init_counter_book_fk
FOREIGN KEY (login) REFERENCES init (login)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

INSERT INTO counter_book VALUES ('aliaksei', '2020-01-01', 2500, 'first_cart'),
								('aliaksei', '2020-01-01', -500, 'first_cart'),
								('aliaksei', '2020-01-01', -500, 'first_cart'),
								('aliaksei', '2020-01-01', -500, 'first_cart');
INSERT INTO counter_book VALUES ('aliaksandr', '2020-01-10', 500, 'first_cart');					

