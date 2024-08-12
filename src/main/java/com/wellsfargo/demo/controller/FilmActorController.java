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
      @GetMapping("/{id}")
      public List<FilmActorDTO> findById(@PathVariable FilmActorId id){
          List<FilmActorDTO> filmActor = filmActorService.findById(id);
          return filmActor.isEmpty() ? null : filmActor;
      }

      @PutMapping("/{id}")
      public FilmActorDTO updateById(@PathVariable FilmActorId id, @RequestBody FilmActorDTO filmActorDTO) {
          return filmActorService.updateById(id, filmActorDTO);
      }

      @DeleteMapping("/{id}")
      public void deleteById(@PathVariable FilmActorId id) {
          filmActorService.deleteById(id);
      }

}
