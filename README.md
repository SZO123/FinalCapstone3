# Grocery Store Web Application

Capstone Project

## Abstract

This project is a full-stack e-commerce web application developed to satisfy the requirements of the Capstone Project. The selected domain for this application is an Online Grocery Store. The system allows users to browse grocery products, manage a shopping cart, and place orders, while administrators can manage product categories. The backend is implemented using Java and Spring Boot, and the frontend is implemented using standard web technologies.

## Technologies Used

### Backend

* Java
* Spring Boot
* Spring Security (JWT Authentication)
* MySQL
* Maven

### Frontend

* HTML
* CSS
* JavaScript

### Testing Tools

* Insomnia REST Client

## Functional Requirements Implemented

### User Functionality

* User registration
* User authentication (login)
* View product categories
* View and search products
* Add products to shopping cart
* Update and clear shopping cart
* Checkout and place orders
* View and update user profile

### Administrator Functionality

* Administrator authentication
* Add new product categories
* Delete existing product categories
* View all categories

## Authentication and Authorization

* JWT-based authentication
* Passwords stored securely using hashing
* Role-based access control (USER, ADMIN)
* Protected endpoints require valid authentication tokens

## Project Structure

capstone-api-starter/
capstone-client-groceryapp/
database/
capstone\_insomnia\_collection.yaml

## Database Configuration

1. Start MySQL Server
2. Execute the SQL script: database/create\_database\_groceryapp.sql
3. Ensure the database and tables are created successfully

## Running the Backend Application

1. Open capstone-api-starter in IntelliJ IDEA
2. Configure database credentials in application.properties
3. Run the Spring Boot application
4. Backend API will be available at http://localhost:3306

## Running the Frontend Application

1. Open capstone-client-groceryapp in IntelliJ IDEA
2. Open index.html using IntelliJ’s built-in browser or a live server
3. The frontend communicates with the backend via REST APIs

## API Testing and Validation

All backend API endpoints were tested using Insomnia.
23 out of 23 test cases passed successfully.

## Conclusion

The Grocery Store Web Application meets all the functional and technical requirements defined in the Capstone Project specification. The project demonstrates secure authentication, RESTful API design, database integration, and full-stack development principles.

## Author

Najib Hassan
