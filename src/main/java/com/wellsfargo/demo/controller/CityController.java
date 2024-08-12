package com.example.demo.controller;

import com.example.demo.dto.CityDTO;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("CITY")
public class CityController {

      @Autowired
      private CityService cityService;

      @GetMapping
      public List<CityDTO> findAll() {
          return cityService.findAll();
      }
      @GetMapping("/{id}")
      public List<CityDTO> findById(@PathVariable Integer id){
          List<CityDTO> city = cityService.findById(id);
          return city.isEmpty() ? null : city;
      }

      @PutMapping("/{id}")
      public CityDTO updateById(@PathVariable Integer id, @RequestBody CityDTO cityDTO) {
          return cityService.updateById(id, cityDTO);
      }

      @DeleteMapping("/{id}")
      public void deleteById(@PathVariable Integer id) {
          cityService.deleteById(id);
      }

}
