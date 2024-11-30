package com.onlineAcademy.CodingSchool.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineAcademy.CodingSchool.DTO.UserDetailsDTO;
import com.onlineAcademy.CodingSchool.Entity.UserDetails;
import com.onlineAcademy.CodingSchool.Mapper.UserDetailsMapper;
import com.onlineAcademy.CodingSchool.Repo.UserDetailsRepository;
import com.onlineAcademy.CodingSchool.Service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final UserDetailsMapper userDetailsMapper;

    @Autowired
    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository, UserDetailsMapper userDetailsMapper) {
        this.userDetailsRepository = userDetailsRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    @Override
    public UserDetailsDTO createUser(UserDetailsDTO userDetailsDTO) {
        UserDetails userDetails = userDetailsMapper.toEntity(userDetailsDTO);
        UserDetails savedUser = userDetailsRepository.save(userDetails);
        return userDetailsMapper.toDTO(savedUser);
    }

    @Override
    public UserDetailsDTO getUserById(Long id) {
        UserDetails userDetails = userDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userDetailsMapper.toDTO(userDetails);
    }

    @Override
    public List<UserDetailsDTO> getAllUsers() {
        List<UserDetails> users = userDetailsRepository.findAll();
        return users.stream()
                .map(userDetailsMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailsDTO updateUser(Long id, UserDetailsDTO userDetailsDTO) {
        UserDetails existingUser = userDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userDetailsMapper.updateEntityFromDTO(userDetailsDTO, existingUser);
        UserDetails updatedUser = userDetailsRepository.save(existingUser);
        return userDetailsMapper.toDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        UserDetails userDetails = userDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userDetailsRepository.delete(userDetails);
    }
}
