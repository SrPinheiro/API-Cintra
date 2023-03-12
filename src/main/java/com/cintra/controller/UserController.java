package com.cintra.controller;

import com.cintra.model.DTO.UserDTO;
import com.cintra.model.User;
import com.cintra.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserController {
    @Autowired
    private UserService userService;
    private User currentUser = null;

    @ModelAttribute
    public void before_action(Model model, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        for(Cookie c: cookies){
            if(c.getName().equals("userToken")){
                currentUser = userService.findByToken(c.getValue());
                break;
            }
        }

        System.out.println(currentUser);
    }

    @GetMapping()
    public Object FindAll(){
        List<UserDTO> users = userService.findAll().stream().map(u -> (UserDTO) u).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable long id, HttpServletResponse response){
        Cookie c = new Cookie("userToken", userService.findById(5).getToken());
        response.addCookie(c);
        return new ResponseEntity<>((UserDTO) userService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public Object createUser(HttpServletResponse response, @RequestBody User u){
        u.setRole(null);
        u.setToken(UUID.randomUUID().toString());

        Cookie authToken = new Cookie("userToken", u.getToken());
        response.addCookie(authToken);

        return userService.save(u);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id){
        userService.deleteById(id);
    }
}
