package com.example.demo.dto;

import com.example.demo.entity.Address;
import java.util.List;
import java.util.stream.Collectors;

public class AddressDTO {
      private Integer addressId;
      private String address;
      private String address2;
      private String district;
      private String postalCode;
      private String phone;
      private String lastUpdate;
      private List<CustomerDTO> customer;
      private List<StaffDTO> staff;
      private List<StoreDTO> store;

      public AddressDTO() {       }

      public static AddressDTO convertToDTO(Address address) {
          AddressDTO addressDTO = new AddressDTO();
          addressDTO.setAddressId(address.getAddressId());
          addressDTO.setAddress(address.getAddress());
          addressDTO.setAddress2(address.getAddress2());
          addressDTO.setDistrict(address.getDistrict());
          addressDTO.setPostalCode(address.getPostalCode());
          addressDTO.setPhone(address.getPhone());
          addressDTO.setLastUpdate(address.getLastUpdate());

          List<CustomerDTO> customerDTO = address.getCustomer().stream().map(CustomerDTO::convertToDTO).collect(Collectors.toList());
          addressDTO.setCustomer(customerDTO);
          List<StaffDTO> staffDTO = address.getStaff().stream().map(StaffDTO::convertToDTO).collect(Collectors.toList());
          addressDTO.setStaff(staffDTO);
          List<StoreDTO> storeDTO = address.getStore().stream().map(StoreDTO::convertToDTO).collect(Collectors.toList());
          addressDTO.setStore(storeDTO);
          return addressDTO;
       }

      public Address convertToEntity() {
          Address address = new Address();
          address.setAddressId(this.addressId);
          address.setAddress(this.address);
          address.setAddress2(this.address2);
          address.setDistrict(this.district);
          address.setPostalCode(this.postalCode);
          address.setPhone(this.phone);
          address.setLastUpdate(this.lastUpdate);
          address.setCustomer(this.customer.stream().map(CustomerDTO::convertToEntity).collect(Collectors.toList()));
          address.setStaff(this.staff.stream().map(StaffDTO::convertToEntity).collect(Collectors.toList()));
          address.setStore(this.store.stream().map(StoreDTO::convertToEntity).collect(Collectors.toList()));
          return address;
       }

public Integer getAddressId() {
      return this.addressId;
}

public void setAddressId(Integer addressId) {
      this.addressId = addressId;
}

public String getAddress() {
      return this.address;
}

public void setAddress(String address) {
      this.address = address;
}

public String getAddress2() {
      return this.address2;
}

public void setAddress2(String address2) {
      this.address2 = address2;
}

public String getDistrict() {
      return this.district;
}

public void setDistrict(String district) {
      this.district = district;
}

public String getPostalCode() {
      return this.postalCode;
}

public void setPostalCode(String postalCode) {
      this.postalCode = postalCode;
}

public String getPhone() {
      return this.phone;
}

public void setPhone(String phone) {
      this.phone = phone;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

public List<CustomerDTO> getCustomer() {
      return this.customer;
}

public void setCustomer(List<CustomerDTO> customer) {
      this.customer = customer;
}

public List<StaffDTO> getStaff() {
      return this.staff;
}

public void setStaff(List<StaffDTO> staff) {
      this.staff = staff;
}

public List<StoreDTO> getStore() {
      return this.store;
}

public void setStore(List<StoreDTO> store) {
      this.store = store;
}

}
