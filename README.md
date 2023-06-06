# P1 - Aquatic Fishkeeper

## Introduction

This is a full-stack web application that allows users to browse a catalog of aquatic species to simulate their own tank with the purpose to educate and entertain. This is built using HTML, CSS, JavaScript framework Angular using PostMan to communicate with the server.

## User Stories

- **As a user**, I want to register an account so I can browse the fish catalog.
- **As a user**, I want to log in so I can see my own fish.
- **As a user**, I want to browse and save fish I own as a logged-in user.
- **As a user**, I want to browse fish by categories: diet, tempermant, other.
- **As a user**, I want to see if my owned fish are compatible with one another.
- **As a user**, I want build my own fish tank with saved fish.
- **As a user**, I want to see other users reviews on a fish to determine interest.
- **As a user**, I want to learn more about the fish: description, name, statistics.


## MVP (Minimum Viable Product)

- User registers and login
- Browsing Catalog of Fish and Searching methods
- Adding fish from the catalog to personal fish tank
- Create user fish tank
- Modifying the fish tank; add fish, remove fish, filtration
- Leave review of experience with fish in fish tank, persists to catalog
- Browse reviews of a fish in catalog, left by existing members

## Stretch Goals
- View other users and their tank
- I want to be able to simulate my fish tank using compatible fish.
- Adding a research feature: additional API

## API
- **Fish Species**: https://rapidapi.com/myapos--FqlEzvrlv/api/fish-species

## Tech Stacks

- **HTML-5**: The main markup language used for building the application.
- **Tailwind CSS**: Styling to apply to HTML
- **Typescript**: Javascript for fullstack development to give page action.
- **PostgreSQL**: Used as the database to store user, product, and order data.
- **Maven or Gradle**: Used for managing project dependencies.
- **JUnit**: A testing framework for Java applications, used to ensure our code works as expected.
- **Log4j**: A logging utility for debugging purposes.
- **JDBC (Java Database Connectivity)**: An API for connecting and executing queries on the database.
- **BCrypt**: A Java library for hashing and checking passwords for security.
- **JUnit, Mockito, and PowerMock**: Used for unit and integration testing.
- **Git and GitHub**: Used for version control.
- **Angular**: TypeScript open-source JavaScript framework for stateless application.

## Requirements

- **Clean Codebase**: All code should be clean and well-documented. The repository should not include any unnecessary files or folders such as the `target/`, `.DS_Store`, etc. All files and directories should be appropriately named and organized.

- **Database Design**: The database should be designed following the principles of the 3rd Normal Form (3NF) to ensure data integrity and efficiency. An Entity Relationship Diagram (ERD) should be included in the documentation.

- **Secure**: All sensitive user data such as passwords must be securely hashed before storing it in the database. The application should not display any sensitive information in error messages.

- **Error Handling**: The application should handle potential errors gracefully and provide clear and helpful error messages to the users.

- **Testing**: The application should have a high test coverage. Unit tests and integration tests should be implemented using JUnit, Mockito, and PowerMock.

- **Version Control**: The application should be developed using a version control system, preferably Git, with regular commits denoting progress.

- **Documentation**: The repository should include a README file with clear instructions on how to run the application. Code should be well-commented to allow for easy understanding and maintenance.

- **Scalable**: The design of the application should be scalable, allowing for easy addition of new features or modifications in the future.

