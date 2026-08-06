package com.debugmate.engine;

import com.debugmate.entity.ErrorDetail;

import java.util.ArrayList;
import java.util.List;

public class StacktraceAnalyzer {

    public static ErrorFingerprint analyze(
            String errorName,
            ErrorDetail detail
    ) {

        String stack =
                detail != null
                        && detail.getStacktraceText() != null
                        ? detail.getStacktraceText()
                        : "";

        String safeErrorName =
                errorName != null
                        ? errorName
                        : "";

        String layer = detectLayer(stack);

        String type = detectType(
                safeErrorName,
                stack
        );

        String rootHint =
                detectRootHint(stack);

        List<String> keywords =
                extractKeywords(stack);

        return ErrorFingerprint.builder()
                .errorType(type)
                .layer(layer)
                .rootHint(rootHint)
                .keywords(keywords)
                .build();
    }

    // =====================================================
    // DETECT SPRING LAYER
    // =====================================================
    private static String detectLayer(
            String stack
    ) {

        if (stack.isBlank()) {
            return "Unknown";
        }

        String lower =
                stack.toLowerCase();

        if (lower.contains(".controller")) {
            return "Controller";
        }

        if (lower.contains(".service")) {
            return "Service";
        }

        if (lower.contains(".repository")) {
            return "Repository";
        }

        if (lower.contains("securityfilterchain")
                || lower.contains("filterchain")) {

            return "Security Filter";
        }

        if (lower.contains("dispatcherservlet")) {
            return "Spring MVC";
        }

        if (lower.contains("hibernate")) {
            return "Persistence";
        }

        return "Application";
    }

    // =====================================================
    // DETECT ERROR TYPE
    // =====================================================
    private static String detectType(
            String errorName,
            String stack
    ) {

        String combined =
                (errorName + " " + stack)
                        .toLowerCase();

        // =====================================================
        // NULL POINTER
        // =====================================================
        if (combined.contains("nullpointerexception")) {
            return "NullPointerException";
        }

        // =====================================================
        // VALIDATION
        // =====================================================
        if (combined.contains("methodargumentnotvalidexception")
                || combined.contains("constraintviolationexception")) {

            return "ValidationError";
        }

        // =====================================================
        // REQUEST BINDING
        // =====================================================
        if (combined.contains("conversionfailedexception")
                || combined.contains("methodargumenttypemismatchexception")
                || combined.contains("httpmessagenotreadableexception")) {

            return "BindingError";
        }

        // =====================================================
        // DEPENDENCY INJECTION
        // =====================================================
        if (combined.contains("nosuchbeandefinitionexception")
                || combined.contains("unsatisfieddependencyexception")
                || combined.contains("beancreationexception")) {

            return "DependencyInjectionError";
        }

        // =====================================================
        // APPLICATION CONTEXT
        // =====================================================
        if (combined.contains("failed to load applicationcontext")
                || combined.contains("applicationcontextexception")) {

            return "ApplicationContextError";
        }

        // =====================================================
        // PROPERTY BINDING
        // =====================================================
        if (combined.contains("failed to bind properties")
                || combined.contains("property binding failure")
                || combined.contains("configurationpropertiesbindexception")) {

            return "PropertyBindingError";
        }

        // =====================================================
        // INVALID CONFIG PROPERTY
        // =====================================================
        if (combined.contains("invalid configuration property")) {

            return "InvalidConfigurationProperty";
        }

        // =====================================================
        // CONFIG LOCATION
        // =====================================================
        if (combined.contains("configdatalocationnotfoundexception")) {

            return "ConfigDataLocationError";
        }

        // =====================================================
        // DATABASE / JDBC / HIBERNATE
        // =====================================================
        if (combined.contains("sql")
                || combined.contains("jdbc")
                || combined.contains("hibernateexception")
                || combined.contains("dataintegrityviolationexception")
                || combined.contains("transactionexception")
                || combined.contains("lazyinitializationexception")) {

            return "DatabaseError";
        }

        // =====================================================
        // ROUTING
        // =====================================================
        if (combined.contains("404")
                || combined.contains("whitelabel")
                || combined.contains("noroute")
                || combined.contains("templatenotfound")
                || combined.contains("templatedoesnotexist")
                || combined.contains("templatedinputexception")
                || combined.contains("noresourcefoundexception")
                || combined.contains("httprequestmethodnotsupportedexception")) {

            return "RoutingError";
        }

        // =====================================================
        // SECURITY
        // =====================================================
        if (combined.contains("accessdeniedexception")
                || combined.contains("jwtexception")
                || combined.contains("authenticationexception")) {

            return "SecurityError";
        }

        // =====================================================
        // SERVER PORT
        // =====================================================
        if (combined.contains("tomcatconnectorstartfailure")) {

            return "ServerPortError";
        }

        // =====================================================
        // PORT CONFLICT
        // =====================================================
        if (combined.contains("port already in use")) {

            return "PortConflictError";
        }

        // =====================================================
        // PORT BINDING
        // =====================================================
        if (combined.contains("bindexception")) {

            return "PortBindingError";
        }

        // =====================================================
        // SPRING BOOT ADMIN
        // =====================================================
        if (combined.contains("springbootadmin")
                || combined.contains("applicationstartedeventfailure")
                || combined.contains("admin connection")) {

            return "SpringBootAdminError";
        }

        // =====================================================
        // CLASS LOADING
        // =====================================================
        if (combined.contains("classnotfoundexception")
                || combined.contains("noclassdeffounderror")) {

            return "ClassLoadingError";
        }

        // =====================================================
        // DEFAULT
        // =====================================================
        return "GeneralError";
    }

    // =====================================================
    // DETECT ROOT HINT
    // =====================================================
    private static String detectRootHint(
            String stack
    ) {

        if (stack.isBlank()) {
            return "No stacktrace available";
        }

        String lower =
                stack.toLowerCase();

        // =====================================================
        // NULL POINTER
        // =====================================================
        if (lower.contains("nullpointerexception")) {
            return "Object was used before initialization";
        }

        // =====================================================
        // VALIDATION
        // =====================================================
        if (lower.contains("methodargumentnotvalidexception")) {
            return "DTO validation failed";
        }

        if (lower.contains("constraintviolationexception")) {
            return "Bean validation constraint failed";
        }

        // =====================================================
        // BINDING
        // =====================================================
        if (lower.contains("conversionfailedexception")) {
            return "Request parameter type conversion failed";
        }

        if (lower.contains("httpmessagenotreadableexception")) {
            return "Request JSON structure is invalid";
        }

        // =====================================================
        // DEPENDENCY INJECTION
        // =====================================================
        if (lower.contains("nosuchbeandefinitionexception")) {
            return "Required Spring bean is missing";
        }

        if (lower.contains("unsatisfieddependencyexception")) {
            return "Spring failed to inject dependency";
        }

        if (lower.contains("beancreationexception")) {
            return "Spring bean creation failed";
        }

        // =====================================================
        // APPLICATION CONTEXT
        // =====================================================
        if (lower.contains("failed to load applicationcontext")) {
            return "Spring ApplicationContext failed during startup";
        }

        // =====================================================
        // PROPERTY BINDING
        // =====================================================
        if (lower.contains("failed to bind properties")) {
            return "Spring failed to bind configuration properties";
        }

        // =====================================================
        // INVALID PROPERTY
        // =====================================================
        if (lower.contains("invalid configuration property")) {
            return "Invalid Spring Boot configuration property";
        }

        // =====================================================
        // CONFIG LOCATION
        // =====================================================
        if (lower.contains("configdatalocationnotfoundexception")) {
            return "External configuration file could not be found";
        }

        // =====================================================
        // ROUTING
        // =====================================================
        if (lower.contains("dispatcherservlet")) {
            return "Invalid controller mapping or request route";
        }

        if (lower.contains("httprequestmethodnotsupportedexception")) {
            return "HTTP method does not match endpoint mapping";
        }

        // =====================================================
        // DATABASE
        // =====================================================
        if (lower.contains("sqlsyntaxerrorexception")) {
            return "SQL syntax is invalid";
        }

        if (lower.contains("dataintegrityviolationexception")) {
            return "Database integrity constraint failed";
        }

        if (lower.contains("lazyinitializationexception")) {
            return "Hibernate lazy-loaded entity accessed outside session";
        }

        // =====================================================
        // SERVER
        // =====================================================
        if (lower.contains("tomcatconnectorstartfailure")) {
            return "Embedded Tomcat failed during startup";
        }

        if (lower.contains("port already in use")) {
            return "Another application is already using the port";
        }

        if (lower.contains("bindexception")) {
            return "Server failed to bind to configured port";
        }

        // =====================================================
        // SECURITY
        // =====================================================
        if (lower.contains("accessdeniedexception")) {
            return "User lacks required authorization";
        }

        if (lower.contains("jwtexception")) {
            return "JWT token validation failed";
        }

        // =====================================================
        // SPRING BOOT ADMIN
        // =====================================================
        if (lower.contains("springbootadmin")) {
            return "Spring Boot Admin connection failure";
        }

        // =====================================================
        // CLASS LOADING
        // =====================================================
        if (lower.contains("classnotfoundexception")) {
            return "Required class dependency is missing";
        }

        // =====================================================
        // DEFAULT
        // =====================================================
        return "Analyze first application stack frame";
    }

    // =====================================================
    // EXTRACT KEYWORDS
    // =====================================================
    private static List<String> extractKeywords(
            String stack
    ) {

        List<String> list =
                new ArrayList<>();

        if (stack.isBlank()) {
            return list;
        }

        String lower =
                stack.toLowerCase();

        // =====================================================
        // NULL
        // =====================================================
        if (lower.contains("nullpointerexception")) {
            list.add("null safety");
        }

        // =====================================================
        // DATABASE
        // =====================================================
        if (lower.contains("sql")
                || lower.contains("jdbc")) {

            list.add("database");
        }

        // =====================================================
        // SPRING MVC
        // =====================================================
        if (lower.contains("dispatcherservlet")) {
            list.add("spring mvc");
        }

        // =====================================================
        // TYPE CONVERSION
        // =====================================================
        if (lower.contains("conversionfailedexception")) {
            list.add("type conversion");
        }

        // =====================================================
        // VALIDATION
        // =====================================================
        if (lower.contains("validation")
                || lower.contains("constraint")) {

            list.add("dto validation");
        }

        // =====================================================
        // SECURITY
        // =====================================================
        if (lower.contains("security")
                || lower.contains("jwt")) {

            list.add("authentication");
        }

        // =====================================================
        // APPLICATION CONTEXT
        // =====================================================
        if (lower.contains("applicationcontext")) {
            list.add("application context");
        }

        // =====================================================
        // CONFIGURATION
        // =====================================================
        if (lower.contains("configurationproperties")) {
            list.add("configuration");
        }

        // =====================================================
        // SPRING BOOT ADMIN
        // =====================================================
        if (lower.contains("spring boot admin")) {
            list.add("monitoring");
        }

        // =====================================================
        // PROPERTY BINDING
        // =====================================================
        if (lower.contains("bind")) {
            list.add("property binding");
        }

        // =====================================================
        // TOMCAT
        // =====================================================
        if (lower.contains("tomcat")) {
            list.add("embedded server");
        }

        // =====================================================
        // HIBERNATE
        // =====================================================
        if (lower.contains("hibernate")) {
            list.add("hibernate");
        }

        return list;
    }
}