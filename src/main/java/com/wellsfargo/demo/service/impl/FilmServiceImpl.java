package com.example.demo.service.impl;

import com.example.demo.dto.FilmDTO;
import com.example.demo.entity.Film;
import com.example.demo.repository.FilmRepository;
import com.example.demo.service.FilmService;

import com.example.demo.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Collections;
import java.util.Optional;
@Service
public class FilmServiceImpl implements FilmService {

      @Autowired
      private FilmRepository filmRepository;

      @Autowired
      private InventoryRepository inventoryRepository;

      @Override
      public List<FilmDTO> findAll() {
          List<Film> film = filmRepository.findAll();
          return film.stream().map(FilmDTO::convertToDTO).collect(Collectors.toList());
      }

      @Override
      public List<FilmDTO> findById(Integer id){
          Optional<Film> film = filmRepository.findById(id);
          return film.map(f -> Collections.singletonList(FilmDTO.convertToDTO(f))).orElse(Collections.emptyList());
      }

      @Override
      public FilmDTO updateById(Integer id, FilmDTO filmDTO) {
          Optional<Film> optionalFilm = filmRepository.findById(id);
          if (optionalFilm.isPresent()) {
              Film film = optionalFilm.get();
              film = filmDTO.convertToEntity();
              film.setFilmId(id);
              filmRepository.save(film);
              return FilmDTO.convertToDTO(film);
          } else {
              return null;
          }
      }

      @Transactional
      @Override
      public void deleteById(Integer id) {
          Optional<Film> optionalFilm = filmRepository.findById(id);
          if (optionalFilm.isPresent()){
              Film film = optionalFilm.get();
              film.getInventory().forEach(inventory -> {
                  inventory.setFilmId(null);
                  inventoryRepository.save(inventory);
              });
              filmRepository.deleteById(id);
           } else { 
               throw new RuntimeException("Film not found");
           }
       }

}
