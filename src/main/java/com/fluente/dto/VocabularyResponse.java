package com.fluente.dto;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyResponse {
    private Integer id;
    private String word;
    private String translation;
    private String pronunciation;
    private String context;
    private String userEmail; // Just include the email, not the full user object
} 