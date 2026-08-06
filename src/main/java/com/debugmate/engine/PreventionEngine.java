package com.debugmate.engine;

import java.util.ArrayList;
import java.util.List;

public class PreventionEngine {

    public static List<String> generatePreventiveSteps(
            ErrorFingerprint fp
    ) {

        List<String> steps = new ArrayList<>();

        switch (fp.getErrorType()) {

            // =====================================================
            // ROUTING
            // =====================================================
            case "RoutingError":

                steps.add("Verify all @GetMapping and @PostMapping routes");

                steps.add("Keep controller URLs consistent with frontend links");

                steps.add("Ensure Thymeleaf/JSP template files exist");

                steps.add("Create custom error pages instead of default Whitelabel page");

                steps.add("Add endpoint integration testing");

                break;

            // =====================================================
            // NULL POINTER
            // =====================================================
            case "NullPointerException":

                steps.add("Always validate objects before usage");

                steps.add("Use constructor injection instead of field injection");

                steps.add("Avoid nullable return values");

                steps.add("Use Optional where appropriate");

                break;

            // =====================================================
            // DEPENDENCY INJECTION
            // =====================================================
            case "DependencyInjectionError":

                steps.add("Use constructor injection for mandatory dependencies");

                steps.add("Ensure all beans are inside component scan packages");

                steps.add("Avoid circular dependencies");

                steps.add("Validate Spring bean creation during startup");

                break;

            // =====================================================
            // APPLICATION CONTEXT
            // =====================================================
            case "ApplicationContextError":

                steps.add("Keep startup configuration modular");

                steps.add("Validate all beans during startup");

                steps.add("Use profile-specific configuration");

                steps.add("Avoid unnecessary startup dependencies");

                break;

            // =====================================================
            // PROPERTY BINDING
            // =====================================================
            case "PropertyBindingError":

                steps.add("Use strongly typed @ConfigurationProperties");

                steps.add("Validate property values before deployment");

                steps.add("Avoid invalid datatype assignments");

                steps.add("Use default fallback values");

                break;

            // =====================================================
            // INVALID PROPERTY
            // =====================================================
            case "InvalidConfigurationProperty":

                steps.add("Validate configuration keys against Spring Boot documentation");

                steps.add("Avoid deprecated properties");

                steps.add("Use IDE Spring Boot property validation");

                break;

            // =====================================================
            // CONFIG LOCATION
            // =====================================================
            case "ConfigDataLocationError":

                steps.add("Verify external config paths before deployment");

                steps.add("Use optional config imports where appropriate");

                steps.add("Keep backup fallback configuration");

                break;

            // =====================================================
            // DATABASE
            // =====================================================
            case "DatabaseError":

                steps.add("Validate datasource credentials securely");

                steps.add("Use migration tools like Flyway or Liquibase");

                steps.add("Monitor database connectivity");

                steps.add("Test queries before production deployment");

                break;

            // =====================================================
            // SERVER PORT
            // =====================================================
            case "ServerPortError":
            case "PortConflictError":

                steps.add("Use environment-based configurable ports");

                steps.add("Monitor startup failures using health checks");

                steps.add("Validate port availability before startup");

                steps.add("Avoid hardcoded ports in production");

                break;

            // =====================================================
            // PORT BINDING
            // =====================================================
            case "PortBindingError":

                steps.add("Validate server binding configuration");

                steps.add("Use valid server.address and server.port values");

                steps.add("Monitor server startup logs");

                break;

            // =====================================================
            // SPRING BOOT ADMIN
            // =====================================================
            case "SpringBootAdminError":

                steps.add("Monitor admin server health");

                steps.add("Validate admin registration URLs");

                steps.add("Secure admin endpoints properly");

                break;

            // =====================================================
            // VALIDATION
            // =====================================================
            case "ValidationError":

                steps.add("Use Bean Validation annotations");

                steps.add("Validate request payloads before processing");

                steps.add("Handle validation exceptions globally");

                steps.add("Add API request validation tests");

                break;

            // =====================================================
            // BINDING ERROR
            // =====================================================
            case "BindingError":

                steps.add("Validate request parameter datatypes");

                steps.add("Use DTO objects instead of raw request parameters");

                steps.add("Add global exception handling for binding failures");

                steps.add("Validate path variables and query parameters");

                break;

            // =====================================================
            // SECURITY ERROR
            // =====================================================
            case "SecurityError":

                steps.add("Validate JWT tokens before accessing protected APIs");

                steps.add("Use proper role-based authorization");

                steps.add("Secure endpoints using Spring Security configuration");

                steps.add("Avoid exposing sensitive endpoints publicly");

                break;

            // =====================================================
            // DEFAULT
            // =====================================================
            default:

                steps.add("Enable structured logging");

                steps.add("Write integration and unit tests");

                steps.add("Validate application configuration regularly");
        }

        // =====================================================
        // COMMON BEST PRACTICES
        // =====================================================

        steps.add("Write unit tests for edge cases");

        steps.add("Enable structured logging for debugging");

        return steps;
    }
}