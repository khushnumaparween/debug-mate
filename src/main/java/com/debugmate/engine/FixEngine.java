package com.debugmate.engine;

import com.debugmate.entity.ErrorDetail;

public class FixEngine {

    // =====================================================
    // QUICK FIX
    // =====================================================
    public static String generateQuickFix(
            ErrorFingerprint fp,
            ErrorDetail d
    ) {

        switch (fp.getErrorType()) {

            // =====================================================
            // NULL POINTER
            // =====================================================
            case "NullPointerException":
                return """
                        Object is being used before initialization.

                        Example issue:

                        User user = null;
                        user.getName();

                        Fix:

                        if(user != null) {
                            user.getName();
                        }

                        OR initialize object properly before usage.
                        """;

            // =====================================================
            // ROUTING / WHITELABEL
            // =====================================================
            case "RoutingError":
                return """
                        Spring could not resolve requested route.

                        Example controller:

                        @Controller
                        public class HomeController {

                            @GetMapping("/home")
                            public String home() {
                                return "home";
                            }
                        }

                        Verify:
                        - URL is correct
                        - HTTP method matches
                        - Template exists
                        - Controller package is scanned

                        URL:
                        http://localhost:8080/home

                        Template path:
                        src/main/resources/templates/home.html

                        Common issue:
                        Using @Controller instead of @RestController.
                        """;

            // =====================================================
            // DEPENDENCY INJECTION
            // =====================================================
            case "DependencyInjectionError":
                return """
                        Spring could not find required bean.

                        Example issue:

                        @Autowired
                        private UserService userService;

                        But service class:

                        public class UserService {
                        }

                        Fix:

                        @Service
                        public class UserService {
                        }

                        Verify:
                        - @Service/@Repository/@Component exists
                        - Package is scanned by Spring Boot
                        - Bean dependency exists

                        Common issue:
                        Bean class outside component scan path.
                        """;

            // =====================================================
            // APPLICATION CONTEXT
            // =====================================================
            case "ApplicationContextError":
                return """
                        Spring failed to initialize ApplicationContext.

                        Common causes:
                        - Bean creation failure
                        - Circular dependency
                        - Invalid datasource configuration
                        - Missing environment variable
                        - Failed configuration class

                        Example circular dependency:

                        @Service
                        class AService {
                            @Autowired
                            BService bService;
                        }

                        @Service
                        class BService {
                            @Autowired
                            AService aService;
                        }

                        Fix:
                        - Use constructor injection
                        - Remove circular dependencies

                        Debug tip:
                        Check FIRST "Caused by" section carefully.
                        """;

            // =====================================================
            // PROPERTY BINDING
            // =====================================================
            case "PropertyBindingError":
                return """
                        Spring failed to bind configuration properties.

                        Example issue:

                        app.timeout=abc

                        Java field:

                        private int timeout;

                        Correct:

                        app.timeout=30

                        Example configuration class:

                        @ConfigurationProperties(prefix = "app")
                        public class AppConfig {

                            private int timeout;
                        }

                        Verify:
                        - Correct datatype
                        - Matching property names
                        - Valid prefixes
                        - No spelling mistakes
                        """;

            // =====================================================
            // INVALID CONFIG PROPERTY
            // =====================================================
            case "InvalidConfigurationProperty":
                return """
                        Invalid Spring Boot property detected.

                        Example invalid property:

                        spring.datasource.user=root

                        Correct:

                        spring.datasource.username=root

                        Verify:
                        - Property names
                        - Deprecated properties
                        - Spring Boot version compatibility

                        Common issue:
                        Typo in property name.
                        """;

            // =====================================================
            // CONFIG DATA LOCATION
            // =====================================================
            case "ConfigDataLocationError":
                return """
                        Spring could not load external configuration.

                        Example:

                        spring.config.import=file:./config/app.properties

                        Fix:
                        - Verify file exists
                        - Verify file path
                        - Verify permissions

                        Safer approach:

                        spring.config.import=optional:file:./config/

                        Common issue:
                        Config file missing on deployment server.
                        """;

            // =====================================================
            // SERVER PORT
            // =====================================================
            case "ServerPortError":
                return """
                        Embedded Tomcat failed to start.

                        Example fix:

                        server.port=8081

                        Verify:
                        - Another application using same port
                        - Invalid server configuration
                        - Firewall restrictions

                        Common issue:
                        Port already occupied.
                        """;

            // =====================================================
            // PORT CONFLICT
            // =====================================================
            case "PortConflictError":
                return """
                        Another process is already using the port.

                        Windows:

                        netstat -ano | findstr :8080

                        Kill process:

                        taskkill /PID <PID> /F

                        OR change port:

                        server.port=8081
                        """;

            // =====================================================
            // PORT BINDING
            // =====================================================
            case "PortBindingError":
                return """
                        Server failed to bind to configured port.

                        Verify:
                        - Port permissions
                        - Port availability
                        - Invalid IP binding

                        Example:

                        server.address=0.0.0.0
                        server.port=8081
                        """;

            // =====================================================
            // DATABASE
            // =====================================================
            case "DatabaseError":
                return """
                        Database operation failed.

                        Verify:
                        - Database server is running
                        - Datasource URL is correct
                        - Credentials are valid
                        - JDBC driver exists

                        Example:

                        spring.datasource.url=jdbc:mysql://localhost:3306/debugmate
                        spring.datasource.username=root
                        spring.datasource.password=root

                        Common issues:
                        - Wrong SQL syntax
                        - Table not found
                        - Connection refused
                        - Entity mapping mismatch
                        """;

            // =====================================================
            // SPRING BOOT ADMIN
            // =====================================================
            case "SpringBootAdminError":
                return """
                        Spring Boot Admin client failed to connect.

                        Verify:
                        - Admin server is running
                        - Client URL is correct
                        - Actuator endpoints are enabled

                        Example:

                        spring.boot.admin.client.url=http://localhost:9090

                        management.endpoints.web.exposure.include=*

                        Common issue:
                        Admin server unavailable or blocked.
                        """;

            // =====================================================
            // VALIDATION
            // =====================================================
            case "ValidationError":
                return """
                        Request validation failed.

                        Example DTO:

                        public class UserDTO {

                            @NotBlank
                            private String name;
                        }

                        Example invalid JSON:

                        {
                            "name": ""
                        }

                        Correct JSON:

                        {
                            "name": "Khushnuma"
                        }

                        Verify:
                        - Required fields
                        - Validation annotations
                        - Request payload structure
                        """;

            // =====================================================
            // BINDING ERROR
            // =====================================================
            case "BindingError":
                return """
                        Spring failed to bind request parameters.

                        Example:

                        @GetMapping("/users")
                        public String users(
                                @RequestParam Integer page
                        )

                        Invalid request:

                        /users?page=abc

                        Correct request:

                        /users?page=1

                        Verify:
                        - Request parameter types
                        - Path variable formats
                        - Request body structure
                        """;

            // =====================================================
            // SECURITY ERROR
            // =====================================================
            case "SecurityError":
                return """
                        Access denied or authentication failed.

                        Verify:
                        - User authentication
                        - User roles and authorities
                        - Spring Security configuration

                        Example:

                        @PreAuthorize("hasRole('ADMIN')")

                        Common issue:
                        Missing required role or invalid token.
                        """;

            // =====================================================
            // DEFAULT
            // =====================================================
            default:

                return d != null
                        && d.getFixCode() != null
                        ? d.getFixCode()
                        : """
                          Check application logs carefully.

                          Focus on:
                          - FIRST exception
                          - FIRST "Caused by"
                          - Application stack frames
                          - Configuration errors
                          """;
        }
    }

    // =====================================================
    // SAFE FIX
    // =====================================================
    public static String generateSafeFix(
            ErrorFingerprint fp,
            ErrorDetail d
    ) {

        switch (fp.getErrorType()) {

            case "NullPointerException":
                return """
                        Use constructor injection,
                        Optional,
                        and defensive null validation.

                        Avoid nullable return values.
                        """;

            case "RoutingError":
                return """
                        Verify:
                        - Endpoint mappings
                        - Template existence
                        - HTTP methods
                        - Controller scan path

                        Add custom error pages
                        instead of default Whitelabel page.
                        """;

            case "DependencyInjectionError":
                return """
                        Use constructor injection
                        instead of field injection.

                        Ensure all beans are scanned properly.
                        """;

            case "ApplicationContextError":
                return """
                        Validate:
                        - Bean lifecycle
                        - Startup dependencies
                        - Datasource configuration
                        - Active Spring profiles
                        """;

            case "PropertyBindingError":
                return """
                        Use strongly typed
                        @ConfigurationProperties
                        with validation.
                        """;

            case "InvalidConfigurationProperty":
                return """
                        Validate all properties
                        against official Spring Boot documentation.
                        """;

            case "ConfigDataLocationError":
                return """
                        Use optional config imports
                        and profile-specific configuration files.
                        """;

            case "ServerPortError":
            case "PortConflictError":
                return """
                        Use configurable environment ports
                        and startup validation checks.
                        """;

            case "DatabaseError":
                return """
                        Use connection pooling,
                        parameterized queries,
                        and transaction management.
                        """;

            case "SpringBootAdminError":
                return """
                        Validate admin server availability,
                        service registration,
                        and actuator exposure.
                        """;

            case "ValidationError":
                return """
                        Use Bean Validation
                        with centralized exception handling.
                        """;

            case "BindingError":
                return """
                        Use DTO mapping
                        and explicit datatype validation.
                        """;

            case "SecurityError":
                return """
                        Use JWT validation,
                        proper role mapping,
                        and centralized security handling.
                        """;

            default:
                return """
                        Add validation layer,
                        structured logging,
                        and centralized exception handling.
                        """;
        }
    }

    // =====================================================
    // BEST PRACTICE FIX
    // =====================================================
    public static String generateBestPracticeFix(
            ErrorFingerprint fp
    ) {

        switch (fp.getErrorType()) {

            case "RoutingError":
                return """
                        Use centralized route management,
                        integration testing,
                        and custom error pages.

                        Example:
                        Create custom /error controller.
                        """;

            case "DependencyInjectionError":
                return """
                        Use constructor injection only.

                        Avoid circular dependencies
                        and field injection.
                        """;

            case "ApplicationContextError":
                return """
                        Keep startup configuration modular.

                        Separate:
                        - Database config
                        - Security config
                        - External service config
                        """;

            case "PropertyBindingError":
                return """
                        Use validated configuration classes
                        and environment profiles.
                        """;

            case "InvalidConfigurationProperty":
                return """
                        Validate properties during CI/CD
                        before deployment.
                        """;

            case "ConfigDataLocationError":
                return """
                        Use profile-aware configuration loading
                        with fallback defaults.
                        """;

            case "ServerPortError":
            case "PortConflictError":
                return """
                        Use environment-based port configuration
                        and startup health monitoring.
                        """;

            case "DatabaseError":
                return """
                        Use Flyway/Liquibase,
                        ORM validation,
                        and query performance testing.
                        """;

            case "SpringBootAdminError":
                return """
                        Use centralized observability,
                        metrics,
                        health checks,
                        and monitoring dashboards.
                        """;

            case "NullPointerException":
                return """
                        Design immutable objects
                        and avoid nullable flows.
                        """;

            case "ValidationError":
                return """
                        Use reusable validation rules
                        and centralized validation handling.
                        """;

            case "SecurityError":
                return """
                        Follow zero-trust security,
                        token validation,
                        and role-based access control.
                        """;

            default:

                return switch (fp.getLayer()) {

                    case "Controller" ->
                            """
                            Use DTO validation
                            and centralized exception handling.
                            """;

                    case "Service" ->
                            """
                            Apply defensive programming
                            and clean service separation.
                            """;

                    case "Repository" ->
                            """
                            Optimize queries
                            and validate entity mappings.
                            """;

                    default ->
                            """
                            Follow layered architecture,
                            logging standards,
                            and automated testing practices.
                            """;
                };
        }
    }
}