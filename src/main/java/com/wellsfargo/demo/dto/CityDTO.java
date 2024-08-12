package com.example.demo.dto;

import com.example.demo.entity.City;
import java.util.List;
import java.util.stream.Collectors;

public class CityDTO {
      private Integer cityId;
      private String city;
      private String lastUpdate;
      private List<AddressDTO> address;

      public CityDTO() {       }

      public static CityDTO convertToDTO(City city) {
          CityDTO cityDTO = new CityDTO();
          cityDTO.setCityId(city.getCityId());
          cityDTO.setCity(city.getCity());
          cityDTO.setLastUpdate(city.getLastUpdate());

          List<AddressDTO> addressDTO = city.getAddress().stream().map(AddressDTO::convertToDTO).collect(Collectors.toList());
          cityDTO.setAddress(addressDTO);
          return cityDTO;
       }

      public City convertToEntity() {
          City city = new City();
          city.setCityId(this.cityId);
          city.setCity(this.city);
          city.setLastUpdate(this.lastUpdate);
          city.setAddress(this.address.stream().map(AddressDTO::convertToEntity).collect(Collectors.toList()));
          return city;
       }

public Integer getCityId() {
      return this.cityId;
}

public void setCityId(Integer cityId) {
      this.cityId = cityId;
}

public String getCity() {
      return this.city;
}

public void setCity(String city) {
      this.city = city;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

public List<AddressDTO> getAddress() {
      return this.address;
}

public void setAddress(List<AddressDTO> address) {
      this.address = address;
}

}
