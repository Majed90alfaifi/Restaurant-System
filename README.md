# Restaurant System

A Java-based restaurant ordering system that allows customers to browse menu items and place orders, while administrators can manage restaurant menu items.

## Features

### Customer
- User registration and login
- Browse available menu items
- Add items to an order
- View the order receipt

### Admin
- Add menu items
- Delete menu items
- Update item information
- Update item prices
- Update item quantities

## Technologies

- Java
- MySQL
- JDBC
- Maven
- Object-Oriented Programming (OOP)
- Command Design Pattern

## Project Structure

The application is organized into separate packages for:

- Database connection
- Customer functionality
- Administrator functionality
- User registration and authentication

## Database

The application uses MySQL to store and retrieve:

- Customer information
- Menu items
- Orders

Database credentials are provided through environment variables rather than being stored directly in the source code.

## How to Run

1. Clone the repository.
2. Create the database, tables and sample data:

```bash
mysql -u root -p < Project252.sql
```

3. Configure the following environment variables:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

If they are not set, the app connects to `jdbc:mysql://localhost:3306/cpit252project_db` as `root` with an empty password.

To sign in as the admin, use phone `0000` and password `admin` (created by `Project252.sql`).
