package com.example.demo.controller;

import com.example.demo.dto.RentalDTO;
import com.example.demo.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("RENTAL")
public class RentalController {

      @Autowired
      private RentalService rentalService;

      @GetMapping
      public List<RentalDTO> findAll() {
          return rentalService.findAll();
      }
      @GetMapping("/{id}")
      public List<RentalDTO> findById(@PathVariable Integer id){
          List<RentalDTO> rental = rentalService.findById(id);
          return rental.isEmpty() ? null : rental;
      }

      @PutMapping("/{id}")
      public RentalDTO updateById(@PathVariable Integer id, @RequestBody RentalDTO rentalDTO) {
          return rentalService.updateById(id, rentalDTO);
      }

      @DeleteMapping("/{id}")
      public void deleteById(@PathVariable Integer id) {
          rentalService.deleteById(id);
      }

}
