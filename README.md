# Uni Students Internships Management Platform

## Table  of Contents

- [About the Project](https://github.com/Diekgbbtt/InternshipsManagementPlatform?tab=readme-ov-file#about-the-project)
- [Pre-requisites](https://github.com/Diekgbbtt/InternshipsManagementPlatform?tab=readme-ov-file#pre-requisities)
- [Installation](https://github.com/Diekgbbtt/InternshipsManagementPlatform?tab=readme-ov-file#installation)
- [Usage](https://github.com/Diekgbbtt/InternshipsManagementPlatform?tab=readme-ov-file#usage)

### About the Project

Model Driven Software Engineering course project. Design and implementation documentation is reported as well.  
Simple web CRUD platform for a general university administrator to manage students internships. Technologies : Java Spring Boot Web framework; MariaDB database; JDBC protocol to communicate with persistent level; Hibernate as ORM; Lombok for boilerplate code; Maven for build and test and dependencies management.  

### Pre-requisities
 
* Java 21+
* Maven 3.9.9+
* MariaDB server 11.1.2+  

First of all make sure your machine have the above required components. Depending on the machine os, they can be installed in different ways.

### Installation

Clone the repo
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









