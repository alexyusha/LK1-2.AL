CREATE DATABASE IF NOT EXISTS insurance;

USE insurance

CREATE TABLE IF NOT EXISTS contracts (
  number INT NOT NULL,
  date_conclusion DATE DEFAULT NULL,
  start_contract DATE DEFAULT NULL,
  finish_contract DATE DEFAULT NULL,
  PRIMARY KEY (number)
);

CREATE TABLE IF NOT EXISTS clients (
  number_contract INT NOT NULL,
  type_client VARCHAR(20) DEFAULT NULL,
  name VARCHAR(50) DEFAULT NULL,
  address VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (number_contract)
);

CREATE TABLE IF NOT EXISTS insured_people (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) DEFAULT NULL,
  last_name VARCHAR(255) DEFAULT NULL,
  middle_name VARCHAR(255) DEFAULT NULL,
  birthday DATE DEFAULT NULL,
  INN VARCHAR(20) DEFAULT NULL,
  price DOUBLE DEFAULT NULL,
  number_contract INT DEFAULT NULL,
  PRIMARY KEY (id)
);