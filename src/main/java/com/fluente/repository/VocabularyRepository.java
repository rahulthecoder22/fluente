package com.fluente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fluente.model.User;
import com.fluente.model.Vocabulary;

public interface VocabularyRepository extends JpaRepository<Vocabulary , Integer> {

    //finds the vocabulary by the user
    List<Vocabulary> findByUser(User user);

    //Find by  word
    List<Vocabulary> findByWordContainingIgnoreCase(String word);

    
     
}
