package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.Staff;
import com.axonactive.basketball.services.dtos.StaffDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StaffMapper {
    StaffMapper INSTANCE = Mappers.getMapper(StaffMapper.class);
    StaffDTO toDTO(Staff staff);
    List<StaffDTO> toDTOs (List<Staff> staffs);
}
