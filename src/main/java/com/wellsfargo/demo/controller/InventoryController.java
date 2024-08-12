package com.example.demo.controller;

import com.example.demo.dto.InventoryDTO;
import com.example.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("INVENTORY")
public class InventoryController {

      @Autowired
      private InventoryService inventoryService;

      @GetMapping
      public List<InventoryDTO> findAll() {
          return inventoryService.findAll();
      }
      @GetMapping("/{id}")
      public List<InventoryDTO> findById(@PathVariable Integer id){
          List<InventoryDTO> inventory = inventoryService.findById(id);
          return inventory.isEmpty() ? null : inventory;
      }

      @PutMapping("/{id}")
      public InventoryDTO updateById(@PathVariable Integer id, @RequestBody InventoryDTO inventoryDTO) {
          return inventoryService.updateById(id, inventoryDTO);
      }

      @DeleteMapping("/{id}")
      public void deleteById(@PathVariable Integer id) {
          inventoryService.deleteById(id);
      }

}
