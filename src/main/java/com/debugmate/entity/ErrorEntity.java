package com.debugmate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "errors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "error_name", nullable = false)
    private String errorName;

    @Column(nullable = false)
    private String category;

    @Column(name = "short_description", length = 1000)
    private String shortDescription;

    @OneToOne(mappedBy = "error", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ErrorDetail detail;
}