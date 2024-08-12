package com.example.demo.service;

import com.example.demo.dto.ActorDTO; 

import java.util.List;

public interface ActorService {
List<ActorDTO> findAll();
List<ActorDTO> findById(Integer id);
ActorDTO updateById(Integer id, ActorDTO actorDTO);
void deleteById(Integer id);
}
