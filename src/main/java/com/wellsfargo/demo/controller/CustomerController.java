package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("CUSTOMER")
public class CustomerController {

      @Autowired
      private CustomerService customerService;

      @GetMapping
      public List<CustomerDTO> findAll() {
          return customerService.findAll();
      }
      @GetMapping("/{id}")
      public List<CustomerDTO> findById(@PathVariable Integer id){
          List<CustomerDTO> customer = customerService.findById(id);
          return customer.isEmpty() ? null : customer;
      }

      @PutMapping("/{id}")
      public CustomerDTO updateById(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
          return customerService.updateById(id, customerDTO);
      }

      @DeleteMapping("/{id}")
      public void deleteById(@PathVariable Integer id) {
          customerService.deleteById(id);
      }

}
