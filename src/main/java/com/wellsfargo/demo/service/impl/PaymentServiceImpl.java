package com.example.demo.service.impl;

import com.example.demo.dto.PaymentDTO;
import com.example.demo.entity.Payment;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Collections;
import java.util.Optional;
@Service
public class PaymentServiceImpl implements PaymentService {

      @Autowired
      private PaymentRepository paymentRepository;

      @Override
      public List<PaymentDTO> findAll() {
          List<Payment> payment = paymentRepository.findAll();
          return payment.stream().map(PaymentDTO::convertToDTO).collect(Collectors.toList());
      }

      @Override
      public List<PaymentDTO> findById(Integer id){
          Optional<Payment> payment = paymentRepository.findById(id);
          return payment.map(f -> Collections.singletonList(PaymentDTO.convertToDTO(f))).orElse(Collections.emptyList());
      }

      @Override
      public PaymentDTO updateById(Integer id, PaymentDTO paymentDTO) {
          Optional<Payment> optionalPayment = paymentRepository.findById(id);
          if (optionalPayment.isPresent()) {
              Payment payment = optionalPayment.get();
              payment = paymentDTO.convertToEntity();
              payment.setPaymentId(id);
              paymentRepository.save(payment);
              return PaymentDTO.convertToDTO(payment);
          } else {
              return null;
          }
      }

      @Transactional
      @Override
      public void deleteById(Integer id) {
          Optional<Payment> optionalPayment = paymentRepository.findById(id);
          if (optionalPayment.isPresent()){
              paymentRepository.deleteById(id);
           } else { 
               throw new RuntimeException("Film not found");
           }
       }

}
