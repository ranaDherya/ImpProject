package com.example.demo.controller;

import com.example.demo.dto.PaymentDTO;
import com.example.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("PAYMENT")
public class PaymentController {

      @Autowired
      private PaymentService paymentService;

      @GetMapping
      public List<PaymentDTO> findAll() {
          return paymentService.findAll();
      }
      @GetMapping("/{id}")
      public List<PaymentDTO> findById(@PathVariable Integer id){
          List<PaymentDTO> payment = paymentService.findById(id);
          return payment.isEmpty() ? null : payment;
      }

      @PutMapping("/{id}")
      public PaymentDTO updateById(@PathVariable Integer id, @RequestBody PaymentDTO paymentDTO) {
          return paymentService.updateById(id, paymentDTO);
      }

      @DeleteMapping("/{id}")
      public void deleteById(@PathVariable Integer id) {
          paymentService.deleteById(id);
      }

      @PostMapping
      public PaymentDTO insertPayment (@RequestBody PaymentDTO paymentDTO) {
          return paymentService.insertPayment(paymentDTO);
       }
}
