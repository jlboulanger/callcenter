SET MODE MySQL;
create table client (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  locked_by INTEGER,
  lastname VARCHAR(255) NOT NULL,
  firstname VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL,
  dt_create DATE NOT NULL,
  dt_update DATE  
);

create table contact_info (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  client_id INTEGER NOT NULL,
  address1 VARCHAR(255) NOT NULL,
  address2 VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL,
  code VARCHAR(255),
  phone VARCHAR(255),
  dt_create DATE NOT NULL,
  dt_update DATE,
  FOREIGN KEY (client_id) references client (id) ON DELETE CASCADE
);


create table employee (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  lastname VARCHAR(255) NOT NULL,
  firstname VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL,
  supervisor INTEGER,
  dt_create DATE NOT NULL,
  dt_update DATE
);
