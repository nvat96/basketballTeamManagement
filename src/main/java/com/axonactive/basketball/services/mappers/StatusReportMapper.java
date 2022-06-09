package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.StatusReport;
import com.axonactive.basketball.services.dtos.StatusReportDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StatusReportMapper {
    StatusReportMapper INSTANCE = Mappers.getMapper(StatusReportMapper.class);
    @Mapping(target = "playerName",source = "player.name")
    @Mapping(target = "status",expression = "java(statusReport.getStatus().toString())")
    StatusReportDTO toDTO(StatusReport statusReport);
    List<StatusReportDTO> toDTOs (List<StatusReport> statusReports);
}
