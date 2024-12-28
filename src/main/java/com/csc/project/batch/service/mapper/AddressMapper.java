package com.csc.project.batch.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.csc.project.batch.dto.AddressDTO;
import com.csc.project.batch.entity.Address;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AddressMapper {

	public Address addressDtoToAddress(AddressDTO addressDto) {
		if (addressDto == null) {
			return null;
		}

		Address address = new Address();
		address.setId(addressDto.getId());
		address.setStreet(addressDto.getStreet());
		address.setCity(addressDto.getCity());
		address.setState(addressDto.getState());
		address.setCountry(addressDto.getCountry());
		address.setZipcode(addressDto.getZipcode());

		return address;
	}

	public AddressDTO addressToAddressDTO(Address address) {
		if (address == null) {
			return null;
		}

		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setId(address.getId());
		addressDTO.setStreet(address.getStreet());
		addressDTO.setCity(address.getCity());
		addressDTO.setState(address.getState());
		addressDTO.setCountry(address.getCountry());
		addressDTO.setZipcode(address.getZipcode());

		return addressDTO;
	}

	public List<AddressDTO> addressesToAddressDTOs(List<Address> addresses) {
		if (addresses == null || addresses.isEmpty()) {
			return List.of();
		}

		return addresses.stream().map(this::addressToAddressDTO).collect(Collectors.toList());
	}

	public void updateAddressFromDto(AddressDTO addressDto, Address existingAddress) {
		if (addressDto == null || existingAddress == null) {
			return;
		}

		if (addressDto.getStreet() != null) {
			existingAddress.setStreet(addressDto.getStreet());
		}
		if (addressDto.getCity() != null) {
			existingAddress.setCity(addressDto.getCity());
		}
		if (addressDto.getState() != null) {
			existingAddress.setState(addressDto.getState());
		}
		if (addressDto.getCountry() != null) {
			existingAddress.setCountry(addressDto.getCountry());
		}
		if (addressDto.getZipcode() != null) {
			existingAddress.setZipcode(addressDto.getZipcode());
		}
	}
}
