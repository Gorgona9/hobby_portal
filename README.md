
# Hobby Portal
(ReactJS/SpringBoot Fullstack Application)
<hr>

This project consists of two applications: a Spring Boot Rest API
called hobby_portal and another is a ReactJS application called
hobby_portal_front.

Service-oriented platform focusing on establishing and maintaining
connections between consumers and small businesses for Arts,
Entertainment, and Recreation sector.

## Applications
<hr>

### - spring-backend

Spring Boot Web Java backend application that exposes a REST API to
manage hobbies. Its secured endpoints can just be accessed if an access
token (JWT) is provided.

spring-backend stores its data in a MySql database.

### - react-frontend

ReactJS frontend application where users can find and save hobbies and businesses can manage offers. In order to access the application, user / business must login using his/her username and password.  All the requests coming from react-frontend to secured endpoints in spring-backend have a access token (JWT) that is generated when user / business logs in.

react-frontend uses Semantic UI React as CSS-styled framework.

### Prerequisites
<hr>

-Java 17

-npm

-JWT

### Set up

<hr>

Clone the repository:

<pre>git clone https://github.com/Gorgona9/hobby_portal.git</pre>

Navigate to the newly created folder:

<pre>cd  hobby_portal</pre>


### Frontend

Install NodeJs / npm

Navigate to react-frontend subfolder:

<pre>cd hobby_portal_front</pre>

Install the modules

<pre>npm i</pre>

Start the application on local host:

<pre>npm start</pre>

Navigate to:

http://localhost:4200


### Backend
Install JDK 17
Install MySQL

Navigate to spring-backend subfolder:

<pre>cd hobby_portal</pre>

Run the project with:

<pre>docker-compose up --build</pre>

The project has the following endpoints:

IMPORTANT: to explore api enter url:  /v3/api-docs

http://localhost:8080/swagger-ui/index.html


NOTE: Testing API

-/signup (create client-user) or /register (create business-user)

-/authenticate (returns JWT authentication token)

-use JWT token in order to authorize access to secured endpoints (click the lock icon or use the Authorize button on the upper right corner - then paste JWT Token )

NOTE: /notification endpoint will return an internal server error if you don't specify spring.mail credentials first.

<pre>The backend will run on http://localhost:8080 </pre>

<b>Spring Mail</b>

Make sure to specify a valid spring.mail.username and spring.mail.password in the application.properties file in order to be able to send an Email confirmation for updating user entries.

IMPORTANT: if you decide not to specify mail credentials, you will get javax.mail.AuthenticationFailedException. The rest of the application should work normally despite this exception.

