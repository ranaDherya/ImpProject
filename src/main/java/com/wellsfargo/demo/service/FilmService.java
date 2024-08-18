package com.example.demo.service;

import com.example.demo.dto.FilmDTO; 

import java.util.List;

public interface FilmService {
List<FilmDTO> findAll();
List<FilmDTO> findById(Integer id);
FilmDTO updateById(Integer id, FilmDTO filmDTO);
void deleteById(Integer id);
FilmDTO insertFilm(FilmDTO filmDTO);
}
