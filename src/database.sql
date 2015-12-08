connect 'jdbc:mysql://localhost:3306/cspExam?user=root&password=root';

drop TABLE IF EXISTS person;
CREATE table person(
  idNum int AUTO_INCREMENT,
  firstName VARCHAR(30) NOT NULL DEFAULT 'Oops',
  lastName VARCHAR(30) NOT NULL ,
  email varchar(30) NOT NULL ,
  phone varchar(30) not null,
  comments varchar (1000),
  CONSTRAINT pk_name PRIMARY KEY(idNum)
);
