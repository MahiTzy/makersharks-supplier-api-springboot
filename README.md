# Supplier Management Application

## Overview

This application is designed to manage and query suppliers. It includes features for searching suppliers based on various criteria and viewing detailed information about them.

## Table of Contents

- [Getting Started](#getting-started)
- [Technologies Used](#technologies-used)
- [Running the Application](#running-the-application)
- [H2 Database](#h2-database)
- [API Documentation with Swagger](#swagger-api-documentation)
- [Validation and Exception Handling](#validation-and-exception-handling)
- [Testing the Application](#testing-the-application)
  - [Using Swagger UI](#using-swagger-ui)
  - [Using cURL](#using-curl)
- [React Frontend](#react-frontend)

## Getting Started

### Prerequisites

Before running the application, ensure you have the following installed:

- [Java JDK 17](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

## Technologies Used

- **Backend**: Spring Boot
- **Frontend**: Postman, React
- **Database**: H2 (in memory database)
- **Testing**: JUnit5, Mockito
- **API Documentation**: Swagger (using openApi)

## Running the Application

1. **Clone the Repository**

   ```bash
   git clone https://github.com/MahiTzy/makersharks-supplier-api-springboot.git
   ```

2. **Navigate to the Backend Directory**

   ```bash
   cd backend
   ```

3. **Configure the Application**

   The application uses an H2 database for simplicity. It is pre-configured to use in-memory mode, so no additional setup is required for the database. The database is populated with hardcoded data in the application. Ensure your `application.properties` or `application.yml` file is configured correctly. For H2 in-memory mode, your configuration might look like this:

   ```properties
   # H2 database configuration
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driver-class-name=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=password
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

   # Enable H2 console for testing
   spring.h2.console.enabled=true
   spring.h2.console.path=/h2-console
   ```

4. **Build the Project**

   ```bash
   mvn clean install
   ```

5. **Run the Application**

   ```bash
   mvn spring-boot:run
   ```

   The application will start and be available at `http://localhost:8080`.

## **H2 Database**

   To access the H2 console for querying the database directly, navigate to:

   ```
   http://localhost:8080/h2-console
   ```

   Use the following credentials:

   - **JDBC URL**: `jdbc:h2:mem:testdb`
   - **Username**: `sa`
   - **Password**: `password`

## Swagger API Documentation

The application uses Swagger for API documentation. You can access the Swagger UI at:

```
http://localhost:8080/swagger-ui/index.html
```
![Screenshot 2024-08-24 at 00-05-44 Swagger UI](https://github.com/user-attachments/assets/5dd7cc86-9f36-437d-b093-b9b7b8373c62)

This will provide a user-friendly interface to explore and test the API endpoints.

## Validation and Exception Handling

The application includes proper validation and exception handling to ensure robust and reliable API interactions.

- **Validation:** The application validates incoming requests to ensure all required parameters are present and correctly formatted. If validation fails, a meaningful error message is returned.
- **Exception Handling:** The application has a global exception handler to manage exceptions and return structured error responses.

### Example Validation

For the `/api/supplier/query` endpoint, the following parameters are validated:

- `location`: Must not be empty.
- `natureOfBusiness`: Must not be empty.
- `manufacturingProcess`: Must not be empty.

### Example Exception Handling

If no suppliers match the provided criteria, a `404 Not Found` response is returned with a message indicating that no suppliers were found.

## Test Data

The application is pre-populated with test data. Here are some examples of the hardcoded data available in the H2 database:

- **Suppliers**:
  - **Supplier 1**:
    - **Company Name**: Company1
    - **Website**: http://company1.com
    - **Location**: Pune
    - **Nature of Business**: small_scale
    - **Manufacturing Processes**: moulding, coating
  - **Supplier 2**:
    - **Company Name**: Company2
    - **Website**: http://company2.com
    - **Location**: Mumbai
    - **Nature of Business**: medium_scale
    - **Manufacturing Processes**: 3d_printing, casting

## Testing the Application 

### **Using Swagger UI**:
   - **API Endpoint**: 'POST' '/api/supplier/query'
   - **Params**: Add the following parameters:
     - **location**: `Pune`
     - **natureOfBusiness**: `small_scale`
     - **manufacturingProcess**: `moulding`
     - **page**: `0` (or any other page number you wish to test)
     - **size**: `5` (or any other page size you wish to test)
  
Click the `Execute` button to make the request.

### Using cURL:

- **Search Suppliers**

  ```bash
  curl -X 'POST' 'http://localhost:8080/api/supplier/query?location=Pune&natureOfBusiness=small_scale&manufacturingProcess=3d_printing&page=0&size=10' \
  -H 'accept: */*' -d ''
  ```

  - **Response Example**:

    ```json
    {
     "content": [
       {
         "supplierId": 3,
         "companyName": "Company3",
         "website": "http://company3.com",
         "location": "Pune",
         "natureOfBusiness": "small_scale",
         "manufacturingProcesses": ["moulding", "coating"]
       }
     ],
     "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
          },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "unpaged": false,
        "paged": true
     },
     "totalPages": 1,
     "totalElements": 1,
     "last": true,
     "size": 10,
     "number": 0,
     "sort": {
       "sorted": false,
       "unsorted": true,
       "empty": true
     },
     "numberOfElements": 1,
     "first": true,
     "empty": false
    }
    ```
## React Frontend

The Supplier Management Application also includes a React frontend for consuming the APIs provided by this backend. The React frontend allows users to query suppliers based on various criteria such as location, nature of business, and manufacturing processes.

### Accessing the Frontend Repository

To get started with the frontend application, please refer to the [React Frontend Repository](https://github.com/MahiTzy/supplier-management-ui-react).

In the frontend repository, you'll find instructions on how to set up and run the React application locally.

