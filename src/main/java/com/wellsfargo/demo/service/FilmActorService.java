package com.example.demo.service;

import com.example.demo.dto.FilmActorDTO; 

import java.util.List;

import com.example.demo.entity.FilmActorId;

public interface FilmActorService {
List<FilmActorDTO> findAll();
List<FilmActorDTO> findById(FilmActorId id);
FilmActorDTO updateById(FilmActorId id, FilmActorDTO filmActorDTO);
void deleteById(FilmActorId id);
FilmActorDTO insertFilmActor(FilmActorDTO filmActorDTO);
}
