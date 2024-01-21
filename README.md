# TopEducation - Tuition Payment Management System

## Project Description

TopEducation pre-university academy, located in Santiago, has decided to modernize and automate its tuition payment management process. Currently, administration is done manually using Excel sheets, leading to inaccuracies and student dissatisfaction. The proposed solution is to develop a web application that automates this process, providing a more efficient and transparent experience for both students and administrative staff.

## Problem Context

The academy has been preparing students for university admission tests for over 15 years. However, the manual payment process has led to complaints due to errors in the payment records. The goal is to develop a web application that automates tuition payment management, improving accuracy and reducing complaints.

## Tuition Payment Process Information

The application will manage detailed information about students, including RUT, last names, first names, date of birth, type of school of origin, among others. Payments can be made in a lump sum or in installments, with applicable discounts based on the type of school and years since the student graduated.

Additionally, additional discounts are considered based on students' academic performance in simulated admission exam tests.

## User Stories

1. **US1: Enter student data from the screen.**
2. **US2: Generate tuition payment installments.**
3. **US3: List payment installments for a student and the payment status of each installment.**
4. **US4: Record tuition installment payments.**
5. **US5: Import exam grades from Excel file.**
6. **US6: Calculate tuition payment schedule.**
7. **US7: Generate a summary report of payment status for students.**

## Product Development Aspects

### 5.1 Project Management

- The project will be managed using SCRUM and the JIRA tool.

### 5.2 Development Constraints

- The application will be developed in Spring Boot (Spring Web, MySQL/PostgreSQL Driver, Spring Data JPA, Thymeleaf, Lombok) with Java.
- The architecture will be monolithic based on the layered architectural pattern.
- A relational database will be used (MySQL or PostgreSQL).
- The application will be developed using an IDE (IntelliJ, VS Code, etc.).

### 5.3 Production Deployment

- The application will be deployed on a cloud server (AWS, Digital Ocean, etc.).
- Terraform will be used to provision the server.
- Deployment will be done with Docker Compose, including the database, web application, and Nginx as a load balancer.

### 5.4 Development and Testing

- Source code will be managed in a GitHub repository.
- Git will be used for version control.
- A continuous delivery pipeline in Jenkins will be implemented to automate development, run unit tests (Junit), perform static analysis with Sonarqube, and upload the application to DockerHub.
- Testing coverage will be equal to or greater than 90%.
- Sonarqube analysis will show acceptable results for Reliability, Maintainability, and Coverage.

## Configuration and Deployment Instructions

1. Clone the repository from GitHub.
2. Configure database properties in the configuration file.
3. Run the Terraform script to provision the cloud server.
4. Deploy the application with Docker Compose.
5. Access the application from any web browser.
