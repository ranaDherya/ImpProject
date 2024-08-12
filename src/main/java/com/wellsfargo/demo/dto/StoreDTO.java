package com.example.demo.dto;

import com.example.demo.entity.Store;
import java.util.List;
import java.util.stream.Collectors;

public class StoreDTO {
      private Integer storeId;
      private Integer managerStaffId;
      private String lastUpdate;
      private List<InventoryDTO> inventory;
      private List<CustomerDTO> customer;
      private List<StaffDTO> staff;

      public StoreDTO() {       }

      public static StoreDTO convertToDTO(Store store) {
          StoreDTO storeDTO = new StoreDTO();
          storeDTO.setStoreId(store.getStoreId());
          storeDTO.setManagerStaffId(store.getManagerStaffId());
          storeDTO.setLastUpdate(store.getLastUpdate());

          List<InventoryDTO> inventoryDTO = store.getInventory().stream().map(InventoryDTO::convertToDTO).collect(Collectors.toList());
          storeDTO.setInventory(inventoryDTO);
          List<CustomerDTO> customerDTO = store.getCustomer().stream().map(CustomerDTO::convertToDTO).collect(Collectors.toList());
          storeDTO.setCustomer(customerDTO);
          List<StaffDTO> staffDTO = store.getStaff().stream().map(StaffDTO::convertToDTO).collect(Collectors.toList());
          storeDTO.setStaff(staffDTO);
          return storeDTO;
       }

      public Store convertToEntity() {
          Store store = new Store();
          store.setStoreId(this.storeId);
          store.setManagerStaffId(this.managerStaffId);
          store.setLastUpdate(this.lastUpdate);
          store.setInventory(this.inventory.stream().map(InventoryDTO::convertToEntity).collect(Collectors.toList()));
          store.setCustomer(this.customer.stream().map(CustomerDTO::convertToEntity).collect(Collectors.toList()));
          store.setStaff(this.staff.stream().map(StaffDTO::convertToEntity).collect(Collectors.toList()));
          return store;
       }

public Integer getStoreId() {
      return this.storeId;
}

public void setStoreId(Integer storeId) {
      this.storeId = storeId;
}

public Integer getManagerStaffId() {
      return this.managerStaffId;
}

public void setManagerStaffId(Integer managerStaffId) {
      this.managerStaffId = managerStaffId;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

public List<InventoryDTO> getInventory() {
      return this.inventory;
}

public void setInventory(List<InventoryDTO> inventory) {
      this.inventory = inventory;
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

}
