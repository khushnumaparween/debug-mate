package com.debugmate.dto;

import lombok.*;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseDTO {

    private Long id;
    private String errorName;
    private String category;
    private String shortDescription;

    private String actualError;
    private String causeText;
    private String solutionText;
    private String exampleCode;
    private String fixCode;

    private String stacktraceText;



    private String quickFix;
    private String safeFix;
    private String bestPracticeFix;


}
