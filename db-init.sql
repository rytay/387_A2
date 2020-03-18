SET SQL_MODE = 'ALLOW_INVALID_DATES';

CREATE DATABASE IF NOT EXISTS library_sys;
CREATE USER IF NOT EXISTS 'dbadmin'@'localhost' identified by 'password';
update user set authentication_string=password('YOURPASSWORDHERE') where user='root';
GRANT ALL PRIVILEGES ON * . * TO 'dbadmin'@'localhost';
FLUSH PRIVILEGES;
USE library_sys;

DROP TABLE IF EXISTS
	book,
    users,
    loan;

CREATE TABLE IF NOT EXISTS book(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    book_desc VARCHAR(255),
    isbn VARCHAR(255),
    author VARCHAR(255),
    publisher VARCHAR(255)

);

INSERT INTO book (title, book_desc, isbn, author, publisher)
VALUES 
	("Test Book", "Test Description", "0000000", "Test Author", "Test Publisher"),
	("Test Book1", "Test Description1", "1111111", "Test Author1", "Test Publisher 1"),
	("Test Book2", "Test Description2", "2222222", "Test Author2", "Test Publisher 2"),
	("Test Book3", "Test Description3", "3333333", "Test Author3", "Test Publisher 3"),
	("Test Book4", "Test Description4", "4444444", "Test Author4", "Test Publisher 4");
    
CREATE TABLE IF NOT EXISTS user(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(255),
    pass VARCHAR(255),
    auth_level INT
);

INSERT INTO user (login, pass, auth_level)
VALUES
	("admin", "admin", 0);

CREATE TABLE IF NOT EXISTS loan(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    book_id INT,
    user_id INT,
    date_borrowed TIMESTAMP,
    date_returned TIMESTAMP
);

INSERT INTO loan (book_id, user_id, date_borrowed, date_returned)
VALUES
	(1, 1, NOW(), null),
	(2, 1, NOW()+1, null),
	(3, 1, NOW()+2, null),
	(4, 1, NOW()+3, null),
	(5, 1, NOW()+4, null);

    

