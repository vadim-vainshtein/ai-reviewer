# AGENTS.md

Guidance for Codex and other coding agents working in this repository.

## Project Context

- Treat this as a Java Spring Boot codebase unless repository structure proves otherwise.
- Prefer changes that fit the existing package layout, naming conventions, build tool, and test style.
- Keep changes focused on the requested behavior. Avoid broad refactors unless they are needed to make the requested change correct and maintainable.

## Code Organization

- Decompose long methods into short, readable steps with clear responsibilities.
- Keep one responsibility per method. A method name should clearly describe the single purpose of that method.
- Use method extraction for logical chunks of behavior, especially validation, mapping, persistence, and external calls.
- Keep controllers thin. Move business logic into services, request/response mapping into dedicated methods or mappers, and persistence concerns into repositories.
- Prefer constructor injection for Spring components. Avoid field injection.
- Keep package boundaries clear: controllers handle HTTP concerns, services coordinate business behavior, repositories handle data access, configuration classes define wiring, and domain/model classes represent data and rules.

## Modern Java Practices

- Use `var` for local variables when the right-hand side makes the type obvious and readability is preserved.
- Prefer `formatted()` over `String.format()`, for example `"Hello %s".formatted(name)`.
- Prefer immutable data where practical. Use records for simple DTOs when compatible with the project's Java and Spring Boot versions.
- Prefer collection factory methods such as `List.of(...)`, `Set.of(...)`, and `Map.of(...)` for small immutable collections.
- Use `Optional` as a return type where absence is expected. Do not use `Optional` for fields, method parameters, or serialization DTO properties.
- Avoid returning or accepting raw types. Use generics with clear element types.

## Spring Boot Practices

- Use Spring stereotypes intentionally: `@RestController` for HTTP APIs, `@Service` for business orchestration, `@Repository` for persistence adapters, and `@Configuration` for bean definitions.
- Validate request DTOs with Jakarta Bean Validation annotations and `@Valid` at controller boundaries.
- Keep transaction boundaries explicit with `@Transactional` on service-layer methods that perform unit-of-work persistence changes.
- Prefer configuration properties classes over scattered `@Value` usage for grouped settings.
- Do not hide errors by catching broad exceptions. Translate expected failures into clear domain outcomes or appropriate HTTP responses.
- Avoid logging sensitive data such as tokens, credentials, personal data, and raw request bodies that may contain secrets.

## Testing

- Add or update tests for behavior changes. Keep tests focused on observable behavior rather than implementation details.
- Prefer `@InjectMocks` with `@Mock` and `@Spy` over manual instantiation in test setup methods when using Mockito.
- Use Spring Boot slice tests when they match the scope, such as `@WebMvcTest` for controllers and `@DataJpaTest` for persistence.
- Use full `@SpringBootTest` tests sparingly, mainly for integration coverage that needs the application context.
- Name tests after the behavior being verified. Avoid names that only repeat the method under test.
- Cover success paths and important failure paths, including validation, missing resources, and downstream failures.

## Verification

- Run the relevant build and test commands before finishing when a build tool is present.
- For Maven projects, prefer `./mvnw test` when the wrapper exists, otherwise `mvn test`.
- For Gradle projects, prefer `./gradlew test` when the wrapper exists, otherwise `gradle test`.
- If verification cannot be run because the repository lacks a build tool, required source files, or dependencies, call that out clearly in the final response.
