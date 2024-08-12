package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
