package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.FilmActor;

import com.example.demo.entity.FilmActorId;

public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {
}
