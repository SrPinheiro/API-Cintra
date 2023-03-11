package com.cintra.controller;

import com.cintra.model.DTO.UserDTO;
import com.cintra.model.User;
import com.cintra.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public Object getAll(){
        List<UserDTO> users = userService.findAll().stream().map(u -> (UserDTO) u).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable long id){
        return new ResponseEntity<>((UserDTO) userService.findById(id), HttpStatus.OK);
    }
}
