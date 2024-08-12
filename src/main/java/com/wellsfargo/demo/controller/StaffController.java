package com.example.demo.controller;

import com.example.demo.dto.StaffDTO;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("STAFF")
public class StaffController {

      @Autowired
      private StaffService staffService;

      @GetMapping
      public List<StaffDTO> findAll() {
          return staffService.findAll();
      }
      @GetMapping("/{id}")
      public List<StaffDTO> findById(@PathVariable Integer id){
          List<StaffDTO> staff = staffService.findById(id);
          return staff.isEmpty() ? null : staff;
      }

      @PutMapping("/{id}")
      public StaffDTO updateById(@PathVariable Integer id, @RequestBody StaffDTO staffDTO) {
          return staffService.updateById(id, staffDTO);
      }

      @DeleteMapping("/{id}")
      public void deleteById(@PathVariable Integer id) {
          staffService.deleteById(id);
      }

}
