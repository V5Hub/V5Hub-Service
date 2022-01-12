CREATE
DATABASE IF NOT EXISTS volunteer;

USE volunteer;


CREATE TABLE IF NOT EXISTS user (
    id VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    student_id VARCHAR(30) DEFAULT NULL,
    email VARCHAR(30),
    phone_number VARCHAR(30),
    password VARCHAR(16),
    college VARCHAR(30),
    deleted BOOLEAN NOT NULL,
    picture BLOB DEFAULT NULL,
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS manager (
    manager_id VARCHAR(30) NOT NULL,
    deleted BOOLEAN NOT NULL,
    FOREIGN KEY (manager_id) REFERENCES user (id),
    PRIMARY KEY (manager_id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS sponsor (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    picture VARCHAR(60), -- URL
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS manager_sponsor (
    manager_id VARCHAR(30) NOT NULL,
    sponsor_id INT NOT NULL,
    PRIMARY KEY (manager_id, sponsor_id),
    FOREIGN KEY (manager_id) REFERENCES manager (manager_id),
    FOREIGN KEY (sponsor_id) REFERENCES sponsor (id)
) DEFAULT CHARSET=utf8;

-- tag content  pic->delete
CREATE TABLE IF NOT EXISTS activity (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    start_time DATETIME,
    end_time DATETIME,
    register_deadline DATETIME,
    position VARCHAR(60),
    level VARCHAR(30),
    manager_id VARCHAR(30),
    sponsor_id VARCHAR(30),
    tags VARCHAR(30),
    description TEXT,
    content TEXT,
    picture VARCHAR(60), -- URL
    picture_horizontal VARCHAR(60), -- URL
    state_type INT NOT NULL,
    FOREIGN KEY (sponsor_id) REFERENCES user (id),
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS register (
    id INT NOT NULL AUTO_INCREMENT,
    activity_id INT NOT NULL,
    applicant_id VARCHAR(30) NOT NULL,
    name VARCHAR(30),
    student_id VARCHAR(30),
    email VARCHAR(30),
    phone_number VARCHAR(30),
    user_class VARCHAR(30),
    college VARCHAR(30),
    state INT NOT NULL,
    FOREIGN KEY (activity_id) REFERENCES activity (id),
    FOREIGN KEY (applicant_id) REFERENCES user (id),
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;


