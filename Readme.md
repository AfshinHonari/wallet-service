# Wallet Service

# Project overview

We are going to create a microservice to manage all data related to user wallets. This service will expose two APIs to other microservices.

## API Information

### `get-balance`
This API returns a JSON object showing the current balance of a user. The parameter required for this API is `user_id`. The output should follow the format below:

- **Input:**
    - `userId`: `int`

- **Output:**
  ```json
  {"balance": 4000}

### `add-money`

- **Input:**
    - `userId`: `int`
    - `amount`: `int` (this parameter can be negative)

- **Output:**
  ```json
  {"referenceId": 12312312312}

## Additional Requirements

- **Dockerize** the project.
- Use **MySQL** as the database to store your data.
- Save all transaction logs for users.
- Implement an API to display the balance for each user.
- Implement an API to add money to a user's wallet.
- Create necessary test cases (6 test cases to ensure understanding of the procedure).
- Implement a daily job to calculate the total amount of transactions and print it to the terminal.

Note: You do not need to develop any API or service for user management, only the necessary services related to the wallet.

## Architecture
This microservice is developed based on DDD approach and Onion Architecture. Also, separate parts of the project are split into multiple modules.
Any user with any username and "password" as the password can log in. The authentication part is implemented in a simple way because it should be defined as a separate microservice that handles all authentication and authorization concerns.

## Hint
To start the cron job, please run the application with the `cron` profile.

## Testing

All tests for the application are located in the `application-service` module. These tests ensure that the APIs (`get-balance`, `do-transaction`, `calculate-total-transactions`) function correctly according to the specified requirements.

To run the test cases, use the following command:

   ```bash
   ./mvnw test
   ```


## Running the Application

1. Manual:

    ```bash
    ./mvnw clean package
    java -jar /container/target/container-0.0.1-SNAPSHOT.jar 
    ```

2. Using Docker Compose:

    ```bash
    docker-compose up
    ```

   This command will start both the MySQL database and the Spring Boot application.
<br />
   15-second sleep is added in the Dockerfile before starting the application to ensure the database is up and running. There are better solutions, such as using scripts like `wait-for-it` or tools like `dockerize`, to solve this issue.

### Accessing Swagger UI

Once the application is running, you can access the Swagger UI to explore the APIs:
http://localhost:8080/swagger-ui/index.html