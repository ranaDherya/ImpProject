package com.example.demo.service.impl;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Collections;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {

      @Autowired
      private CategoryRepository categoryRepository;

      @Override
      public List<CategoryDTO> findAll() {
          List<Category> category = categoryRepository.findAll();
          return category.stream().map(CategoryDTO::convertToDTO).collect(Collectors.toList());
      }

      @Override
      public List<CategoryDTO> findById(Integer id){
          Optional<Category> category = categoryRepository.findById(id);
          return category.map(f -> Collections.singletonList(CategoryDTO.convertToDTO(f))).orElse(Collections.emptyList());
      }

      @Override
      public CategoryDTO updateById(Integer id, CategoryDTO categoryDTO) {
          Optional<Category> optionalCategory = categoryRepository.findById(id);
          if (optionalCategory.isPresent()) {
              Category category = optionalCategory.get();
              category = categoryDTO.convertToEntity();
              category.setCategoryId(id);
              categoryRepository.save(category);
              return CategoryDTO.convertToDTO(category);
          } else {
              return null;
          }
      }

      @Transactional
      @Override
      public void deleteById(Integer id) {
          Optional<Category> optionalCategory = categoryRepository.findById(id);
          if (optionalCategory.isPresent()){
              categoryRepository.deleteById(id);
           } else { 
               throw new RuntimeException("Film not found");
           }
       }

      @Override
      public CategoryDTO insertCategory (CategoryDTO categoryDTO) {
          Category category = categoryDTO.convertToEntity();
          Category savedCategory = categoryRepository.save(category);
          return CategoryDTO.convertToDTO(savedCategory);
      }

}
