package com.example.demo.service.impl;

import com.example.demo.dto.FilmActorDTO;
import com.example.demo.entity.FilmActor;
import com.example.demo.repository.FilmActorRepository;
import com.example.demo.service.FilmActorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Collections;
import java.util.Optional;
import com.example.demo.entity.FilmActorId;

@Service
public class FilmActorServiceImpl implements FilmActorService {

      @Autowired
      private FilmActorRepository filmActorRepository;

      @Override
      public List<FilmActorDTO> findAll() {
          List<FilmActor> filmActor = filmActorRepository.findAll();
          return filmActor.stream().map(FilmActorDTO::convertToDTO).collect(Collectors.toList());
      }

      @Override
      public List<FilmActorDTO> findById(FilmActorId id){
          Optional<FilmActor> filmActor = filmActorRepository.findById(id);
          return filmActor.map(f -> Collections.singletonList(FilmActorDTO.convertToDTO(f))).orElse(Collections.emptyList());
      }

      @Override
      public FilmActorDTO updateById(FilmActorId id, FilmActorDTO filmActorDTO) {
          Optional<FilmActor> optionalFilmActor = filmActorRepository.findById(id);
          if (optionalFilmActor.isPresent()) {
              FilmActor filmActor = optionalFilmActor.get();
              filmActor = filmActorDTO.convertToEntity();
              filmActor.setFilmActorId(id);
              filmActorRepository.save(filmActor);
              return FilmActorDTO.convertToDTO(filmActor);
          } else {
              return null;
          }
      }

      @Transactional
      @Override
      public void deleteById(FilmActorId id) {
          Optional<FilmActor> optionalFilmActor = filmActorRepository.findById(id);
          if (optionalFilmActor.isPresent()){
              filmActorRepository.deleteById(id);
           } else { 
               throw new RuntimeException("Film not found");
           }
       }

      @Override
      public FilmActorDTO insertFilmActor (FilmActorDTO filmActorDTO) {
          FilmActor filmActor = filmActorDTO.convertToEntity();
          FilmActor savedFilmActor = filmActorRepository.save(filmActor);
          return FilmActorDTO.convertToDTO(savedFilmActor);
      }

}
