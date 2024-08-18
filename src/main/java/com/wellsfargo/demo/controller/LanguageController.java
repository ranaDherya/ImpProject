package com.example.demo.controller;

import com.example.demo.dto.LanguageDTO;
import com.example.demo.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("LANGUAGE")
public class LanguageController {

      @Autowired
      private LanguageService languageService;

      @GetMapping
      public List<LanguageDTO> findAll() {
          return languageService.findAll();
      }
      @GetMapping("/{id}")
      public List<LanguageDTO> findById(@PathVariable Integer id){
          List<LanguageDTO> language = languageService.findById(id);
          return language.isEmpty() ? null : language;
      }

      @PutMapping("/{id}")
      public LanguageDTO updateById(@PathVariable Integer id, @RequestBody LanguageDTO languageDTO) {
          return languageService.updateById(id, languageDTO);
      }

      @DeleteMapping("/{id}")
      public void deleteById(@PathVariable Integer id) {
          languageService.deleteById(id);
      }

      @PostMapping
      public LanguageDTO insertLanguage (@RequestBody LanguageDTO languageDTO) {
          return languageService.insertLanguage(languageDTO);
       }
}
