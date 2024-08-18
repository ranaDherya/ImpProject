package com.example.demo.controller;

import com.example.demo.dto.AddressDTO;
import com.example.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("ADDRESS")
public class AddressController {

      @Autowired
      private AddressService addressService;

      @GetMapping
      public List<AddressDTO> findAll() {
          return addressService.findAll();
      }
      @GetMapping("/{id}")
      public List<AddressDTO> findById(@PathVariable Integer id){
          List<AddressDTO> address = addressService.findById(id);
          return address.isEmpty() ? null : address;
      }

      @PutMapping("/{id}")
      public AddressDTO updateById(@PathVariable Integer id, @RequestBody AddressDTO addressDTO) {
          return addressService.updateById(id, addressDTO);
      }

      @DeleteMapping("/{id}")
      public void deleteById(@PathVariable Integer id) {
          addressService.deleteById(id);
      }

      @PostMapping
      public AddressDTO insertAddress (@RequestBody AddressDTO addressDTO) {
          return addressService.insertAddress(addressDTO);
       }
}
