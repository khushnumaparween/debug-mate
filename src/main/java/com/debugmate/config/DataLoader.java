package com.debugmate.config;

import com.debugmate.entity.ErrorDetail;
import com.debugmate.entity.ErrorEntity;
import com.debugmate.repository.ErrorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ErrorRepository repository;

    @Override
    public void run(String... args) {

        if (repository.count() == 0)
            {

            List<ErrorEntity> errors = List.of(
                // all your errors here
                    createError(
                            "Whitelabel Error Page",
                            "Spring Boot",
                            "Spring Boot shows a fallback error page when no controller or view is mapped to the requested URL.",
                            "No mapping found for HTTP request, default error page is displayed",
                            "Occurs when no controller mapping or template exists for requested route.",
                            "@GetMapping(\"/\") public String home(){ return \"home\"; }",
                            "@GetMapping(\"/\") public String home(){ return \"index\"; }"
                    ),

                    createError(
                            "NoSuchBeanDefinitionException",
                            "Spring Boot",
                            "Spring fails to find a required bean in the application context during dependency injection.",
                            "No qualifying bean of type found for dependency injection",
                            "Missing @Service, @Component or bean configuration in Spring context.",
                            "public class UserService {}",
                            "@Service public class UserService {}"
                    ),

                    createError(
                            "BeanCreationException",
                            "Spring Core",
                            "Spring fails while creating a bean due to configuration or dependency issues.",
                            "Error creating bean with name, initialization failure",
                            "Invalid constructor, missing dependencies or circular references.",
                            "@Bean public Service service(){ return new Service(); }",
                            "Ensure dependencies are valid and constructors are correct."
                    ),

                    createError(
                            "UnsatisfiedDependencyException",
                            "Spring Core",
                            "Spring cannot resolve a required dependency for a bean.",
                            "Unsatisfied dependency expressed through constructor or field injection",
                            "Missing or incorrect bean definition in context.",
                            "@Autowired private UserService service;",
                            "Ensure UserService is annotated and scanned by Spring."
                    ),

                    createError(
                            "Circular Dependency Error",
                            "Spring Core",
                            "Two or more beans depend on each other directly or indirectly causing a loop.",
                            "Bean currently in creation error due to circular reference",
                            "A depends on B and B depends on A causing infinite dependency loop.",
                            "Use constructor injection carefully or @Lazy annotation",
                            "Refactor dependencies to remove cyclic reference."
                    ),

                    createError(
                            "Failed to Configure DataSource",
                            "Database",
                            "Spring Boot cannot configure database connection automatically.",
                            "Cannot determine embedded database driver or connection properties missing",
                            "Missing URL, username, password or driver dependency.",
                            "spring.datasource.url=jdbc:mysql://localhost:3306/db",
                            "Provide correct DB URL, driver and credentials."
                    ),

                    createError(
                            "Port Already in Use",
                            "Server",
                            "Application fails to start because configured port is occupied.",
                            "Web server failed to start. Port 8080 already in use",
                            "Another application is using the same port.",
                            "server.port=8081",
                            "Change port or kill process using current port."
                    ),

                    createError(
                            "HttpRequestMethodNotSupportedException",
                            "Spring MVC",
                            "HTTP method used is not supported by the endpoint.",
                            "Request method GET not supported for this endpoint",
                            "Wrong HTTP method used in controller mapping.",
                            "@PostMapping instead of @GetMapping",
                            "Match HTTP method with controller mapping."
                    ),

                    createError(
                            "MissingServletRequestParameterException",
                            "Spring MVC",
                            "Required request parameter is missing in HTTP request.",
                            "Required request parameter 'id' is not present",
                            "Client did not send required query parameter.",
                            "@RequestParam int id",
                            "Ensure client sends all required parameters."
                    ),

                    createError(
                            "MethodArgumentTypeMismatchException",
                            "Spring MVC",
                            "Request parameter type does not match expected type.",
                            "Failed to convert String to Integer for parameter",
                            "Invalid type passed in URL or request body.",
                            "/user?id=abc",
                            "Validate input type before request processing."
                    ),

                    createError(
                            "HttpMessageNotReadableException",
                            "Spring MVC",
                            "Request body cannot be parsed into required object.",
                            "JSON parse error: unexpected token",
                            "Malformed JSON request body.",
                            "{name: John}",
                            "Ensure valid JSON format in request body."
                    ),

                    createError(
                            "Cannot Resolve Symbol",
                            "Compilation",
                            "Compiler cannot find referenced class, method or variable.",
                            "Cannot resolve symbol 'UserService'",
                            "Missing import or dependency.",
                            "UserService service;",
                            "Import correct package or add dependency."
                    ),

                    createError(
                            "Template Not Found",
                            "View Layer",
                            "Spring cannot locate the specified view template.",
                            "Template 'index.html' not found",
                            "Missing file in templates directory.",
                            "return \"index\";",
                            "Place file in src/main/resources/templates."
                    ),

                    createError(
                            "Static Resource Not Found",
                            "Web",
                            "Requested static file is missing from static directory.",
                            "Resource not found: style.css",
                            "File not placed in static folder.",
                            "link rel=stylesheet href=/css/style.css",
                            "Place files in src/main/resources/static."
                    ),

                    createError(
                            "LazyInitializationException",
                            "Hibernate",
                            "Hibernate session is closed before accessing lazy-loaded entity.",
                            "failed to lazily initialize collection",
                            "Accessing entity outside session scope.",
                            "FetchType.LAZY usage",
                            "Use FetchType.EAGER or open session in view."
                    ),

                    createError(
                            "Entity Not Managed",
                            "JPA",
                            "Entity is not attached to persistence context.",
                            "EntityManager does not manage the given entity",
                            "Entity is detached or not persisted properly.",
                            "entityManager.merge(entity)",
                            "Ensure entity is persisted before update."
                    ),

                    createError(
                            "DataIntegrityViolationException",
                            "Database",
                            "Database constraint violation occurs during insert or update.",
                            "Duplicate entry or foreign key constraint fails",
                            "Violates unique or foreign key constraints.",
                            "Insert duplicate email",
                            "Validate data before persistence."
                    ),

                    createError(
                            "SQLGrammarException",
                            "Database",
                            "Invalid SQL syntax executed by Hibernate or JDBC.",
                            "Syntax error in SQL query",
                            "Incorrect query formation.",
                            "SELECT * FORM users",
                            "Correct SQL syntax errors."
                    ),

                    createError(
                            "Access Denied 403",
                            "Security",
                            "User does not have permission to access resource.",
                            "Access is denied for this resource",
                            "Insufficient roles or authorities.",
                            "@PreAuthorize(\"hasRole('USER')\")",
                            "Assign correct roles and permissions."
                    ),

                    createError(
                            "JWT Token Invalid",
                            "Security",
                            "JWT token provided is malformed or tampered.",
                            "Invalid JWT signature or token format",
                            "Token corrupted or modified.",
                            "Authorization header token",
                            "Ensure valid token generation and validation."
                    ),

                    createError(
                            "Cannot Deserialize JSON",
                            "Serialization",
                            "Jackson cannot map JSON to Java object.",
                            "Cannot construct instance from JSON",
                            "Mismatch between JSON and class structure.",
                            "{name: \"John\"}",
                            "Align JSON fields with DTO structure."
                    ),

                    createError(
                            "Multipart File Upload Error",
                            "File Upload",
                            "File upload request fails during processing.",
                            "Failed to parse multipart request",
                            "Incorrect content type or request format.",
                            "multipart/form-data",
                            "Ensure correct request encoding."
                    ),

                    createError(
                            "Maximum Upload Size Exceeded",
                            "File Upload",
                            "Uploaded file exceeds configured size limit.",
                            "File size exceeds maximum allowed size",
                            "Large file upload without limit handling.",
                            "spring.servlet.multipart.max-file-size",
                            "Increase limit or compress file."
                    ),

                    createError(
                            "Hibernate Dialect Not Found",
                            "Hibernate",
                            "Hibernate cannot determine SQL dialect for database.",
                            "No Dialect mapping found for JDBC type",
                            "Missing dialect configuration.",
                            "spring.jpa.properties.hibernate.dialect",
                            "Set correct dialect for database."
                    ),

                    createError(
                            "TransactionRequiredException",
                            "JPA",
                            "Operation requires active transaction but none exists.",
                            "No EntityManager with active transaction",
                            "Missing @Transactional annotation.",
                            "@Transactional public void save()",
                            "Wrap DB operations in transaction."
                    ),

                    createError(
                            "Connection Refused",
                            "Database",
                            "Application cannot connect to database server.",
                            "Connection refused by database host",
                            "DB server not running or wrong port.",
                            "jdbc:mysql://localhost:3306/db",
                            "Start DB service and verify port."
                    ),

                    createError(
                            "Unknown Database",
                            "Database",
                            "Database specified in connection string does not exist.",
                            "Unknown database 'testdb'",
                            "Wrong database name in config.",
                            "spring.datasource.url",
                            "Create database or fix name."
                    ),

                    createError(
                            "404 Not Found",
                            "HTTP",
                            "Requested endpoint does not exist on server.",
                            "No mapping found for requested URL",
                            "Incorrect URL or missing controller mapping.",
                            "/api/user",
                            "Verify endpoint mapping."
                    ),

                    createError(
                            "500 Internal Server Error",
                            "HTTP",
                            "Unexpected server-side failure occurred.",
                            "Generic server exception occurred",
                            "Unhandled exception in backend logic.",
                            "NullPointerException",
                            "Add proper exception handling."
                    ),

                    createError(
                            "No Mapping Found for HTTP Request",
                            "Spring MVC",
                            "Spring cannot find any controller method mapped to the requested URL.",
                            "No mapping found for HTTP request with URI",
                            "Occurs when no @RequestMapping, @GetMapping, or @PostMapping matches the request URL.",
                            "@GetMapping(\"/user\")",
                            "Ensure correct URL mapping in controller."
                    ),

                    createError(
                            "Failed to Load ApplicationContext",
                            "Spring Boot",
                            "Spring application context fails to initialize during startup.",
                            "ApplicationContext failed to load due to bean or configuration errors",
                            "Missing beans, invalid configuration, or circular dependencies.",
                            "SpringApplication.run(App.class)",
                            "Fix configuration and resolve bean issues."
                    ),

                    createError(
                            "Could Not Autowire",
                            "Spring Core",
                            "Spring fails to inject dependency into a bean.",
                            "Could not autowire field, no qualifying bean found",
                            "Bean is missing or not scanned by Spring.",
                            "@Autowired private Service service;",
                            "Add @Service or correct component scanning."
                    ),

                    createError(
                            "Dependency Injection Failure",
                            "Spring Core",
                            "Spring cannot resolve dependencies required for a bean.",
                            "Injection of autowired dependencies failed",
                            "Missing or incompatible bean definitions.",
                            "@Autowired constructor injection",
                            "Ensure all dependencies are declared as beans."
                    ),

                    createError(
                            "Circular View Path",
                            "Spring MVC",
                            "View resolver loops back to same view causing infinite resolution.",
                            "Circular view path [index]: would dispatch back to same URL",
                            "Returning same view name as request mapping.",
                            "return \"index\";",
                            "Use proper view names or redirect."
                    ),

                    createError(
                            "SessionFactory Creation Failed",
                            "Hibernate",
                            "Hibernate fails to create session factory during startup.",
                            "SessionFactory could not be built",
                            "Invalid configuration or missing DB connection settings.",
                            "LocalSessionFactoryBean",
                            "Fix Hibernate configuration and DB settings."
                    ),

                    createError(
                            "Could Not Open JPA EntityManager",
                            "JPA",
                            "EntityManager cannot be created for persistence operations.",
                            "Failed to obtain JPA EntityManager instance",
                            "Database connection or persistence unit misconfigured.",
                            "@PersistenceContext",
                            "Fix persistence configuration."
                    ),

                    createError(
                            "Duplicate Bean Definition",
                            "Spring Core",
                            "Multiple beans with same name are defined in context.",
                            "BeanDefinitionOverrideException",
                            "Two beans registered with same name or type.",
                            "@Component class ServiceA",
                            "Remove duplicates or use @Primary."
                    ),

                    createError(
                            "Invalid Bean Scope",
                            "Spring Core",
                            "Bean scope configuration is incorrect or unsupported.",
                            "Invalid scope value for bean definition",
                            "Incorrect scope like prototype or singleton misused.",
                            "@Scope(\"prototype\")",
                            "Use valid Spring bean scopes."
                    ),

                    createError(
                            "Property Binding Failure",
                            "Spring Boot",
                            "Spring fails to bind configuration properties to class fields.",
                            "Failed to bind properties under 'app.config'",
                            "Mismatch between property names and class fields.",
                            "@ConfigurationProperties",
                            "Ensure correct property naming."
                    ),

                    createError(
                            "Failed to Bind Properties",
                            "Spring Boot",
                            "Configuration properties cannot be mapped to Java object.",
                            "Binding to target failed due to invalid format",
                            "Incorrect YAML or properties file format.",
                            "application.yml",
                            "Fix property structure and types."
                    ),

                    createError(
                            "Invalid Configuration Property",
                            "Spring Boot",
                            "Unknown or incorrect configuration property is used.",
                            "Configuration property is not recognized",
                            "Typo or unsupported property key.",
                            "spring.datasource.urll",
                            "Correct property name."
                    ),

                    createError(
                            "Spring Security Login Loop",
                            "Security",
                            "User is redirected repeatedly to login page.",
                            "Too many redirects to login page",
                            "Missing authentication success configuration.",
                            "http.formLogin()",
                            "Configure success handler properly."
                    ),

                    createError(
                            "CSRF Token Missing",
                            "Security",
                            "Request blocked due to missing CSRF token.",
                            "Invalid CSRF token or missing request parameter",
                            "CSRF protection enabled but token not sent.",
                            "<input type='hidden' name='_csrf' />",
                            "Include CSRF token in form."
                    ),

                    createError(
                            "JWT Expired Exception",
                            "Security",
                            "JWT token has expired and is no longer valid.",
                            "JWT expired at timestamp",
                            "Token validity period exceeded.",
                            "exp claim in JWT",
                            "Refresh or regenerate token."
                    ),

                    createError(
                            "Jackson Serialization Error",
                            "Serialization",
                            "Jackson fails to convert Java object to JSON.",
                            "Could not write JSON: serialization failure",
                            "Unsupported field type or circular reference.",
                            "ObjectMapper.writeValue",
                            "Fix object structure or annotations."
                    ),

                    createError(
                            "Infinite Recursion JSON Error",
                            "Serialization",
                            "Jackson enters infinite loop while serializing bidirectional relationships.",
                            "StackOverflowError during JSON serialization",
                            "Bidirectional entity relationship.",
                            "@JsonManagedReference",
                            "Use @JsonManagedReference / @JsonBackReference."
                    ),

                    createError(
                            "Failed to Convert Value",
                            "Spring Core",
                            "Spring cannot convert request parameter to required type.",
                            "Failed to convert String to required type",
                            "Invalid type conversion in request.",
                            "@RequestParam int id",
                            "Validate and convert input properly."
                    ),

                    createError(
                            "Thymeleaf Parsing Error",
                            "View Layer",
                            "Thymeleaf template contains syntax error.",
                            "Template parsing error in HTML file",
                            "Invalid Thymeleaf expression.",
                            "th:text=\"${name}\"",
                            "Fix template syntax."
                    ),

                    createError(
                            "Thymeleaf Template Input Exception",
                            "View Layer",
                            "Thymeleaf fails to process template input data.",
                            "Error resolving template input",
                            "Missing variables in model.",
                            "Model.addAttribute(\"user\", obj)",
                            "Ensure model attributes exist."
                    ),

                    createError(
                            "Could Not Extract ResultSet",
                            "Database",
                            "JDBC cannot read query result properly.",
                            "Error extracting ResultSet from query",
                            "Mismatch between query and entity mapping.",
                            "SELECT * FROM users",
                            "Fix entity mapping and query."
                    ),

                    createError(
                            "JDBC Connection Failure",
                            "Database",
                            "Application fails to establish JDBC connection.",
                            "Failed to connect to database via JDBC",
                            "Wrong credentials or DB down.",
                            "DriverManager.getConnection",
                            "Check DB status and credentials."
                    ),

                    createError(
                            "Communications Link Failure",
                            "Database",
                            "Network failure between application and database.",
                            "Communications link failure during DB request",
                            "Database unreachable or timeout.",
                            "jdbc:mysql://host:3306/db",
                            "Check network and DB server."
                    ),

                    createError(
                            "Access Denied for User",
                            "Database",
                            "Database rejects user authentication attempt.",
                            "Access denied for user 'root'",
                            "Incorrect username or password.",
                            "spring.datasource.username",
                            "Fix DB credentials."
                    ),

                    createError(
                            "Driver Class Not Found",
                            "Database",
                            "JDBC driver is missing from project dependencies.",
                            "ClassNotFoundException for JDBC driver",
                            "Missing database driver dependency.",
                            "com.mysql.cj.jdbc.Driver",
                            "Add correct DB driver dependency."
                    ),

                    createError(
                            "Maven Dependency Conflict",
                            "Build Tool",
                            "Multiple versions of same dependency cause conflict.",
                            "Dependency convergence error in Maven build",
                            "Version mismatch in dependencies.",
                            "pom.xml",
                            "Align dependency versions."
                    ),

                    createError(
                            "Gradle Build Failed",
                            "Build Tool",
                            "Gradle project fails during compilation or dependency resolution.",
                            "Build failed with errors",
                            "Incorrect Gradle configuration.",
                            "gradlew build",
                            "Fix build.gradle file."
                    ),

                    createError(
                            "Bean Currently in Creation",
                            "Spring Core",
                            "Spring detects bean is already in initialization process.",
                            "Requested bean is currently in creation",
                            "Circular dependency or lazy initialization missing.",
                            "@Lazy annotation",
                            "Break dependency cycle."
                    ),

                    createError(
                            "SpEL Expression Exception",
                            "Spring Core",
                            "Spring Expression Language evaluation fails.",
                            "Expression parsing error in SpEL",
                            "Invalid expression syntax.",
                            "@Value(\"#{systemProperties['user.name']}\")",
                            "Correct SpEL syntax."
                    ),

                    createError(
                            "RestTemplate Connection Timeout",
                            "HTTP Client",
                            "External API call via RestTemplate times out.",
                            "Connection timed out while calling external service",
                            "Slow or unreachable external service.",
                            "restTemplate.getForObject",
                            "Increase timeout or retry logic."
                    ),

                    createError(
                            "ApplicationContextException",
                            "Spring Boot",
                            "Spring fails to initialize the application context due to invalid configuration or runtime failure during startup.",
                            "ApplicationContext could not be started due to bean initialization failure",
                            "Occurs when one or more beans fail during context refresh phase.",
                            "SpringApplication.run(Application.class)",
                            "Check logs for root bean failure and fix configuration issues."
                    ),

                    createError(
                            "BeanInstantiationException",
                            "Spring Core",
                            "Spring fails to instantiate a bean due to constructor or abstract class issues.",
                            "Failed to instantiate bean class, constructor threw exception",
                            "Bean class is abstract, interface, or has failing constructor logic.",
                            "@Component class UserService",
                            "Ensure bean has valid concrete implementation and working constructor."
                    ),

                    createError(
                            "Failed to Start Bean",
                            "Spring Core",
                            "A bean fails during initialization lifecycle phase in Spring container.",
                            "Bean creation aborted during initialization phase",
                            "Runtime exception inside @PostConstruct or init method.",
                            "@PostConstruct void init() {}",
                            "Fix initialization logic inside bean lifecycle methods."
                    ),

                    createError(
                            "MissingPathVariableException",
                            "Spring MVC",
                            "Required path variable is missing in request mapping.",
                            "Required URI template variable 'id' is not present",
                            "URL does not include required path parameter.",
                            "@GetMapping(\"/user/{id}\")",
                            "Ensure client provides required path variable."
                    ),

                    createError(
                            "PathVariable Exception",
                            "Spring MVC",
                            "Spring fails to bind path variable to method parameter.",
                            "Failed to bind path variable to required type",
                            "Type mismatch or missing path variable name.",
                            "@PathVariable int id",
                            "Ensure correct type and matching variable name."
                    ),

                    createError(
                            "RequestMapping Ambiguity",
                            "Spring MVC",
                            "Multiple controller methods match the same request mapping causing conflict.",
                            "Ambiguous mapping found for HTTP request",
                            "Two or more endpoints have identical URL patterns.",
                            "@GetMapping(\"/user\")",
                            "Make mappings unique using different paths or HTTP methods."
                    ),

                    createError(
                            "Failed to Evaluate Expression",
                            "Spring Core",
                            "Spring fails while evaluating SpEL or dynamic expression at runtime.",
                            "Expression evaluation failed in Spring context",
                            "Invalid syntax or null reference in expression.",
                            "@Value(\"#{T(java.lang.Math).random()}\")",
                            "Validate SpEL syntax and null safety."
                    ),

                    createError(
                            "HttpMediaTypeNotSupportedException",
                            "Spring MVC",
                            "Client sends request with unsupported Content-Type header.",
                            "Content type 'text/plain' not supported",
                            "Controller expects JSON but receives different media type.",
                            "@PostMapping consumes = application/json",
                            "Send correct Content-Type header."
                    ),

                    createError(
                            "HttpMessageNotReadableException",
                            "Spring MVC",
                            "Request body cannot be parsed into Java object due to malformed structure.",
                            "JSON parse error: unexpected token or invalid structure",
                            "Malformed JSON request payload.",
                            "{ \"name\": \"John\" }",
                            "Ensure valid JSON format and matching DTO fields."
                    ),

                    createError(
                            "Method Not Allowed 405",
                            "HTTP",
                            "Client uses an HTTP method that is not supported for the endpoint.",
                            "Request method not supported for this endpoint",
                            "Endpoint exists but does not support given HTTP method.",
                            "GET on POST-only endpoint",
                            "Use correct HTTP method or update controller mapping."
                    ),

                    createError(
                            "Unsupported Media Type 415",
                            "HTTP",
                            "Server rejects request due to unsupported Content-Type.",
                            "415 Unsupported Media Type error",
                            "Incorrect or missing Content-Type header in request.",
                            "Content-Type: text/plain instead of application/json",
                            "Set correct Content-Type header."
                    ),

                    createError(
                            "NoSuchBeanDefinitionException",
                            "Spring Core",
                            "Spring cannot find a required bean in the application context during dependency injection.",
                            "No qualifying bean of type found in application context",
                            "Bean is not declared, not scanned, or missing annotation like @Service or @Component.",
                            "@Autowired private UserService userService;",
                            "Ensure the class is annotated and included in component scanning."
                    ),

                    createError(
                            "Failed to Load ApplicationContext",
                            "Spring Boot",
                            "Application context fails to initialize due to configuration or bean errors.",
                            "Context initialization failed during application startup",
                            "One or more beans failed during creation or configuration loading.",
                            "SpringApplication.run(App.class)",
                            "Check logs and fix the root cause bean or config issue."
                    ),

                    createError(
                            "BeanCreationException",
                            "Spring Core",
                            "Spring fails to create a bean due to initialization or dependency problems.",
                            "Error creating bean with name during startup",
                            "Invalid constructor, missing dependency, or runtime exception in bean.",
                            "@Bean public Service service()",
                            "Fix constructor logic and dependency injection issues."
                    ),

                    createError(
                            "UnsatisfiedDependencyException",
                            "Spring Core",
                            "Spring cannot resolve required dependency for a bean during injection.",
                            "Unsatisfied dependency expressed through constructor or field injection",
                            "Required bean is missing or not properly defined.",
                            "@Autowired private Repo repo;",
                            "Ensure dependency bean exists and is properly registered."
                    ),

                    createError(
                            "Could Not Autowire",
                            "Spring Core",
                            "Spring fails to inject dependency because no matching bean is available.",
                            "Could not autowire field: no qualifying bean found",
                            "Missing or incorrect bean definition in application context.",
                            "@Autowired private Service service;",
                            "Add @Service or correct package scanning."
                    ),

                    createError(
                            "Failed to Evaluate Expression",
                            "Spring Core",
                            "Spring Expression Language (SpEL) evaluation fails at runtime.",
                            "SpEL expression evaluation error",
                            "Invalid syntax or null reference in expression.",
                            "@Value(\"#{systemProperties['os.name']}\")",
                            "Correct SpEL syntax and ensure safe references."
                    ),

                    createError(
                            "SpEL Expression Exception",
                            "Spring Core",
                            "Spring Expression Language cannot parse or evaluate expression.",
                            "Expression parsing failed in SpEL context",
                            "Invalid or malformed expression.",
                            "@Value(\"#{T(java.lang.Math).random()}\")",
                            "Fix expression syntax."
                    ),

                    createError(
                            "Bean Currently in Creation",
                            "Spring Core",
                            "Spring detects a circular dependency while a bean is still being created.",
                            "Requested bean is currently in creation",
                            "Circular dependency between beans.",
                            "@Autowired dependency cycle",
                            "Break cycle or use @Lazy annotation."
                    ),

                    createError(
                            "Circular Dependency Error",
                            "Spring Core",
                            "Two or more beans depend on each other directly or indirectly.",
                            "Circular reference detected between beans",
                            "A depends on B and B depends on A.",
                            "ServiceA -> ServiceB -> ServiceA",
                            "Refactor dependencies to remove circular reference."
                    ),

                    createError(
                            "Property Binding Failure",
                            "Spring Boot",
                            "Configuration properties cannot be bound to Java object fields.",
                            "Failed to bind properties under configuration",
                            "Mismatch between property keys and Java class structure.",
                            "@ConfigurationProperties(prefix = \"app\")",
                            "Ensure correct property naming and structure."
                    ),

                    createError(
                            "Invalid Configuration Property",
                            "Spring Boot",
                            "Unknown or incorrectly defined configuration property is used.",
                            "Configuration property is not recognized by Spring",
                            "Typo or unsupported property key in config file.",
                            "spring.datasource.urll",
                            "Fix property name in application.properties or yaml."
                    ),

                    createError(
                            "Failed to Bind Properties",
                            "Spring Boot",
                            "Spring cannot bind external configuration to Java object.",
                            "Binding error for configuration properties",
                            "Incorrect format or incompatible data type.",
                            "application.yml binding issue",
                            "Ensure correct structure and data types."
                    ),

                    createError(
                            "SessionFactory Creation Failed",
                            "Hibernate",
                            "Hibernate fails to build session factory due to configuration issues.",
                            "SessionFactory could not be created",
                            "Incorrect Hibernate or datasource configuration.",
                            "LocalSessionFactoryBean setup",
                            "Fix DB configuration and Hibernate settings."
                    ),

                    createError(
                            "Could Not Open JPA EntityManager",
                            "JPA",
                            "EntityManager cannot be created for persistence operations.",
                            "Failed to obtain EntityManager instance",
                            "Persistence unit misconfigured or DB connection failure.",
                            "@PersistenceContext",
                            "Fix persistence configuration and DB connection."
                    ),

                    createError(
                            "Entity Not Managed",
                            "JPA",
                            "Entity is not attached to persistence context during operation.",
                            "EntityManager does not manage this entity",
                            "Entity is detached or not persisted properly.",
                            "entityManager.persist(entity)",
                            "Ensure entity is managed before update."
                    ),

                    createError(
                            "TransactionRequiredException",
                            "JPA",
                            "Database operation requires active transaction but none exists.",
                            "No transaction is active for this operation",
                            "Missing @Transactional annotation.",
                            "@Transactional public void save()",
                            "Wrap DB operations inside transaction."
                    ),

                    createError(
                            "LazyInitializationException",
                            "Hibernate",
                            "Lazy-loaded entity accessed outside active session.",
                            "failed to lazily initialize proxy",
                            "Session closed before accessing relationship.",
                            "FetchType.LAZY usage",
                            "Use eager fetching or open session in view."
                    ),

                    createError(
                            "SQLGrammarException",
                            "Database",
                            "Invalid SQL syntax executed by Hibernate or JDBC.",
                            "SQL syntax error in query execution",
                            "Incorrect SQL statement or mapping issue.",
                            "SELECT * FORM users",
                            "Correct SQL syntax."
                    ),

                    createError(
                            "DataIntegrityViolationException",
                            "Database",
                            "Database constraint violation occurs during insert or update.",
                            "Unique or foreign key constraint violation",
                            "Duplicate or invalid relational data.",
                            "Insert duplicate email",
                            "Validate data before saving."
                    ),

                    createError(
                            "Communications Link Failure",
                            "Database",
                            "Database connection lost or cannot be established.",
                            "Connection failure between application and database",
                            "DB server down or network issue.",
                            "jdbc:mysql://localhost:3306/db",
                            "Check DB server and network connectivity."
                    ),

                    createError(
                            "Access Denied for User",
                            "Database",
                            "Database rejects authentication due to invalid credentials.",
                            "Access denied for database user",
                            "Incorrect username or password.",
                            "spring.datasource.username=root",
                            "Verify DB credentials."
                    ),

                    createError(
                            "Driver Class Not Found",
                            "Database",
                            "JDBC driver is missing from project dependencies.",
                            "ClassNotFoundException for database driver",
                            "Missing driver dependency in build file.",
                            "com.mysql.cj.jdbc.Driver",
                            "Add correct JDBC driver dependency."
                    ),

                    createError(
                            "Cannot Deserialize JSON",
                            "Serialization",
                            "Jackson fails to convert JSON into Java object.",
                            "Cannot construct instance from JSON input",
                            "Mismatch between JSON structure and Java class.",
                            "{ \"name\": \"John\" }",
                            "Align DTO fields with JSON structure."
                    ),

                    createError(
                            "Infinite Recursion JSON Error",
                            "Serialization",
                            "Jackson enters infinite loop during serialization of bidirectional relationships.",
                            "StackOverflowError during JSON serialization",
                            "Bidirectional entity relationship not handled properly.",
                            "@OneToMany / @ManyToOne relationship",
                            "Use @JsonManagedReference and @JsonBackReference."
                    ),

                    createError(
                            "Jackson Serialization Error",
                            "Serialization",
                            "Jackson fails to convert Java object into JSON format.",
                            "Could not write JSON due to serialization failure",
                            "Unsupported type or circular reference.",
                            "ObjectMapper.writeValueAsString(obj)",
                            "Fix object structure or add proper annotations."
                    ),

                    createError(
                            "404 Not Found",
                            "HTTP",
                            "Requested endpoint does not exist on server.",
                            "No handler found for HTTP request",
                            "Wrong URL or missing controller mapping.",
                            "/api/users",
                            "Verify endpoint mapping in controller."
                    ),

                    createError(
                            "405 Method Not Allowed",
                            "HTTP",
                            "HTTP method used is not supported for this endpoint.",
                            "Request method not supported",
                            "Wrong HTTP method used for mapped endpoint.",
                            "GET on POST endpoint",
                            "Use correct HTTP method mapping."
                    ),

                    createError(
                            "415 Unsupported Media Type",
                            "HTTP",
                            "Server rejects request due to incorrect Content-Type.",
                            "Unsupported media type in request header",
                            "Wrong or missing Content-Type header.",
                            "Content-Type: text/plain",
                            "Set Content-Type to application/json."
                    ),

                    createError(
                            "500 Internal Server Error",
                            "HTTP",
                            "Unexpected server-side error occurs during request processing.",
                            "Generic internal server failure",
                            "Unhandled exception in backend logic.",
                            "NullPointerException",
                            "Add proper exception handling and logging."
                    ),
                    createError(
                            "BeanDefinitionOverrideException",
                            "Spring Core",
                            "Spring detects multiple bean definitions with the same name causing override conflict.",
                            "BeanDefinitionOverrideException thrown during context initialization",
                            "Two beans defined with same name and overriding disabled.",
                            "@Bean public Service service() {}",
                            "Rename bean or enable spring.main.allow-bean-definition-overriding"
                    ),

                    createError(
                            "ConfigDataLocationNotFoundException",
                            "Spring Boot",
                            "Spring Boot fails to locate configuration file or config server.",
                            "Config data location 'application.properties' not found",
                            "Missing or incorrect config file path.",
                            "spring.config.location",
                            "Check file path and config directory"
                    ),

                    createError(
                            "HttpMediaTypeNotAcceptableException",
                            "Spring MVC",
                            "Client cannot accept server response media type.",
                            "Could not find acceptable representation",
                            "Mismatch in Accept header and produced response type.",
                            "Accept: application/xml",
                            "Ensure correct response content type"
                    ),

                    createError(
                            "AsyncRequestTimeoutException",
                            "Spring MVC",
                            "Asynchronous request processing exceeds timeout limit.",
                            "Async request timed out",
                            "Long running async processing or low timeout value.",
                            "@Async controller method",
                            "Increase async timeout or optimize processing"
                    ),

                    createError(
                            "MethodArgumentNotValidException",
                            "Validation",
                            "Request body validation fails for @Valid annotated objects.",
                            "Validation failed for request body",
                            "Invalid or missing field values in JSON payload.",
                            "@Valid @RequestBody UserDTO",
                            "Fix request body according to validation rules"
                    ),

                    createError(
                            "ConstraintViolationException",
                            "Validation",
                            "Validation fails on method parameters or service layer constraints.",
                            "Constraint violation occurred",
                            "Invalid request parameters or service validation failure.",
                            "@NotNull @RequestParam id",
                            "Ensure valid input parameters"
                    ),

                    createError(
                            "AuthenticationException",
                            "Security",
                            "Base exception for authentication failures in Spring Security.",
                            "Authentication failed",
                            "Invalid credentials or missing authentication.",
                            "Security filter chain",
                            "Provide valid authentication token"
                    ),

                    createError(
                            "BadCredentialsException",
                            "Security",
                            "Authentication fails due to invalid username or password.",
                            "Bad credentials provided",
                            "Wrong login credentials.",
                            "UsernamePasswordAuthenticationToken",
                            "Check username and password"
                    ),

                    createError(
                            "LockedException",
                            "Security",
                            "User account is locked and cannot authenticate.",
                            "User account is locked",
                            "Account disabled due to security policy.",
                            "UserDetailsService",
                            "Unlock account or contact admin"
                    ),

                    createError(
                            "DisabledException",
                            "Security",
                            "User account is disabled and cannot log in.",
                            "User account is disabled",
                            "Account manually disabled by admin.",
                            "UserDetails.isEnabled",
                            "Enable account in database"
                    ),

                    createError(
                            "InsufficientAuthenticationException",
                            "Security",
                            "Authentication is missing or incomplete for accessing resource.",
                            "Full authentication is required",
                            "Missing or invalid authentication token.",
                            "Spring Security filter",
                            "Provide valid authentication"
                    ),

                    createError(
                            "EntityNotFoundException",
                            "JPA",
                            "Requested database entity does not exist.",
                            "Entity not found in persistence context",
                            "Invalid entity ID lookup.",
                            "entityManager.find()",
                            "Validate entity existence before access"
                    ),

                    createError(
                            "OptimisticLockException",
                            "JPA",
                            "Concurrent update conflict detected in database entity.",
                            "Optimistic locking failure",
                            "Multiple updates on same record.",
                            "@Version field",
                            "Retry transaction or handle concurrency"
                    ),

                    createError(
                            "QueryTimeoutException",
                            "Database",
                            "Database query execution exceeds allowed time limit.",
                            "Query execution timeout",
                            "Slow query or missing index.",
                            "JPA query execution",
                            "Optimize query or increase timeout"
                    ),

                    createError(
                            "ResponseStatusException",
                            "HTTP",
                            "Explicit exception used to return HTTP status from controller.",
                            "ResponseStatusException thrown",
                            "Manual status control in controller.",
                            "throw new ResponseStatusException(HttpStatus.NOT_FOUND)",
                            "Use proper error handling strategy"
                    ),

                    createError(
                            "HttpClientErrorException",
                            "HTTP Client",
                            "Client-side HTTP request failure (4xx responses).",
                            "Client error response from external API",
                            "Invalid request sent to external service.",
                            "RestTemplate call",
                            "Fix request parameters"
                    ),

                    createError(
                            "HttpServerErrorException",
                            "HTTP Client",
                            "External server returns 5xx error response.",
                            "Server error response from external API",
                            "Downstream service failure.",
                            "RestTemplate or WebClient",
                            "Retry or fallback mechanism"
                    ),

                    createError(
                            "ResourceAccessException",
                            "HTTP Client",
                            "Failed to access external resource due to network issues.",
                            "I/O error on HTTP request",
                            "Network failure or timeout.",
                            "RestTemplate call",
                            "Check network or retry"
                    ),

                    createError(
                            "RestClientException",
                            "HTTP Client",
                            "Generic exception for RestTemplate failures.",
                            "Error during REST client operation",
                            "Any HTTP or conversion failure.",
                            "RestTemplate.exchange()",
                            "Handle exceptions properly"
                    ),

                    createError(
                            "WebClientResponseException",
                            "HTTP Client",
                            "WebClient receives error response from server.",
                            "WebClient request failed with error status",
                            "4xx or 5xx response from API.",
                            "WebClient.get().retrieve()",
                            "Handle response status properly"
                    ),

                    createError(
                            "FeignException",
                            "Microservices",
                            "Feign client call fails while communicating between services.",
                            "Feign client request failed",
                            "Remote service error or network issue.",
                            "@FeignClient interface",
                            "Add fallback or retry logic"
                    ),

                    createError(
                            "MaxUploadSizeExceededException",
                            "File Upload",
                            "Uploaded file exceeds maximum allowed size.",
                            "Maximum upload size exceeded",
                            "Large file upload beyond configured limit.",
                            "spring.servlet.multipart.max-file-size",
                            "Increase file size limit"
                    ),

                    createError(
                            "MultipartException",
                            "File Upload",
                            "Multipart request is malformed or invalid.",
                            "Failed to parse multipart request",
                            "Incorrect form-data structure.",
                            "multipart/form-data request",
                            "Fix request encoding"
                    ),

                    createError(
                            "ConversionFailedException",
                            "Spring Core",
                            "Spring fails to convert value between types.",
                            "Type conversion failed in Spring context",
                            "Invalid input type mapping.",
                            "@RequestParam int id",
                            "Validate and convert input properly"
                    ),

                    createError(
                            "TypeMismatchException",
                            "Spring Core",
                            "Request parameter type does not match expected type.",
                            "Failed to convert parameter type",
                            "Invalid data type provided.",
                            "@PathVariable int id",
                            "Ensure correct type in request"
                    ),

                    createError(
                            "SpelEvaluationException",
                            "Spring Core",
                            "Spring Expression Language evaluation fails at runtime.",
                            "SpEL expression evaluation error",
                            "Invalid expression syntax or null reference.",
                            "@Value(\"#{systemProperties['user.name']}\")",
                            "Fix SpEL expression"
                    ),

                    createError(
                            "BindException",
                            "Validation",
                            "Spring fails to bind request parameters to object due to invalid input.",
                            "Binding error for request parameters",
                            "Invalid form data or type mismatch in request params.",
                            "@ModelAttribute User user",
                            "Ensure correct request parameter structure"
                    ),

                    createError(
                            "MissingServletRequestPartException",
                            "Spring MVC",
                            "Required multipart file part is missing in request.",
                            "Required request part is not present",
                            "Client did not send file or form-data part.",
                            "MultipartFile file",
                            "Ensure file is included in request"
                    ),

                    createError(
                            "MissingRequestHeaderException",
                            "Spring MVC",
                            "Required HTTP header is missing in request.",
                            "Required request header is not present",
                            "Client did not send required headers.",
                            "@RequestHeader String token",
                            "Send required headers in request"
                    ),

                    createError(
                            "HttpMessageNotWritableException",
                            "Spring MVC",
                            "Spring fails to serialize response body into JSON.",
                            "Could not write JSON response",
                            "Serialization issue or circular reference.",
                            "return ResponseEntity.ok(obj)",
                            "Fix DTO structure or JSON mapping"
                    ),

                    createError(
                            "ConversionNotSupportedException",
                            "Spring Core",
                            "Spring cannot convert value between incompatible types.",
                            "Conversion not supported for given type",
                            "Unsupported type conversion in Spring context.",
                            "@RequestParam Enum value",
                            "Provide proper converter or valid input"
                    ),

                    createError(
                            "NoResourceFoundException",
                            "Spring MVC",
                            "Requested static or controller resource is not found.",
                            "No resource found for given path",
                            "Invalid URL or missing static resource.",
                            "/static/style.css",
                            "Verify resource path"
                    ),

                    createError(
                            "DataAccessResourceFailureException",
                            "Database",
                            "Database connection failure at resource level.",
                            "Failed to obtain JDBC Connection",
                            "DB server down or network issue.",
                            "JdbcTemplate query",
                            "Check DB connectivity"
                    ),

                    createError(
                            "DuplicateKeyException",
                            "Database",
                            "Database insert fails due to duplicate key constraint.",
                            "Duplicate entry for primary or unique key",
                            "Inserting already existing record.",
                            "INSERT INTO users",
                            "Check uniqueness before insert"
                    ),

                    createError(
                            "DeadlockLoserDataAccessException",
                            "Database",
                            "Database transaction fails due to deadlock.",
                            "Deadlock detected during transaction",
                            "Concurrent DB operations conflicting.",
                            "@Transactional service method",
                            "Retry transaction or optimize queries"
                    ),

                    createError(
                            "InvalidDataAccessApiUsageException",
                            "Database",
                            "Incorrect use of Spring Data API causes failure.",
                            "Invalid data access API usage detected",
                            "Wrong repository or query usage.",
                            "JpaRepository call",
                            "Fix repository method usage"
                    ),

                    createError(
                            "DataAccessException",
                            "Database",
                            "Generic Spring database access failure.",
                            "Unexpected data access error",
                            "Any DB-related runtime issue.",
                            "Spring Data JPA repository",
                            "Check DB logs for root cause"
                    ),

                    createError(
                            "InvalidDefinitionException",
                            "Serialization",
                            "Jackson cannot construct object due to invalid class definition.",
                            "Invalid JSON serialization definition",
                            "Missing constructor or incompatible type.",
                            "ObjectMapper usage",
                            "Fix DTO structure"
                    ),

                    createError(
                            "JsonMappingException",
                            "Serialization",
                            "Jackson fails while mapping JSON to Java object.",
                            "Error mapping JSON structure",
                            "Mismatch between JSON and Java class.",
                            "REST API request body",
                            "Align JSON and DTO"
                    ),

                    createError(
                            "MismatchedInputException",
                            "Serialization",
                            "Jackson receives unexpected JSON input format.",
                            "Cannot deserialize JSON input",
                            "Wrong JSON structure or type.",
                            "{ \"id\": \"abc\" }",
                            "Fix input format"
                    ),

                    createError(
                            "TaskRejectedException",
                            "Spring Async",
                            "Task is rejected by executor due to overload or shutdown.",
                            "Task rejected from thread pool",
                            "Executor saturated or stopped.",
                            "@Async method",
                            "Increase thread pool size"
                    ),

                    createError(
                            "MailSendException",
                            "Email",
                            "Spring Mail fails to send email message.",
                            "Failed to send email via SMTP",
                            "SMTP server failure or invalid config.",
                            "JavaMailSender.send()",
                            "Check SMTP configuration"
                    ),

                    createError(
                            "MessagingException",
                            "Messaging",
                            "General messaging system failure.",
                            "Messaging system error occurred",
                            "Email or queue communication failure.",
                            "JavaMail or JMS",
                            "Check messaging configuration"
                    ),

                    createError(
                            "ConcurrentModificationException",
                            "Java Core",
                            "Collection modified while iterating causing runtime failure.",
                            "Concurrent modification detected",
                            "Unsafe iteration over collections.",
                            "List iteration loop",
                            "Use Iterator or concurrent collections"
                    ),

                    createError(
                            "HttpServerErrorException",
                            "HTTP Client",
                            "External server returns 5xx error response.",
                            "Server error from external API",
                            "Downstream service failure.",
                            "RestTemplate call",
                            "Handle retries or fallback"
                    ),

                    createError(
                            "ResponseStatusException",
                            "Spring MVC",
                            "Explicit exception used to return HTTP status from controller.",
                            "Manual HTTP status exception thrown",
                            "Used for custom API error responses.",
                            "throw new ResponseStatusException(HttpStatus.NOT_FOUND)",
                            "Use centralized exception handling"
                    ),

                    createError(
                            "AuthenticationException",
                            "Security",
                            "Base authentication failure in Spring Security.",
                            "Authentication failed",
                            "Invalid or missing credentials.",
                            "Security filter chain",
                            "Provide valid authentication"
                    ),

                    createError(
                            "BadCredentialsException",
                            "Security",
                            "Login fails due to incorrect username or password.",
                            "Invalid credentials provided",
                            "Wrong login details.",
                            "UsernamePasswordAuthenticationToken",
                            "Check user credentials"
                    ),

                    createError(
                            "LockedException",
                            "Security",
                            "User account is locked and cannot authenticate.",
                            "User account is locked",
                            "Account disabled or locked by admin.",
                            "UserDetailsService",
                            "Unlock account"
                    ),

                    createError(
                            "DisabledException",
                            "Security",
                            "User account is disabled in authentication system.",
                            "User account is disabled",
                            "Account marked inactive.",
                            "UserDetails.isEnabled()",
                            "Enable account"
                    ),

                    createError(
                            "InsufficientAuthenticationException",
                            "Security",
                            "Authentication is missing or incomplete for accessing resource.",
                            "Full authentication required",
                            "Missing token or session.",
                            "Spring Security filter",
                            "Provide authentication token"
                    ),
                    createError(
                            "ApplicationStartedEventFailure",
                            "Spring Boot",
                            "Spring Boot application fails after context startup event.",
                            "Failure during ApplicationStartedEvent execution",
                            "Error in post-startup initialization logic.",
                            "@EventListener(ApplicationReadyEvent.class)",
                            "Fix startup event logic"
                    ),

                    createError(
                            "SpringBootAdminConnectionFailure",
                            "Spring Boot",
                            "Fails to register with Spring Boot Admin server.",
                            "Cannot connect to Spring Boot Admin",
                            "Wrong admin URL or network issue.",
                            "spring.boot.admin.client.url",
                            "Verify admin server configuration"
                    ),
                    createError(
                            "NoUniqueBeanDefinitionException",
                            "Spring Core",
                            "Multiple beans found when single bean was expected.",
                            "No qualifying bean of type found: expected single matching bean but found multiple",
                            "Two beans of same type exist in context.",
                            "@Autowired Service service;",
                            "Use @Qualifier or @Primary"
                    ),

                    createError(
                            "BeanNotOfRequiredTypeException",
                            "Spring Core",
                            "Bean type mismatch during injection.",
                            "Bean is not of required type",
                            "Wrong bean casting or definition.",
                            "ApplicationContext.getBean()",
                            "Fix bean type or interface mapping"
                    ),
                    createError(
                            "CannotCreateTransactionException",
                            "Database",
                            "Transaction creation fails due to DB connectivity issues.",
                            "Could not open JDBC Connection for transaction",
                            "Database unreachable during transaction start.",
                            "@Transactional service method",
                            "Check DB connection and pool"
                    ),

                    createError(
                            "InvalidResultSetAccessException",
                            "Database",
                            "Error while reading JDBC result set.",
                            "Invalid column index or metadata mismatch",
                            "Query result does not match expected mapping.",
                            "JdbcTemplate query",
                            "Fix column mapping"
                    ),
                    createError(
                            "TomcatConnectorStartFailure",
                            "Server",
                            "Embedded Tomcat fails to start.",
                            "Tomcat failed to start connector",
                            "Port conflict or invalid configuration.",
                            "server.port=8080",
                            "Change port or fix server config"
                    ),

                    createError(
                            "EmbeddedServerStartException",
                            "Server",
                            "Spring Boot embedded server fails to initialize.",
                            "Failed to start embedded web server",
                            "Configuration or dependency issue.",
                            "SpringApplication.run()",
                            "Fix server config or dependencies"
                    ),
                    createError(
                            "SessionAuthenticationException",
                            "Security",
                            "Session authentication fails in Spring Security.",
                            "Invalid or expired session",
                            "Session timeout or invalid session ID.",
                            "HttpSessionSecurityContextRepository",
                            "Re-authenticate user"
                    ),
                    createError(
                            "UnknownHttpStatusCodeException",
                            "HTTP Client",
                            "Received unknown HTTP status from external service.",
                            "Unknown status code from API response",
                            "Non-standard response from external API.",
                            "RestTemplate exchange",
                            "Handle unexpected response codes"
                    ),
                    createError(
                            "FileSizeLimitExceededException",
                            "File Upload",
                            "File exceeds server-defined size limit.",
                            "File size exceeds limit",
                            "Large upload rejected by server.",
                            "MultipartFile upload",
                            "Increase max file size limit"
                    )



           );

         repository.saveAll(errors);

          System.out.println("DebugMate errors inserted successfully.");
       }
    }

    private ErrorEntity createError(
            String name,
            String category,
            String shortDesc,
            String actualError,
            String cause,
            String example,
            String fix
    ) {

        ErrorDetail detail = ErrorDetail.builder()
                .actualError(actualError)
                .causeText(cause)
                .solutionText(shortDesc)
                .exampleCode(example)
                .fixCode(fix)
                .build();

        ErrorEntity error = ErrorEntity.builder()
                .errorName(name)
                .category(category)
                .shortDescription(shortDesc)
                .detail(detail)
                .build();

        detail.setError(error);

        return error;
    }
}