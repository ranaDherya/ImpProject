package com.example.demo.service.impl;

import com.example.demo.dto.FilmCategoryDTO;
import com.example.demo.entity.FilmCategory;
import com.example.demo.repository.FilmCategoryRepository;
import com.example.demo.service.FilmCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Collections;
import java.util.Optional;
import com.example.demo.entity.FilmCategoryId;

@Service
public class FilmCategoryServiceImpl implements FilmCategoryService {

      @Autowired
      private FilmCategoryRepository filmCategoryRepository;

      @Override
      public List<FilmCategoryDTO> findAll() {
          List<FilmCategory> filmCategory = filmCategoryRepository.findAll();
          return filmCategory.stream().map(FilmCategoryDTO::convertToDTO).collect(Collectors.toList());
      }

      @Override
      public List<FilmCategoryDTO> findById(FilmCategoryId id){
          Optional<FilmCategory> filmCategory = filmCategoryRepository.findById(id);
          return filmCategory.map(f -> Collections.singletonList(FilmCategoryDTO.convertToDTO(f))).orElse(Collections.emptyList());
      }

      @Override
      public FilmCategoryDTO updateById(FilmCategoryId id, FilmCategoryDTO filmCategoryDTO) {
          Optional<FilmCategory> optionalFilmCategory = filmCategoryRepository.findById(id);
          if (optionalFilmCategory.isPresent()) {
              FilmCategory filmCategory = optionalFilmCategory.get();
              filmCategory = filmCategoryDTO.convertToEntity();
              filmCategory.setFilmCategoryId(id);
              filmCategoryRepository.save(filmCategory);
              return FilmCategoryDTO.convertToDTO(filmCategory);
          } else {
              return null;
          }
      }

      @Transactional
      @Override
      public void deleteById(FilmCategoryId id) {
          Optional<FilmCategory> optionalFilmCategory = filmCategoryRepository.findById(id);
          if (optionalFilmCategory.isPresent()){
              filmCategoryRepository.deleteById(id);
           } else { 
               throw new RuntimeException("Film not found");
           }
       }

}
