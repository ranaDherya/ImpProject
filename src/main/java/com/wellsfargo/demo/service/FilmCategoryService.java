package com.example.demo.service;

import com.example.demo.dto.FilmCategoryDTO; 

import java.util.List;

import com.example.demo.entity.FilmCategoryId;

public interface FilmCategoryService {
List<FilmCategoryDTO> findAll();
List<FilmCategoryDTO> findById(FilmCategoryId id);
FilmCategoryDTO updateById(FilmCategoryId id, FilmCategoryDTO filmCategoryDTO);
void deleteById(FilmCategoryId id);
}
