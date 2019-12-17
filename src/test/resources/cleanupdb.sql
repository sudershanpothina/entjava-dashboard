use dashboard;
DELETE FROM ERROR_DATA;
DELETE FROM APPLICATIONS;
DELETE FROM ROLE;
DELETE FROM USER;
DELETE FROM TEAMS;
# INSERT TEAMS
INSERT INTO dashboard.TEAMS (ID, NAME, DESCRIPTION, IMAGE_URL) VALUES (1, 'Avengers', 'Team assigned to watch and protect east America', null);
INSERT INTO dashboard.TEAMS (ID, NAME, DESCRIPTION, IMAGE_URL) VALUES (2, 'XMen', 'Team assigned to watch and protect all mutants', null);
INSERT INTO dashboard.TEAMS (ID, NAME, DESCRIPTION, IMAGE_URL) VALUES (3, 'TMNT', 'Team assigned to watch and protect NewYork sewers', null);
# INSERT USERS
INSERT INTO dashboard.USER (ID, FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, TEAM_ID, DATE_OF_BIRTH, IMAGE_URL) VALUES (1, 'Tony', 'Stark', 'tstark', '1', 1, '1964-04-01', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJJHxTYMlVcuxOjif7Nu6LGy4Amfa8NXYhmS0MfBf0Ilnbm12j&s');
INSERT INTO dashboard.USER (ID, FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, TEAM_ID, DATE_OF_BIRTH, IMAGE_URL) VALUES (2, 'Bruce', 'Banner', 'bbanner', '2', 1, '2011-12-08', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJJHxTYMlVcuxOjif7Nu6LGy4Amfa8NXYhmS0MfBf0Ilnbm12j&s');
INSERT INTO dashboard.USER (ID, FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, TEAM_ID, DATE_OF_BIRTH, IMAGE_URL) VALUES (3, 'Thor', 'Odinson', 'todins2', '1', 1, '2011-12-06', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJJHxTYMlVcuxOjif7Nu6LGy4Amfa8NXYhmS0MfBf0Ilnbm12j&s');
INSERT INTO dashboard.USER (ID, FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, TEAM_ID, DATE_OF_BIRTH, IMAGE_URL) VALUES (4, 'X', 'Professor', 'xprof', '1', 2, '2019-12-02', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJJHxTYMlVcuxOjif7Nu6LGy4Amfa8NXYhmS0MfBf0Ilnbm12j&s');
# INSERT ROLES
INSERT INTO dashboard.ROLE (ID, ROLE_NAME, USER_NAME, USER_ID) VALUES (1, 'team-admin', 'tstark', 1);
INSERT INTO dashboard.ROLE (ID, ROLE_NAME, USER_NAME, USER_ID) VALUES (2, 'team-member', 'bbanner', 2);
INSERT INTO dashboard.ROLE (ID, ROLE_NAME, USER_NAME, USER_ID) VALUES (3, 'team-member', 'todins2', 3);
INSERT INTO dashboard.ROLE (ID, ROLE_NAME, USER_NAME, USER_ID) VALUES (4, 'team-admin', 'xprof', 4);
# INSERT APPLICATIONS
INSERT INTO dashboard.APPLICATIONS (ID, NAME, DESCRIPTION, TEAM_ID) VALUES (1, 'Application 1', 'This is the first application', 1);