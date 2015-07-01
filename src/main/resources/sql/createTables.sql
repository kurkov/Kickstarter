CREATE TABLE categories (
  id   INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(512)
);

CREATE TABLE projects (
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(512),
  description TEXT,
  id_category INTEGER
);

CREATE TABLE users (
  id INT KEY AUTO_INCREMENT,
  username VARCHAR(50),
  password VARCHAR(50),
  email VARCHAR(50),
  enable BOOLEAN
);

CREATE TABLE authorities (
  id INT KEY AUTO_INCREMENT,
  username VARCHAR(50),
  authority VARCHAR(50)
);

CREATE TABLE user_authority(
  user_id INTEGER(6) NOT NULL,
  authority_id INTEGER(6) NOT NULL,
  KEY user(user_id),
  KEY authority(authority_id)
);