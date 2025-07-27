package com.fluente.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fluente.model.User;
import com.fluente.model.Vocabulary;
import com.fluente.repository.VocabularyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VocabularyService {

    private final VocabularyRepository vocabularyRepository;

    //Save a new word entry
    public Vocabulary saveWord(Vocabulary vocabulary) {
        return vocabularyRepository.save(vocabulary);
    }

    // Get all vocabulary entries for a specific user
    public List<Vocabulary> getVocabularyByUser(User user) {
        return vocabularyRepository.findByUser(user);
    }
    
    // (Optional for later) Get vocab by ID
    public Vocabulary getWordById(Integer id) {
        return vocabularyRepository.findById(id).orElse(null);
    }

    // (Optional for later) Delete word    
    public void deleteWord(Integer id) {
        vocabularyRepository.deleteById(id);
    }




}
