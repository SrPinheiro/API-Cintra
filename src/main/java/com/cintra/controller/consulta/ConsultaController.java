package com.cintra.controller.consulta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cintra.helpers.Users;
import com.cintra.model.Consulta;
import com.cintra.model.User;
import com.cintra.services.ConsultaService;
import com.cintra.services.RoleService;
import com.cintra.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value="consultas")
public class ConsultaController {
    @Autowired
    private UserService userService;
    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private RoleService roleService;
    private User currentUser;


    @ModelAttribute
    public void before_action(Model model, HttpServletRequest request,HttpServletResponse response){
        currentUser = Users.getCurrentUser(request, userService);
        
        if (currentUser != null){
            currentUser.setUip(request.getRemoteAddr());
            userService.save(currentUser);
        }else{
            breakCookie(response);
        }
    }


    @PostMapping
    public Object createConsulta(@RequestBody Consulta consulta) {
        if(currentUser != null){
            consulta.setClient(currentUser);
            consulta.setData_de_marcacao(LocalDateTime.now().toString());
            consultaService.save(consulta);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }

    @GetMapping("/{id}")
    public Object getConsultaById(@PathVariable("id") long id) {
        Consulta c = consultaService.findById(id);

        if(currentUser != null && c != null && c.getClient() == currentUser )
            return new ResponseEntity<>(c, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    
    }

    @DeleteMapping("/{id}")
    public void deleteConsulta(@PathVariable("id") long id) {
        Consulta c = consultaService.findById(id);
        if(currentUser != null && c.getClient() == currentUser && c != null)
            consultaService.delete(id);
            
    }

    @GetMapping
    public Object getAllConsultas() {
               
        if(currentUser != null)
            return new ResponseEntity(consultaService.findAll().stream().filter(c-> c.getClient() == currentUser).collect(Collectors.toList()), HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    public void breakCookie(HttpServletResponse response){
        Cookie userToken = new Cookie("userToken", null);
        userToken.setMaxAge(0);
        response.addCookie(userToken);
    }
    
}
