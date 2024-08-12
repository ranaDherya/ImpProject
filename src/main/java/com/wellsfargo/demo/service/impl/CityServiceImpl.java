package com.example.demo.service.impl;

import com.example.demo.dto.CityDTO;
import com.example.demo.entity.City;
import com.example.demo.repository.CityRepository;
import com.example.demo.service.CityService;

import com.example.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Collections;
import java.util.Optional;
@Service
public class CityServiceImpl implements CityService {

      @Autowired
      private CityRepository cityRepository;

      @Autowired
      private AddressRepository addressRepository;

      @Override
      public List<CityDTO> findAll() {
          List<City> city = cityRepository.findAll();
          return city.stream().map(CityDTO::convertToDTO).collect(Collectors.toList());
      }

      @Override
      public List<CityDTO> findById(Integer id){
          Optional<City> city = cityRepository.findById(id);
          return city.map(f -> Collections.singletonList(CityDTO.convertToDTO(f))).orElse(Collections.emptyList());
      }

      @Override
      public CityDTO updateById(Integer id, CityDTO cityDTO) {
          Optional<City> optionalCity = cityRepository.findById(id);
          if (optionalCity.isPresent()) {
              City city = optionalCity.get();
              city = cityDTO.convertToEntity();
              city.setCityId(id);
              cityRepository.save(city);
              return CityDTO.convertToDTO(city);
          } else {
              return null;
          }
      }

      @Transactional
      @Override
      public void deleteById(Integer id) {
          Optional<City> optionalCity = cityRepository.findById(id);
          if (optionalCity.isPresent()){
              City city = optionalCity.get();
              city.getAddress().forEach(address -> {
                  address.setCityId(null);
                  addressRepository.save(address);
              });
              cityRepository.deleteById(id);
           } else { 
               throw new RuntimeException("Film not found");
           }
       }

}
