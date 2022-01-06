-- user
INSERT INTO user(id,name,student_id,email,phone_number,password,college,deleted)
VALUES ('1','test1','2020302029','1979557180@qq.com','12345678901','test1','软件学院',false),
       ('2','test2','2020302030','test2@gmail.com','12345678901','test2','计算机学院',false),
       ('3','test3','2020302031','test3@gmail.com','12345678901','test3','计算机学院',false),
       ('4','test4','2020302032','test4@gmail.com','12345678901','test4','自动化学院',false);

INSERT INTO manager(manager_id,deleted)
    VALUE ('1',false),('2',false);

INSERT INTO activity(id,name,position,sponsor_id,description,state_type)
VALUE (1,'程序星大赛','实验大楼','1','编程',1),
      (2,'足基元旦杯','实验大楼','2','编程',1),
      (3,'文娱活动','实验大楼','2','文娱',1);

INSERT INTO register(id,activity_id,applicant_id,name,student_id,state)
VALUE (1,1,'3','test3','2020302031',1),
      (2,1,'4','test4','2020302032',2),
      (3,2,'1','test1','2020302029',3);
