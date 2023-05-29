# News Management Application

This is a readme file for the News Management Application, which is a web-based application developed using **Spring framework**, **Spring Data**, **Spring MVC**, **JWT**, **Flyway**, **PostgreSQL** and **Docker**. The application is designed to store, process, and display news articles. It includes various models such as User, Role, News, NewsTopic, and NewsSource.

## Table of Contents

* Features
* Installation
* Endpoints
* Swagger API Documentation


## Features

* User registration and authentication using JSON Web Tokens (JWT)
* Storing news articles with details such as title, content, author, publication date, etc.
* Associating news articles with topics and sources for better categorization and filtering
* CRUD operations for managing news articles, topics, and sources
* Pagination for efficient data retrieval
* Database migration and version control using Flyway
* Utilizing PostgreSQL as the relational database management system
* Containerization using Docker for easy deployment and scalability
* Swagger API documentation for exploring and testing the API endpoints

## Installation
To install and set up the application, follow these steps:

```shell
git clone https://github.com/Ernar2002/strongteam-news
```

```shell
docker compose up --build
```

The application will be accessible at http://localhost:8080 in your web browser.

## Endpoints

* `/api/v1/auth` - authentication endpoints
* `/api/v1/news` - News article management endpoints (GET, POST, PUT, DELETE)
* `/api/v1/sources` - News source management endpoints (GET, POST, PUT, DELETE)
* `/api/v1/topics` - News topic management endpoints (GET, POST, PUT, DELETE)

## Swagger API Documentation
To access the Swagger UI page and explore the API documentation, please follow the steps below:

1. Make sure your application is running locally on your machine.

2. Open your preferred web browser.

3. In the address bar, enter the following URL: http://localhost:8080/swagger-ui.html. Press Enter or Return to navigate to the Swagger UI page.

4. Once the Swagger UI page loads, you will see a search bar and a list of available endpoints.

5. To explore a specific API endpoint, follow these steps:

6. Locate the input field labeled "Explore".

7. Enter /v1/api-docs into the input field. This value represents the endpoint you want to explore.

As you type, the Swagger UI page will automatically filter and display matching endpoints.
