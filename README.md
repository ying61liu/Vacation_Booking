# Vacation_Booking

This repo documents my work with an existing MySQL database and Angular front end for a travel agency's Ecommerce site. 

Tasks accomplished while working on this project:

1. Created a new Java project using Spring Initializr selected dependencies;
2. Constructed four new packages: controllers, entities, dao, and services, which are used for a checkout form and vacations packages list;
3. Wrote code for the entities package that includes entity classes and the enum designed to match the UML diagram;
4. Wrote code for the dao package that includes repository interfaces for the entities that extend JpaRepository, and added cross-origin support;
5. Wrote code for the services package that includes each of the following:
•    a purchase data class with a customer cart and a set of cart items
•    a purchase response data class that contains an order tracking number
•    a checkout service interface
•    a checkout service implementation class
6. Wrote code to include validation to enforce the inputs needed by the Angular front-end;
7. Wrote code for the controllers package that includes a REST controller checkout controller class with a post mapping to place orders;
8. Debugged and made sure the program integrates successfully with the forntend app, and the data transfers accurately from/to MySQL database.

