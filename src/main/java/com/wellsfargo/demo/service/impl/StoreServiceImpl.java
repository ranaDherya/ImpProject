package com.example.demo.service.impl;

import com.example.demo.dto.StoreDTO;
import com.example.demo.entity.Store;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.StoreService;

import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Collections;
import java.util.Optional;
@Service
public class StoreServiceImpl implements StoreService {

      @Autowired
      private StoreRepository storeRepository;

      @Autowired
      private InventoryRepository inventoryRepository;

      @Autowired
      private CustomerRepository customerRepository;

      @Autowired
      private StaffRepository staffRepository;

      @Override
      public List<StoreDTO> findAll() {
          List<Store> store = storeRepository.findAll();
          return store.stream().map(StoreDTO::convertToDTO).collect(Collectors.toList());
      }

      @Override
      public List<StoreDTO> findById(Integer id){
          Optional<Store> store = storeRepository.findById(id);
          return store.map(f -> Collections.singletonList(StoreDTO.convertToDTO(f))).orElse(Collections.emptyList());
      }

      @Override
      public StoreDTO updateById(Integer id, StoreDTO storeDTO) {
          Optional<Store> optionalStore = storeRepository.findById(id);
          if (optionalStore.isPresent()) {
              Store store = optionalStore.get();
              store = storeDTO.convertToEntity();
              store.setStoreId(id);
              storeRepository.save(store);
              return StoreDTO.convertToDTO(store);
          } else {
              return null;
          }
      }

      @Transactional
      @Override
      public void deleteById(Integer id) {
          Optional<Store> optionalStore = storeRepository.findById(id);
          if (optionalStore.isPresent()){
              Store store = optionalStore.get();
              store.getInventory().forEach(inventory -> {
                  inventory.setStoreId(null);
                  inventoryRepository.save(inventory);
              });
              store.getCustomer().forEach(customer -> {
                  customer.setStoreId(null);
                  customerRepository.save(customer);
              });
              store.getStaff().forEach(staff -> {
                  staff.setStoreId(null);
                  staffRepository.save(staff);
              });
              storeRepository.deleteById(id);
           } else { 
               throw new RuntimeException("Film not found");
           }
       }

}
