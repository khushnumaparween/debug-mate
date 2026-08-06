package com.debugmate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class FixResponseDTO {

    private String errorName;

    private String errorType; // Runtime, Compilation, Spring, DB, etc.

    private String severity; // LOW, MEDIUM, HIGH, BLOCKER

    private String quickFix;

    private String safeFix;

    private String bestPracticeFix;

    private List<String> rootCauses;

    private List<String> preventiveSteps;
}
