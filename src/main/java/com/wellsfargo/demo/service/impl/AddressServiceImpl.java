package com.example.demo.service.impl;

import com.example.demo.dto.AddressDTO;
import com.example.demo.entity.Address;
import com.example.demo.repository.AddressRepository;
import com.example.demo.service.AddressService;

import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.StaffRepository;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Collections;
import java.util.Optional;
@Service
public class AddressServiceImpl implements AddressService {

      @Autowired
      private AddressRepository addressRepository;

      @Autowired
      private CustomerRepository customerRepository;

      @Autowired
      private StaffRepository staffRepository;

      @Autowired
      private StoreRepository storeRepository;

      @Override
      public List<AddressDTO> findAll() {
          List<Address> address = addressRepository.findAll();
          return address.stream().map(AddressDTO::convertToDTO).collect(Collectors.toList());
      }

      @Override
      public List<AddressDTO> findById(Integer id){
          Optional<Address> address = addressRepository.findById(id);
          return address.map(f -> Collections.singletonList(AddressDTO.convertToDTO(f))).orElse(Collections.emptyList());
      }

      @Override
      public AddressDTO updateById(Integer id, AddressDTO addressDTO) {
          Optional<Address> optionalAddress = addressRepository.findById(id);
          if (optionalAddress.isPresent()) {
              Address address = optionalAddress.get();
              address = addressDTO.convertToEntity();
              address.setAddressId(id);
              addressRepository.save(address);
              return AddressDTO.convertToDTO(address);
          } else {
              return null;
          }
      }

      @Transactional
      @Override
      public void deleteById(Integer id) {
          Optional<Address> optionalAddress = addressRepository.findById(id);
          if (optionalAddress.isPresent()){
              Address address = optionalAddress.get();
              address.getCustomer().forEach(customer -> {
                  customer.setAddressId(null);
                  customerRepository.save(customer);
              });
              address.getStaff().forEach(staff -> {
                  staff.setAddressId(null);
                  staffRepository.save(staff);
              });
              address.getStore().forEach(store -> {
                  store.setAddressId(null);
                  storeRepository.save(store);
              });
              addressRepository.deleteById(id);
           } else { 
               throw new RuntimeException("Film not found");
           }
       }

}
