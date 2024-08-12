package com.example.demo.controller;

import com.example.demo.dto.ActorDTO;
import com.example.demo.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("ACTOR")
public class ActorController {

      @Autowired
      private ActorService actorService;

      @GetMapping
      public List<ActorDTO> findAll() {
          return actorService.findAll();
      }
      @GetMapping("/{id}")
      public List<ActorDTO> findById(@PathVariable Integer id){
          List<ActorDTO> actor = actorService.findById(id);
          return actor.isEmpty() ? null : actor;
      }

      @PutMapping("/{id}")
      public ActorDTO updateById(@PathVariable Integer id, @RequestBody ActorDTO actorDTO) {
          return actorService.updateById(id, actorDTO);
      }

      @DeleteMapping("/{id}")
      public void deleteById(@PathVariable Integer id) {
          actorService.deleteById(id);
      }

}
