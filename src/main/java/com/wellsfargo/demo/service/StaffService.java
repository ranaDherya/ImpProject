package com.example.demo.service;

import com.example.demo.dto.StaffDTO; 

import java.util.List;

public interface StaffService {
List<StaffDTO> findAll();
List<StaffDTO> findById(Integer id);
StaffDTO updateById(Integer id, StaffDTO staffDTO);
void deleteById(Integer id);
StaffDTO insertStaff(StaffDTO staffDTO);
}
