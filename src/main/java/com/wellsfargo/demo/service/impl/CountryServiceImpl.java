package com.example.demo.service.impl;

import com.example.demo.dto.CountryDTO;
import com.example.demo.entity.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Collections;
import java.util.Optional;
@Service
public class CountryServiceImpl implements CountryService {

      @Autowired
      private CountryRepository countryRepository;

      @Override
      public List<CountryDTO> findAll() {
          List<Country> country = countryRepository.findAll();
          return country.stream().map(CountryDTO::convertToDTO).collect(Collectors.toList());
      }

      @Override
      public List<CountryDTO> findById(Integer id){
          Optional<Country> country = countryRepository.findById(id);
          return country.map(f -> Collections.singletonList(CountryDTO.convertToDTO(f))).orElse(Collections.emptyList());
      }

      @Override
      public CountryDTO updateById(Integer id, CountryDTO countryDTO) {
          Optional<Country> optionalCountry = countryRepository.findById(id);
          if (optionalCountry.isPresent()) {
              Country country = optionalCountry.get();
              country = countryDTO.convertToEntity();
              country.setCountryId(id);
              countryRepository.save(country);
              return CountryDTO.convertToDTO(country);
          } else {
              return null;
          }
      }

      @Transactional
      @Override
      public void deleteById(Integer id) {
          Optional<Country> optionalCountry = countryRepository.findById(id);
          if (optionalCountry.isPresent()){
              countryRepository.deleteById(id);
           } else { 
               throw new RuntimeException("Film not found");
           }
       }

}
