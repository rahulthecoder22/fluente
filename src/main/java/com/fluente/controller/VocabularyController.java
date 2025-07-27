package com.fluente.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.extern.slf4j.Slf4j;

import com.fluente.dto.VocabularyRequest;
import com.fluente.dto.VocabularyResponse;
import com.fluente.model.User;
import com.fluente.model.Vocabulary;
import com.fluente.service.VocabularyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vocabulary")
@RequiredArgsConstructor
@Slf4j
public class VocabularyController {

    private final VocabularyService vocabularyService;

    @PostMapping
    public ResponseEntity<VocabularyResponse> addVocabulary(
        @RequestBody VocabularyRequest request,
        @AuthenticationPrincipal User user
    ) {
        log.info("Received vocabulary request: {}", request);
        log.info("User: {}", user);
        
        // Validate required fields
        if (request.getWord() == null || request.getWord().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        if (request.getTranslation() == null || request.getTranslation().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        Vocabulary vocabulary = Vocabulary.builder()
                .word(request.getWord().trim())
                .translation(request.getTranslation().trim())
                .pronunciation(request.getPronunciation() != null ? request.getPronunciation().trim() : null)
                .context(request.getContext() != null ? request.getContext().trim() : null)
                .user(user)
                .build();

        Vocabulary savedVocabulary = vocabularyService.saveWord(vocabulary);
        
        VocabularyResponse response = VocabularyResponse.builder()
                .id(savedVocabulary.getId())
                .word(savedVocabulary.getWord())
                .translation(savedVocabulary.getTranslation())
                .pronunciation(savedVocabulary.getPronunciation())
                .context(savedVocabulary.getContext())
                .userEmail(user.getEmail())
                .build();

        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<List<VocabularyResponse>> getAllVocabulary(
            @AuthenticationPrincipal User user
    ) {
        log.info("Getting vocabulary for user: {}", user.getEmail());
        List<Vocabulary> vocabularyList = vocabularyService.getVocabularyByUser(user);
        log.info("Found {} vocabulary entries for user", vocabularyList.size());
        
        List<VocabularyResponse> responses = vocabularyList.stream()
            .map(vocab -> VocabularyResponse.builder()
                .id(vocab.getId())
                .word(vocab.getWord())
                .translation(vocab.getTranslation())
                .pronunciation(vocab.getPronunciation())
                .context(vocab.getContext())
                .userEmail(user.getEmail())
                .build())
            .toList();
            
        return ResponseEntity.ok(responses);

    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Vocabulary endpoint is working!");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("Error in VocabularyController: ", e);
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }

    
}
