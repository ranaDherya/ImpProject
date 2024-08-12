package com.example.demo.dto;

import com.example.demo.entity.Payment;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentDTO {
      private Integer paymentId;
      private String amount;
      private String paymentDate;

      public PaymentDTO() {       }

      public static PaymentDTO convertToDTO(Payment payment) {
          PaymentDTO paymentDTO = new PaymentDTO();
          paymentDTO.setPaymentId(payment.getPaymentId());
          paymentDTO.setAmount(payment.getAmount());
          paymentDTO.setPaymentDate(payment.getPaymentDate());

          return paymentDTO;
       }

      public Payment convertToEntity() {
          Payment payment = new Payment();
          payment.setPaymentId(this.paymentId);
          payment.setAmount(this.amount);
          payment.setPaymentDate(this.paymentDate);
          return payment;
       }

public Integer getPaymentId() {
      return this.paymentId;
}

public void setPaymentId(Integer paymentId) {
      this.paymentId = paymentId;
}

public String getAmount() {
      return this.amount;
}

public void setAmount(String amount) {
      this.amount = amount;
}

public String getPaymentDate() {
      return this.paymentDate;
}

public void setPaymentDate(String paymentDate) {
      this.paymentDate = paymentDate;
}

}
