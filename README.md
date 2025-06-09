# RESTful Web Services

## Overview
This project demonstrates the implementation of RESTful web services using Spring Boot. It provides a simple API to perform CRUD (Create, Read, Update, Delete) operations on user data. The goal is to showcase the design and development of scalable and maintainable REST APIs.

## Features
- Create, retrieve, update, and delete user records
- RESTful endpoints following best practices
- Exception handling for invalid requests
- Basic validation of input data

## Technologies Used
- Java 21
- Spring Boot 3.4.x
- Spring MVC / REST
- Maven (for build and dependency management)

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 17 or higher installed
- Maven installed
- IDE like IntelliJ IDEA, Eclipse, or VS Code (optional but recommended)

### Running the Application
1. Clone the repository:
   
   git clone https://github.com/krishna5312/restful-web-services.git
   
2. Navigate to the project directory:
   
   cd restful-web-services
   
3. Build the project using Maven:
   
   mvn clean install
   
4. Run the application:
   
   mvn spring-boot:run
   
5. The application will start on `http://localhost:8080`

## API Endpoints
| Method | Endpoint           | Description                  |
|--------|--------------------|------------------------------|
| GET    | `/users`           | Retrieve all users            |
| GET    | `/users/{id}`      | Retrieve user by ID           |
| POST   | `/users`           | Create a new user             |
| PUT    | `/users/{id}`      | Update an existing user       |
| DELETE | `/users/{id}`      | Delete a user by ID           |

### Sample Request

POST /users
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "birthDate": "1990-01-01"
}


### Sample Response
json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "birthDate": "1990-01-01"
}

## Filtered Responses

This application demonstrates dynamic filtering of JSON responses using Jackson filters:

- **`GET /filter-cred`**  
  Returns student data excluding sensitive credentials.

- **`GET /filter-id`**  
  Returns student data excluding the student ID.

These endpoints illustrate how the same object can be serialized differently depending on the use case or consumer requirements.

## Internationalization (i18n)

The endpoint **`GET /hello-world-i18n`** demonstrates internationalization support. It returns a localized greeting message based on the `Accept-Language` header in the request.

Example:

http
GET /hello-world-i18n
Accept-Language: fr


Response:

json
"Bonjour le monde"


Supports multiple languages, allowing the application to serve a global user base.

## Error Handling
- Returns `404 Not Found` if a user with the specified ID does not exist.
- Returns `400 Bad Request` for invalid input data.

## Future Improvements
- Add unit and integration tests
- Implement authentication and authorization
- Enhance validation rules
- Add pagination for user list endpoint

