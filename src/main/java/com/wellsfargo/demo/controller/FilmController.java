package com.example.demo.controller;

import com.example.demo.dto.FilmDTO;
import com.example.demo.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("FILM")
public class FilmController {

      @Autowired
      private FilmService filmService;

      @GetMapping
      public List<FilmDTO> findAll() {
          return filmService.findAll();
      }
      @GetMapping("/{id}")
      public List<FilmDTO> findById(@PathVariable Integer id){
          List<FilmDTO> film = filmService.findById(id);
          return film.isEmpty() ? null : film;
      }

      @PutMapping("/{id}")
      public FilmDTO updateById(@PathVariable Integer id, @RequestBody FilmDTO filmDTO) {
          return filmService.updateById(id, filmDTO);
      }

      @DeleteMapping("/{id}")
      public void deleteById(@PathVariable Integer id) {
          filmService.deleteById(id);
      }

      @PostMapping
      public FilmDTO insertFilm (@RequestBody FilmDTO filmDTO) {
          return filmService.insertFilm(filmDTO);
       }
}
