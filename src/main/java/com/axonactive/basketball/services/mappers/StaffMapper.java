package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.Staff;
import com.axonactive.basketball.services.dtos.StaffDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StaffMapper {
    StaffMapper INSTANCE = Mappers.getMapper(StaffMapper.class);
    @Mapping(target = "teamName",source = "team.name")
    @Mapping(target = "staffName",expression = "java(staff.getFirstName() + \"\" + staff.getLastName())")
    StaffDTO toDTO(Staff staff);
    List<StaffDTO> toDTOs (List<Staff> staffs);
    @AfterMapping
    default void setStaffName(Staff staff, @MappingTarget StaffDTO target){
        target.setStaffName(staff.getFirstName() + " " + staff.getLastName());
    }
}
