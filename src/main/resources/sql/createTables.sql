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
