package com.example.demo.service;

import com.example.demo.dto.CustomerDTO; 

import java.util.List;

public interface CustomerService {
List<CustomerDTO> findAll();
List<CustomerDTO> findById(Integer id);
CustomerDTO updateById(Integer id, CustomerDTO customerDTO);
void deleteById(Integer id);
}
