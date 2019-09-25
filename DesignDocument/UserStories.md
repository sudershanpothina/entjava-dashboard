# User Stories

### Create Login Page (MVP)
create a default page that the application will serve. For MVP this will just use default credentials.
### Create stub user repository and service (MVP)
create a dummy repo with hardcoded users. This story will entail creating a new table. populating it with dummy data and accessing that stored information
### Create Team Page (MVP)
Page to show information about the respective team. Use bootstrap css to build this page and pull information from the user DAO and display it on the jsp
### Display user name and team information (MVP)
Upon login for a particular user figure out the right team and route to the appropriate team page and display information about the team and also the user in this page
### Create stub Application repository and service (MVP)
create dummy application repo with application information and have options to perform CRUD operations
### Create Application Page (MVP)
Display the list of applications for the team. This will be a simple table showing the name and the description of the application along with options to add and delete new or existing applications. 
Each application will have a link to the its own error page.   
### Create stub Error Repository and service
create a dummy error list for the team's application. This will connect the entity to an existing error table and pull that information. 
### Create Error Display page
Error Display page will have information about the app that caused the error and the timestamp when it occurred. This page will also show the information about errors code and the error message. 
### Create Service for Application CRUD
This service will be used to create update, view and delete applications according based on the user who has logged in
### Integrate Application Repo service to Dashboard service
Add the jsp page that shows the information from the application crud
### Create service for Error lookup
This service will show accurate information of the errors encountered with a selected application over a course of time frame. 
### Integrate Error lookup to Dashboard Service
Add the jsp page to display the information about the errors that have been encountered for an application. Also add in drop downs to select different applications based on what team member has logged in.
### Integrate External Service (like weather ) 
Create a service to connect to external weather api and display todays weather information based on the teams locations
### Add authentication to login Page 
Validate and authenticate the user credentials and route to the appropriate page. 