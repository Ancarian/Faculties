
INSERT INTO subjects VALUES (1, 'certificate'), (2, 'math'), (3, 'biology');
INSERT INTO UNIVERSITIES VALUES (1, 'university#1');
INSERT INTO FACULTIES VALUES (1, 'Faculty#1', 'Faculty#1', 1);

INSERT INTO groups(id,ENROLL_MARK, COUNT_OF_USERS,COUNT_OF_ALL_USERS,INFORMATION,VALID_TILL,COUNT,ISSUE_DATE, FACULTIES_ID, QUALIFY) VALUES (1,0,1,1, 'group №1', '2017-12-12', 3, '2018-12-12',1, 'builder');
INSERT INTO groups(id,ENROLL_MARK, COUNT_OF_USERS,COUNT_OF_ALL_USERS,INFORMATION,VALID_TILL,COUNT,ISSUE_DATE,FACULTIES_ID, QUALIFY)  VALUES (2,0,1,1, 'group №2', '2017-12-12', 3, '2018-12-12',1, 'programmer');
INSERT INTO groups(id,ENROLL_MARK, COUNT_OF_USERS,COUNT_OF_ALL_USERS,INFORMATION,VALID_TILL,COUNT,ISSUE_DATE, FACULTIES_ID, QUALIFY)  VALUES (3,0,1,1, 'group №3', '2017-12-12', 3, '2018-12-12',1, 'medic');

INSERT INTO groups_subjects VALUES (1, 1), (1, 2), (1, 3),(2, 1), (2, 2), (2, 3), (3, 1), (3, 2), (3, 3);


INSERT INTO users(name, lastname, patronymic, groups_id) VALUES ('username_1','username_1','username_1', 1), ('username_2','username_1','username_1', 1), ('username_3','username_1','username_1', 1), ( 'username_4','username_1','username_1', 1),('username_5','username_1','username_1', 1), ('username_6','username_1','username_1', 1), ('username_7','username_1','username_1', 2), ('username_8','username_1','username_1', 2),('username_9','username_1','username_1', 2), ('username_10','username_1','username_1', 2), ('username_11','username_1','username_1', 2), ('username_12','username_1','username_1', 2),('username_13','username_1','username_1', 1), ('username_14','username_1','username_1', 1), ('username_15','username_1','username_1', 1), ('username_16','username_1','username_1', NULL), ('username_17','username_1','username_1', Null);


INSERT INTO user_information (email, nickname, password) VALUES ('email_1', 'nickname_1', 'password_1'),('oldEmail@gmail.com', 'nickname_2', 'oldPassword'),('email_3', 'nickname_3', 'password_3'),('email_4', 'nickname_4', 'password_4'),('email_5', 'nickname_5', 'password_5'),('email_6', 'nickname_6', 'password_6'),('email_7', 'nickname_7', 'password_7'),('email_8', 'nickname_8', 'password_8'),('email_9', 'nickname_9', 'password_9'),('email_10', 'nickname_10', 'password_10'),('email_11', 'nickname_11', 'password_11'),('email_12', 'nickname_12', 'password_12'),('email_13', 'nickname_13', 'password_13'),('email_14', 'nickname_14', 'password_14'),('email_15', 'nickname_15', 'password_15'),('email_16', 'nickname_16', 'password_16'),('email_17', 'nickname_17', 'password_17');

INSERT INTO user_information_roles (ROLES, USER_INFORMATION_ID) VALUES (2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(1,13),(1,14),(1,15),(1,16),(1,17);


INSERT INTO users_subjects (users_id, subjects_id, mark) VALUES (1, 1, 10), (1, 2, 10), (1, 3, 10), (2, 1, 20), (2, 2, 20), (2, 3, 20), (3, 1, 30), (3, 2, 30), (3, 3, 30),(4, 1, 40), (4, 2, 40), (4, 3, 40), (5, 1, 50), (5, 2, 50), (5, 3, 50), (6, 1, 60), (6, 2, 60), (6, 3, 60),(7, 1, 70), (7, 2, 70), (7, 3, 70), (8, 1, 80), (8, 2, 80), (8, 3, 80), (9, 1, 90), (9, 2, 90), (9, 3, 90),(10, 1, 100), (10, 2, 100), (10, 3, 100), (11, 1, 110), (11, 2, 110), (11, 3, 110), (12, 1, 120), (12, 2, 120),(12, 3, 120), (13, 3, 10), (14, 1, 10), (14, 2, 10), (14, 3, 10), (15, 1, 10), (15, 2, 10),(15, 3, 10), (16, 1, 10), (16, 2, 10), (16, 3, 10);


ALTER TABLE PUBLIC.USER_INFORMATION_ROLES DROP CONSTRAINT FKT140WFQK89SM888A90CY73LGA;
ALTER TABLE PUBLIC.USER_INFORMATION_ROLES ADD CONSTRAINT FKT140WFQK89SM888A90CY73LGA FOREIGN KEY (USER_INFORMATION_ID) REFERENCES USER_INFORMATION (ID) ON DELETE CASCADE;

ALTER TABLE PUBLIC.USERS DROP CONSTRAINT FKA0X3HQO3K1895LK412MWOPNO2;
ALTER TABLE PUBLIC.USERS ADD CONSTRAINT FKA0X3HQO3K1895LK412MWOPNO2 FOREIGN KEY (GROUPS_ID) REFERENCES GROUPS (ID) ON DELETE SET NULL;

ALTER TABLE PUBLIC.FACULTIES DROP CONSTRAINT FKNI6I0BQ8B6525W806EO593N7E;
ALTER TABLE PUBLIC.FACULTIES ADD CONSTRAINT FKNI6I0BQ8B6525W806EO593N7E FOREIGN KEY (UNIVERSITIES_ID) REFERENCES UNIVERSITIES (ID) ON DELETE CASCADE;

ALTER TABLE PUBLIC.GROUPS DROP CONSTRAINT FKIP0OV3LLFM2NXXIUE5RT0VTN2;
ALTER TABLE PUBLIC.GROUPS ADD CONSTRAINT FKIP0OV3LLFM2NXXIUE5RT0VTN2 FOREIGN KEY (FACULTIES_ID) REFERENCES FACULTIES (ID) ON DELETE CASCADE;

ALTER TABLE PUBLIC.GROUPS_SUBJECTS DROP CONSTRAINT FKOJXXFM09OSDV6J9JDB59FLSA1;
ALTER TABLE PUBLIC.GROUPS_SUBJECTS ADD CONSTRAINT FKOJXXFM09OSDV6J9JDB59FLSA1 FOREIGN KEY (GROUPS_ID) REFERENCES GROUPS (ID) ON DELETE CASCADE;

ALTER TABLE PUBLIC.USERS_SUBJECTS DROP CONSTRAINT FKMSLW7WMND17MFAL87LTS6H68D;
ALTER TABLE PUBLIC.USERS_SUBJECTS ADD CONSTRAINT FKMSLW7WMND17MFAL87LTS6H68D FOREIGN KEY (USERS_ID) REFERENCES USERS (ID) ON DELETE CASCADE;
ALTER TABLE PUBLIC.USERS_SUBJECTS DROP CONSTRAINT FK7BWCD3WPFHA2R4KK5K2MO8J55; ALTER TABLE PUBLIC.USERS_SUBJECTS ADD CONSTRAINT FK7BWCD3WPFHA2R4KK5K2MO8J55 FOREIGN KEY (SUBJECTS_ID) REFERENCES SUBJECTS (ID) ON DELETE CASCADE;


CREATE INDEX FACULTIES_ID_index ON FACULTIES (ID);
CREATE INDEX GROUPS_ID_index ON GROUPS (ID);
CREATE INDEX USERS_ID_index ON USERS (ID);
CREATE INDEX SUBJECTS_ID_index ON SUBJECTS (ID);
CREATE INDEX USERS_SUBJECTS_USERS_ID_index ON USERS_SUBJECTS (USERS_ID);


