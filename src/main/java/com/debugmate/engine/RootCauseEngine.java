package com.debugmate.engine;

import com.debugmate.entity.ErrorDetail;

import java.util.List;

public class RootCauseEngine {

    public static List<String> extractRootCauses(
            ErrorFingerprint fp,
            ErrorDetail d
    ) {

        switch (fp.getErrorType()) {

            case "RoutingError":

                return List.of(
                        "Missing or incorrect @GetMapping/@PostMapping endpoint",
                        "Browser URL does not match controller mapping",
                        "Returned template/view file does not exist",
                        "HTTP method mismatch (GET vs POST)",
                        "Controller package not scanned by Spring Boot"
                );

            case "NullPointerException":

                return List.of(
                        "Object reference was null before method access",
                        "Dependency or variable not initialized",
                        "Repository/service returned null value",
                        "Missing null validation before object usage"
                );

            case "DependencyInjectionError":

                return List.of(
                        "Missing @Service/@Repository/@Component annotation",
                        "Bean package is outside component scan path",
                        "Circular dependency between Spring beans",
                        "Required bean could not be created during startup"
                );

            case "ApplicationContextError":

                return List.of(
                        "Spring failed during bean initialization",
                        "Invalid startup configuration",
                        "Circular dependency between beans",
                        "Datasource or external service misconfiguration",
                        "Bean creation exception during application startup"
                );

            case "PropertyBindingError":

                return List.of(
                        "Invalid datatype in application.properties",
                        "Incorrect property value format",
                        "Spring failed to bind property to configuration class",
                        "Property name mismatch in @ConfigurationProperties"
                );

            case "InvalidConfigurationProperty":

                return List.of(
                        "Invalid Spring Boot configuration property name",
                        "Deprecated or unsupported property used",
                        "Spelling mistake in application.properties",
                        "Configuration key does not exist in Spring Boot"
                );

            case "ConfigDataLocationError":

                return List.of(
                        "External configuration file path is invalid",
                        "Referenced config file does not exist",
                        "Spring failed to load imported config file",
                        "Incorrect spring.config.import configuration"
                );

            case "DatabaseError":

                return List.of(
                        "Datasource configuration is invalid",
                        "Database server is not running",
                        "Incorrect SQL query or table mapping",
                        "Invalid database credentials",
                        "JDBC driver dependency missing"
                );

            case "ServerPortError":
            case "PortConflictError":

                return List.of(
                        "Another application is already using the configured port",
                        "Embedded Tomcat failed during startup",
                        "Invalid server.port configuration",
                        "Server failed to bind to requested port"
                );

            case "PortBindingError":

                return List.of(
                        "Server does not have permission to bind port",
                        "Configured IP/port combination is invalid",
                        "Port already reserved by another process"
                );

            case "SpringBootAdminError":

                return List.of(
                        "Spring Boot Admin server is unreachable",
                        "Invalid admin server URL configuration",
                        "Application registration failed",
                        "Network/firewall issue blocked connection"
                );

            case "ValidationError":

                return List.of(
                        "DTO validation failed",
                        "Required request field is missing",
                        "Invalid request payload format",
                        "Bean Validation constraint violation occurred"
                );

            case "BindingError":

                return List.of(
                        "Request parameter datatype mismatch",
                        "Invalid path variable format",
                        "Incorrect query parameter value",
                        "Spring failed to convert request parameter"
                );

            case "SecurityError":

                return List.of(
                        "User authentication failed",
                        "User does not have required role/authority",
                        "JWT token is invalid or expired",
                        "Protected endpoint accessed without authorization"
                );

            default:

                return List.of(
                        fp.getRootHint(),
                        "Check application logs carefully",
                        "Analyze first application stacktrace frame"
                );
        }
    }
}