create database dashboard;
use dashboard;
create table Users
(
    id         int auto_increment
        primary key,
    user_name  varchar(15) null,
    first_name varchar(30) null,
    last_name  varchar(30) null,
    user_role  int         null,
    team_id    int         null,
    constraint Users_Users__fk
        foreign key (team_id) references Users (id)
);
create table User_Roles
(
    id          int auto_increment
        primary key,
    name        varchar(15) null,
    description varchar(15) null
);
create table Teams
(
    id     int auto_increment
        primary key,
    name   varchar(20) null,
    domain varchar(10) null
);
create table Applications
(
    id          int auto_increment
        primary key,
    name        varchar(20) null,
    description varchar(40) null,
    user_id     int         null,
    team_id     int         null,
    constraint Applications_Teams_id_fk
        foreign key (team_id) references Teams (id),
    constraint Applications_Users_id_fk
        foreign key (user_id) references Users (id)
);
create table Error
(
    id             int auto_increment
        primary key,
    message        varchar(20) null,
    description    varchar(45) null,
    application_id int         null,
    constraint Error_Applications_id_fk
        foreign key (application_id) references Applications (id)
);