package com.example.demo.dto;

import com.example.demo.entity.Country;
import java.util.List;
import java.util.stream.Collectors;

public class CountryDTO {
      private Integer countryId;
      private String country;
      private String lastUpdate;
      private List<CityDTO> city;

      public CountryDTO() {       }

      public static CountryDTO convertToDTO(Country country) {
          CountryDTO countryDTO = new CountryDTO();
          countryDTO.setCountryId(country.getCountryId());
          countryDTO.setCountry(country.getCountry());
          countryDTO.setLastUpdate(country.getLastUpdate());

          List<CityDTO> cityDTO = country.getCity().stream().map(CityDTO::convertToDTO).collect(Collectors.toList());
          countryDTO.setCity(cityDTO);
          return countryDTO;
       }

      public Country convertToEntity() {
          Country country = new Country();
          country.setCountryId(this.countryId);
          country.setCountry(this.country);
          country.setLastUpdate(this.lastUpdate);
          country.setCity(this.city.stream().map(CityDTO::convertToEntity).collect(Collectors.toList()));
          return country;
       }

public Integer getCountryId() {
      return this.countryId;
}

public void setCountryId(Integer countryId) {
      this.countryId = countryId;
}

public String getCountry() {
      return this.country;
}

public void setCountry(String country) {
      this.country = country;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

public List<CityDTO> getCity() {
      return this.city;
}

public void setCity(List<CityDTO> city) {
      this.city = city;
}

}
