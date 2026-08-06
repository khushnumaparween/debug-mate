package com.debugmate.repository;

import com.debugmate.entity.ErrorDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorDetailRepository extends JpaRepository<ErrorDetail, Long> {

    ErrorDetail findByError_Id(Long id);
}
