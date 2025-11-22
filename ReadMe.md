GitHub Copilot Chat Assistant.

let-s-play
A minimal Spring Boot REST API with JWT authentication.

Requirements

- Java 11+
- Maven
- (Dev) H2 in-memory DB; (Prod) PostgreSQL or MySQL recommended
- Docker & Docker Compose (optional)

Quick start (dev)

1. Clone:
   git clone https://github.com/anlazaar/let-s-play.git
   cd let-s-play
2. Build:
   mvn clean package
3. Run:
   java -jar target/let-s-play-0.0.1-SNAPSHOT.jar
4. Defaults:
   - Server: http://localhost:8080
   - H2 console: http://localhost:8080/h2-console (enabled in dev)

Important environment properties

- app.jwtSecret (e.g., APP_JWTSECRET)
- app.jwtExpirationMs (milliseconds, e.g., 3600000)
- spring.datasource.url / username / password (use SPRING\_... env vars in Docker)

Common endpoints (examples)

- POST /api/auth/register
  Body: { "username":"alice", "password":"P@ssw0rd", "email":"alice@example.com" }
- POST /api/auth/login
  Body: { "username":"alice", "password":"P@ssw0rd" }
  Response: { "accessToken": "<jwt>", "tokenType": "Bearer" }
- GET /api/users/me
  Header: Authorization: Bearer <jwt>

Docker (simple)

- Build image:
  docker build -t let-s-play:latest .
- Run:
  docker run -e APP_JWTSECRET=supersecret -p 8080:8080 let-s-play:latest
- Example docker-compose: pair the app with a postgres service and set SPRING_DATASOURCE_URL to jdbc:postgresql://db:5432/let_s_play

API docs

- Swagger/OpenAPI available (e.g., /swagger-ui.html or /swagger-ui/index.html)
- OpenAPI JSON: /v3/api-docs

Key concepts (short)

- JWT for stateless auth (tokens signed with app.jwtSecret)
- Spring Security for endpoint protection
- BCrypt for password hashing
- DTOs + validation for request/response
- ControllerAdvice for centralized error handling

Notes

- Use strong secrets (do not commit them). Prefer environment secrets or a vault for production.
- For production DBs, use migrations (Flyway/Liquibase) and set spring.jpa.hibernate.ddl-auto=validate or none.

License

- Add your preferred license file (e.g., MIT) to the repo.

If you want, I can generate a one-file Dockerfile, a docker-compose.yml, or a short example application.properties next. Which would you like?
