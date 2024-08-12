package com.example.demo.dto;

import com.example.demo.entity.Customer;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerDTO {
      private Integer customerId;
      private String firstName;
      private String lastName;
      private String email;
      private Boolean active;
      private String createDate;
      private String lastUpdate;
      private List<RentalDTO> rental;
      private List<PaymentDTO> payment;

      public CustomerDTO() {       }

      public static CustomerDTO convertToDTO(Customer customer) {
          CustomerDTO customerDTO = new CustomerDTO();
          customerDTO.setCustomerId(customer.getCustomerId());
          customerDTO.setFirstName(customer.getFirstName());
          customerDTO.setLastName(customer.getLastName());
          customerDTO.setEmail(customer.getEmail());
          customerDTO.setActive(customer.getActive());
          customerDTO.setCreateDate(customer.getCreateDate());
          customerDTO.setLastUpdate(customer.getLastUpdate());

          List<RentalDTO> rentalDTO = customer.getRental().stream().map(RentalDTO::convertToDTO).collect(Collectors.toList());
          customerDTO.setRental(rentalDTO);
          List<PaymentDTO> paymentDTO = customer.getPayment().stream().map(PaymentDTO::convertToDTO).collect(Collectors.toList());
          customerDTO.setPayment(paymentDTO);
          return customerDTO;
       }

      public Customer convertToEntity() {
          Customer customer = new Customer();
          customer.setCustomerId(this.customerId);
          customer.setFirstName(this.firstName);
          customer.setLastName(this.lastName);
          customer.setEmail(this.email);
          customer.setActive(this.active);
          customer.setCreateDate(this.createDate);
          customer.setLastUpdate(this.lastUpdate);
          customer.setRental(this.rental.stream().map(RentalDTO::convertToEntity).collect(Collectors.toList()));
          customer.setPayment(this.payment.stream().map(PaymentDTO::convertToEntity).collect(Collectors.toList()));
          return customer;
       }

public Integer getCustomerId() {
      return this.customerId;
}

public void setCustomerId(Integer customerId) {
      this.customerId = customerId;
}

public String getFirstName() {
      return this.firstName;
}

public void setFirstName(String firstName) {
      this.firstName = firstName;
}

public String getLastName() {
      return this.lastName;
}

public void setLastName(String lastName) {
      this.lastName = lastName;
}

public String getEmail() {
      return this.email;
}

public void setEmail(String email) {
      this.email = email;
}

public Boolean getActive() {
      return this.active;
}

public void setActive(Boolean active) {
      this.active = active;
}

public String getCreateDate() {
      return this.createDate;
}

public void setCreateDate(String createDate) {
      this.createDate = createDate;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

public List<RentalDTO> getRental() {
      return this.rental;
}

public void setRental(List<RentalDTO> rental) {
      this.rental = rental;
}

public List<PaymentDTO> getPayment() {
      return this.payment;
}

public void setPayment(List<PaymentDTO> payment) {
      this.payment = payment;
}

}
