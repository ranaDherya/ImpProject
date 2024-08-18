package com.example.demo.service.impl;

import com.example.demo.dto.LanguageDTO;
import com.example.demo.entity.Language;
import com.example.demo.repository.LanguageRepository;
import com.example.demo.service.LanguageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Collections;
import java.util.Optional;
@Service
public class LanguageServiceImpl implements LanguageService {

      @Autowired
      private LanguageRepository languageRepository;

      @Override
      public List<LanguageDTO> findAll() {
          List<Language> language = languageRepository.findAll();
          return language.stream().map(LanguageDTO::convertToDTO).collect(Collectors.toList());
      }

      @Override
      public List<LanguageDTO> findById(Integer id){
          Optional<Language> language = languageRepository.findById(id);
          return language.map(f -> Collections.singletonList(LanguageDTO.convertToDTO(f))).orElse(Collections.emptyList());
      }

      @Override
      public LanguageDTO updateById(Integer id, LanguageDTO languageDTO) {
          Optional<Language> optionalLanguage = languageRepository.findById(id);
          if (optionalLanguage.isPresent()) {
              Language language = optionalLanguage.get();
              language = languageDTO.convertToEntity();
              language.setLanguageId(id);
              languageRepository.save(language);
              return LanguageDTO.convertToDTO(language);
          } else {
              return null;
          }
      }

      @Transactional
      @Override
      public void deleteById(Integer id) {
          Optional<Language> optionalLanguage = languageRepository.findById(id);
          if (optionalLanguage.isPresent()){
              languageRepository.deleteById(id);
           } else { 
               throw new RuntimeException("Film not found");
           }
       }

      @Override
      public LanguageDTO insertLanguage (LanguageDTO languageDTO) {
          Language language = languageDTO.convertToEntity();
          Language savedLanguage = languageRepository.save(language);
          return LanguageDTO.convertToDTO(savedLanguage);
      }

}
