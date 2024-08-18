package com.example.demo.controller;

import com.example.demo.dto.FilmCategoryDTO;
import com.example.demo.service.FilmCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.demo.entity.FilmCategoryId;

@RestController
@RequestMapping("FILM_CATEGORY")
public class FilmCategoryController {

      @Autowired
      private FilmCategoryService filmCategoryService;

      @GetMapping
      public List<FilmCategoryDTO> findAll() {
          return filmCategoryService.findAll();
      }
      @GetMapping("/{filmId}/{categoryId}")
      public List<FilmCategoryDTO> findById(@PathVariable Integer filmId, @PathVariable Integer categoryId){
          FilmCategoryId id = new FilmCategoryId(filmId, categoryId);
          List<FilmCategoryDTO> filmCategory = filmCategoryService.findById(id);
          return filmCategory.isEmpty() ? null : filmCategory;
      }

      @PutMapping("/{filmId}/{categoryId}")
      public FilmCategoryDTO updateById(@PathVariable Integer filmId, @PathVariable Integer categoryId,  @RequestBody FilmCategoryDTO filmCategoryDTO) {
          FilmCategoryId id = new FilmCategoryId(filmId, categoryId);
          return filmCategoryService.updateById(id, filmCategoryDTO);
      }

      @DeleteMapping("/{filmId}/{categoryId}")
      public void deleteById(@PathVariable Integer filmId, @PathVariable Integer categoryId) {
          FilmCategoryId id = new FilmCategoryId(filmId, categoryId);
          filmCategoryService.deleteById(id);
      }

      @PostMapping
      public FilmCategoryDTO insertFilmCategory (@RequestBody FilmCategoryDTO filmCategoryDTO) {
          return filmCategoryService.insertFilmCategory(filmCategoryDTO);
       }
}
