package com.debugmate.service;

import com.debugmate.dto.ErrorResponseDTO;
import com.debugmate.dto.FixResponseDTO;
import com.debugmate.engine.*;
import com.debugmate.entity.ErrorDetail;
import com.debugmate.entity.ErrorEntity;
import com.debugmate.repository.ErrorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ErrorService {

    private final ErrorRepository repository;

    // =====================================================
    // MAIN FIX GENERATOR
    // =====================================================
    public FixResponseDTO generateFix(ErrorEntity error) {

        ErrorDetail d = error.getDetail();

        ErrorFingerprint fp =
                StacktraceAnalyzer.analyze(
                        error.getErrorName(),
                        d
                );

        return FixResponseDTO.builder()
                .errorName(error.getErrorName())
                .errorType(fp.getErrorType())
                .severity(
                        SeverityEngine.detectSeverity(fp, d)
                )
                .quickFix(
                        FixEngine.generateQuickFix(fp, d)
                )
                .safeFix(
                        FixEngine.generateSafeFix(fp, d)
                )
                .bestPracticeFix(
                        FixEngine.generateBestPracticeFix(fp)
                )
                .rootCauses(
                        RootCauseEngine.extractRootCauses(fp, d)
                )
                .preventiveSteps(
                        PreventionEngine.generatePreventiveSteps(fp)
                )
                .build();
    }

    // =====================================================
    // SEARCH ERRORS
    // =====================================================
    public List<ErrorResponseDTO> searchErrors(
            String keyword
    ) {

        return repository
                .findByErrorNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // =====================================================
    // GET CATEGORY
    // =====================================================
    public List<ErrorResponseDTO> getByCategory(
            String category
    ) {

        return repository
                .findByCategory(category)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // =====================================================
    // GET ALL
    // =====================================================
    public List<ErrorResponseDTO> getAllErrors() {

        return repository
                .findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // =====================================================
    // DTO CONVERTER
    // =====================================================
    private ErrorResponseDTO toDTO(
            ErrorEntity error
    ) {

        ErrorDetail d = error.getDetail();

        return ErrorResponseDTO.builder()

                .id(error.getId())

                .errorName(error.getErrorName())

                .category(error.getCategory())

                .shortDescription(
                        error.getShortDescription()
                )

                .actualError(
                        d != null ? d.getActualError() : null
                )

                .causeText(
                        d != null ? d.getCauseText() : null
                )

                .solutionText(
                        d != null ? d.getSolutionText() : null
                )

                .exampleCode(
                        d != null ? d.getExampleCode() : null
                )

                .fixCode(
                        d != null ? d.getFixCode() : null
                )

                .stacktraceText(
                        d != null ? d.getStacktraceText() : null
                )

                .build();
    }

    // =====================================================
    // SAVE / UPDATE
    // =====================================================
    public void saveOrUpdate(
            ErrorResponseDTO dto
    ) {

        ErrorEntity error;

        if (dto.getId() != null) {

            error = repository.findById(dto.getId())
                    .orElse(new ErrorEntity());

        } else {

            error = new ErrorEntity();
        }

        error.setErrorName(dto.getErrorName());

        error.setCategory(dto.getCategory());

        error.setShortDescription(
                dto.getShortDescription()
        );

        ErrorDetail detail = error.getDetail();

        if (detail == null) {

            detail = new ErrorDetail();
        }

        detail.setActualError(dto.getActualError());

        detail.setCauseText(dto.getCauseText());

        detail.setSolutionText(dto.getSolutionText());

        detail.setExampleCode(dto.getExampleCode());

        detail.setFixCode(dto.getFixCode());

        detail.setStacktraceText(
                dto.getStacktraceText()
        );

        detail.setError(error);

        error.setDetail(detail);

        repository.save(error);
    }
}