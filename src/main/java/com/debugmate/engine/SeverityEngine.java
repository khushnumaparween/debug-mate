package com.debugmate.engine;

import com.debugmate.entity.ErrorDetail;

public class SeverityEngine {

    public static String detectSeverity(
            ErrorFingerprint fp,
            ErrorDetail d
    ) {

        // =========================
        // CRITICAL SPRING ERRORS
        // =========================
        if ("DatabaseError".equals(fp.getErrorType())) {
            return "HIGH";
        }

        if ("DependencyInjectionError".equals(fp.getErrorType())) {
            return "HIGH";
        }

        if ("ServerPortError".equals(fp.getErrorType())) {
            return "HIGH";
        }

        if ("PortConflictError".equals(fp.getErrorType())) {
            return "HIGH";
        }

        if ("ConfigurationError".equals(fp.getErrorType())) {
            return "HIGH";
        }

        if ("ApplicationContextError".equals(fp.getErrorType())) {
            return "HIGH";
        }

        // =========================
        // MEDIUM SEVERITY
        // =========================
        if ("PropertyBindingError".equals(fp.getErrorType())) {
            return "MEDIUM";
        }

        if ("ValidationError".equals(fp.getErrorType())) {
            return "MEDIUM";
        }

        if ("BindingError".equals(fp.getErrorType())) {
            return "MEDIUM";
        }

        // =========================
        // NULL DETAIL
        // =========================
        if (d == null) {
            return "LOW";
        }

        // =========================
        // LARGE STACKTRACE
        // =========================
        if (d.getStacktraceText() != null
                && d.getStacktraceText().length() > 2000) {

            return "HIGH";
        }

        // =========================
        // NULL RELATED ISSUES
        // =========================
        if (d.getCauseText() != null
                && d.getCauseText()
                .toLowerCase()
                .contains("null")) {

            return "MEDIUM";
        }

        // =========================
        // DEFAULT
        // =========================
        return "LOW";
    }
}