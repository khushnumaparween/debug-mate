package com.debugmate.engine;

import lombok.*;

        import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorFingerprint {

    private String errorType;
    private String layer;
    private String rootHint;
    private List<String> keywords;
}