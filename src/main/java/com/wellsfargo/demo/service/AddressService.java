package com.example.demo.service;

import com.example.demo.dto.AddressDTO; 

import java.util.List;

public interface AddressService {
List<AddressDTO> findAll();
List<AddressDTO> findById(Integer id);
AddressDTO updateById(Integer id, AddressDTO addressDTO);
void deleteById(Integer id);
}
