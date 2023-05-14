# News Management Application

This is a readme file for the News Management Application, which is a web-based application developed using **Spring framework**, **Spring Data**, **Spring MVC**, **JWT**, **Flyway**, **PostgreSQL** and **Docker**. The application is designed to store, process, and display news articles. It includes various models such as User, Role, News, NewsTopic, and NewsSource.

## Table of Contents

* Features
* Prerequisites
* Installation
* Configuration
* Usage
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

## Prerequisites
Before installing and running the application, make sure you have the following prerequisites:

* Java Development Kit (JDK) version 17 or higher
* Maven build tool
* Docker (for running the application in a container)
* PostgreSQL database

## Installation
To install and set up the application, follow these steps:

```shell
git clone https://github.com/Ernar2002/strongteam-news
```

```shell
mvn clean install
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
