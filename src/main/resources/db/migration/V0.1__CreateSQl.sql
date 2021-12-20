CREATE
DATABASE IF NOT EXISTS volunteer;

USE volunteer;

CREATE TABLE  IF NOT EXISTS user (
                        id int(11) NOT NULL AUTO_INCREMENT,
                        name VARCHAR(30) NOT NULL,
                        age int(11) DEFAULT NULL,
                        PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

INSERT INTO user(id,name,age)
VALUES (1,'test1',20),
       (2,'test2',20),
       (3,'test3',20);