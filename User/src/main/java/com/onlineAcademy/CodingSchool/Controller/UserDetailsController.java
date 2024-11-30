package com.onlineAcademy.CodingSchool.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.onlineAcademy.CodingSchool.DTO.UserDetailsDTO;
import com.onlineAcademy.CodingSchool.Service.UserDetailsService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public UserDetailsDTO createUser(@RequestBody UserDetailsDTO userDetailsDTO) {
        return userDetailsService.createUser(userDetailsDTO);
    }

    @GetMapping("/{id}")
    public UserDetailsDTO getUserById(@PathVariable Long id) {
        return userDetailsService.getUserById(id);
    }

    @GetMapping
    public List<UserDetailsDTO> getAllUsers() {
        return userDetailsService.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserDetailsDTO updateUser(@PathVariable Long id, @RequestBody UserDetailsDTO userDetailsDTO) {
        return userDetailsService.updateUser(id, userDetailsDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userDetailsService.deleteUser(id);
    }
}
