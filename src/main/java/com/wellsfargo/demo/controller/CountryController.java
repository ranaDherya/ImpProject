package com.example.demo.controller;

import com.example.demo.dto.CountryDTO;
import com.example.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("COUNTRY")
public class CountryController {

      @Autowired
      private CountryService countryService;

      @GetMapping
      public List<CountryDTO> findAll() {
          return countryService.findAll();
      }
      @GetMapping("/{id}")
      public List<CountryDTO> findById(@PathVariable Integer id){
          List<CountryDTO> country = countryService.findById(id);
          return country.isEmpty() ? null : country;
      }

      @PutMapping("/{id}")
      public CountryDTO updateById(@PathVariable Integer id, @RequestBody CountryDTO countryDTO) {
          return countryService.updateById(id, countryDTO);
      }

      @DeleteMapping("/{id}")
      public void deleteById(@PathVariable Integer id) {
          countryService.deleteById(id);
      }

}
