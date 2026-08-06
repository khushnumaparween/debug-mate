package com.debugmate.controller;

import com.debugmate.dto.FixResponseDTO;
import com.debugmate.entity.ErrorDetail;
import com.debugmate.entity.ErrorEntity;
import com.debugmate.repository.ErrorDetailRepository;
import com.debugmate.repository.ErrorRepository;
import com.debugmate.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;




@Controller
@RequiredArgsConstructor
public class ErrorController {

    private final ErrorRepository repository;

    private final ErrorService service;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/category/{category}")
    public String getByCategory(@PathVariable String category, Model model) {

        model.addAttribute("errors", repository.findByCategory(category));
        model.addAttribute("category", category);

        return "category";
    }


    @GetMapping("/search")
    public String search(@RequestParam(required = false) String keyword, Model model) {

        if (keyword == null || keyword.isBlank()) {
            model.addAttribute("errors", List.of());
            model.addAttribute("keyword", "");
            return "results";
        }

        List<ErrorEntity> results = repository.searchByKeyword(keyword);
        List<ErrorEntity> stackResults = repository.searchByStacktrace(keyword);

        Set<ErrorEntity> combined = new LinkedHashSet<>();
        combined.addAll(results);
        combined.addAll(stackResults);

        model.addAttribute("errors", combined);
        model.addAttribute("keyword", keyword);

        return "results";
    }


    @Autowired
    private ErrorDetailRepository detailRepository;


@GetMapping("/error/{id}")
public String getErrorDetails(@PathVariable Long id, Model model) {

    ErrorDetail detail = detailRepository.findByError_Id(id);

    List<String> flowSteps = List.of();

    if (detail.getDebugFlow() != null &&
            !detail.getDebugFlow().isBlank()) {

        flowSteps = Arrays.asList(detail.getDebugFlow().split(","));
    }

    // Determine which step should be highlighted
    String failedStep = getFailedStep(detail.getFailurePoint());

    model.addAttribute("detail", detail);
    model.addAttribute("flowSteps", flowSteps);
    model.addAttribute("failedStep", failedStep);

    return "error-details";
}








    //fix code
    @GetMapping("/fix/{id}")
    public String getFix(@PathVariable Long id, Model model) {

        ErrorEntity error = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error not found"));

        FixResponseDTO fix = service.generateFix(error);

        model.addAttribute("fix", fix);

        return "fix-details";
    }


//for highlighting the failure point accurately

    private String getFailedStep(String failurePoint) {

        if (failurePoint == null) return "";

        String fp = failurePoint.toLowerCase();

        // Multipart
        if (fp.contains("multipart") || fp.contains("upload") || fp.contains("file")) {
            return "Multipart Resolver";
        }

        // JSON / Serialization
        if (fp.contains("json") || fp.contains("jackson")) {
            return "JSON Parser";
        }

        // Controller Layer
        if (fp.contains("controller")) {
            return "Controller";
        }

        // Service Layer
        if (fp.contains("service")) {
            return "Service";
        }

        // Repository / JPA
        if (fp.contains("repository")
                || fp.contains("jpa")
                || fp.contains("entity manager")
                || fp.contains("transaction")) {
            return "Repository";
        }

        // Hibernate
        if (fp.contains("hibernate")) {
            return "Hibernate";
        }

        // Database
        if (fp.contains("database")
                || fp.contains("sql")
                || fp.contains("jdbc")
                || fp.contains("dialect")
                || fp.contains("datasource")) {
            return "Database";
        }

        // Security
        if (fp.contains("security filter")
                || fp.contains("jwt")
                || fp.contains("csrf")
                || fp.contains("authorization")) {
            return "Security Filter";
        }

        if (fp.contains("authentication")) {
            return "Authentication";
        }

        // HTTP Client
        if (fp.contains("resttemplate")
                || fp.contains("webclient")
                || fp.contains("feign")
                || fp.contains("http client")) {
            return "HTTP Client";
        }

        // HTTP
        if (fp.contains("http")) {
            return "HTTP";
        }

        // Build Tool
        if (fp.contains("maven")
                || fp.contains("gradle")
                || fp.contains("build")) {
            return "Build Tool";
        }

        // Compilation
        if (fp.contains("compiler")
                || fp.contains("compilation")
                || fp.contains("classpath")
                || fp.contains("indexing")) {
            return "Compiler";
        }

        // View Layer
        if (fp.contains("template")
                || fp.contains("thymeleaf")
                || fp.contains("view")) {
            return "Template Engine";
        }

        // Web
        if (fp.contains("resource")
                || fp.contains("static")) {
            return "Response";
        }

        // Server
        if (fp.contains("server")
                || fp.contains("tomcat")
                || fp.contains("port")) {
            return "Server";
        }

        // Generic request
        if (fp.contains("request")) {
            return "Request";
        }



        // HTTP Client
        if (fp.contains("resttemplate")
                || fp.contains("webclient")
                || fp.contains("feign")
                || fp.contains("http client")) {
            return "RestTemplate/WebClient";
        }

// HTTP
        if (fp.contains("http")) {
            return "HTTP";
        }

// Build Tool
        if (fp.contains("maven")
                || fp.contains("gradle")
                || fp.contains("build")) {
            return "Build Tool";
        }

// Compilation
        if (fp.contains("compiler")
                || fp.contains("compilation")
                || fp.contains("classpath")
                || fp.contains("indexing")) {
            return "Compiler";
        }

// View Layer
        if (fp.contains("template")
                || fp.contains("thymeleaf")
                || fp.contains("view")) {
            return "Template Engine";
        }

// Web
        if (fp.contains("resource")
                || fp.contains("static")
                || fp.contains("web")) {
            return "Response";
        }

// Server
        if (fp.contains("server")
                || fp.contains("tomcat")
                || fp.contains("port")) {
            return "Server";
        }

// Generic request
        if (fp.contains("request")) {
            return "Request";
        }

        return failurePoint;

    }
}