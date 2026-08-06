package com.debugmate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "error_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "actual_error")
    private String actualError;

    @Lob
    @Column(name = "cause_text")
    private String causeText;

    @Lob
    @Column(name = "solution_text")
    private String solutionText;

    @Lob
    @Column(name = "example_code")
    private String exampleCode;

    @Lob
    @Column(name = "fix_code")
    private String fixCode;

    @OneToOne
    @JoinColumn(name = "error_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ErrorEntity error;

    @Column(name = "debug_flow")
    private String debugFlow;

    @Column(name = "failure_point")
    private String failurePoint;

    @Lob
    @Column(name = "stacktrace_text")
    private String stacktraceText;

    @Column(length = 2000)
    private String stacktraceSummary;


}