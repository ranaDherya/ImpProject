package com.example.demo.dto;

import com.example.demo.entity.Inventory;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryDTO {
      private Integer inventoryId;
      private String lastUpdate;
      private List<RentalDTO> rental;

      public InventoryDTO() {       }

      public static InventoryDTO convertToDTO(Inventory inventory) {
          InventoryDTO inventoryDTO = new InventoryDTO();
          inventoryDTO.setInventoryId(inventory.getInventoryId());
          inventoryDTO.setLastUpdate(inventory.getLastUpdate());

          List<RentalDTO> rentalDTO = inventory.getRental().stream().map(RentalDTO::convertToDTO).collect(Collectors.toList());
          inventoryDTO.setRental(rentalDTO);
          return inventoryDTO;
       }

      public Inventory convertToEntity() {
          Inventory inventory = new Inventory();
          inventory.setInventoryId(this.inventoryId);
          inventory.setLastUpdate(this.lastUpdate);
          inventory.setRental(this.rental.stream().map(RentalDTO::convertToEntity).collect(Collectors.toList()));
          return inventory;
       }

public Integer getInventoryId() {
      return this.inventoryId;
}

public void setInventoryId(Integer inventoryId) {
      this.inventoryId = inventoryId;
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

}
