package com.fluente.dto;

import lombok.Data;

@Data
public class VocabularyRequest {
    private String word;
    private String translation;
    private String pronunciation; // Optional pronunciation
    private String context; // Optional sentence or phrase
}
