CREATE DATABASE IF NOT EXISTS lib_examples
    DEFAULT CHARSET 'utf8mb4';

USE lib_examples;

DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user (
    id         INT      NOT NULL AUTO_INCREMENT,
    name       CHAR(8)  NOT NULL DEFAULT '',
    createTime DATETIME NOT NULL DEFAULT current_timestamp,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET 'utf8mb4';