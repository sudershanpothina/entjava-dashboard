create database dashboard;
use dashboard;

create table USER
(
    ID INT AUTO_INCREMENT,
    FIRST_NAME VARCHAR(15) NULL,
    LAST_NAME VARCHAR(15) NULL,
    USER_NAME VARCHAR(15) NULL,
    PASSWORD VARCHAR(15) null,
    TEAM_ID INT NULL,
    DATE_OF_BIRTH DATE NULL,
    CONSTRAINT user_id_uindex
        UNIQUE (ID),
    CONSTRAINT users_user_name_uindex
        UNIQUE (USER_NAME)
);

CREATE TABLE ROLE
(
    ID        INT AUTO_INCREMENT
        PRIMARY KEY,
    ROLE_NAME VARCHAR(25) NOT NULL,
    USER_NAME VARCHAR(15) NOT NULL,
    USER_ID   INT         NOT NULL,
    CONSTRAINT role_id_uindex
        UNIQUE (ID),
    CONSTRAINT role_user_user_id_fk
        FOREIGN KEY (USER_ID) REFERENCES USER (ID)
            ON UPDATE CASCADE
            ON DELETE CASCADE
)
    ENGINE = InnoDB;

CREATE INDEX role_user_user_id_fk
    ON ROLE (USER_ID);



create table TEAMS
(
	ID int auto_increment,
	NAME VARCHAR(15) null,
	DOMAIN VARCHAR(15) null,
	constraint TEAMS_pk
		primary key (ID)
);

create table USERS
(
	ID int auto_increment,
	USER_NAME VARCHAR(15) not null,
	FIRST_NAME VARCHAR(30) not null,
	LAST_NAME VARCHAR(30) not null,
	USER_ROLE_ID int null,
	TEAM_ID int null,
	constraint USERS_pk
		primary key (ID),
	constraint USERS_TEAMS_ID_fk
		foreign key (TEAM_ID) references TEAMS (ID),
	constraint USERS_USER_ROLES_ID_fk
		foreign key (USER_ROLE_ID) references USER_ROLES (ID)
);

create table APPLICATIONS
(
	ID int auto_increment,
	NAME VARCHAR(30) not null,
	DESCRIPTION VARCHAR(30) null,
	TEAM_ID int null,
	constraint APPLICATIONS_pk
		primary key (ID),
	constraint APPLICATIONS_TEAMS_ID_fk
		foreign key (TEAM_ID) references TEAMS (ID)
);

create table ERRORS
(
	ID int null,
	MESSAGE VARCHAR(30) not null,
	DESCRIPTION VARCHAR(45) null,
	APPLICATION_ID int null,
	constraint ERRORS_APPLICATIONS_ID_fk
		foreign key (APPLICATION_ID) references APPLICATIONS (ID)
);







