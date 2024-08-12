package com.example.demo.service;

import com.example.demo.dto.PaymentDTO; 

import java.util.List;

public interface PaymentService {
List<PaymentDTO> findAll();
List<PaymentDTO> findById(Integer id);
PaymentDTO updateById(Integer id, PaymentDTO paymentDTO);
void deleteById(Integer id);
}
