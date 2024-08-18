package com.example.demo.service;

import com.example.demo.dto.InventoryDTO; 

import java.util.List;

public interface InventoryService {
List<InventoryDTO> findAll();
List<InventoryDTO> findById(Integer id);
InventoryDTO updateById(Integer id, InventoryDTO inventoryDTO);
void deleteById(Integer id);
InventoryDTO insertInventory(InventoryDTO inventoryDTO);
}
