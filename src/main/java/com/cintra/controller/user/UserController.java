package com.cintra.controller.user;

import com.cintra.helpers.Users;
import com.cintra.model.DTO.AuthDAO;
import com.cintra.model.DTO.UserDTO;
import com.cintra.model.User;
import com.cintra.services.RoleService;
import com.cintra.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    private User currentUser;

    @ModelAttribute
    public void before_action(Model model, HttpServletRequest request){
        currentUser = Users.getCurrentUser(request, userService);

        if (currentUser != null){
            currentUser.setUip(request.getRemoteAddr());
            userService.save(currentUser);
        }
    }

    @GetMapping()
    public Object FindAll(){
        try {
            if (currentUser != null && currentUser.getRole().name("admin")) {
                List<UserDTO> users = userService.findAll().stream().map(u -> (UserDTO) u).collect(Collectors.toList());
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
        }catch (NullPointerException npe){
            currentUser.setRole(roleService.findByName("user"));
            try{
                userService.save(currentUser);
            }
            catch (Exception err){
                System.out.println(err);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{id}")
    public Object findById(@PathVariable long id, HttpServletResponse response){
        return new ResponseEntity<>((UserDTO) userService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public Object createUser(HttpServletResponse response, @RequestBody User u){
        try{
            if (userService.findByName(u.getName()) != null)
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }catch(Exception err){
            err = err;
        }
        
        u.setId(null);
        u.setToken(UUID.randomUUID().toString());
        u.setRole(roleService.findByName("user"));
        
    
        response.addCookie(generateCookie(u));

        return new ResponseEntity<>(userService.save(u), HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public Object returnMe(){
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id){
        userService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Object updateUser(HttpServletResponse respose, @PathVariable long id, @RequestBody User user){
        user.setId(id);
        User userUp = userService.findById(id);
        user.setCreatedAt(userUp.getCreatedAt());
        user.setToken(userUp.getToken());

        if (currentUser == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if (!currentUser.equals(userUp) && !(currentUser.getRole().getPower() > 0))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        if(!(currentUser.getRole().getPower() > 0))
            user.setRole(userUp.getRole());

        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping
    public Object authUser(HttpServletResponse response, HttpServletRequest request, @RequestBody AuthDAO params){
        User user = userService.findByName(params.getLogin());

        if(user == null || !user.getPassword().equals(params.getPassword()))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        response.addCookie(generateCookie(user));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/logout")
    public Object LogoutAll(HttpServletResponse response, @PathVariable int id){
        User userUp = userService.findById(id);

        if (userUp == null || currentUser != userUp)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        
        Cookie userToken = new Cookie("userToken", null);
        userToken.setMaxAge(0);
        response.addCookie(userToken);

        currentUser.setToken(null);
        userService.save(currentUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Cookie generateCookie(User u){

        if(u.getToken() == null)
            u.setToken(String.valueOf(UUID.randomUUID()));
    
        Cookie userToken = new Cookie("userToken", u.getToken());
        userToken.setMaxAge(1 * 60 * 60 * 24 * 7);

        userService.save(u);
        return userToken;
        
    }
}
