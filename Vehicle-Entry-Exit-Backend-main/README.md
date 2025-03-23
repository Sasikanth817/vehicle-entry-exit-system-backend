# Vehicle Entry Exit Backend

## Objective

The objective of this project was to develop a robust and secure backend system for managing vehicle entry and exit within a university or similar environment. This system provides a comprehensive solution for user management, vehicle registration, access control, and logging, leveraging Spring Boot, JWT authentication, and MongoDB (with a planned migration to MariaDB). The implementation of Role-Based Access Control (RBAC) ensures that different user roles (User, Security Guard, Manager) have appropriate access and permissions.

### Skills Learned

-   Development of a RESTful API using Spring Boot.
-   Implementation of JWT (JSON Web Token) for secure authentication.
-   Database management with MongoDB (and planning for MariaDB migration).
-   Design and implementation of Role-Based Access Control (RBAC).
-   Generation and handling of QR codes.
-   Log management and data retrieval.
-   Understanding of application security principles.
-   Experience with version control using Git.

### Tools Used

-   Spring Boot: For backend development and REST API creation.
-   JWT (JSON Web Token): For secure authentication and authorization.
-   MongoDB: For database management (initially), planned migration to MariaDB.
-   Maven/Gradle: For dependency management and build automation.
-   Postman/Insomnia/ThunderClient: For API testing.
-   Git: For version control.
-   (Future) MariaDB: For relational database management.

# Features

## User Features

-   **Account Management:**
    -      Users can create accounts as either an Employee or Student.
    -      Secure login functionality.
-   **Vehicle Management:**
    -      Users can add, edit, and delete their vehicle details.
    -      A unique QR code is generated based on the vehicle number, which can be downloaded and saved to the user's gallery.
    -      Users can view a log of all entries and exits associated with their vehicles.
-   **Information Access:**
    -      Users can view announcements posted by the manager.

## Security Guard Features

-   **Secure Login:**
    -      Security guards can securely log in to the system.
-   **Vehicle Entry/Exit Management:**
    -      Security guards can scan QR codes to fetch vehicle details.
    -      They can post entry or exit logs by selecting the appropriate gate and status.
-   **Log Access:**
    -      Security guards can view all vehicle logs.
-   **Information Access:**
    -      Security guards can access announcements posted by the manager.
-   **Profile Management:**
    -   Security guards can view their own profile information.

## Manager Features

-   **Security Guard Management:**
    -      Managers can add, edit, delete, and view security guard profiles.
-   **Gate Management:**
    -      Managers can configure gates by adding, editing, and deleting them.
-   **Announcement Management:**
    -      Managers can post and manage announcements.
-   **University Vehicle Management:**
    -      Managers can add, edit, delete, and view university vehicles.
-   **Data Access:**
    -      Managers can view all registered users and vehicles.

# Project Structure

This project follows a layered architecture with clear separation of concerns:

-   **`controller`**: Handles HTTP requests and routes them to the appropriate service.
-   **`dto`**: Data Transfer Objects for passing data between layers.
-   **`enums`**: Enumerations for fixed sets of constants like the roles here
-   **`filter`**: Custom filters for request/response processing (JWT).
-   **`model`**: Collection classes representing the data model.
-   **`repository`**: Interfaces for data access and persistence.
-   **`service`**: Business logic implementation.
-   **`util`**: Utility classes for common functionality.
-   **`Learn1Application.java`**: Main Spring Boot application class.
-   **`SecurityConfig.java`**: Security configuration for authentication and authorization.
