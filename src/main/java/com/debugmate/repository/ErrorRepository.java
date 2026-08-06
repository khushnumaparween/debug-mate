package com.debugmate.repository;

import com.debugmate.entity.ErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ErrorRepository extends JpaRepository<ErrorEntity, Long> {

    List<ErrorEntity> findByErrorNameContainingIgnoreCase(String keyword);

    List<ErrorEntity> findByCategory(String category);

    // keyword search (general search)
    @Query("""
SELECT e FROM ErrorEntity e
JOIN e.detail d
WHERE LOWER(e.errorName) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(e.category) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(e.shortDescription) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR d.actualError LIKE CONCAT('%', :keyword, '%')
   OR d.causeText LIKE CONCAT('%', :keyword, '%')
   OR d.solutionText LIKE CONCAT('%', :keyword, '%')
""")
    List<ErrorEntity> searchByKeyword(@Param("keyword") String keyword);

    // stacktrace search (large logs safe search)
    @Query("""
SELECT e FROM ErrorEntity e
JOIN e.detail d
WHERE LOWER(e.errorName) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(e.category) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(e.shortDescription) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR d.actualError LIKE CONCAT('%', :keyword, '%')
   OR d.stacktraceText LIKE CONCAT('%', :keyword, '%')
   OR d.stacktraceSummary LIKE CONCAT('%', :keyword, '%')
""")
    List<ErrorEntity> searchByStacktrace(@Param("keyword") String keyword);
}