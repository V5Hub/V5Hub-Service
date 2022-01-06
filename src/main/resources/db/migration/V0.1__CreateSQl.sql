CREATE
DATABASE IF NOT EXISTS volunteer;

USE volunteer;


CREATE TABLE  IF NOT EXISTS user (
    id VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    student_id VARCHAR(10) DEFAULT NULL,
    email VARCHAR(30),
    phone_number VARCHAR(11),
    password VARCHAR(11),
    college VARCHAR(30),
    deleted BOOLEAN NOT NULL,
    picture BLOB DEFAULT NULL,
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE  IF NOT EXISTS manager (
    manager_id VARCHAR(30) NOT NULL,
    deleted BOOLEAN NOT NULL,
    FOREIGN KEY (manager_id) REFERENCES user (id),
    PRIMARY KEY (manager_id)
) DEFAULT CHARSET=utf8;


-- tag content  pic->delete
CREATE TABLE IF NOT EXISTS activity (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    start_time TIME,
    end_time TIME,
    register_deadline TIME,
    position VARCHAR(30),
    sponsor_name VARCHAR(30),
    sponsor_id VARCHAR(30) NOT NULL,
    tags VARCHAR(30),
    description TEXT,
    content TEXT,
    picture LONGBLOB,
    state_type  INT NOT NULL,
    FOREIGN KEY (sponsor_id) REFERENCES user (id),
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS register (
    id INT NOT NULL AUTO_INCREMENT,
    activity_id INT NOT NULL,
    applicant_id VARCHAR(30) NOT NULL,
    name VARCHAR(30),
    student_id VARCHAR(10),
    email VARCHAR(30),
    phone_number VARCHAR(11),
    user_class VARCHAR(11),
    college VARCHAR(30),
    state INT NOT NULL,
    FOREIGN KEY (activity_id) REFERENCES activity (id),
    FOREIGN KEY (applicant_id) REFERENCES user (id),
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;


