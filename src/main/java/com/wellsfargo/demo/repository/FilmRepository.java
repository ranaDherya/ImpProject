package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Film;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
