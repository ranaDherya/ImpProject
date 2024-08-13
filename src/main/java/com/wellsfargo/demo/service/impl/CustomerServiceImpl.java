package com.example.demo.service.impl;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Collections;
import java.util.Optional;
@Service
public class CustomerServiceImpl implements CustomerService {

      @Autowired
      private CustomerRepository customerRepository;

      @Override
      public List<CustomerDTO> findAll() {
          List<Customer> customer = customerRepository.findAll();
          return customer.stream().map(CustomerDTO::convertToDTO).collect(Collectors.toList());
      }

      @Override
      public List<CustomerDTO> findById(Integer id){
          Optional<Customer> customer = customerRepository.findById(id);
          return customer.map(f -> Collections.singletonList(CustomerDTO.convertToDTO(f))).orElse(Collections.emptyList());
      }

      @Override
      public CustomerDTO updateById(Integer id, CustomerDTO customerDTO) {
          Optional<Customer> optionalCustomer = customerRepository.findById(id);
          if (optionalCustomer.isPresent()) {
              Customer customer = optionalCustomer.get();
              customer = customerDTO.convertToEntity();
              customer.setCustomerId(id);
              customerRepository.save(customer);
              return CustomerDTO.convertToDTO(customer);
          } else {
              return null;
          }
      }

      @Transactional
      @Override
      public void deleteById(Integer id) {
          Optional<Customer> optionalCustomer = customerRepository.findById(id);
          if (optionalCustomer.isPresent()){
              customerRepository.deleteById(id);
           } else { 
               throw new RuntimeException("Film not found");
           }
       }

}
