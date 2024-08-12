package com.example.demo.service.impl;

import com.example.demo.dto.RentalDTO;
import com.example.demo.entity.Rental;
import com.example.demo.repository.RentalRepository;
import com.example.demo.service.RentalService;

import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Collections;
import java.util.Optional;
@Service
public class RentalServiceImpl implements RentalService {

      @Autowired
      private RentalRepository rentalRepository;

      @Autowired
      private PaymentRepository paymentRepository;

      @Override
      public List<RentalDTO> findAll() {
          List<Rental> rental = rentalRepository.findAll();
          return rental.stream().map(RentalDTO::convertToDTO).collect(Collectors.toList());
      }

      @Override
      public List<RentalDTO> findById(Integer id){
          Optional<Rental> rental = rentalRepository.findById(id);
          return rental.map(f -> Collections.singletonList(RentalDTO.convertToDTO(f))).orElse(Collections.emptyList());
      }

      @Override
      public RentalDTO updateById(Integer id, RentalDTO rentalDTO) {
          Optional<Rental> optionalRental = rentalRepository.findById(id);
          if (optionalRental.isPresent()) {
              Rental rental = optionalRental.get();
              rental = rentalDTO.convertToEntity();
              rental.setRentalId(id);
              rentalRepository.save(rental);
              return RentalDTO.convertToDTO(rental);
          } else {
              return null;
          }
      }

      @Transactional
      @Override
      public void deleteById(Integer id) {
          Optional<Rental> optionalRental = rentalRepository.findById(id);
          if (optionalRental.isPresent()){
              Rental rental = optionalRental.get();
              rental.getPayment().forEach(payment -> {
                  payment.setRentalId(null);
                  paymentRepository.save(payment);
              });
              rentalRepository.deleteById(id);
           } else { 
               throw new RuntimeException("Film not found");
           }
       }

}
