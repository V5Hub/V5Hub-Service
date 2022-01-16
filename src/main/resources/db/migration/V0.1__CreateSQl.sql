CREATE
DATABASE IF NOT EXISTS volunteer;

USE volunteer;


CREATE TABLE IF NOT EXISTS user (
    id INT NOT NULL AUTO_INCREMENT,
    openid VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    student_id VARCHAR(30) DEFAULT NULL,
    email VARCHAR(30),
    phone_number VARCHAR(30),
    password VARCHAR(16),
    college VARCHAR(30),
    deleted BOOLEAN NOT NULL,
    picture BLOB DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (openid)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS manager (
    id INT NOT NULL,
    deleted BOOLEAN NOT NULL,
    FOREIGN KEY (id) REFERENCES user (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS sponsor (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    picture VARCHAR(60), -- URL
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS manager_sponsor (
    manager_id INT NOT NULL,
    sponsor_id INT NOT NULL,
    PRIMARY KEY (manager_id, sponsor_id),
    FOREIGN KEY (manager_id) REFERENCES manager (id) ON DELETE CASCADE,
    FOREIGN KEY (sponsor_id) REFERENCES sponsor (id) ON DELETE CASCADE
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
    manager_id INT,
    sponsor_id INT,
    description TEXT,
    content TEXT,
    picture VARCHAR(60), -- URL
    picture_horizontal VARCHAR(60), -- URL
    state_type INT NOT NULL,
    FOREIGN KEY (sponsor_id) REFERENCES sponsor (id),
    FOREIGN KEY (manager_id) REFERENCES manager (id),
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS register (
    id INT NOT NULL AUTO_INCREMENT,
    activity_id INT NOT NULL,
    applicant_id INT NOT NULL,
    name VARCHAR(30),
    student_id VARCHAR(30),
    email VARCHAR(30),
    phone_number VARCHAR(30),
    user_class VARCHAR(30),
    college VARCHAR(30),
    state INT NOT NULL,
    FOREIGN KEY (activity_id) REFERENCES activity (id) ON DELETE CASCADE,
    FOREIGN KEY (applicant_id) REFERENCES user (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS tag (
    id INT NOT NULL AUTO_INCREMENT,
    text VARCHAR(16) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (text)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS subscribe_sponsor (
    user_id INT NOT NULL,
    sponsor_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (sponsor_id) REFERENCES sponsor (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, sponsor_id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS subscribe_tag (
    user_id INT NOT NULL,
    tag_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, tag_id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS activity_tag (
    activity_id INT NOT NULL,
    tag_id INT NOT NULL,
    FOREIGN KEY (activity_id) REFERENCES activity (id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE,
    PRIMARY KEY (activity_id, tag_id)
) DEFAULT CHARSET=utf8;


