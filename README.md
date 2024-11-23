# Problem-Solving Platform

A recruitment platform designed to evaluate candidates' technical skills in real-time through coding challenges. The platform streamlines the hiring process by providing accurate and efficient assessments.

---

## Features

### Admin Role:
- **Create, update, delete coding problems**: Admins can manage the list of coding problems.
- **Define constraints**: Set time limits, memory usage, and points allocation.
- **Test Cases Management**: Create and assign test cases to problems.
- **Metrics and Analytics**: Track candidate submissions and performance metrics.

### Candidate Role:
- **Solve coding problems**: Candidates can attempt problems within a pre-defined time frame.
- **Multi-language support**: Write and execute code in various programming languages.
- **Performance Feedback**: View detailed performance metrics such as memory and time usage for each submission.

---

## Platform Capabilities:
- **Multi-language Support**: Using **Judge0** to compile and execute code in multiple languages.
- **Analytics & Reporting**: Provides real-time feedback on code execution, performance, and submission efficiency.
- **Real-Time Feedback**: Track memory usage, time taken, and test case results during problem-solving.

---

## Technologies Used

### Backend:
- **Language**: Java
- **Framework**: Spring Boot
- **Database**: PostgreSQL
- **Microservices**: Modular architecture with dedicated APIs.

### Frontend:
- **Framework**: Angular (TypeScript)
- **Design**: Component-based architecture for scalable UI.

### Tools:
- **Code Execution**: Judge0 (local instance for code compilation and evaluation).
- **Containerization**: Docker and Docker Compose for easy deployment and scaling.
- **Version Control**: Git, with GitHub as the hosting service for repository management.

---

## Architecture Overview

The system follows a **microservices architecture**, where each core component is broken down into a separate service. These services communicate with each other using APIs. Key components include:

### 1. API Gateway:
- Routes all requests between the frontend and backend services.
- Handles communication between the **Coding Platform** and **Compiler** microservices.

### 2. Coding Platform Microservice:
- **Manages coding problems**: Stores problems, test cases, and submissions in the database.
- **Interfaces with the Database**: Uses PostgreSQL for persistent storage of coding problems, test cases, and results.

### 3. Compiler Microservice:
- **Interacts with Judge0**: Sends code snippets to Judge0 for compilation and execution.
- **Handles Results Transformation**: Transforms Judge0’s raw output into a user-friendly format with memory and time feedback.
- **Error Reporting**: Provides detailed error reports and feedback in case of compilation errors.

### 4. Frontend:
- **Admin View**: Allows admins to manage coding problems, monitor candidate performance, and track analytics.
- **Candidate View**: Displays coding problems, test case results, and real-time feedback on submissions.
