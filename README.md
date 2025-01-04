# InternshipsManagementPlatform

## Table  of Contents

- [About the Project](https://github.com/Diekgbbtt/InternshipsManagementPlatform?tab=readme-ov-file#about-the-project)
- [Pre-requisites](https://github.com/Diekgbbtt/InternshipsManagementPlatform?tab=readme-ov-file#pre-requisities)
- [Installation](https://github.com/Diekgbbtt/InternshipsManagementPlatform?tab=readme-ov-file#installation)
- [Usage](https://github.com/Diekgbbtt/InternshipsManagementPlatform?tab=readme-ov-file#usage)

### About the Project

This web application was developed for the practical test of Model Driven Software Engineering course, it's really simple and it's not intended to be used in production. I have added the report on the design and implementation process with a model driven approach that covers technical insights.  
It's simply a web CRUD platform for universities to manage students internships. It's basically only the backend and it's implemented with Java Spring Boot Starter Web framework; jdbc to interact with persistent level and Hibernate as ORM module both part of Spring Boot JPA; Lombok for easier development; Maven for build and test automation and dependencies management; MariaDB as database.  

### Pre-requisities
 
* Java 21+
* Maven 3.9.9+
* MariaDB server 11.1.2+  

First of all make sure your machine have the above required components. Depending on the machine os different, they can be installed in different ways.

### Installation

1. Start MariaDB server  

   Start mariadb server at port 3306(default). Make sure to have a root user or a custom user that matches the value of the property spring.datasource.username. Create a databse mysql, or a database with the same name of the value of the property spring.datasource.database. Use the database and for each class in Model add a table with the same name and attributes as columns.

2. Clone the repo
   ```sh    
   git clone https://github.com/Diekgbbtt/InternshipsManagementPlatform.git
   ```

### Usage

1. Build the project
   ```sh
   mvn clean install
   ```
2. Run the project
   ```sh
   mvn spring-boot:run
   ```
Maven automates the build process or as referred in maven context the lifecycle, the default lifecycle involves : cleaning of previous compiled files, gathering the source code and dependencies delaclared in pom.xml from maven remote repository (https://mvnrepository.com/repos/central), compiling source code, running test and packaging the compiled code into a jar file. For this simple web application we just clean and compile the source code and then with maven spring-boot plugin run the application.









