package com.example.demo.service;

import com.example.demo.dto.StoreDTO; 

import java.util.List;

public interface StoreService {
List<StoreDTO> findAll();
List<StoreDTO> findById(Integer id);
StoreDTO updateById(Integer id, StoreDTO storeDTO);
void deleteById(Integer id);
StoreDTO insertStore(StoreDTO storeDTO);
}
