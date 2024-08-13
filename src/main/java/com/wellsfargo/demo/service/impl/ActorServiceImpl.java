package com.example.demo.service.impl;

import com.example.demo.dto.ActorDTO;
import com.example.demo.entity.Actor;
import com.example.demo.repository.ActorRepository;
import com.example.demo.service.ActorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Collections;
import java.util.Optional;
@Service
public class ActorServiceImpl implements ActorService {

      @Autowired
      private ActorRepository actorRepository;

      @Override
      public List<ActorDTO> findAll() {
          List<Actor> actor = actorRepository.findAll();
          return actor.stream().map(ActorDTO::convertToDTO).collect(Collectors.toList());
      }

      @Override
      public List<ActorDTO> findById(Integer id){
          Optional<Actor> actor = actorRepository.findById(id);
          return actor.map(f -> Collections.singletonList(ActorDTO.convertToDTO(f))).orElse(Collections.emptyList());
      }

      @Override
      public ActorDTO updateById(Integer id, ActorDTO actorDTO) {
          Optional<Actor> optionalActor = actorRepository.findById(id);
          if (optionalActor.isPresent()) {
              Actor actor = optionalActor.get();
              actor = actorDTO.convertToEntity();
              actor.setActorId(id);
              actorRepository.save(actor);
              return ActorDTO.convertToDTO(actor);
          } else {
              return null;
          }
      }

      @Transactional
      @Override
      public void deleteById(Integer id) {
          Optional<Actor> optionalActor = actorRepository.findById(id);
          if (optionalActor.isPresent()){
              actorRepository.deleteById(id);
           } else { 
               throw new RuntimeException("Film not found");
           }
       }

}
