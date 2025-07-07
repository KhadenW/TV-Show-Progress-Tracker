# TV Show Progress Tracker

A console-based Java Maven application to track TV show progress for users, with MySQL backend.

## Features
- User login and registration
- Track TV show progress (not started, in-progress, completed)
- Admin management for TV shows
- User ratings for shows
- Console menu navigation

## Tech Stack
- Java 17
- Maven
- MySQL
- JDBC

## Setup
1. Clone the repo
2. Run the SQL script in `src/main/resources/db/init.sql` to set up the database
3. Configure your MySQL credentials in `src/main/resources/db.properties`
4. Build and run the project with Maven

## ER Diagram
See `docs/er_diagram.pdf` for the database structure. 