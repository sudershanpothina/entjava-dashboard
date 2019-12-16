create database dashboard;
use dashboard;

CREATE TABLE `TEAMS` (
                         `ID` int(11) NOT NULL AUTO_INCREMENT,
                         `NAME` varchar(20) NOT NULL,
                         `DESCRIPTION` varchar(60) NOT NULL,
                         `IMAGE_URL` varchar(200) DEFAULT NULL,
                         PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `USER` (
                        `ID` int(11) NOT NULL AUTO_INCREMENT,
                        `FIRST_NAME` varchar(15) DEFAULT NULL,
                        `LAST_NAME` varchar(15) DEFAULT NULL,
                        `USER_NAME` varchar(15) DEFAULT NULL,
                        `PASSWORD` varchar(15) DEFAULT NULL,
                        `TEAM_ID` int(11) DEFAULT NULL,
                        `DATE_OF_BIRTH` date DEFAULT NULL,
                        `IMAGE_URL` varchar(2000) DEFAULT NULL,
                        UNIQUE KEY `user_id_uindex` (`ID`),
                        UNIQUE KEY `users_user_name_uindex` (`USER_NAME`),
                        KEY `USER_TEAMS_ID_fk` (`TEAM_ID`),
                        CONSTRAINT `USER_TEAMS_ID_fk` FOREIGN KEY (`TEAM_ID`) REFERENCES `TEAMS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ROLE` (
                        `ID` int(11) NOT NULL AUTO_INCREMENT,
                        `ROLE_NAME` varchar(25) NOT NULL,
                        `USER_NAME` varchar(15) NOT NULL,
                        `USER_ID` int(11) NOT NULL,
                        PRIMARY KEY (`ID`),
                        UNIQUE KEY `role_id_uindex` (`ID`),
                        KEY `role_user_user_id_fk` (`USER_ID`),
                        CONSTRAINT `role_user_user_id_fk` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





CREATE TABLE `APPLICATIONS` (
                                `ID` int(11) NOT NULL AUTO_INCREMENT,
                                `NAME` varchar(20) DEFAULT NULL,
                                `DESCRIPTION` varchar(100) DEFAULT NULL,
                                `TEAM_ID` int(11) DEFAULT NULL,
                                PRIMARY KEY (`ID`),
                                KEY `APPLICATIONS_TEAMS_ID_fk` (`TEAM_ID`),
                                CONSTRAINT `APPLICATIONS_TEAMS_ID_fk` FOREIGN KEY (`TEAM_ID`) REFERENCES `TEAMS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ERROR_DATA` (
                              `ID` int(11) NOT NULL AUTO_INCREMENT,
                              `MESSAGE` varchar(20) DEFAULT NULL,
                              `DESCRIPTION` varchar(200) DEFAULT NULL,
                              `APPLICATION_ID` int(11) DEFAULT NULL,
                              `DTTM` timestamp NULL DEFAULT NULL,
                              PRIMARY KEY (`ID`),
                              KEY `ERROR_DATE_APPLICATIONS_ID_fk` (`APPLICATION_ID`),
                              CONSTRAINT `ERROR_DATE_APPLICATIONS_ID_fk` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `APPLICATIONS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;









