/*
Explanation of Packages:


controller:
This package will contain the REST controllers that expose endpoints to the frontend (React app).
Controllers will define APIs like /login, /signup, /profile, /pizza-items, and /orders.


service:
The service layer contains business logic. 
Each service interacts with the repository layer to perform database operations and responds to the controllers with the required data.
Examples: UserService, PizzaService, OrderService.

repository:
This package will contain the repositories that interact with the database using Spring Data JPA.
Each repository corresponds to an entity class and is used to perform CRUD operations on the database.
Examples: UserRepository, PizzaRepository, OrderRepository.


model:
This package will contain the entity classes that represent the database tables.
Each model class is an entity that will be mapped to the database with fields corresponding to the table columns.
Examples: User, Pizza, Order, Address, OrderItem.



dto (Data Transfer Object):
DTOs are used to pass data between the frontend (React) and backend (Spring Boot).
These are useful for encapsulating data and sending only the required information over the network, not the whole entity.
Examples: LoginRequest, SignupRequest, UserProfileDto, OrderDto.
config:

This package will contain configuration classes for security, database, and other general settings (such as CORS configuration for handling cross-origin requests between React and Spring Boot).
Examples: SecurityConfig, CorsConfig.
*/