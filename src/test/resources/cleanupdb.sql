DELETE FROM ERROR_DATA;
DELETE FROM APPLICATIONS;
INSERT INTO APPLICATIONS VALUES (1, "Application 1", "This is the first application"), (2, "Application 2", "This is the second Application");
INSERT INTO ERROR_DATA VALUES (1,'ERR-100','ERROR processing request', 1),(2,'ERR-101','ERROR processing 2 request', 2),(3,'ERR-103','ERROR processing 3 request', 1);