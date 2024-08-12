package com.example.demo.service;

import com.example.demo.dto.CategoryDTO; 

import java.util.List;

public interface CategoryService {
List<CategoryDTO> findAll();
List<CategoryDTO> findById(Integer id);
CategoryDTO updateById(Integer id, CategoryDTO categoryDTO);
void deleteById(Integer id);
}
