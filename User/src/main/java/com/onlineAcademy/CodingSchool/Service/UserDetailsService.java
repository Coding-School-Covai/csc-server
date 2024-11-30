package com.onlineAcademy.CodingSchool.Service;


import java.util.List;

import com.onlineAcademy.CodingSchool.DTO.UserDetailsDTO;

public interface UserDetailsService {

    UserDetailsDTO createUser(UserDetailsDTO userDetailsDTO);

    UserDetailsDTO getUserById(Long id);

    List<UserDetailsDTO> getAllUsers();

    UserDetailsDTO updateUser(Long id, UserDetailsDTO userDetailsDTO);

    void deleteUser(Long id);
}
