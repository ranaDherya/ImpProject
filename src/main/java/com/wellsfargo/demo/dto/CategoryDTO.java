package com.example.demo.dto;

import com.example.demo.entity.Category;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryDTO {
      private Integer categoryId;
      private String name;
      private String lastUpdate;
      private List<FilmCategoryDTO> filmCategory;

      public CategoryDTO() {       }

      public static CategoryDTO convertToDTO(Category category) {
          CategoryDTO categoryDTO = new CategoryDTO();
          categoryDTO.setCategoryId(category.getCategoryId());
          categoryDTO.setName(category.getName());
          categoryDTO.setLastUpdate(category.getLastUpdate());

          List<FilmCategoryDTO> filmCategoryDTO = category.getFilmCategory().stream().map(FilmCategoryDTO::convertToDTO).collect(Collectors.toList());
          categoryDTO.setFilmCategory(filmCategoryDTO);
          return categoryDTO;
       }

      public Category convertToEntity() {
          Category category = new Category();
          category.setCategoryId(this.categoryId);
          category.setName(this.name);
          category.setLastUpdate(this.lastUpdate);
          category.setFilmCategory(this.filmCategory.stream().map(FilmCategoryDTO::convertToEntity).collect(Collectors.toList()));
          return category;
       }

public Integer getCategoryId() {
      return this.categoryId;
}

public void setCategoryId(Integer categoryId) {
      this.categoryId = categoryId;
}

public String getName() {
      return this.name;
}

public void setName(String name) {
      this.name = name;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

public List<FilmCategoryDTO> getFilmCategory() {
      return this.filmCategory;
}

public void setFilmCategory(List<FilmCategoryDTO> filmCategory) {
      this.filmCategory = filmCategory;
}

}
