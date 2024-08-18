package com.example.demo.controller;

import com.example.demo.dto.FilmActorDTO;
import com.example.demo.service.FilmActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.demo.entity.FilmActorId;

@RestController
@RequestMapping("FILM_ACTOR")
public class FilmActorController {

      @Autowired
      private FilmActorService filmActorService;

      @GetMapping
      public List<FilmActorDTO> findAll() {
          return filmActorService.findAll();
      }
      @GetMapping("/{actorId}/{filmId}")
      public List<FilmActorDTO> findById(@PathVariable Integer actorId, @PathVariable Integer filmId){
          FilmActorId id = new FilmActorId(actorId, filmId);
          List<FilmActorDTO> filmActor = filmActorService.findById(id);
          return filmActor.isEmpty() ? null : filmActor;
      }

      @PutMapping("/{actorId}/{filmId}")
      public FilmActorDTO updateById(@PathVariable Integer actorId, @PathVariable Integer filmId,  @RequestBody FilmActorDTO filmActorDTO) {
          FilmActorId id = new FilmActorId(actorId, filmId);
          return filmActorService.updateById(id, filmActorDTO);
      }

      @DeleteMapping("/{actorId}/{filmId}")
      public void deleteById(@PathVariable Integer actorId, @PathVariable Integer filmId) {
          FilmActorId id = new FilmActorId(actorId, filmId);
          filmActorService.deleteById(id);
      }

      @PostMapping
      public FilmActorDTO insertFilmActor (@RequestBody FilmActorDTO filmActorDTO) {
          return filmActorService.insertFilmActor(filmActorDTO);
       }
}
