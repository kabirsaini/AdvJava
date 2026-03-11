# Library Management System

A simple Spring Boot MVC web application for managing library books with user registration and login functionality.

## Features

- User Registration with validation
- User Login
- Add Books with validation
- View All Books
- View Book Details
- Delete Books
- Global Exception Handling
- In-memory storage using ArrayList (no database)

## Technologies Used

- Spring Boot 3.5.11
- Spring MVC
- Thymeleaf
- Bean Validation
- Java 21
- Maven

## How to Run

1. Navigate to the project directory:
   ```
   cd LibraryManagementApp
   ```

2. Run the application using Maven:
   ```
   mvnw.cmd spring-boot:run
   ```

3. Open your browser and go to:
   ```
   http://localhost:8080
   ```

## Application Flow

1. Start at the login page (redirects from home)
2. Register a new user account
3. Login with your credentials
4. Access the dashboard
5. Add books to the library
6. View all books in a table
7. View individual book details
8. Delete books

## Project Structure

```
src/main/java/com/example/LibraryManagementApp/
├── controller/
│   ├── UserController.java
│   └── BookController.java
├── model/
│   ├── User.java
│   └── Book.java
├── service/
│   ├── UserService.java
│   └── BookService.java
├── exception/
│   ├── BookNotFoundException.java
│   └── GlobalExceptionHandler.java
└── LibraryManagementAppApplication.java

src/main/resources/
├── templates/
│   ├── register.html
│   ├── login.html
│   ├── dashboard.html
│   ├── add-book.html
│   ├── view-books.html
│   ├── book-details.html
│   └── error.html
└── application.properties
```

## Validation Rules

### User Registration
- Name: Required, minimum 3 characters
- Email: Required, valid email format
- Password: Required, minimum 6 characters

### Book Management
- Title: Required
- Author: Required
- Price: Required, must be greater than 0

## Notes

- All data is stored in-memory using ArrayList
- Data will be lost when the application restarts
- No database or Spring Security is used
- Simple and clean UI/UX design
