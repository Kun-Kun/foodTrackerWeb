# foodTrackerWeb

## Purpose

Tracking your food and calorie intake can be important.
It helps you achieve and maintain your weight goal. By logging your meals each day, you can track your progress towards an estimated goal based on caloric intake and burn. Your goal may be to lose weight, gain weight, or maintain your current weight. 

## How to use app

Weight Loss /Food Tracking System. Customer chooses food (name, count proteins, fats, carbohydrates), which he ate (from the already prepared list) and writes Amount. The client can add his  food (name, count of proteins, fats, carbon,calories ). If the client has exceeded the daily rate, the system will inform him and write down how much Norma was exceeded. Norm take from the parameters Client (age, height, weight, lifestyle, etc.).

## Prerequisite for launch

  * MySQL like database management system (MySQL or MariaDB).
  * Maven 
  * Tomcat (Successfully tested on 8 and 9 versions)
  
## Build and Usage

### Database & other

Create schema 'tracker' and fill out database with data from foodTrackerWeb/src/main/resources/sql.
Change database credentials in foodTrackerWeb/src/main/webapp/META-INF/context.xml

Change logging folder, logging level in foodTrackerWeb/src/main/resources/log4j2.xml

### Maven

./mvn clean install

### Tomcat

Deploy artifact

### Running in IDE

Intelij idea : You have to add "lombok" plugin to process lombok annotation @Data etc.. Add tomcat server (choose /bin folder) and choose configuration files from project
NetBeans 8 : Add tomcat server and just click run.

This will build and start the application listening on port 8080 with path /food/

##Web application features

###Security

  * Authentication
  * Authorization
  * User roles and privileges
  * CSRF token request protection
  * Filter builder, that allows to build tree-like filtering algorythm (allow to filtrate by matching servlet/uri paths, roles, privileges, authentication their absence in different variations) and apply actions (throw exception, send error code page, send redirect, forward).

###Database

  * Object-Relational Mapping based on jackson databind .
  * Batch request
  
###Technologies

  * JSP + JSTL;
  * Servlets;
  * JDBC;
  * Log4J;
  * Apache commons;
  * Slf4J;
  * Own Exceptions.
  * Jackson-databind
