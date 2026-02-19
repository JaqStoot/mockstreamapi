# MockStream-API

MockStream-API is a lightweight, dynamic utility for mocking REST API responses at runtime. It allows developers to register custom endpoints and define their return values (status codes, payloads, and content types) without needing to restart the application or modify source code.

This tool is designed to facilitate microservice integration testing by simulating third-party services or backend systems that are either unavailable or difficult to trigger specific error states from.

---

## Current Tech Stack

* Java 17
* Spring Boot 3.4.2
* Maven
* Project Lombok
* JUnit 5 and MockMvc

---

## Getting Started

### Prerequisites

* Java Development Kit (JDK) 17 or higher
* Maven 3.6 or higher

### Installation

1. Clone the repository:
   ```bash
   git clone [https://github.com/your-username/mockstream-api.git](https://github.com/your-username/mockstream-api.git)
   ```

2. Navigate to the project directory:
   ```bash
   cd mockstream-api
   ```

3. Build the project using the Maven Wrapper:
   ```bash
   ./mvnw clean install
   ```

4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

The application will be available at http://localhost:8080.

---

## Usage

### 1. Register a Mock Endpoint
Use the admin configuration endpoint to define a new mock path and its behavior.

* **Endpoint:** POST /admin/configure
* **Query Parameter:** path (e.g., /user/details)
* **Request Body:**
  ```json
  {
    "httpStatus": 200,
    "payload": "{\"id\": 1, \"name\": \"John Doe\"}",
    "contentType": "application/json"
  }
  ```

### 2. Access the Mocked Response
Once registered, any request sent to the /mock prefix with your defined path will return the configured response.

* **Example:** GET /mock/user/details
* **Response:** 200 OK with the JSON payload provided during registration.

---

## Testing

The project includes unit and integration tests to ensure context stability and controller logic. To execute the tests, run:

```bash
./mvnw test
```

---

## License

This project is licensed under the **GNU General Public License v3.0**. Refer to the LICENSE file for full terms.

---

### Author
JaqStoot
