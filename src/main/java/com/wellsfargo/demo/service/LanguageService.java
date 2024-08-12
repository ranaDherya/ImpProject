package com.example.demo.service;

import com.example.demo.dto.LanguageDTO; 

import java.util.List;

public interface LanguageService {
List<LanguageDTO> findAll();
List<LanguageDTO> findById(Integer id);
LanguageDTO updateById(Integer id, LanguageDTO languageDTO);
void deleteById(Integer id);
}
