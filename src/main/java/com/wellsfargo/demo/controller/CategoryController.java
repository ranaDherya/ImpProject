package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("CATEGORY")
public class CategoryController {

      @Autowired
      private CategoryService categoryService;

      @GetMapping
      public List<CategoryDTO> findAll() {
          return categoryService.findAll();
      }
      @GetMapping("/{id}")
      public List<CategoryDTO> findById(@PathVariable Integer id){
          List<CategoryDTO> category = categoryService.findById(id);
          return category.isEmpty() ? null : category;
      }

      @PutMapping("/{id}")
      public CategoryDTO updateById(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
          return categoryService.updateById(id, categoryDTO);
      }

      @DeleteMapping("/{id}")
      public void deleteById(@PathVariable Integer id) {
          categoryService.deleteById(id);
      }

}
