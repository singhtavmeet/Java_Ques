
CREATE DATABASE orderdb;
CREATE DATABASE orderdb_test;


USE orderdb;
CREATE TABLE orders (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  customer_name VARCHAR(100) NOT NULL,
  total_amount DECIMAL(10,2) NOT NULL
);

USE orderdb_test;
CREATE TABLE orders (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  customer_name VARCHAR(100) NOT NULL,
  total_amount DECIMAL(10,2) NOT NULL
);

