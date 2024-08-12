package com.example.demo.dto;

import com.example.demo.entity.Rental;
import java.util.List;
import java.util.stream.Collectors;

public class RentalDTO {
      private Integer rentalId;
      private String rentalDate;
      private String returnDate;
      private String lastUpdate;
      private List<PaymentDTO> payment;

      public RentalDTO() {       }

      public static RentalDTO convertToDTO(Rental rental) {
          RentalDTO rentalDTO = new RentalDTO();
          rentalDTO.setRentalId(rental.getRentalId());
          rentalDTO.setRentalDate(rental.getRentalDate());
          rentalDTO.setReturnDate(rental.getReturnDate());
          rentalDTO.setLastUpdate(rental.getLastUpdate());

          List<PaymentDTO> paymentDTO = rental.getPayment().stream().map(PaymentDTO::convertToDTO).collect(Collectors.toList());
          rentalDTO.setPayment(paymentDTO);
          return rentalDTO;
       }

      public Rental convertToEntity() {
          Rental rental = new Rental();
          rental.setRentalId(this.rentalId);
          rental.setRentalDate(this.rentalDate);
          rental.setReturnDate(this.returnDate);
          rental.setLastUpdate(this.lastUpdate);
          rental.setPayment(this.payment.stream().map(PaymentDTO::convertToEntity).collect(Collectors.toList()));
          return rental;
       }

public Integer getRentalId() {
      return this.rentalId;
}

public void setRentalId(Integer rentalId) {
      this.rentalId = rentalId;
}

public String getRentalDate() {
      return this.rentalDate;
}

public void setRentalDate(String rentalDate) {
      this.rentalDate = rentalDate;
}

public String getReturnDate() {
      return this.returnDate;
}

public void setReturnDate(String returnDate) {
      this.returnDate = returnDate;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

public List<PaymentDTO> getPayment() {
      return this.payment;
}

public void setPayment(List<PaymentDTO> payment) {
      this.payment = payment;
}

}
