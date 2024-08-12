package com.example.demo.controller;

import com.example.demo.dto.StoreDTO;
import com.example.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("STORE")
public class StoreController {

      @Autowired
      private StoreService storeService;

      @GetMapping
      public List<StoreDTO> findAll() {
          return storeService.findAll();
      }
      @GetMapping("/{id}")
      public List<StoreDTO> findById(@PathVariable Integer id){
          List<StoreDTO> store = storeService.findById(id);
          return store.isEmpty() ? null : store;
      }

      @PutMapping("/{id}")
      public StoreDTO updateById(@PathVariable Integer id, @RequestBody StoreDTO storeDTO) {
          return storeService.updateById(id, storeDTO);
      }

      @DeleteMapping("/{id}")
      public void deleteById(@PathVariable Integer id) {
          storeService.deleteById(id);
      }

}
