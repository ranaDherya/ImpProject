package com.example.demo.dto;

import com.example.demo.entity.Staff;
import java.util.List;
import java.util.stream.Collectors;

public class StaffDTO {
      private Integer staffId;
      private String firstName;
      private String lastName;
      private String email;
      private Boolean active;
      private String username;
      private String password;
      private String lastUpdate;
      private List<RentalDTO> rental;
      private List<PaymentDTO> payment;

      public StaffDTO() {       }

      public static StaffDTO convertToDTO(Staff staff) {
          StaffDTO staffDTO = new StaffDTO();
          staffDTO.setStaffId(staff.getStaffId());
          staffDTO.setFirstName(staff.getFirstName());
          staffDTO.setLastName(staff.getLastName());
          staffDTO.setEmail(staff.getEmail());
          staffDTO.setActive(staff.getActive());
          staffDTO.setUsername(staff.getUsername());
          staffDTO.setPassword(staff.getPassword());
          staffDTO.setLastUpdate(staff.getLastUpdate());

          List<RentalDTO> rentalDTO = staff.getRental().stream().map(RentalDTO::convertToDTO).collect(Collectors.toList());
          staffDTO.setRental(rentalDTO);
          List<PaymentDTO> paymentDTO = staff.getPayment().stream().map(PaymentDTO::convertToDTO).collect(Collectors.toList());
          staffDTO.setPayment(paymentDTO);
          return staffDTO;
       }

      public Staff convertToEntity() {
          Staff staff = new Staff();
          staff.setStaffId(this.staffId);
          staff.setFirstName(this.firstName);
          staff.setLastName(this.lastName);
          staff.setEmail(this.email);
          staff.setActive(this.active);
          staff.setUsername(this.username);
          staff.setPassword(this.password);
          staff.setLastUpdate(this.lastUpdate);
          staff.setRental(this.rental.stream().map(RentalDTO::convertToEntity).collect(Collectors.toList()));
          staff.setPayment(this.payment.stream().map(PaymentDTO::convertToEntity).collect(Collectors.toList()));
          return staff;
       }

public Integer getStaffId() {
      return this.staffId;
}

public void setStaffId(Integer staffId) {
      this.staffId = staffId;
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

public String getUsername() {
      return this.username;
}

public void setUsername(String username) {
      this.username = username;
}

public String getPassword() {
      return this.password;
}

public void setPassword(String password) {
      this.password = password;
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
