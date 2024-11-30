package com.onlineAcademy.CodingSchool.Mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.onlineAcademy.CodingSchool.DTO.UserDetailsDTO;
import com.onlineAcademy.CodingSchool.Entity.UserDetails;

//@Mapper(componentModel = "spring")
public interface UserDetailsMapper {

    @Mapping(target = "addressId", source = "address.id")
    @Mapping(target = "batchId", source = "batch.id")
    @Mapping(target = "statusId", source = "status.id")
    UserDetailsDTO toDTO(UserDetails userDetails);

    @Mapping(target = "address.id", source = "addressId")
    @Mapping(target = "batch.id", source = "batchId")
    @Mapping(target = "status.id", source = "statusId")
    UserDetails toEntity(UserDetailsDTO userDetailsDTO);

    @Mapping(target = "address.id", source = "addressId")
    @Mapping(target = "batch.id", source = "batchId")
    @Mapping(target = "status.id", source = "statusId")
    void updateEntityFromDTO(UserDetailsDTO userDetailsDTO, @MappingTarget UserDetails userDetails);
}
