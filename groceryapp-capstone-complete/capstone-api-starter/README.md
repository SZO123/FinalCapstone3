# Grocery App - Capstone 3 (E-Commerce API + Site)

This project implements the **Grocery App** store option for Capstone 3.

## What’s implemented

### Required
- **Categories API** (GET/GET by id/POST/PUT/DELETE) with **ADMIN-only** write operations.
- **Fixed Product Search bug** (correct category/minPrice/maxPrice/subCategory filtering).
- **Fixed Product Update bug** (PUT now updates instead of inserting duplicates).

### Optional (Implemented)
- **Shopping Cart API** (GET/POST/PUT/DELETE) for logged-in users.
- **User Profile API** (GET/PUT) for logged-in users.
- **Checkout API** (POST /orders) converts the current cart into an order and clears the cart.

## Database
Run the grocery database script:
- `database/create_database_groceryapp.sql`

Update `src/main/resources/application.properties` if your MySQL username/password differs.

## Run
1. Start MySQL and run the SQL script.
2. Run the Spring Boot API (port 8080).
3. Open the front-end:
   - `capstone-web-applications/capstone-client-groceryapp/index.html` (hosted via IntelliJ static server)

## Key Endpoints
- Auth: `POST /register`, `POST /login`
- Categories: `GET /categories`, `GET /categories/{id}`, `POST/PUT/DELETE /categories/{id}` (ADMIN)
- Products: `GET /products`, `GET /products/{id}`, `POST/PUT/DELETE /products/{id}` (ADMIN write)
- Cart: `GET /cart`, `POST /cart/products/{productId}`, `PUT /cart/products/{productId}`, `DELETE /cart`
- Profile: `GET /profile`, `PUT /profile`
- Checkout: `POST /orders`

