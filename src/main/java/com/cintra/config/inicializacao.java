package com.cintra.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.cintra.model.Role;
import com.cintra.model.User;
import com.cintra.services.RoleService;
import com.cintra.services.UserService;

@Configuration
public class inicializacao implements CommandLineRunner {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if(roleService.count() == 0)
            addRoles();
        if(userService.count() == 0)
            createAdmin();
    }

    private void addRoles() {
        Role[] roles = new Role[3];
        roles[0] = new Role(null, "user", 1);
        roles[1] = new Role(null, "doctor",2);
        roles[2] = new Role(null, "admin", 3);

        Arrays.stream(roles).forEach(roleService::save);
    }

    private void createAdmin() {
        User admin = new User();
        admin.setName("admin");
        admin.setPassword("admin");
        admin.setRole(roleService.findByName("admin"));

        userService.save(admin);
    }
    
}
